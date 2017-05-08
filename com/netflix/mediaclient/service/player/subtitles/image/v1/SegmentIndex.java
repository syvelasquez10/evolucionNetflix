// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v1;

import java.util.Arrays;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.Log;
import java.io.DataInputStream;
import java.util.UUID;

public class SegmentIndex
{
    public static final int HEADER_SIZE = 46;
    public static final int MIN_SIZE = 46;
    private static final byte[] SIDX;
    protected static final String TAG = "nf_subtitles";
    private boolean mDownloadSegment;
    private int mDuration;
    private int mEndTime;
    private short mEntryCount;
    private int mIdentifier;
    private SegmentIndex$ImageDescriptorV1[] mImages;
    private int mIndex;
    private UUID mIndexID;
    private long mSegmentSize;
    private long mSegmentStartPosition;
    private int mSize;
    private int mStartTime;
    
    static {
        SIDX = new byte[] { 115, 105, 100, 120 };
    }
    
    public SegmentIndex(final DataInputStream dataInputStream, int i, int totalIndex) {
        this.mDownloadSegment = true;
        this.mEndTime = 0;
        if (dataInputStream == null) {
            throw new IllegalArgumentException("Segment index is null!");
        }
        final byte[] array = { dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte() };
        for (int j = 0; j < array.length; ++j) {
            if (array[j] != SegmentIndex.SIDX[j]) {
                throw new IllegalArgumentException("Identifier  is NOT 'sidx'");
            }
        }
        this.mIdentifier = (array[0] << 24 | array[1] << 16 | array[2] << 8 | array[3]);
        final byte[] array2 = new byte[36];
        dataInputStream.read(array2);
        this.mIndexID = UUID.fromString(new String(array2));
        this.mDuration = dataInputStream.readInt();
        this.mStartTime = i;
        this.mEndTime = this.mStartTime + this.mDuration;
        this.mEntryCount = dataInputStream.readShort();
        this.mSize = this.mEntryCount * 28 + 46;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Header: " + this.toString());
        }
        this.mImages = new SegmentIndex$ImageDescriptorV1[this.mEntryCount];
        for (i = 0; i < this.mEntryCount; ++i, ++totalIndex) {
            Log.d("nf_subtitles", "Parsing image " + i + " metadata.");
            (this.mImages[i] = new SegmentIndex$ImageDescriptorV1(dataInputStream, null)).setTotalIndex(totalIndex);
            Log.d("nf_subtitles", "Parsing image " + i + " metadata done.");
        }
        if (this.mEntryCount > 0) {
            this.mSegmentStartPosition = this.mImages[0].getImageStartPosition();
            i = this.mImages.length - 1;
            this.mSegmentSize = this.mImages[i].getSize() + (this.mImages[i].getImageStartPosition() - this.mSegmentStartPosition);
        }
    }
    
    public void downloadStarted() {
        synchronized (this) {
            this.mDownloadSegment = false;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final SegmentIndex segmentIndex = (SegmentIndex)o;
            if (this.mIndex != segmentIndex.mIndex) {
                return false;
            }
            if (this.mIndexID == null) {
                if (segmentIndex.mIndexID != null) {
                    return false;
                }
            }
            else if (!this.mIndexID.equals(segmentIndex.mIndexID)) {
                return false;
            }
        }
        return true;
    }
    
    public List<SubtitleBlock> getAllVisibleSubtitleBlocks(final long n) {
        final ArrayList<SubtitleBlock> list = new ArrayList<SubtitleBlock>();
        if (this.inRange(n) && this.mImages != null) {
            final SegmentIndex$ImageDescriptorV1[] mImages = this.mImages;
            for (int length = mImages.length, i = 0; i < length; ++i) {
                final SegmentIndex$ImageDescriptorV1 segmentIndex$ImageDescriptorV1 = mImages[i];
                if (segmentIndex$ImageDescriptorV1.inRange(n)) {
                    list.add(new ImageSubtitleBlock(segmentIndex$ImageDescriptorV1));
                }
            }
        }
        return list;
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public int getEndTime() {
        return this.mEndTime;
    }
    
    public short getEntryCount() {
        return this.mEntryCount;
    }
    
    public int getIdentifier() {
        return this.mIdentifier;
    }
    
    public ImageDescriptor[] getImages() {
        return this.mImages;
    }
    
    public int getIndex() {
        return this.mIndex;
    }
    
    public UUID getIndexID() {
        return this.mIndexID;
    }
    
    public long getSegmentSize() {
        return this.mSegmentSize;
    }
    
    public long getSegmentStartPosition() {
        return this.mSegmentStartPosition;
    }
    
    public int getSize() {
        return this.mSize;
    }
    
    public int getStartTime() {
        return this.mStartTime;
    }
    
    @Override
    public int hashCode() {
        final int mIndex = this.mIndex;
        int hashCode;
        if (this.mIndexID == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mIndexID.hashCode();
        }
        return hashCode + (mIndex + 31) * 31;
    }
    
    public boolean inRange(final long n) {
        return this.mStartTime <= n && this.mEndTime > n;
    }
    
    public void setIndex(final int mIndex) {
        this.mIndex = mIndex;
    }
    
    public boolean shouldDownload() {
        synchronized (this) {
            return this.mDownloadSegment;
        }
    }
    
    @Override
    public String toString() {
        return "SegmentIndex [mIdentifier=" + this.mIdentifier + ", mIndexID=" + this.mIndexID + ", mDuration=" + this.mDuration + ", mEntryCount=" + this.mEntryCount + ", mSize=" + this.mSize + ", mImages=" + Arrays.toString(this.mImages) + ", mDownloadSegment=" + this.mDownloadSegment + ", mSegmentStartPosition=" + this.mSegmentStartPosition + ", mSegmentSize=" + this.mSegmentSize + ", mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + "]";
    }
}
