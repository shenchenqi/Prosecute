package com.micro.network.http3.filter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author lun_fang on 2018/8/10.
 */

public class MapResultFilter<T> implements Function<BaseBean<T>, ObservableSource<T>> {
    private Class clazz;

    public MapResultFilter() {
    }

    /**
     * 如果后台返回data里的数据为null，就把bean数据的class文件传过来，方便新建对象
     *
     * @param clazz clazz
     */
    public MapResultFilter(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public ObservableSource<T> apply(final BaseBean<T> tBaseBean) throws Exception {
        if (tBaseBean != null) {
            if (tBaseBean.getCode() == 200) {
                if (tBaseBean.getData() != null) {
                    return Observable.just(tBaseBean.getData());
                } else { //如果后台返回null，则用string或者请求时传过来的bean接收...
                    if (clazz != null) {
                        T t = (T) clazz.newInstance();
                        return Observable.just(t);
                    } else {
                        T t = (T) "";
                        return Observable.just(t);
                    }
                }
            } else {
                throw new Exception(String.format("网络请求报错：　[%s][%s]", tBaseBean.getCode(), tBaseBean.getMessage()));
            }
        }
        throw new NullPointerException();
    }
}
