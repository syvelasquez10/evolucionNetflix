// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import android.view.Surface;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public final class SetVideoSurface extends BaseInvoke
{
    private static final String METHOD = "setVideoSurface";
    private static final String TARGET = "android";
    private Surface surface;
    
    public SetVideoSurface(final Surface surface) {
        super("android", "setVideoSurface");
        if (surface == null) {
            throw new IllegalArgumentException("Surface can not be null!");
        }
        this.surface = surface;
    }
    
    public Surface getSurface() {
        return this.surface;
    }
}
