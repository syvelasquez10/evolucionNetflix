// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.BehindLiveWindowException;
import com.google.android.exoplayer.dash.mpd.RangedUri;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer.extractor.webm.WebmExtractor;
import com.google.android.exoplayer.dash.mpd.Representation;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.chunk.ChunkExtractorWrapper;

public final class DashChunkSource$RepresentationHolder
{
    public final ChunkExtractorWrapper extractorWrapper;
    public MediaFormat mediaFormat;
    public final boolean mimeTypeIsRawText;
    private long periodDurationUs;
    private final long periodStartTimeUs;
    public Representation representation;
    public DashSegmentIndex segmentIndex;
    private int segmentNumShift;
    
    public DashChunkSource$RepresentationHolder(final long periodStartTimeUs, final long periodDurationUs, final Representation representation) {
        this.periodStartTimeUs = periodStartTimeUs;
        this.periodDurationUs = periodDurationUs;
        this.representation = representation;
        final String mimeType = representation.format.mimeType;
        this.mimeTypeIsRawText = DashChunkSource.mimeTypeIsRawText(mimeType);
        ChunkExtractorWrapper extractorWrapper;
        if (this.mimeTypeIsRawText) {
            extractorWrapper = null;
        }
        else {
            Extractor extractor;
            if (DashChunkSource.mimeTypeIsWebm(mimeType)) {
                extractor = new WebmExtractor();
            }
            else {
                extractor = new FragmentedMp4Extractor();
            }
            extractorWrapper = new ChunkExtractorWrapper(extractor);
        }
        this.extractorWrapper = extractorWrapper;
        this.segmentIndex = representation.getIndex();
    }
    
    public int getFirstAvailableSegmentNum() {
        return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
    }
    
    public int getLastSegmentNum() {
        return this.segmentIndex.getLastSegmentNum(this.periodDurationUs);
    }
    
    public long getSegmentEndTimeUs(final int n) {
        return this.getSegmentStartTimeUs(n) + this.segmentIndex.getDurationUs(n - this.segmentNumShift, this.periodDurationUs);
    }
    
    public int getSegmentNum(final long n) {
        return this.segmentIndex.getSegmentNum(n - this.periodStartTimeUs, this.periodDurationUs) + this.segmentNumShift;
    }
    
    public long getSegmentStartTimeUs(final int n) {
        return this.segmentIndex.getTimeUs(n - this.segmentNumShift) + this.periodStartTimeUs;
    }
    
    public RangedUri getSegmentUrl(final int n) {
        return this.segmentIndex.getSegmentUrl(n - this.segmentNumShift);
    }
    
    public boolean isBeyondLastSegment(final int n) {
        final int lastSegmentNum = this.getLastSegmentNum();
        return lastSegmentNum != -1 && n > lastSegmentNum + this.segmentNumShift;
    }
    
    public void updateRepresentation(long timeUs, final Representation representation) {
        final DashSegmentIndex index = this.representation.getIndex();
        final DashSegmentIndex index2 = representation.getIndex();
        this.periodDurationUs = timeUs;
        this.representation = representation;
        if (index != null) {
            this.segmentIndex = index2;
            if (index.isExplicit()) {
                final int lastSegmentNum = index.getLastSegmentNum(this.periodDurationUs);
                timeUs = index.getTimeUs(lastSegmentNum);
                timeUs += index.getDurationUs(lastSegmentNum, this.periodDurationUs);
                final int firstSegmentNum = index2.getFirstSegmentNum();
                final long timeUs2 = index2.getTimeUs(firstSegmentNum);
                if (timeUs == timeUs2) {
                    this.segmentNumShift += index.getLastSegmentNum(this.periodDurationUs) + 1 - firstSegmentNum;
                    return;
                }
                if (timeUs < timeUs2) {
                    throw new BehindLiveWindowException();
                }
                this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum;
            }
        }
    }
}
