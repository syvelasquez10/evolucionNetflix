// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.dash.DashSegmentIndex;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatWrapper;

public abstract class Representation implements FormatWrapper
{
    private final String cacheKey;
    public final String contentId;
    public final Format format;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;
    
    private Representation(final String contentId, final long revisionId, final Format format, final SegmentBase segmentBase, String string) {
        this.contentId = contentId;
        this.revisionId = revisionId;
        this.format = format;
        if (string == null) {
            string = contentId + "." + format.id + "." + revisionId;
        }
        this.cacheKey = string;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }
    
    public static Representation newInstance(final String s, final long n, final Format format, final SegmentBase segmentBase) {
        return newInstance(s, n, format, segmentBase, null);
    }
    
    public static Representation newInstance(final String s, final long n, final Format format, final SegmentBase segmentBase, final String s2) {
        if (segmentBase instanceof SegmentBase$SingleSegmentBase) {
            return new Representation$SingleSegmentRepresentation(s, n, format, (SegmentBase$SingleSegmentBase)segmentBase, s2, -1L);
        }
        if (segmentBase instanceof SegmentBase$MultiSegmentBase) {
            return new Representation$MultiSegmentRepresentation(s, n, format, (SegmentBase$MultiSegmentBase)segmentBase, s2);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }
    
    public String getCacheKey() {
        return this.cacheKey;
    }
    
    @Override
    public Format getFormat() {
        return this.format;
    }
    
    public abstract DashSegmentIndex getIndex();
    
    public abstract RangedUri getIndexUri();
    
    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }
}
