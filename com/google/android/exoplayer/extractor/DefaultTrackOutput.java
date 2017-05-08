// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.MediaFormat;

public class DefaultTrackOutput implements TrackOutput
{
    private volatile MediaFormat format;
    private volatile long largestParsedTimestampUs;
    private long lastReadTimeUs;
    private boolean needKeyframe;
    private final RollingSampleBuffer rollingBuffer;
    private final SampleHolder sampleInfoHolder;
    private long spliceOutTimeUs;
    
    public DefaultTrackOutput(final Allocator allocator) {
        this.rollingBuffer = new RollingSampleBuffer(allocator);
        this.sampleInfoHolder = new SampleHolder(0);
        this.needKeyframe = true;
        this.lastReadTimeUs = Long.MIN_VALUE;
        this.spliceOutTimeUs = Long.MIN_VALUE;
        this.largestParsedTimestampUs = Long.MIN_VALUE;
    }
    
    private boolean advanceToEligibleSample() {
        boolean peekSample2;
        boolean peekSample = peekSample2 = this.rollingBuffer.peekSample(this.sampleInfoHolder);
        if (this.needKeyframe) {
            while (true) {
                peekSample2 = peekSample;
                if (!peekSample) {
                    break;
                }
                peekSample2 = peekSample;
                if (this.sampleInfoHolder.isSyncFrame()) {
                    break;
                }
                this.rollingBuffer.skipSample();
                peekSample = this.rollingBuffer.peekSample(this.sampleInfoHolder);
            }
        }
        return peekSample2 && (this.spliceOutTimeUs == Long.MIN_VALUE || this.sampleInfoHolder.timeUs < this.spliceOutTimeUs);
    }
    
    public void clear() {
        this.rollingBuffer.clear();
        this.needKeyframe = true;
        this.lastReadTimeUs = Long.MIN_VALUE;
        this.spliceOutTimeUs = Long.MIN_VALUE;
        this.largestParsedTimestampUs = Long.MIN_VALUE;
    }
    
    public void discardUpstreamSamples(final int n) {
        this.rollingBuffer.discardUpstreamSamples(n);
        long timeUs;
        if (this.rollingBuffer.peekSample(this.sampleInfoHolder)) {
            timeUs = this.sampleInfoHolder.timeUs;
        }
        else {
            timeUs = Long.MIN_VALUE;
        }
        this.largestParsedTimestampUs = timeUs;
    }
    
    @Override
    public void format(final MediaFormat format) {
        this.format = format;
    }
    
    public long getLargestParsedTimestampUs() {
        return this.largestParsedTimestampUs;
    }
    
    public int getReadIndex() {
        return this.rollingBuffer.getReadIndex();
    }
    
    public boolean getSample(final SampleHolder sampleHolder) {
        if (!this.advanceToEligibleSample()) {
            return false;
        }
        this.rollingBuffer.readSample(sampleHolder);
        this.needKeyframe = false;
        this.lastReadTimeUs = sampleHolder.timeUs;
        return true;
    }
    
    public int getWriteIndex() {
        return this.rollingBuffer.getWriteIndex();
    }
    
    public boolean isEmpty() {
        return !this.advanceToEligibleSample();
    }
    
    @Override
    public int sampleData(final ExtractorInput extractorInput, final int n, final boolean b) {
        return this.rollingBuffer.appendData(extractorInput, n, b);
    }
    
    public int sampleData(final DataSource dataSource, final int n, final boolean b) {
        return this.rollingBuffer.appendData(dataSource, n, b);
    }
    
    @Override
    public void sampleData(final ParsableByteArray parsableByteArray, final int n) {
        this.rollingBuffer.appendData(parsableByteArray, n);
    }
    
    @Override
    public void sampleMetadata(final long n, final int n2, final int n3, final int n4, final byte[] array) {
        this.largestParsedTimestampUs = Math.max(this.largestParsedTimestampUs, n);
        this.rollingBuffer.commitSample(n, n2, this.rollingBuffer.getWritePosition() - n3 - n4, n3, array);
    }
    
    public boolean skipToKeyframeBefore(final long n) {
        return this.rollingBuffer.skipToKeyframeBefore(n);
    }
}
