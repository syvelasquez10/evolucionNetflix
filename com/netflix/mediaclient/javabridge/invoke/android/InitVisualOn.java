// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public final class InitVisualOn extends BaseInvoke
{
    private static final String METHOD = "initVisualOn";
    private static final String TARGET = "android";
    private long ctxt;
    private long ptr;
    
    public InitVisualOn(final long ptr, final long ctxt) {
        super("android", "initVisualOn");
        this.ptr = ptr;
        this.ctxt = ctxt;
    }
    
    public long getCtxt() {
        return this.ctxt;
    }
    
    public long getPtr() {
        return this.ptr;
    }
}
