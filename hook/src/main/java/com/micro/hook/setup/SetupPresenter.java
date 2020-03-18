package com.micro.hook.setup;

/**
 * created by kilin on 20-3-18 上午9:36
 */
public abstract class SetupPresenter<Inter extends SetupInter> {

    private Inter clazz;

    void setClazz(Inter clazz) throws Throwable {
        if (clazz == null) {
            throw new Throwable("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    public Inter getClazz() {
        return clazz;
    }

    public abstract void onAttached();

    public void onDetached() {
        if (clazz != null) {
            clazz = null;
        }
    }
}