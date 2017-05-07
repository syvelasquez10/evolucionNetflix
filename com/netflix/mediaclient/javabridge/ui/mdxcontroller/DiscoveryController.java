// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import java.util.Map;

public interface DiscoveryController
{
    public static final String LAUNCH_ARG_INTENT = "intent";
    public static final String LAUNCH_ARG_INTENT_VALUE_PLAY = "play";
    public static final String LAUNCH_ARG_INTENT_VALUE_SYNC = "sync";
    public static final String LAUNCH_ARG_TITLEID = "titleid";
    
    void isRemoteDeviceReady(final String p0);
    
    void launchNetflix(final String p0, final Map<String, String> p1);
}
