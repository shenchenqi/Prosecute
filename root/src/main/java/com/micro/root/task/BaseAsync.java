package com.micro.root.task;

import android.content.Context;
import android.os.AsyncTask;

import com.micro.root.Logger;

/**
 * @Description: 基类异步
 * @Author: ALin
 * @CreateDate: 19-5-13
 */
public abstract class BaseAsync<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected final Context context;

    protected BaseAsync(Context context) {
        super();
        Logger.getLogger("root", "Base-Async").i("BaseAsync", "AsyncTask初始化");
        this.context = context;
    }

    @Override
    protected Result doInBackground(Params... params) {
        return asyncRun(params);
    }

    protected abstract Result asyncRun(Params... params);

    @Override
    protected void onPreExecute() {
        preExecute();
    }

    protected void preExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Result result) {
        postExecute(result);
    }

    protected void postExecute(Result result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        progressUpdate(values);
    }

    protected void progressUpdate(Progress... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Result result) {
        cancelled(result);
    }

    protected void cancelled(Result result) {
        super.onCancelled(result);
    }

    @Override
    protected void onCancelled() {
        cancelled();
    }

    protected void cancelled() {
        super.onCancelled();
    }
}