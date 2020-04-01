package com.micro.prosecute;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.micro.root.acitivity.BaseActivity;
import com.micro.root.mvp.BasePresenter;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:28
 */
public class MainActivity extends BaseActivity {

    @Override
    protected boolean isHide() {
        return true;
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView(ViewDataBinding binding) {
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void removeData() {

    }

    @Override
    protected void removeView() {

    }
}