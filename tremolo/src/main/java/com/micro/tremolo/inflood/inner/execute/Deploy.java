package com.micro.tremolo.inflood.inner.execute;

import com.micro.root.Logger;

/**
 * @Author Kilin
 * @Date 2020/3/26 16:56
 */
public interface Deploy {
    Logger monitorLogger = com.micro.root.Logger.getLogger("Execute-monitor-Log");
    Logger controlLogger = com.micro.root.Logger.getLogger("Execute-control-Log");
}