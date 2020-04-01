package com.micro.hook.plugin;

import android.content.Context;

import com.micro.network.http3.filter.BaseBean;
import com.micro.network.http3.filter.MapResultFilter;
import com.micro.root.mvp.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public abstract class PluginPresenter<Inter extends PluginInter> extends BasePresenter<Inter> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected <P> Observable getObservable(Observable<BaseBean<P>> observable) {
        return observable.compose(io2main()).flatMap(new MapResultFilter());
    }

    protected <P> Observable getObservable(Observable<BaseBean<P>> observable, Class<P> pClass) {
        return observable.compose(io2main()).flatMap(new MapResultFilter(pClass));
    }

    private ObservableTransformer io2main() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}