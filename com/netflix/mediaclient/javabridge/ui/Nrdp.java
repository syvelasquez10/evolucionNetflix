// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;

public interface Nrdp
{
    public static final String ACTION_ID = "ACTION_ID";
    public static final String COMPLETE = "COMPLETE";
    public static final String ERROR = "ERROR";
    public static final String EVENT_ObjectSyncComplete = "ObjectSyncComplete";
    public static final String EVENT_background = "background";
    public static final String EVENT_command = "command";
    public static final String EVENT_config = "config";
    public static final String EVENT_fatalerror = "fatalerror";
    public static final String EVENT_nrdconf = "nrdconf";
    public static final String NAME = "";
    public static final String NETWORK_ERROR = "NETWORK_ERROR";
    public static final String PATH = "nrdp";
    public static final String READY = "READY";
    
    void addEventListener(final String p0, final EventListener p1);
    
    boolean debug();
    
    void exit();
    
    void getConfigList();
    
    Device getDevice();
    
    Log getLog();
    
    MdxController getMdxController();
    
    IMedia getMedia();
    
    Registration getRegistration();
    
    Storage getStorage();
    
    boolean isReady();
    
    long now();
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void setConfigData(final String p0, final String p1);
}
