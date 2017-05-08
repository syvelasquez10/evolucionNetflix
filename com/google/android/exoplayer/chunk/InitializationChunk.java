// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.DefaultExtractorInput;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;

public class InitializationChunk extends Chunk implements ChunkExtractorWrapper$SingleTrackOutput
{
    private volatile int bytesLoaded;
    private DrmInitData drmInitData;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private MediaFormat mediaFormat;
    private SeekMap seekMap;
    
    public InitializationChunk(final DataSource dataSource, final DataSpec dataSpec, final int n, final Format format, final ChunkExtractorWrapper extractorWrapper, final int n2) {
        super(dataSource, dataSpec, 2, n, format, n2);
        this.extractorWrapper = extractorWrapper;
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
    public void drmInitData(final DrmInitData drmInitData) {
        this.drmInitData = drmInitData;
    }
    
    @Override
    public void format(final MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }
    
    public DrmInitData getDrmInitData() {
        return this.drmInitData;
    }
    
    public MediaFormat getFormat() {
        return this.mediaFormat;
    }
    
    public SeekMap getSeekMap() {
        return this.seekMap;
    }
    
    public boolean hasDrmInitData() {
        return this.drmInitData != null;
    }
    
    public boolean hasFormat() {
        return this.mediaFormat != null;
    }
    
    public boolean hasSeekMap() {
        return this.seekMap != null;
    }
    
    @Override
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }
    
    @Override
    public void load() {
        final DataSpec remainderDataSpec = Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded);
        try {
            final DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.dataSource, remainderDataSpec.absoluteStreamPosition, this.dataSource.open(remainderDataSpec));
            if (this.bytesLoaded == 0) {
                this.extractorWrapper.init(this);
            }
            int read = 0;
            while (true) {
                if (read != 0) {
                    return;
                }
                try {
                    if (!this.loadCanceled) {
                        read = this.extractorWrapper.read(defaultExtractorInput);
                        continue;
                    }
                }
                finally {
                    this.bytesLoaded = (int)(defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                }
            }
        }
        finally {
            this.dataSource.close();
        }
    }
    
    @Override
    public int sampleData(final ExtractorInput extractorInput, final int n, final boolean b) {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }
    
    @Override
    public void sampleData(final ParsableByteArray parsableByteArray, final int n) {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }
    
    @Override
    public void sampleMetadata(final long n, final int n2, final int n3, final int n4, final byte[] array) {
        throw new IllegalStateException("Unexpected sample data in initialization chunk");
    }
    
    @Override
    public void seekMap(final SeekMap seekMap) {
        this.seekMap = seekMap;
    }
}
