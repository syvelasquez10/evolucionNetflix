// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.Util;

public abstract class SegmentBase
{
    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;
    
    public SegmentBase(final RangedUri initialization, final long timescale, final long presentationTimeOffset) {
        this.initialization = initialization;
        this.timescale = timescale;
        this.presentationTimeOffset = presentationTimeOffset;
    }
    
    public RangedUri getInitialization(final Representation representation) {
        return this.initialization;
    }
    
    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, 1000000L, this.timescale);
    }
}
