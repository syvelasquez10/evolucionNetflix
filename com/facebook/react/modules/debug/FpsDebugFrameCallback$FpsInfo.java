// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

public class FpsDebugFrameCallback$FpsInfo
{
    public final double fps;
    public final double jsFps;
    public final int total4PlusFrameStutters;
    public final int totalExpectedFrames;
    public final int totalFrames;
    public final int totalJsFrames;
    public final int totalTimeMs;
    
    public FpsDebugFrameCallback$FpsInfo(final int totalFrames, final int totalJsFrames, final int totalExpectedFrames, final int total4PlusFrameStutters, final double fps, final double jsFps, final int totalTimeMs) {
        this.totalFrames = totalFrames;
        this.totalJsFrames = totalJsFrames;
        this.totalExpectedFrames = totalExpectedFrames;
        this.total4PlusFrameStutters = total4PlusFrameStutters;
        this.fps = fps;
        this.jsFps = jsFps;
        this.totalTimeMs = totalTimeMs;
    }
}
