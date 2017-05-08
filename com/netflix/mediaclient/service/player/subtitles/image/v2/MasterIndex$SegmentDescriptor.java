// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.io.DataInputStream;

public class MasterIndex$SegmentDescriptor
{
    public static final int FIXED_SIZE = 6;
    private boolean downloadSegment;
    private int duration;
    private ISCSegment segment;
    private short size;
    private long startOffset;
    
    public MasterIndex$SegmentDescriptor(final DataInputStream dataInputStream, final long startOffset) {
        this.downloadSegment = true;
        this.duration = dataInputStream.readInt();
        this.size = dataInputStream.readShort();
        this.startOffset = startOffset;
    }
    
    public void downloadStarted() {
        synchronized (this) {
            this.downloadSegment = false;
        }
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public ISCSegment getSegment() {
        return this.segment;
    }
    
    public short getSize() {
        return this.size;
    }
    
    public long getStartOffset() {
        return this.startOffset;
    }
    
    public boolean isDownloaded() {
        return this.segment != null;
    }
    
    public void setSegment(final ISCSegment segment) {
        this.segment = segment;
    }
    
    public boolean shouldDownload() {
        synchronized (this) {
            return this.downloadSegment;
        }
    }
    
    @Override
    public String toString() {
        return "SegmentDescriptor{duration=" + this.duration + ", size=" + this.size + ", startOffset=" + this.startOffset + ", segment=" + this.segment + ", downloadSegment=" + this.downloadSegment + '}';
    }
}
