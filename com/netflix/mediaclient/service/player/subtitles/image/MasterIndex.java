// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import java.util.Arrays;
import java.io.DataInputStream;
import java.util.UUID;

public class MasterIndex
{
    public static final int HEADER_SIZE = 114;
    private static final byte[] MIDX;
    public static final int MIN_SIZE = 120;
    public static final int VERSION = 0;
    private long mCreationTime;
    private short mEntryCount;
    private int mIdentifier;
    private UUID mIndexID;
    private String mLanguageCode;
    private long mMovieID;
    private long mPackageID;
    private short mRootContainerExtentX;
    private short mRootContainerExtentY;
    private int mSegmentIndexesSize;
    private MasterIndex$SegmentDescriptor[] mSegments;
    private int mSize;
    private long mStartOffset;
    private String mTextType;
    private int mVersion;
    
    static {
        MIDX = new byte[] { 109, 105, 100, 120 };
    }
    
    public MasterIndex(final DataInputStream dataInputStream) {
        final short n = 0;
        if (dataInputStream == null) {
            throw new IllegalArgumentException("Master index is null!");
        }
        final byte[] array = { dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte() };
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != MasterIndex.MIDX[i]) {
                throw new IllegalArgumentException("Identifier  is NOT 'midx'");
            }
        }
        this.mIdentifier = (array[0] << 24 | array[1] << 16 | array[2] << 8 | array[3]);
        this.mVersion = dataInputStream.readInt();
        final byte[] array2 = new byte[36];
        dataInputStream.read(array2);
        this.mIndexID = UUID.fromString(new String(array2));
        this.mCreationTime = dataInputStream.readLong();
        this.mPackageID = dataInputStream.readLong();
        this.mMovieID = dataInputStream.readLong();
        this.mRootContainerExtentX = dataInputStream.readShort();
        this.mRootContainerExtentY = dataInputStream.readShort();
        final byte[] array3 = new byte[16];
        dataInputStream.read(array3);
        this.mLanguageCode = new String(array3);
        final byte[] array4 = new byte[16];
        dataInputStream.read(array4);
        this.mTextType = new String(array4);
        this.mStartOffset = dataInputStream.readLong();
        this.mEntryCount = dataInputStream.readShort();
        this.mSegments = new MasterIndex$SegmentDescriptor[this.mEntryCount];
        this.mSize = this.mEntryCount * 6 + 114;
        long mStartOffset = this.mStartOffset;
        for (short n2 = n; n2 < this.mEntryCount; ++n2) {
            this.mSegments[n2] = new MasterIndex$SegmentDescriptor(dataInputStream, mStartOffset);
            mStartOffset += this.mSegments[n2].getDuration();
            this.mSegmentIndexesSize += this.mSegments[n2].mSize;
        }
    }
    
    public long getCreationTime() {
        return this.mCreationTime;
    }
    
    public short getEntryCount() {
        return this.mEntryCount;
    }
    
    public int getIdentifier() {
        return this.mIdentifier;
    }
    
    public UUID getIndexID() {
        return this.mIndexID;
    }
    
    public String getLanguageCode() {
        return this.mLanguageCode;
    }
    
    public long getMovieID() {
        return this.mMovieID;
    }
    
    public long getPackageID() {
        return this.mPackageID;
    }
    
    public short getRootContainerExtentX() {
        return this.mRootContainerExtentX;
    }
    
    public short getRootContainerExtentY() {
        return this.mRootContainerExtentY;
    }
    
    public int getSegmentIndexesSize() {
        return this.mSegmentIndexesSize;
    }
    
    public MasterIndex$SegmentDescriptor[] getSegments() {
        return this.mSegments;
    }
    
    public int getSize() {
        return this.mSize;
    }
    
    public long getStartOffset() {
        return this.mStartOffset;
    }
    
    public String getTextType() {
        return this.mTextType;
    }
    
    public int getVersion() {
        return this.mVersion;
    }
    
    @Override
    public String toString() {
        return "MasterIndex [mIdentifier=" + this.mIdentifier + ", mVersion=" + this.mVersion + ", mIndexID=" + this.mIndexID + ", mCreationTime=" + this.mCreationTime + ", mPackageID=" + this.mPackageID + ", mMovieID=" + this.mMovieID + ", mRootContainerExtentX=" + this.mRootContainerExtentX + ", mRootContainerExtentY=" + this.mRootContainerExtentY + ", mLanguageCode=" + this.mLanguageCode + ", mTextType=" + this.mTextType + ", mStartOffset=" + this.mStartOffset + ", mEntryCount=" + this.mEntryCount + ", mSize=" + this.mSize + ", mSegmentIndexesSize=" + this.mSegmentIndexesSize + ", mSegments=" + Arrays.toString(this.mSegments) + "]";
    }
}
