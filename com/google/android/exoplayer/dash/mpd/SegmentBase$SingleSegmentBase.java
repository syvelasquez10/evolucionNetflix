// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

public class SegmentBase$SingleSegmentBase extends SegmentBase
{
    final long indexLength;
    final long indexStart;
    public final String uri;
    
    public SegmentBase$SingleSegmentBase(final RangedUri rangedUri, final long n, final long n2, final String uri, final long indexStart, final long indexLength) {
        super(rangedUri, n, n2);
        this.uri = uri;
        this.indexStart = indexStart;
        this.indexLength = indexLength;
    }
    
    public SegmentBase$SingleSegmentBase(final String s) {
        this(null, 1L, 0L, s, 0L, -1L);
    }
    
    public RangedUri getIndex() {
        if (this.indexLength <= 0L) {
            return null;
        }
        return new RangedUri(this.uri, null, this.indexStart, this.indexLength);
    }
}
