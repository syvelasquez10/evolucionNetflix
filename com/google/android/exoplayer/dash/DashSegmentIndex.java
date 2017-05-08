// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.RangedUri;

public interface DashSegmentIndex
{
    long getDurationUs(final int p0, final long p1);
    
    int getFirstSegmentNum();
    
    int getLastSegmentNum(final long p0);
    
    int getSegmentNum(final long p0, final long p1);
    
    RangedUri getSegmentUrl(final int p0);
    
    long getTimeUs(final int p0);
    
    boolean isExplicit();
}
