package com.micro.hook.plugin;

import android.content.Context;

import com.micro.hook.AutoControlLayout;
import com.micro.network.http3.filter.BaseBean;
import com.micro.network.http3.filter.MapResultFilter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public abstract class PluginPresenter<Inter extends PluginInter> extends AutoControlLayout {
    private Inter clazz;

    public Inter getClazz() {
        return clazz;
    }

    void setClazz(Inter clazz) throws Throwable {
        if (clazz == null) {
            throw new Throwable("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void onAttached();

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