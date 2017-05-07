// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import org.json.JSONArray;

public interface MdxConfiguration
{
    JSONArray getCastWhiteList();
    
    JSONArray getMdxBlackListTargets();
    
    boolean isDisableMdx();
    
    boolean isDisableWebsocket();
    
    boolean isEnableCast();
    
    boolean isRemoteControlLockScreenEnabled();
    
    boolean isRemoteControlNotificationEnabled();
}
