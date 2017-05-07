// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class ef implements Parcelable
{
    @Deprecated
    public static final Parcelable$Creator<ef> CREATOR;
    private String mValue;
    private String wp;
    private String wq;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ef>() {
            @Deprecated
            public ef i(final Parcel parcel) {
                return new ef(parcel);
            }
            
            @Deprecated
            public ef[] u(final int n) {
                return new ef[n];
            }
        };
    }
    
    public ef() {
    }
    
    ef(final Parcel parcel) {
        this.readFromParcel(parcel);
    }
    
    public ef(final String wp, final String wq, final String mValue) {
        this.wp = wp;
        this.wq = wq;
        this.mValue = mValue;
    }
    
    @Deprecated
    private void readFromParcel(final Parcel parcel) {
        this.wp = parcel.readString();
        this.wq = parcel.readString();
        this.mValue = parcel.readString();
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.wp;
    }
    
    public String getValue() {
        return this.mValue;
    }
    
    @Deprecated
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.wp);
        parcel.writeString(this.wq);
        parcel.writeString(this.mValue);
    }
}
