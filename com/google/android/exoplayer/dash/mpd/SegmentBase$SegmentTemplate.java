// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.Util;
import java.util.List;

public class SegmentBase$SegmentTemplate extends SegmentBase$MultiSegmentBase
{
    private final String baseUrl;
    final UrlTemplate initializationTemplate;
    final UrlTemplate mediaTemplate;
    
    public SegmentBase$SegmentTemplate(final RangedUri rangedUri, final long n, final long n2, final int n3, final long n4, final List<SegmentBase$SegmentTimelineElement> list, final UrlTemplate initializationTemplate, final UrlTemplate mediaTemplate, final String baseUrl) {
        super(rangedUri, n, n2, n3, n4, list);
        this.initializationTemplate = initializationTemplate;
        this.mediaTemplate = mediaTemplate;
        this.baseUrl = baseUrl;
    }
    
    @Override
    public RangedUri getInitialization(final Representation representation) {
        if (this.initializationTemplate != null) {
            return new RangedUri(this.baseUrl, this.initializationTemplate.buildUri(representation.format.id, 0, representation.format.bitrate, 0L), 0L, -1L);
        }
        return super.getInitialization(representation);
    }
    
    @Override
    public int getLastSegmentNum(final long n) {
        if (this.segmentTimeline != null) {
            return this.segmentTimeline.size() + this.startNumber - 1;
        }
        if (n == -1L) {
            return -1;
        }
        return (int)Util.ceilDivide(n, this.duration * 1000000L / this.timescale) + this.startNumber - 1;
    }
    
    @Override
    public RangedUri getSegmentUrl(final Representation representation, final int n) {
        long startTime;
        if (this.segmentTimeline != null) {
            startTime = this.segmentTimeline.get(n - this.startNumber).startTime;
        }
        else {
            startTime = (n - this.startNumber) * this.duration;
        }
        return new RangedUri(this.baseUrl, this.mediaTemplate.buildUri(representation.format.id, n, representation.format.bitrate, startTime), 0L, -1L);
    }
}
