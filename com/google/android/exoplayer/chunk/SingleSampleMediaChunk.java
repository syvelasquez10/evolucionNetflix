// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;

public final class SingleSampleMediaChunk extends BaseMediaChunk
{
    private volatile int bytesLoaded;
    private volatile boolean loadCanceled;
    private final DrmInitData sampleDrmInitData;
    private final MediaFormat sampleFormat;
    
    public SingleSampleMediaChunk(final DataSource dataSource, final DataSpec dataSpec, final int n, final Format format, final long n2, final long n3, final int n4, final MediaFormat sampleFormat, final DrmInitData sampleDrmInitData, final int n5) {
        super(dataSource, dataSpec, n, format, n2, n3, n4, true, n5);
        this.sampleFormat = sampleFormat;
        this.sampleDrmInitData = sampleDrmInitData;
    }
    
    @Override
    public long bytesLoaded() {
        return this.bytesLoaded;
    }
    
    @Override
    public void cancelLoad() {
        this.loadCanceled = true;
    }
    
    @Override
    public DrmInitData getDrmInitData() {
        return this.sampleDrmInitData;
    }
    
    @Override
    public MediaFormat getMediaFormat() {
        return this.sampleFormat;
    }
    
    @Override
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }
    
    @Override
    public void load() {
        int i = 0;
        final DataSpec remainderDataSpec = Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded);
        try {
            this.dataSource.open(remainderDataSpec);
            while (i != -1) {
                this.bytesLoaded += i;
                i = this.getOutput().sampleData(this.dataSource, Integer.MAX_VALUE, true);
            }
            this.getOutput().sampleMetadata(this.startTimeUs, 1, this.bytesLoaded, 0, null);
        }
        finally {
            this.dataSource.close();
        }
    }
}
