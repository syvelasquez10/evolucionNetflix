// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public interface Device
{
    public static final String EVENT_factoryReset = "factoryReset";
    public static final String NAME = "device";
    public static final String PATH = "nrdp.device";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void factoryReset(final Callback p0);
    
    String getCertificationVersion();
    
    String getDeviceModel();
    
    String getESN();
    
    String getESNPrefix();
    
    String getFriendlyName();
    
    String getLanguage();
    
    String getSDKVersion();
    
    String getSoftwareVersion();
    
    String[] getUILanguages();
    
    String getUIVersion();
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void setUILanguages(final String[] p0);
    
    void setUIVersion(final String p0);
}
