// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;

final class TrackFragment
{
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public int length;
    public long nextFragmentDecodeTime;
    public int[] sampleCompositionTimeOffsetTable;
    public long[] sampleDecodingTimeTable;
    public ParsableByteArray sampleEncryptionData;
    public int sampleEncryptionDataLength;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public int[] sampleSizeTable;
    public TrackEncryptionBox trackEncryptionBox;
    
    public void fillEncryptionData(final ExtractorInput extractorInput) {
        extractorInput.readFully(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }
    
    public void fillEncryptionData(final ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }
    
    public long getSamplePresentationTime(final int n) {
        return this.sampleDecodingTimeTable[n] + this.sampleCompositionTimeOffsetTable[n];
    }
    
    public void initEncryptionData(final int sampleEncryptionDataLength) {
        if (this.sampleEncryptionData == null || this.sampleEncryptionData.limit() < sampleEncryptionDataLength) {
            this.sampleEncryptionData = new ParsableByteArray(sampleEncryptionDataLength);
        }
        this.sampleEncryptionDataLength = sampleEncryptionDataLength;
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }
    
    public void initTables(int length) {
        this.length = length;
        if (this.sampleSizeTable == null || this.sampleSizeTable.length < this.length) {
            length = length * 125 / 100;
            this.sampleSizeTable = new int[length];
            this.sampleCompositionTimeOffsetTable = new int[length];
            this.sampleDecodingTimeTable = new long[length];
            this.sampleIsSyncFrameTable = new boolean[length];
            this.sampleHasSubsampleEncryptionTable = new boolean[length];
        }
    }
    
    public void reset() {
        this.length = 0;
        this.nextFragmentDecodeTime = 0L;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }
}
