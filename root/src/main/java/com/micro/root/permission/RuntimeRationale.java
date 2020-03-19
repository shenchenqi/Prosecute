package com.micro.root.permission;

import android.content.Context;
import android.text.TextUtils;

import com.micro.root.R;
import com.micro.root.dialog.CommonDialog;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

public final class RuntimeRationale implements Rationale<List<String>> {
    @Override
    public void showRationale(Context context, List<String> permissions, final RequestExecutor executor) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.my_app_name) + context.getString(R.string.message_permission_rationale);
        message = String.format(message, TextUtils.join("\n", permissionNames));
        CommonDialog.builder(context)
                .title(context.getString(R.string.title_dialog))
                .msg(message)
                .hideEditText()
                .setSureText(R.string.resume)
                .setDismissListener(new CommonDialog.BuildDismissListener() {
                    @Override
                    public void onNegativeClick() {
                        executor.cancel();
                    }

                    @Override
                    public void onPositiveClick(String msg) {
                        executor.execute();
                    }
                })
                .build();

    }
}
