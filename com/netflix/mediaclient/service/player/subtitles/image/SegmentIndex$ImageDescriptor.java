// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import java.io.DataInputStream;

public class SegmentIndex$ImageDescriptor
{
    public static final int SIZE = 28;
    private int mDuration;
    private int mEndTime;
    private short mHeight;
    private long mImageStartPosition;
    private String mLocalImagePath;
    private String mName;
    private short mOriginX;
    private short mOriginY;
    private int mSize;
    private int mStartTime;
    private int mTotalIndex;
    private short mWidth;
    
    private SegmentIndex$ImageDescriptor(final DataInputStream dataInputStream) {
        this.mStartTime = dataInputStream.readInt();
        this.mDuration = dataInputStream.readInt();
        this.mEndTime = this.mStartTime + this.mDuration;
        this.mOriginX = dataInputStream.readShort();
        this.mOriginY = dataInputStream.readShort();
        this.mWidth = dataInputStream.readShort();
        this.mHeight = dataInputStream.readShort();
        this.mImageStartPosition = dataInputStream.readLong();
        this.mSize = dataInputStream.readInt();
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
            if (this.mTotalIndex != ((SegmentIndex$ImageDescriptor)o).mTotalIndex) {
                return false;
            }
        }
        return true;
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public int getEndTime() {
        return this.mEndTime;
    }
    
    public short getHeight() {
        return this.mHeight;
    }
    
    public long getImageStartPosition() {
        return this.mImageStartPosition;
    }
    
    public String getLocalImagePath() {
        synchronized (this) {
            return this.mLocalImagePath;
        }
    }
    
    public String getName() {
        return this.mName;
    }
    
    public short getOriginX() {
        return this.mOriginX;
    }
    
    public short getOriginY() {
        return this.mOriginY;
    }
    
    public int getSize() {
        return this.mSize;
    }
    
    public int getStartTime() {
        return this.mStartTime;
    }
    
    public int getTotalIndex() {
        return this.mTotalIndex;
    }
    
    public short getWidth() {
        return this.mWidth;
    }
    
    @Override
    public int hashCode() {
        return this.mTotalIndex + 31;
    }
    
    public boolean inRange(final long n) {
        return this.mStartTime <= n && this.mEndTime > n;
    }
    
    public boolean isDownloaded() {
        return this.mLocalImagePath != null;
    }
    
    public void setLocalImagePath(final String mLocalImagePath) {
        synchronized (this) {
            this.mLocalImagePath = mLocalImagePath;
        }
    }
    
    public void setTotalIndex(final int mTotalIndex) {
        this.mTotalIndex = mTotalIndex;
        this.mName = mTotalIndex + ".png";
    }
    
    @Override
    public String toString() {
        return "ImageDescriptor [mStartTime=" + this.mStartTime + ", mDuration=" + this.mDuration + ", mEndTime=" + this.mEndTime + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + ", mWidth=" + this.mWidth + ", mHeight=" + this.mHeight + ", mImageStartPosition=" + this.mImageStartPosition + ", mSize=" + this.mSize + ", mLocalImagePath=" + this.mLocalImagePath + ", mName=" + this.mName + ", mTotalIndex=" + this.mTotalIndex + "]";
    }
}
