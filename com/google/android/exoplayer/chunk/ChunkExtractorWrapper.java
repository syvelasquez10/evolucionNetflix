// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.extractor.ExtractorOutput;

public class ChunkExtractorWrapper implements ExtractorOutput, TrackOutput
{
    private final Extractor extractor;
    private boolean extractorInitialized;
    private ChunkExtractorWrapper$SingleTrackOutput output;
    private boolean seenTrack;
    
    public ChunkExtractorWrapper(final Extractor extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public void drmInitData(final DrmInitData drmInitData) {
        this.output.drmInitData(drmInitData);
    }
    
    @Override
    public void endTracks() {
        Assertions.checkState(this.seenTrack);
    }
    
    @Override
    public void format(final MediaFormat mediaFormat) {
        this.output.format(mediaFormat);
    }
    
    public void init(final ChunkExtractorWrapper$SingleTrackOutput output) {
        this.output = output;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            this.extractorInitialized = true;
            return;
        }
        this.extractor.seek();
    }
    
    public int read(final ExtractorInput extractorInput) {
        boolean b = true;
        final int read = this.extractor.read(extractorInput, null);
        if (read == 1) {
            b = false;
        }
        Assertions.checkState(b);
        return read;
    }
    
    @Override
    public int sampleData(final ExtractorInput extractorInput, final int n, final boolean b) {
        return this.output.sampleData(extractorInput, n, b);
    }
    
    @Override
    public void sampleData(final ParsableByteArray parsableByteArray, final int n) {
        this.output.sampleData(parsableByteArray, n);
    }
    
    @Override
    public void sampleMetadata(final long n, final int n2, final int n3, final int n4, final byte[] array) {
        this.output.sampleMetadata(n, n2, n3, n4, array);
    }
    
    @Override
    public void seekMap(final SeekMap seekMap) {
        this.output.seekMap(seekMap);
    }
    
    @Override
    public TrackOutput track(final int n) {
        Assertions.checkState(!this.seenTrack);
        this.seenTrack = true;
        return this;
    }
}
