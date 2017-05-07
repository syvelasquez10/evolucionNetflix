// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public interface Registration
{
    public static final String EVENT_activate = "activate";
    public static final String EVENT_activateComplete = "activateComplete";
    public static final String EVENT_bind = "bind";
    public static final String EVENT_deactivated = "deactivated";
    public static final String NAME = "registration";
    public static final String PATH = "nrdp.registration";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void createDeviceAccount(final Callback p0);
    
    void deactivate(final DeviceAccount p0, final Callback p1);
    
    void deactivateAll(final Callback p0);
    
    void emailActivate(final String p0, final String p1);
    
    void esnMigration();
    
    ActivationTokens getActivationTokens();
    
    DeviceAccount getCurrentDeviceAccount();
    
    DeviceAccount[] getDeviceAccounts();
    
    void getDeviceTokens();
    
    String[] getUILanguages();
    
    boolean isRegistered();
    
    void massDeactivationCheck();
    
    void ping();
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void selectDeviceAccount(final DeviceAccount p0, final Callback p1);
    
    void setActivationTokens(final ActivationTokens p0);
    
    void setUILanguages(final String[] p0);
    
    void tokenActivate(final ActivationTokens p0);
    
    void unselectDeviceAccount(final Callback p0);
}
