// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v1;

import java.io.DataInputStream;

public class MasterIndex$SegmentDescriptor
{
    public static final int SIZE = 6;
    private boolean mDownloadSegment;
    private int mDuration;
    private SegmentIndex mSegment;
    private short mSize;
    private long mStartOffset;
    
    public MasterIndex$SegmentDescriptor(final DataInputStream dataInputStream, final long mStartOffset) {
        this.mDownloadSegment = true;
        this.mDuration = dataInputStream.readInt();
        this.mSize = dataInputStream.readShort();
        this.mStartOffset = mStartOffset;
    }
    
    public void downloadStarted() {
        synchronized (this) {
            this.mDownloadSegment = false;
        }
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public SegmentIndex getSegment() {
        return this.mSegment;
    }
    
    public short getSize() {
        return this.mSize;
    }
    
    public long getStartOffset() {
        return this.mStartOffset;
    }
    
    public boolean isDownloaded() {
        return this.mSegment != null;
    }
    
    public void setSegment(final SegmentIndex mSegment) {
        this.mSegment = mSegment;
    }
    
    public boolean shouldDownload() {
        synchronized (this) {
            return this.mDownloadSegment;
        }
    }
    
    @Override
    public String toString() {
        return "SegmentDesciptor [mDuration=" + this.mDuration + ", mSize=" + this.mSize + ", mStartOffset=" + this.mStartOffset + ", mSegment=" + this.mSegment + ", mDownloadSegment=" + this.mDownloadSegment + "]";
    }
}
