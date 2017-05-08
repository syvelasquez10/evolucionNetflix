// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.UriUtil;
import android.net.Uri;
import com.google.android.exoplayer.util.Assertions;

public final class RangedUri
{
    private final String baseUri;
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;
    
    public RangedUri(final String baseUri, final String referenceUri, final long start, final long length) {
        Assertions.checkArgument(baseUri != null || referenceUri != null);
        this.baseUri = baseUri;
        this.referenceUri = referenceUri;
        this.start = start;
        this.length = length;
    }
    
    public RangedUri attemptMerge(final RangedUri rangedUri) {
        long n = -1L;
        if (rangedUri != null && this.getUriString().equals(rangedUri.getUriString())) {
            if (this.length != -1L && this.start + this.length == rangedUri.start) {
                final String baseUri = this.baseUri;
                final String referenceUri = this.referenceUri;
                final long start = this.start;
                if (rangedUri.length != -1L) {
                    n = this.length + rangedUri.length;
                }
                return new RangedUri(baseUri, referenceUri, start, n);
            }
            if (rangedUri.length != -1L && rangedUri.start + rangedUri.length == this.start) {
                final String baseUri2 = this.baseUri;
                final String referenceUri2 = this.referenceUri;
                final long start2 = rangedUri.start;
                if (this.length != -1L) {
                    n = rangedUri.length + this.length;
                }
                return new RangedUri(baseUri2, referenceUri2, start2, n);
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final RangedUri rangedUri = (RangedUri)o;
            if (this.start != rangedUri.start || this.length != rangedUri.length || !this.getUriString().equals(rangedUri.getUriString())) {
                return false;
            }
        }
        return true;
    }
    
    public Uri getUri() {
        return UriUtil.resolveToUri(this.baseUri, this.referenceUri);
    }
    
    public String getUriString() {
        return UriUtil.resolve(this.baseUri, this.referenceUri);
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (((int)this.start + 527) * 31 + (int)this.length) * 31 + this.getUriString().hashCode();
        }
        return this.hashCode;
    }
}
