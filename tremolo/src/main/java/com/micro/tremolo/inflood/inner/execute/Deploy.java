package com.micro.tremolo.inflood.inner.execute;

import com.micro.root.Logger;

/**
 * @Author Kilin
 * @Date 2020/3/26 16:56
 */
public interface Deploy {
    Logger monitorLogger = com.micro.root.Logger.getLogger("Execute-Log-Monitor");
    Logger controlLogger = com.micro.root.Logger.getLogger("Execute-Log-Control");
    Logger netLogger = com.micro.root.Logger.getLogger("Net-Log");
}