package com.micro.root.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.micro.root.R;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.mvp.BasePresenter;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:40
 */
public abstract class BaseActivity<I extends BaseInterface, P extends BasePresenter<I>> extends AppCompatActivity implements BaseInterface, View.OnClickListener {
    private static final String TAG = "BaseActivity";

    public P presenter;
    private Context context;

    protected abstract boolean isHide();

    private void setWindowTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (isHide()) {
            actionBar.hide();
        } else {
            actionBar.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWindowTitle();
        super.onCreate(savedInstanceState);
        context = this;
        if (getLayoutId() == 0) {
            setContentView(R.layout.base_empty_view);
        } else {
            ViewDataBinding binding = DataBindingUtil.setContentView(this, getLayoutId());
            initConfig();
            initView(binding);
            initData(getIntent().getExtras());
        }
    }

    protected abstract P setPresenter();

    protected abstract int getLayoutId();

    protected void initConfig() {
        presenter = setPresenter();
        if (presenter != null) {
            presenter.setClass((I) this);
        }
    }

    protected abstract void initView(ViewDataBinding binding);

    protected abstract void initData(Bundle bundle);

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeData();
    }

    protected abstract void removeData();

    @Override
    protected void onStop() {
        super.onStop();
        removeView();
    }

    protected abstract void removeView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeConfig();
    }

    protected void removeConfig() {
        presenter = null;
    }

    @Override
    public Context getIContext() {
        return context;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    public void onClick(View view) {

    }

    public void sendActivity(Class clazz) {
        this.sendActivity(clazz, null);
    }

    public void sendActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getIContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        super.startActivity(intent);
    }

    public void sendBroadcast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        super.sendBroadcast(intent);
    }
}