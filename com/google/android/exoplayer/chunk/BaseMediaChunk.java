// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;

public abstract class BaseMediaChunk extends MediaChunk
{
    private int firstSampleIndex;
    public final boolean isMediaFormatFinal;
    private DefaultTrackOutput output;
    
    public BaseMediaChunk(final DataSource dataSource, final DataSpec dataSpec, final int n, final Format format, final long n2, final long n3, final int n4, final boolean isMediaFormatFinal, final int n5) {
        super(dataSource, dataSpec, n, format, n2, n3, n4, n5);
        this.isMediaFormatFinal = isMediaFormatFinal;
    }
    
    public abstract DrmInitData getDrmInitData();
    
    public final int getFirstSampleIndex() {
        return this.firstSampleIndex;
    }
    
    public abstract MediaFormat getMediaFormat();
    
    protected final DefaultTrackOutput getOutput() {
        return this.output;
    }
    
    public void init(final DefaultTrackOutput output) {
        this.output = output;
        this.firstSampleIndex = output.getWriteIndex();
    }
}
