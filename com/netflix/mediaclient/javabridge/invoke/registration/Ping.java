// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.registration;

import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Ping extends BaseInvoke
{
    private static final String METHOD = "ping";
    private static final String TARGET = "registration";
    
    public Ping() {
        super("registration", "ping");
    }
}
