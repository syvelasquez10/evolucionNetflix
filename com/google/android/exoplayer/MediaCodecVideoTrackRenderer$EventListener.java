// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.view.Surface;

public interface MediaCodecVideoTrackRenderer$EventListener extends MediaCodecTrackRenderer$EventListener
{
    void onDrawnToSurface(final Surface p0);
    
    void onDroppedFrames(final int p0, final long p1);
    
    void onVideoSizeChanged(final int p0, final int p1, final int p2, final float p3);
}
