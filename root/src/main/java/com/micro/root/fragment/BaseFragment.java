package com.micro.root.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.micro.root.R;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.mvp.BasePresenter;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:42
 */
public abstract class BaseFragment<I extends BaseInterface, P extends BasePresenter<I>> extends Fragment implements BaseInterface, View.OnClickListener {
    private static final String TAG = "BaseFragment";

    protected P presenter;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return inflater.inflate(R.layout.base_empty_view, container, false);
        } else {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            initView(binding);
            initData(getArguments());
            return binding.getRoot();
        }
    }

    protected abstract P setPresenter();

    protected abstract int getLayoutId();

    protected void initConfig(){
        presenter = setPresenter();
        if (presenter != null) {
            presenter.setClass((I) this);
        }
    }

    protected abstract void initView(ViewDataBinding binding);

    protected abstract void initData(Bundle bundle);

    public abstract void setPageTitle(CharSequence title);

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        removeView();
    }

    protected abstract void removeView();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeConfig();
    }

    protected void removeConfig() {
        presenter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Context getIContext() {
        return context;
    }

    @Override
    public void onClick(View view) {

    }
}