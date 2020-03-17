package com.micro.rom;

/**
 * created by kilin on 20-1-3 下午2:25
 */
public abstract class BackupCallback {
    private String outPath;

    public abstract void onFinish(boolean isSuccess, String outPath);

    public String getOutPath() {
        return this.outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}