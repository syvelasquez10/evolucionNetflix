// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.NrdController;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.ServiceAgent;

public class AgentPerfHelper
{
    public static void endSession(final ServiceAgent serviceAgent) {
        manageSession(serviceAgent, false);
    }
    
    private static void manageSession(final ServiceAgent serviceAgent, final boolean b) {
        if (serviceAgent instanceof ConfigurationAgent) {
            if (!b) {
                PerformanceProfiler.getInstance().endSession(Sessions.CONFIG_AGENT_LOADED);
                return;
            }
            PerformanceProfiler.getInstance().startSession(Sessions.CONFIG_AGENT_LOADED);
        }
        else if (serviceAgent instanceof UserAgent) {
            if (b) {
                PerformanceProfiler.getInstance().startSession(Sessions.USER_AGENT_LOADED);
                return;
            }
            PerformanceProfiler.getInstance().endSession(Sessions.USER_AGENT_LOADED);
        }
        else if (serviceAgent instanceof LoggingAgent) {
            if (b) {
                PerformanceProfiler.getInstance().startSession(Sessions.LOGGING_AGENT_LOADED);
                return;
            }
            PerformanceProfiler.getInstance().endSession(Sessions.LOGGING_AGENT_LOADED);
        }
        else if (serviceAgent instanceof NrdController) {
            if (b) {
                PerformanceProfiler.getInstance().startSession(Sessions.NRD_CONTROLLER_LOADED);
                return;
            }
            PerformanceProfiler.getInstance().endSession(Sessions.NRD_CONTROLLER_LOADED);
        }
        else if (serviceAgent instanceof ResourceFetcher) {
            if (b) {
                PerformanceProfiler.getInstance().startSession(Sessions.RESOURCE_FETCHER_LOAD);
                return;
            }
            PerformanceProfiler.getInstance().endSession(Sessions.RESOURCE_FETCHER_LOAD);
        }
        else if (serviceAgent instanceof FalkorAgent) {
            if (b) {
                PerformanceProfiler.getInstance().startSession(Sessions.FALKOR_AGENT_LOADED);
                return;
            }
            PerformanceProfiler.getInstance().endSession(Sessions.FALKOR_AGENT_LOADED);
        }
    }
    
    public static void startSession(final ServiceAgent serviceAgent) {
        manageSession(serviceAgent, true);
    }
}
