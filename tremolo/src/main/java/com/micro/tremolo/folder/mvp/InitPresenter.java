package com.micro.tremolo.folder.mvp;

import android.content.Context;

import com.micro.network.OkHttp3;
import com.micro.rom.RomPresenter;
import com.micro.tremolo.ApiService;
import com.micro.tremolo.folder.InitRoot;
import com.micro.tremolo.inflood.utils.CacheDefault;

import org.litepal.LitePal;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * created by kilin on 20-3-18 下午1:48
 */
public class InitPresenter extends RomPresenter<InitInter> {

    public InitPresenter(Context context, String packageName, String createDir) {
        super(context, packageName, createDir);
    }

    @Override
    protected void onAttached() {
        InitRoot.logger.i("配置成功");
    }

    @Override
    protected void initConfig() {
        LitePal.initialize(getContext());
        CacheDefault.setInstance(getContext());
    }

    public void login() {
        OkHttp3.getInstance(getContext()).create(ApiService.class)
                .post("", "")
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }).dispose();

    }
}