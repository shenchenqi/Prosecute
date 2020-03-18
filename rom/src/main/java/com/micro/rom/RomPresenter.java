package com.micro.rom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.micro.rom.core.CreateRom;
import com.micro.rom.core.basis.BackupCallback;
import com.micro.rom.core.basis.BaseRom;
import com.micro.root.Logger;
import com.miui.enterprise.sdk.ApplicationManager;

/**
 * created by kilin on 20-3-18 下午1:26
 */
public abstract class RomPresenter <Inter extends RomInter> extends CreateRom {

    private static final Logger logger = Logger.getLogger("RomLog");
    private static final String TAG = "Root-Rom";

    protected RomPresenter(Context context, String packageName, String createDir) {
        super(context, packageName, createDir);
    }

    private Inter clazz;

    public Inter getClazz() {
        return clazz;
    }

    void setClazz(Inter clazz) {
        this.clazz = clazz;
        this.onAttached();
    }

    protected abstract void onAttached();

    private Handler getHandler(String name) {
        return new Handler(getHandlerLooper(name));
    }

    private Looper getHandlerLooper(String name) {
        HandlerThread thread = new HandlerThread(name);
        thread.start();
        return thread.getLooper();
    }

    private SystemUI systemUI;

    @Override
    protected boolean isRom(String manufacturer) {
        logger.d(TAG, "当前操作系统为: %s", manufacturer);
        systemUI = new MIUI(getContext(), manufacturer);
        if (systemUI.isUI()) {
            return systemUI.isRom();
        }
        return false;
    }

    @Override
    protected void copyFile(String beauPath, String copyPath, String packageName, int requestId) {
        if (systemUI == null) {
            logger.e(TAG, "未检测到系统UI");
            return;
        }
        systemUI.copyFile(beauPath, copyPath, packageName, requestId);
    }

    public String copyFileItem(String beauDir, String copyDir, String fileItem, BackupCallback callback) {
        return super.copyFiles(0, beauDir, copyDir, callback, fileItem);
    }

    @Override
    protected boolean isAppInXSpace(String packName) {
        if (systemUI == null) {
            logger.e(TAG, "未检测到系统UI");
            return false;
        }
        return systemUI.isAppInXSpace(packName);
    }

    private class MIUI extends SystemUI {

        private MIUI(Context context, String manufacturer) {//huawei, meizu
            super(context, "Xiaomi", manufacturer);
            final Handler handler = getHandler("copy_sub_handler");
            registerBroadcast(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    final int intExtra = intent.getIntExtra("com.miui.enterprise.EXTRA_BACKUP_ID", -1);
                    final BackupCallback backupCallback = BaseRom.backupCallbackMap.get(intExtra);
                    if (intent.getAction().equals("com.miui.enterprise.ACTION_BACKUP_FINISH")) {
                        if (backupCallback != null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    backupCallback.onFinish(true, backupCallback.getOutPath());
                                    BaseRom.backupCallbackMap.remove(intExtra);
                                }
                            });
                        }
                    } else if (intent.getAction().equals("com.miui.enterprise.ACTION_BACKUP_FAILED")) {
                        if (backupCallback != null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    backupCallback.onFinish(false, backupCallback.getOutPath());
                                    BaseRom.backupCallbackMap.remove(intExtra);
                                }
                            });
                        }
                    }
                }
            }, "com.miui.enterprise.ACTION_BACKUP_FAILED", "com.miui.enterprise.ACTION_BACKUP_FINISH");
        }

        private synchronized void registerBroadcast(BroadcastReceiver broadcast, String... actions) {
            IntentFilter intentFilter = new IntentFilter();
            for (String action : actions) {
                intentFilter.addAction(action);
            }
            context.registerReceiver(broadcast, intentFilter);
        }

        @Override
        public boolean isRom() {
            try {
                if (Class.forName("com.miui.enterprise.sdk.ApplicationManager") == null) {
                    return false;
                } else {
                    return ApplicationManager.getInstance() != null;
                }
            } catch (ClassNotFoundException e) {
                return false;
            }
        }

        @Override
        public void copyFile(String beauPath, String copyPath, String packageName, int requestId) {
            ApplicationManager.getInstance().backupData(beauPath, copyPath, packageName, requestId);
        }

        @Override
        public boolean isAppInXSpace(String packName) {
            return ApplicationManager.getInstance().isAppInXSpace(packName);
        }
    }
}