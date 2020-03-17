package com.micro.network.http3.logging;

import okhttp3.internal.platform.Platform;

/**
 * @author ihsan on 11/07/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    Logger DEFAULT = (level, tag, message) -> Platform.get().log(level, message, null);

    void log(int level, String tag, String msg);
}
