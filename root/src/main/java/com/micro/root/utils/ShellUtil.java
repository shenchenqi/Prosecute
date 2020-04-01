package com.micro.root.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShellUtil {
    public static final String COMMAND_SU = "su";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    private ShellUtil() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static ShellUtil.CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static ShellUtil.CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[])commands.toArray(new String[0]), isRoot, true);
    }

    public static ShellUtil.CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    public static ShellUtil.CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static ShellUtil.CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[])commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    public static ShellUtil.CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        int result = -1;
        if (commands != null && commands.length != 0) {
            Process process = null;
            BufferedReader successResult = null;
            BufferedReader errorResult = null;
            StringBuilder successMsg = null;
            StringBuilder errorMsg = null;
            DataOutputStream os = null;

            try {
                process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
                os = new DataOutputStream(process.getOutputStream());
                String[] var13 = commands;
                int var12 = commands.length;

                String s;
                for(int var11 = 0; var11 < var12; ++var11) {
                    s = var13[var11];
                    if (s != null) {
                        os.write(s.getBytes());
                        os.writeBytes("\n");
                        os.flush();
                    }
                }

                os.writeBytes("exit\n");
                os.flush();
                result = process.waitFor();
                if (isNeedResultMsg) {
                    successMsg = new StringBuilder();
                    errorMsg = new StringBuilder();
                    successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                    while((s = successResult.readLine()) != null) {
                        successMsg.append(s);
                    }

                    while((s = errorResult.readLine()) != null) {
                        errorMsg.append(s);
                    }
                }
            } catch (IOException var24) {
                var24.printStackTrace();
            } catch (Exception var25) {
                var25.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }

                    if (successResult != null) {
                        successResult.close();
                    }

                    if (errorResult != null) {
                        errorResult.close();
                    }
                } catch (IOException var23) {
                    var23.printStackTrace();
                }

                if (process != null) {
                    process.destroy();
                }

            }

            return new ShellUtil.CommandResult(result, successMsg == null ? null : successMsg.toString(), errorMsg == null ? null : errorMsg.toString());
        } else {
            return new ShellUtil.CommandResult(result, (String)null, (String)null);
        }
    }

    public static boolean RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;

        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
            return true;
        } catch (Exception var12) {
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

                process.destroy();
            } catch (Exception var11) {
            }

        }

        return false;
    }

    public static class CommandResult {
        public int result;
        public String successMsg;
        public String errorMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}