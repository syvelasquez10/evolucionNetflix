// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public interface Storage
{
    public static final String EVENT_overbudget = "overbudget";
    public static final String NAME = "storage";
    public static final String NO_DEVICE_ACCOUNT = "NDAKADN";
    public static final String PATH = "nrdp.storage";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void clear(final String p0);
    
    void clearAll();
    
    String getItem(final String p0, final String p1);
    
    String key(final String p0, final int p1);
    
    int length(final String p0);
    
    void removeEventListener(final String p0, final EventListener p1);
    
    void removeItem(final String p0, final String p1);
    
    void setItem(final String p0, final String p1, final String p2);
    
    int size();
}
