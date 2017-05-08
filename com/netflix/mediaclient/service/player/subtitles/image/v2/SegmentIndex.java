// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.util.Arrays;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.Log;
import java.io.DataInputStream;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import java.util.UUID;

public class SegmentIndex extends FullBox
{
    public static final String USER_TYPE = "com.netflix.sidx";
    private UUID assetID;
    private int duration;
    private ISCSegment mContainer;
    private boolean mDownloadSegment;
    private int mEndTime;
    private ImageDescriptor[] mImages;
    private int mIndex;
    private long mSegmentSize;
    private long mSegmentStartPosition;
    private int mStartTime;
    private int sampleCount;
    
    public SegmentIndex(final BoxHeader boxHeader, int i, int totalIndex, final DataInputStream dataInputStream, final ISCSegment mContainer) {
        super(boxHeader, dataInputStream);
        this.mDownloadSegment = true;
        if (!this.getBoxHeader().isUserType("com.netflix.sidx")) {
            throw new IllegalStateException("SegmentIndex does not have expected user type value!");
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Content size of box in bytes: " + boxHeader.getContentSizeInBytes());
        }
        this.mContainer = mContainer;
        this.assetID = ParserUtils.readUUID(dataInputStream);
        this.duration = dataInputStream.readInt();
        this.sampleCount = dataInputStream.readInt();
        this.mStartTime = i;
        this.mEndTime = this.mStartTime + this.duration;
        this.mImages = new SegmentIndex$ImageDescriptorV2[this.sampleCount];
        for (i = 0; i < this.sampleCount; ++i, ++totalIndex) {
            Log.d("nf_subtitles_imv2", "Parsing image " + i + " metadata.");
            (this.mImages[i] = new SegmentIndex$ImageDescriptorV2(dataInputStream, null)).setTotalIndex(totalIndex);
            Log.d("nf_subtitles_imv2", "Parsing image " + i + " metadata done.");
        }
        if (this.sampleCount > 0) {
            this.mSegmentStartPosition = this.mImages[0].getImageStartPosition();
            i = this.mImages.length - 1;
            this.mSegmentSize = this.mImages[i].getSize() + (this.mImages[i].getImageStartPosition() - this.mSegmentStartPosition);
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", this.toString());
        }
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.sidx".equals(boxHeader.getUserType());
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
            if (this.assetID == null) {
                if (segmentIndex.assetID != null) {
                    return false;
                }
            }
            else if (!this.assetID.equals(segmentIndex.assetID)) {
                return false;
            }
        }
        return true;
    }
    
    public List<SubtitleBlock> getAllVisibleSubtitleBlocks(final long n) {
        final ArrayList<SubtitleBlock> list = new ArrayList<SubtitleBlock>();
        if (this.inRange(n) && this.mImages != null) {
            final ImageDescriptor[] mImages = this.mImages;
            for (int length = mImages.length, i = 0; i < length; ++i) {
                final ImageDescriptor imageDescriptor = mImages[i];
                if (imageDescriptor.inRange(n)) {
                    list.add(new ImageSubtitleBlock(imageDescriptor));
                }
            }
        }
        return list;
    }
    
    public UUID getAssetID() {
        return this.assetID;
    }
    
    public ISCSegment getContainer() {
        return this.mContainer;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public int getEndTime() {
        return this.mEndTime;
    }
    
    public ImageDescriptor[] getImages() {
        return this.mImages;
    }
    
    public int getIndex() {
        return this.mIndex;
    }
    
    public int getSampleCount() {
        return this.sampleCount;
    }
    
    public long getSegmentSize() {
        return this.mSegmentSize;
    }
    
    public long getSegmentStartPosition() {
        return this.mSegmentStartPosition;
    }
    
    public int getStartTime() {
        return this.mStartTime;
    }
    
    @Override
    public int hashCode() {
        final int mIndex = this.mIndex;
        int hashCode;
        if (this.assetID == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.assetID.hashCode();
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
        return "SegmentIndex{assetID=" + this.assetID + ", duration=" + this.duration + ", sampleCount=" + this.sampleCount + ", mDownloadSegment=" + this.mDownloadSegment + ", mSegmentStartPosition=" + this.mSegmentStartPosition + ", mSegmentSize=" + this.mSegmentSize + ", mIndex=" + this.mIndex + ", mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + ", mImages=" + Arrays.toString(this.mImages) + "} " + super.toString();
    }
}
