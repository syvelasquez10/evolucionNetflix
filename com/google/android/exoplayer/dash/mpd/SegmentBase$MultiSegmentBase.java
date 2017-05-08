// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.Util;
import java.util.List;

public abstract class SegmentBase$MultiSegmentBase extends SegmentBase
{
    final long duration;
    final List<SegmentBase$SegmentTimelineElement> segmentTimeline;
    final int startNumber;
    
    public SegmentBase$MultiSegmentBase(final RangedUri rangedUri, final long n, final long n2, final int startNumber, final long duration, final List<SegmentBase$SegmentTimelineElement> segmentTimeline) {
        super(rangedUri, n, n2);
        this.startNumber = startNumber;
        this.duration = duration;
        this.segmentTimeline = segmentTimeline;
    }
    
    public int getFirstSegmentNum() {
        return this.startNumber;
    }
    
    public abstract int getLastSegmentNum(final long p0);
    
    public final long getSegmentDurationUs(final int n, final long n2) {
        if (this.segmentTimeline != null) {
            return this.segmentTimeline.get(n - this.startNumber).duration * 1000000L / this.timescale;
        }
        if (n == this.getLastSegmentNum(n2)) {
            return n2 - this.getSegmentTimeUs(n);
        }
        return this.duration * 1000000L / this.timescale;
    }
    
    public int getSegmentNum(final long n, long segmentTimeUs) {
        final int firstSegmentNum = this.getFirstSegmentNum();
        int lastSegmentNum = this.getLastSegmentNum(segmentTimeUs);
        if (this.segmentTimeline != null) {
            int i = firstSegmentNum;
            while (i <= lastSegmentNum) {
                final int n2 = (i + lastSegmentNum) / 2;
                segmentTimeUs = this.getSegmentTimeUs(n2);
                if (segmentTimeUs < n) {
                    i = n2 + 1;
                }
                else {
                    if (segmentTimeUs <= n) {
                        return n2;
                    }
                    lastSegmentNum = n2 - 1;
                }
            }
            if (i != firstSegmentNum) {
                i = lastSegmentNum;
            }
            return i;
        }
        segmentTimeUs = this.duration * 1000000L / this.timescale;
        final int n3 = (int)(n / segmentTimeUs) + this.startNumber;
        if (n3 < firstSegmentNum) {
            return firstSegmentNum;
        }
        if (lastSegmentNum != -1 && n3 > lastSegmentNum) {
            return lastSegmentNum;
        }
        return n3;
    }
    
    public final long getSegmentTimeUs(final int n) {
        long n2;
        if (this.segmentTimeline != null) {
            n2 = this.segmentTimeline.get(n - this.startNumber).startTime - this.presentationTimeOffset;
        }
        else {
            n2 = (n - this.startNumber) * this.duration;
        }
        return Util.scaleLargeTimestamp(n2, 1000000L, this.timescale);
    }
    
    public abstract RangedUri getSegmentUrl(final Representation p0, final int p1);
    
    public boolean isExplicit() {
        return this.segmentTimeline != null;
    }
}
