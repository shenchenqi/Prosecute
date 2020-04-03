package com.micro.root.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.micro.root.Logger;
import com.micro.root.R;
import com.micro.root.dialog.CommonDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionUtils {

    private static final Logger logger = Logger.getLogger("root", "Permission");

    public static final int INSTALL_APK_REQUEST_CODE = 100;
    private static PermissionUtils instance;

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance() {
        if (instance == null) {
            instance = new PermissionUtils();
        }
        return instance;
    }

    /**
     * Request permissions.
     */
    public void requestPermission(Context context, OnPermissionListener onPermissionListener, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            throw new IllegalArgumentException("The length of the third parameter(String... permissions) must be greater than 0!");
        }
        if (hasPermissions(context, permissions)) {
            if (onPermissionListener != null) {
                onPermissionListener.onGranted(Arrays.asList(permissions));
                return;
            }
        }
        if (context == null) {
            return;
        }
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(permissions1 -> {
                    if (onPermissionListener != null) {
                        onPermissionListener.onGranted(permissions1);
                    }
                })
                .onDenied(permissions2 -> {
                    if (AndPermission.hasAlwaysDeniedPermission(context, permissions2)) {
                        showSettingDialog(context, onPermissionListener, permissions2);
                    }
                    if (onPermissionListener != null) {
                        onPermissionListener.onDenied(permissions2);
                    }
                })
                .start();
    }

    /**
     * Display setting dialog.
     */
    public void showSettingDialog(Context context, OnPermissionListener onPermissionListener, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, android.text.TextUtils.join("\n", permissionNames));

        CommonDialog.builder(context)
                .title(context.getString(R.string.title_dialog))
                .msg(message)
                .hideEditText()
                .setSureText(R.string.setting)
                .setDismissListener(new CommonDialog.BuildDismissListener() {
                    @Override
                    public void onNegativeClick() {
                        logger.d("PermissionUtils", "cancel to setting");
                    }

                    @Override
                    public void onPositiveClick(String msg) {
                        setPermission(context, onPermissionListener, permissions);
                    }
                })
                .build();
    }

    /**
     * 是否开启允许未知来源应用安装权限
     *
     * @param context context
     * @return boolean
     */
    public boolean hasUnknownSourceInstallApkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 处理8.0安装未知来源apk
     *
     * @param context context
     */
    public void handleUnknownSourceInstallApk(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            boolean haveInstallPermission = context.getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                showHandleUnknownSettingDialog(context);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showHandleUnknownSettingDialog(Context context) {
        String message = "安装应用需要打开未知来源权限，请去设置中开启权限";

        AlertDialog dialogs = new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, (dialog, which) -> startInstallPermissionSettingActivity(context))
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                })
                .show();

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(dialogs);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(Color.BLACK);
            mTitle.setAccessible(true);
            TextView mTitleView = (TextView) mTitle.get(mAlertController);
            mTitleView.setTextColor(Color.BLACK);
        } catch (Exception e) {
            logger.e(e, "PermissionUtils", "申请报错");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        ((Activity) context).startActivityForResult(intent, INSTALL_APK_REQUEST_CODE);
    }

    /**
     * Set permissions.
     */
    private void setPermission(Context context, OnPermissionListener onPermissionListener, List<String> permissions) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(() -> {
                    for (String permission : permissions) {
                        if (hasPermission(context, permission)) {
                            ((Activity) context).runOnUiThread(() -> requestPermission(context, onPermissionListener, permission));
                        }
                    }
                })
                .start();
    }

    public boolean hasPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context == null) {
            return false;
        }
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (context == null) {
            return false;
        }
        List<Boolean> hasPermissions = new ArrayList<>();
        for (String permission : permissions) {
            boolean has = ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            hasPermissions.add(has);
        }
        return !hasPermissions.contains(false);
    }

    public interface OnPermissionListener {
        /**
         * onGranted
         *
         * @param permissions permissions
         */
        void onGranted(List<String> permissions);

        /**
         * onDenied
         *
         * @param permissions permissions
         */
        void onDenied(List<String> permissions);
    }
}
