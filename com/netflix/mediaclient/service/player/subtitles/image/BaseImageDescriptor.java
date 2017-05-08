// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import com.netflix.mediaclient.Log;

public abstract class BaseImageDescriptor implements ImageDescriptor
{
    public static final int SIZE = 28;
    protected static final String TAG = "nf_subtitles";
    protected boolean mDisplayed;
    protected int mDuration;
    protected int mEndTime;
    protected short mHeight;
    protected long mImageStartPosition;
    protected String mLocalImagePath;
    protected String mName;
    protected int mNumberOfDisplays;
    protected short mOriginX;
    protected short mOriginY;
    protected int mSize;
    protected int mStartTime;
    protected int mTotalIndex;
    protected short mWidth;
    
    @Override
    public void displayed() {
        if (this.mDisplayed) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Subtitle " + this.mName + " is already visible, do not count it again as displayed.");
            }
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitle " + this.mName + " was not visible, count it as displayed.");
        }
        this.mDisplayed = true;
        ++this.mNumberOfDisplays;
    }
    
    @Override
    public int getDuration() {
        return this.mDuration;
    }
    
    @Override
    public int getEndTime() {
        return this.mEndTime;
    }
    
    @Override
    public short getHeight() {
        return this.mHeight;
    }
    
    @Override
    public long getImageStartPosition() {
        return this.mImageStartPosition;
    }
    
    @Override
    public String getLocalImagePath() {
        synchronized (this) {
            return this.mLocalImagePath;
        }
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public int getNumberOfDisplays() {
        return this.mNumberOfDisplays;
    }
    
    @Override
    public short getOriginX() {
        return this.mOriginX;
    }
    
    @Override
    public short getOriginY() {
        return this.mOriginY;
    }
    
    @Override
    public int getSize() {
        return this.mSize;
    }
    
    @Override
    public int getStartTime() {
        return this.mStartTime;
    }
    
    @Override
    public int getTotalIndex() {
        return this.mTotalIndex;
    }
    
    @Override
    public short getWidth() {
        return this.mWidth;
    }
    
    @Override
    public boolean inRange(final long n) {
        return this.mStartTime <= n && this.mEndTime > n;
    }
    
    @Override
    public boolean isDownloaded() {
        return this.mLocalImagePath != null;
    }
    
    @Override
    public boolean isVisibleInGivenTimeRange(final long n, final long n2) {
        final boolean b = false;
        final boolean b2 = false;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Is block will visible for range (" + n + ", " + n2 + ") when its start time " + this.mStartTime + " and its end time " + this.mEndTime);
        }
        boolean b3;
        if (n > n2) {
            Log.e("nf_subtitles", "From can not be later than to!");
            b3 = b2;
        }
        else {
            boolean b4 = b;
            if (n2 > this.mStartTime) {
                b4 = b;
                if (n <= this.mEndTime) {
                    b4 = true;
                }
            }
            b3 = b4;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Block is visible" + b4);
                return b4;
            }
        }
        return b3;
    }
    
    @Override
    public void seeked(final long n) {
        int n2;
        if (n < this.mEndTime) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            this.mDisplayed = false;
        }
    }
    
    @Override
    public void setLocalImagePath(final String mLocalImagePath) {
        synchronized (this) {
            this.mLocalImagePath = mLocalImagePath;
        }
    }
    
    @Override
    public void setTotalIndex(final int mTotalIndex) {
        this.mTotalIndex = mTotalIndex;
        this.mName = mTotalIndex + ".png";
    }
    
    @Override
    public boolean wasDisplayed() {
        return this.mDisplayed;
    }
}
