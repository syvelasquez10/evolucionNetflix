// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.List;

public class SegmentBase$SegmentList extends SegmentBase$MultiSegmentBase
{
    final List<RangedUri> mediaSegments;
    
    public SegmentBase$SegmentList(final RangedUri rangedUri, final long n, final long n2, final int n3, final long n4, final List<SegmentBase$SegmentTimelineElement> list, final List<RangedUri> mediaSegments) {
        super(rangedUri, n, n2, n3, n4, list);
        this.mediaSegments = mediaSegments;
    }
    
    @Override
    public int getLastSegmentNum(final long n) {
        return this.startNumber + this.mediaSegments.size() - 1;
    }
    
    @Override
    public RangedUri getSegmentUrl(final Representation representation, final int n) {
        return this.mediaSegments.get(n - this.startNumber);
    }
    
    @Override
    public boolean isExplicit() {
        return true;
    }
}
