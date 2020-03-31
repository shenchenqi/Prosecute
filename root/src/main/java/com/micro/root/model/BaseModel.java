package com.micro.root.model;

import com.micro.root.mvp.BaseInterface;
import com.micro.root.mvp.BasePresenter;

public abstract class BaseModel<I extends BaseInterface, P extends BasePresenter<I>> {

    protected final P presenter;

    protected BaseModel() {
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setClass((I) this);
        }
    }

    protected abstract P getPresenter();
}