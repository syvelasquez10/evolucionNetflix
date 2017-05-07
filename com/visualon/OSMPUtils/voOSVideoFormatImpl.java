// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSVideoFormatImpl implements voOSVideoFormat
{
    private int mHeight;
    private int mType;
    private int mWidth;
    
    public voOSVideoFormatImpl() {
    }
    
    public voOSVideoFormatImpl(final int mWidth, final int mHeight, final int mType) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mType = mType;
    }
    
    @Override
    public int Height() {
        return this.mHeight;
    }
    
    @Override
    public int Type() {
        return this.mType;
    }
    
    @Override
    public int Width() {
        return this.mWidth;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mType = parcel.readInt();
        return true;
    }
}
