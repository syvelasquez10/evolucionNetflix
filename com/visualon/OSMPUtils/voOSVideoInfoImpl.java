// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSVideoInfoImpl implements voOSVideoInfo
{
    private int mAngle;
    private voOSVideoFormat mFormat;
    
    public voOSVideoInfoImpl() {
    }
    
    public voOSVideoInfoImpl(final voOSVideoFormat mFormat, final int mAngle) {
        this.mFormat = mFormat;
        this.mAngle = mAngle;
    }
    
    @Override
    public int Angle() {
        return this.mAngle;
    }
    
    @Override
    public voOSVideoFormat Format() {
        return this.mFormat;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        (this.mFormat = new voOSVideoFormatImpl()).parse(parcel);
        this.mAngle = parcel.readInt();
        return true;
    }
}
