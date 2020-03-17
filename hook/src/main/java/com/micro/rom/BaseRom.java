package com.micro.rom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by kilin on 20-1-3 下午2:27
 */
public abstract class BaseRom {
    private static final AtomicInteger mRequestId = new AtomicInteger(0);
    public static final Map<Integer, BackupCallback> backupCallbackMap = new ConcurrentHashMap<>();
    protected static final Map<String, String> backupPath = new ConcurrentHashMap<>();

    private final Context context;
    private final String packageName;
    private final String createDir;

    private final File externalStorageDirectory;

    private PackageInfo packageInfo;
    private ApplicationInfo applicationInfo;

    private boolean isBaseRom;

    protected BaseRom(Context context, String packageName, String createDir) {
        this.context = context;
        this.packageName = packageName;
        this.createDir = createDir;
        this.externalStorageDirectory = Environment.getExternalStorageDirectory();
        this.isBaseRom = isRom();
        try {
            this.packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            this.applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
        } catch (Exception e) {
            this.packageInfo = null;
            this.applicationInfo = null;
        }
    }

    public Context getContext() {
        return context;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getCreateDir() {
        return createDir;
    }

    public boolean isBaseRom() {
        return isBaseRom;
    }

    protected abstract boolean isRom();

    protected String copyFiles(final int index, final String beauDir, final String copyDir, final BackupCallback callback, final String... fileItems) {
        if (!isBaseRom) {
            return "";
        }
        BackupCallback backupCallback;
        if (index < fileItems.length - 1) {
            backupCallback = new BackupCallback() {
                @Override
                public void onFinish(boolean isSuccess, String outPath) {
                    String status = copyFiles(index + 1, beauDir, copyDir, callback, fileItems);
                }
            };
        } else {
            backupCallback = callback;
        }
        return copyFile(beauDir, copyDir, fileItems[index], backupCallback);
    }

    private synchronized String copyFile(String beauDir, String copyDir, String fileItem, BackupCallback callback) {
        StringBuilder sb = new StringBuilder();
        if (!isBaseRom) {
            sb.append("未ROM");
            return sb.toString();
        }
        if (TextUtils.isEmpty(beauDir) || TextUtils.isEmpty(copyDir) || TextUtils.isEmpty(fileItem)) {
            sb.append("拷贝情况： 对象文件夹[");
            sb.append(beauDir);
            sb.append("]拷贝文件夹[");
            sb.append(copyDir);
            sb.append("]文件名[");
            sb.append(fileItem);
            sb.append("]");
            return sb.toString();
        }

        String beauPath = beauDir + "/" + fileItem;
        File fileDir;
        if (!copyDir.contains(externalStorageDirectory.getAbsolutePath())) {
            fileDir = new File(externalStorageDirectory, copyDir);
        } else {
            fileDir = new File(copyDir);
        }
        String copyPath = new File(fileDir, fileItem).getAbsolutePath();
        int requestId = mRequestId.incrementAndGet();
        callback.setOutPath(copyPath);
        backupCallbackMap.put(requestId, callback);
        copyFile(beauPath, copyPath, packageName, requestId);
        sb.append("开始");
        sb.append(requestId);
        sb.append(": ");
        sb.append(copyPath);
        sb.append("【from】");
        sb.append(beauPath);
        return sb.toString();
    }

    /**
     * @param beauPath    对象路径
     * @param copyPath    拷贝路径
     * @param packageName 包名
     * @param requestId   请求ID
     */
    protected abstract void copyFile(String beauPath, String copyPath, String packageName, int requestId);

    public boolean isAppExist() {
        if (applicationInfo == null) {
            return false;
        } else if (applicationInfo.uid > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getVersionName() {
        if (packageInfo == null) {
            return "";
        }
        return packageInfo.versionName;
    }

    public int getVersionCode() {
        if (packageInfo == null) {
            return 0;
        }
        return packageInfo.versionCode;
    }

    private String copyDir = "";

    public String getCopyDir() {
        return copyDir;
    }

    public boolean createCopyDir(String dir) {
        try {
            createDir(dir);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void createDir(String dir) throws IOException {
        if (TextUtils.isEmpty(dir)) {
            throw new IOException("拷贝路径为空");
        }
        File file = new File(externalStorageDirectory, dir);
        delete(file);
        file.mkdirs();
        copyDir = file.getAbsolutePath();
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File absoluteFile : listFiles) {
                    delete(absoluteFile);
                }
            }
            deleteFile(file);
            return;
        }
        deleteFile(file);
    }

    private boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        try {
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint("SdCardPath")
    public String getPackageDir() {
        return String.format("/data/data/%s", packageName);
    }
}