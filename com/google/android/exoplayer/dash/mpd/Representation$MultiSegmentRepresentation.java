// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.dash.DashSegmentIndex;

public class Representation$MultiSegmentRepresentation extends Representation implements DashSegmentIndex
{
    private final SegmentBase$MultiSegmentBase segmentBase;
    
    public Representation$MultiSegmentRepresentation(final String s, final long n, final Format format, final SegmentBase$MultiSegmentBase segmentBase, final String s2) {
        super(s, n, format, segmentBase, s2, null);
        this.segmentBase = segmentBase;
    }
    
    @Override
    public long getDurationUs(final int n, final long n2) {
        return this.segmentBase.getSegmentDurationUs(n, n2);
    }
    
    @Override
    public int getFirstSegmentNum() {
        return this.segmentBase.getFirstSegmentNum();
    }
    
    @Override
    public DashSegmentIndex getIndex() {
        return this;
    }
    
    @Override
    public RangedUri getIndexUri() {
        return null;
    }
    
    @Override
    public int getLastSegmentNum(final long n) {
        return this.segmentBase.getLastSegmentNum(n);
    }
    
    @Override
    public int getSegmentNum(final long n, final long n2) {
        return this.segmentBase.getSegmentNum(n, n2);
    }
    
    @Override
    public RangedUri getSegmentUrl(final int n) {
        return this.segmentBase.getSegmentUrl(this, n);
    }
    
    @Override
    public long getTimeUs(final int n) {
        return this.segmentBase.getSegmentTimeUs(n);
    }
    
    @Override
    public boolean isExplicit() {
        return this.segmentBase.isExplicit();
    }
}
