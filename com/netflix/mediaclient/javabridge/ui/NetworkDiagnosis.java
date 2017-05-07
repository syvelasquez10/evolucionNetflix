// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public interface NetworkDiagnosis
{
    public static final String NAME = "network";
    public static final String PATH = "nrdp.network";
    
    void addEventListener(final String p0, final EventListener p1);
    
    void get(final String p0);
    
    void removeEventListener(final String p0, final EventListener p1);
}
