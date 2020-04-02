package com.micro.network;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.micro.network.available.RPCApiFactory;
import com.micro.network.available.model.ApiResponseBase;
import com.micro.network.callback.ActivityCallback;
import com.micro.network.callback.FragmentCallback;
import com.micro.network.callback.ModelCallback;

import retrofit2.Call;

/**
 * @Author KiLin
 * @Time 2020/4/2 14:31
 */
public class NetworkManager {

    public synchronized static void setNetwork(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static abstract class ModelNet<C, M> implements Runnable {
        private C clazz;
        private Call<ApiResponseBase<M>> call;
        private final Context context;

        public ModelNet(Context context) {
            this.context = context;
        }

        public void setClazz(Class<C> clazz) {
            this.clazz = RPCApiFactory.create(clazz);
        }

        public C getClazz() {
            return clazz;
        }

        public void setCall(Call<ApiResponseBase<M>> call) {
            this.call = call;
        }

        public void call() {
            call.enqueue(new ModelCallback<ApiResponseBase<M>>(context) {
                @Override
                public void onResponse(ApiResponseBase<M> response) {
                    if (response.isSuccess()) {
                        success(response.getData(), response.getMessage());
                    } else {
                        fail(response.getCode(), response.getMessage());
                    }
                }
            });
        }

        @Override
        public void run() {
            call();
        }

        protected abstract void success(M model, String message);

        protected abstract void fail(String code, String message);
    }

    public static abstract class ActivityNet<C, M> implements Runnable {
        private C clazz;
        private Call<ApiResponseBase<M>> call;
        private final Activity activity;

        public ActivityNet(Activity activity) {
            this.activity = activity;
        }

        public void setClazz(Class<C> clazz) {
            this.clazz = RPCApiFactory.create(clazz);
        }

        public C getClazz() {
            return clazz;
        }

        public void setCall(Call<ApiResponseBase<M>> call) {
            this.call = call;
        }

        public void call() {
            call.enqueue(new ActivityCallback<ApiResponseBase<M>>(activity) {
                @Override
                public void onResponse(ApiResponseBase<M> response) {
                    if (response.isSuccess()) {
                        success(response.getData(), response.getMessage());
                    } else {
                        fail(response.getCode(), response.getMessage());
                    }
                }
            });
        }

        @Override
        public void run() {
            call();
        }

        protected abstract void success(M model, String message);

        protected abstract void fail(String code, String message);
    }

    public static abstract class FragmentNet<C, M> implements Runnable {
        private C clazz;
        private Call<ApiResponseBase<M>> call;
        private final Fragment fragment;

        public FragmentNet(Fragment fragment) {
            this.fragment = fragment;
        }

        public void setClazz(Class<C> clazz) {
            this.clazz = RPCApiFactory.create(clazz);
        }

        public C getClazz() {
            return clazz;
        }

        public void setCall(Call<ApiResponseBase<M>> call) {
            this.call = call;
        }

        public void call() {
            call.enqueue(new FragmentCallback<ApiResponseBase<M>>(fragment) {
                @Override
                public void onResponse(ApiResponseBase<M> response) {
                    if (response.isSuccess()) {
                        success(response.getData(), response.getMessage());
                    } else {
                        fail(response.getCode(), response.getMessage());
                    }
                }
            });
        }

        @Override
        public void run() {
            call();
        }

        protected abstract void success(M model, String message);

        protected abstract void fail(String code, String message);
    }
}