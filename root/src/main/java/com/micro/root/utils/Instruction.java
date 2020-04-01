package com.micro.root.utils;

public class Instruction {
    public Instruction() {
    }

    public class ShellCommands {
        public static final String SHELL_APKROOT = "chmod 777 -r ";
        public static final String SHELL_SCREENDARK = "settings put system screen_brightness 0";
        public static final String SHELL_SCREENBRIGHT = "settings put system screen_brightness 125";
        public static final String SHELL_SCREEHOME = "input keyevent 3";
        public static final String SHELL_SCREENMENU = "input keyevent 82";
        public static final String SHELL_SCREENBACK = "input keyevent 4";
        public static final String SHELL_REBOOT = "su -c \"/system/bin/reboot\"";
        public static final String SHELL_INSTALL = "pm install -r ";
        public static final String SHELL_STOP_WHATSAPP = "am force-stop com.whatsapp";
        public static final String SHELL_STOP_GALLERY3D = "am force-stop com.android.gallery3d";
        public static final String SHELL_GREPBIERSERVICE = "ls /data/app |grep com.example.bierwathsapp";
        public static final String SHELL_GREPWHATSAPP = "ls /data/app |grep com.wathsapp";

        public ShellCommands() {
        }
    }

    public class System {
        public static final String OpenApplication = "OpenApplication";
        public static final String StartOperation = "StartOperation";
        public static final String StopOperation = "StopOperation";
        public static final String BrightScreen = "BrightScreen";
        public static final String DarkScreen = "DarkScreen";
        public static final String RebootDevice = "RebootDevice";
        public static final String CloseApplication = "CloseApplication";
        public static final String ChangeDeviceNumber = "ChangeDeviceNumber";
        public static final String UpgradeService = "UpgradeService";
        public static final String UpgradeApplication = "UpgradeApplication";
        public static final String OpenVPN = "OpenVPN";
        public static final String StopVPN = "StopVPN";

        public System() {
        }
    }
}