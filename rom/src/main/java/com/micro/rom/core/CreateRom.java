package com.micro.rom.core;

import android.content.Context;
import android.os.Build;

import com.micro.rom.core.basis.BackupCallback;
import com.micro.rom.core.basis.BaseRom;

/**
 * created by kilin on 20-3-11 下午2:21
 */
public abstract class CreateRom {

    private final BaseRom baseRom;

    protected CreateRom(Context context, String packageName, String createDir) {
        initConfig();
        this.baseRom = new BaseRom(context, packageName, createDir) {
            @Override
            protected boolean isRom() {
                return CreateRom.this.isRom(Build.MANUFACTURER);
            }

            @Override
            protected void copyFile(String beauPath, String copyPath, String packageName, int requestId) {
                CreateRom.this.copyFile(beauPath, copyPath, packageName, requestId);
            }
        };
    }

    protected BaseRom getBaseRom() {
        return baseRom;
    }

    protected Context getContext() {
        return baseRom.getContext();
    }

    protected String getPackageName() {
        return baseRom.getPackageName();
    }

    protected boolean createCopyDir(String dir) {
        return baseRom.createCopyDir(dir);
    }

    protected String getCreateDir() {
        return baseRom.getCreateDir();
    }

    public String getCopyDir() {
        return baseRom.getCopyDir();
    }

    protected boolean isBaseRom() {
        return baseRom.isBaseRom();
    }

    protected boolean isAppExist() {
        return baseRom.isAppExist();
    }

    protected String getVersionName() {
        return baseRom.getVersionName();
    }

    protected int getVersionCode() {
        return baseRom.getVersionCode();
    }

    public String getPackageDir(){
        return baseRom.getPackageDir();
    }

    protected String copyFiles(int index, String beauDir, String copyDir, BackupCallback callback, String... fileItems) {
        return baseRom.copyFiles(index, beauDir, copyDir, callback, fileItems);
    }

    protected abstract void initConfig();

    protected abstract boolean isRom(String manufacturer);

    protected abstract void copyFile(String beauPath, String copyPath, String packageName, int requestId);

    protected abstract boolean isAppInXSpace(String packName);

    public abstract static class SystemUI {

        private final boolean isUI;
        protected final Context context;

        public SystemUI(Context context, String name, String manufacturer) {
            this.context = context;
            this.isUI = name.equalsIgnoreCase(manufacturer);
        }

        public boolean isUI() {
            return isUI;
        }

        public abstract boolean isRom();

        public abstract void copyFile(String beauPath, String copyPath, String packageName, int requestId);

        public abstract boolean isAppInXSpace(String packName);
    }
}