// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.util.Arrays;
import java.io.DataInputStream;

public class MasterIndex extends FullBox
{
    public static final int FIXED_SIZE = 12;
    public static final String USER_TYPE_MIDX = "com.netflix.midx";
    private int segmentCount;
    private int segmentIndexesSize;
    private long segmentOffset;
    private MasterIndex$SegmentDescriptor[] segments;
    
    public MasterIndex(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader, dataInputStream);
        if (!this.getBoxHeader().isExtendedType()) {
            throw new IllegalStateException("MasterIndex is supposed to be extended type!");
        }
        if (!this.getBoxHeader().isUserType("com.netflix.midx")) {
            throw new IllegalStateException("MasterIndex does not have expected user type value!");
        }
        this.segmentOffset = dataInputStream.readLong();
        this.segmentCount = dataInputStream.readInt();
        this.segments = new MasterIndex$SegmentDescriptor[this.segmentCount];
        long segmentOffset = this.segmentOffset;
        for (int i = 0; i < this.segmentCount; ++i) {
            this.segments[i] = new MasterIndex$SegmentDescriptor(dataInputStream, segmentOffset);
            segmentOffset += this.segments[i].getDuration();
            this.segmentIndexesSize += this.segments[i].getSize();
        }
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.midx".equals(boxHeader.getUserType());
    }
    
    public int getSegmentCount() {
        return this.segmentCount;
    }
    
    public int getSegmentIndexesSize() {
        return this.segmentIndexesSize;
    }
    
    public long getSegmentOffset() {
        return this.segmentOffset;
    }
    
    public MasterIndex$SegmentDescriptor[] getSegments() {
        return this.segments;
    }
    
    @Override
    public String toString() {
        return "MasterIndex{segmentOffset=" + this.segmentOffset + ", segmentCount=" + this.segmentCount + ", segments=" + Arrays.toString(this.segments) + ", segmentIndexesSize=" + this.segmentIndexesSize + "} " + super.toString();
    }
}
