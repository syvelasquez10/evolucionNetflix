// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.dash.DashSegmentIndex;

final class DashSingleSegmentIndex implements DashSegmentIndex
{
    private final RangedUri uri;
    
    public DashSingleSegmentIndex(final RangedUri uri) {
        this.uri = uri;
    }
    
    @Override
    public long getDurationUs(final int n, final long n2) {
        return n2;
    }
    
    @Override
    public int getFirstSegmentNum() {
        return 0;
    }
    
    @Override
    public int getLastSegmentNum(final long n) {
        return 0;
    }
    
    @Override
    public int getSegmentNum(final long n, final long n2) {
        return 0;
    }
    
    @Override
    public RangedUri getSegmentUrl(final int n) {
        return this.uri;
    }
    
    @Override
    public long getTimeUs(final int n) {
        return 0L;
    }
    
    @Override
    public boolean isExplicit() {
        return true;
    }
}
