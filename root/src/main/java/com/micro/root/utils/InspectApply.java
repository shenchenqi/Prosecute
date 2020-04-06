package com.micro.root.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

public class InspectApply {

    /**
     * 方法描述：指定某个app开启
     *
     * @param context     上下文
     * @param packagename 包名
     * @return true : 开启  false : 未开启
     */
    public static boolean openApply(Context context, String packagename) {
        int iImportance = isAppOnForeground(context, packagename);
        if (iImportance == IMPORTANCE_EMPTY || iImportance == IMPORTANCE_BACKGROUND) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent();
            intent = packageManager.getLaunchIntentForPackage(packagename);
            context.startActivity(intent);
            return true;
        }
        return false;
    }


    /**
     * 方法描述：指定某个app关闭
     *
     * @param context     上下文
     * @param packagename 包名
     */
    public static void shutDownApply(Context context, String packagename) {
        if (isAppOnForeground(context, packagename) == IMPORTANCE_FOREGROUND) {
            shellCommand(Instruction.ShellCommands.SHELL_SCREEHOME);
            shellCommand(Instruction.ShellCommands.SHELL_STOP_WHATSAPP);
            shellCommand(Instruction.ShellCommands.SHELL_STOP_GALLERY3D);
        }
    }

    /**
     * 方法描述：shell命令执行
     *
     * @param command 执行命令
     */
    public static void shellCommand(String command) {
        ShellUtil.execCommand(command, true);
    }

    public static final int IMPORTANCE_FOREGROUND = ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;//100
    public static final int IMPORTANCE_BACKGROUND = ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;//400;
    public static final int IMPORTANCE_EMPTY = ActivityManager.RunningAppProcessInfo.IMPORTANCE_EMPTY;//500;

    /**
     * 方法描述：判断某一app运行状态
     *
     * @param context     上下文
     * @param packagename 包名
     * @return 100 表示正在前台运行，400 表示正在后台运行， 500 表示没运行
     */
    public static int isAppOnForeground(Context context, String packagename) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(packagename) && appProcess.importance == IMPORTANCE_FOREGROUND) {
                    return IMPORTANCE_FOREGROUND;
                } else if (appProcess.processName.equals(packagename) && appProcess.importance == IMPORTANCE_BACKGROUND) {
                    return IMPORTANCE_BACKGROUND;
                }
            }
        }
        return IMPORTANCE_EMPTY;
    }

    /**
     * 方法描述：将某app从后台切换至前台
     *
     * @param context     上下文
     * @param packagename 包名
     */
    public static void setBackstageToFrontDesk(Context context, String packagename) {
        ActivityManager mAm = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获得当前后台运行的task
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> taskList = mAm.getRunningTasks(IMPORTANCE_BACKGROUND);
        for (ActivityManager.RunningTaskInfo rti : taskList) {
            //找到当前应用的task，并启动task的栈顶activity，达到程序切换到前台
            if (rti.topActivity.getPackageName().equals(packagename)) {
                mAm.moveTaskToFront(rti.id, 0);//报错，manifast文件定义中没有加入action
                return;
            }
        }
    }
}