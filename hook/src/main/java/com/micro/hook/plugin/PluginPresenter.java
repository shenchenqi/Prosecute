package com.micro.hook.plugin;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public abstract class PluginPresenter<Inter extends PluginInter> {
    private Inter clazz;

    public Inter getClazz() {
        return clazz;
    }

    public void setClazz(Inter clazz) throws Throwable {
        if (clazz == null) {
            throw new Throwable("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    public abstract void onAttached();

    public void onDetached() {
        if (clazz != null) {
            clazz = null;
        }
    }
}