// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.dash.DashSegmentIndex;
import com.google.android.exoplayer.chunk.Format;
import android.net.Uri;

public class Representation$SingleSegmentRepresentation extends Representation
{
    public final long contentLength;
    private final RangedUri indexUri;
    private final DashSingleSegmentIndex segmentIndex;
    public final Uri uri;
    
    public Representation$SingleSegmentRepresentation(final String s, final long n, final Format format, final SegmentBase$SingleSegmentBase segmentBase$SingleSegmentBase, final String s2, final long contentLength) {
        super(s, n, format, segmentBase$SingleSegmentBase, s2, null);
        this.uri = Uri.parse(segmentBase$SingleSegmentBase.uri);
        this.indexUri = segmentBase$SingleSegmentBase.getIndex();
        this.contentLength = contentLength;
        DashSingleSegmentIndex segmentIndex;
        if (this.indexUri != null) {
            segmentIndex = null;
        }
        else {
            segmentIndex = new DashSingleSegmentIndex(new RangedUri(segmentBase$SingleSegmentBase.uri, null, 0L, contentLength));
        }
        this.segmentIndex = segmentIndex;
    }
    
    @Override
    public DashSegmentIndex getIndex() {
        return this.segmentIndex;
    }
    
    @Override
    public RangedUri getIndexUri() {
        return this.indexUri;
    }
}
