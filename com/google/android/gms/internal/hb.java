// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class hb implements Parcelable
{
    @Deprecated
    public static final Parcelable$Creator<hb> CREATOR;
    private String BL;
    private String BM;
    private String mValue;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<hb>() {
            @Deprecated
            public hb[] H(final int n) {
                return new hb[n];
            }
            
            @Deprecated
            public hb k(final Parcel parcel) {
                return new hb(parcel);
            }
        };
    }
    
    public hb() {
    }
    
    hb(final Parcel parcel) {
        this.readFromParcel(parcel);
    }
    
    public hb(final String bl, final String bm, final String mValue) {
        this.BL = bl;
        this.BM = bm;
        this.mValue = mValue;
    }
    
    @Deprecated
    private void readFromParcel(final Parcel parcel) {
        this.BL = parcel.readString();
        this.BM = parcel.readString();
        this.mValue = parcel.readString();
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.BL;
    }
    
    public String getValue() {
        return this.mValue;
    }
    
    @Deprecated
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.BL);
        parcel.writeString(this.BM);
        parcel.writeString(this.mValue);
    }
}
