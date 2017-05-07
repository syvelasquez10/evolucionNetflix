// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UnclaimBleDeviceRequest implements SafeParcelable
{
    public static final Parcelable$Creator<UnclaimBleDeviceRequest> CREATOR;
    private final int BR;
    private final String TX;
    
    static {
        CREATOR = (Parcelable$Creator)new ag();
    }
    
    UnclaimBleDeviceRequest(final int br, final String tx) {
        this.BR = br;
        this.TX = tx;
    }
    
    public UnclaimBleDeviceRequest(final String s) {
        this(3, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getDeviceAddress() {
        return this.TX;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", this.TX);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ag.a(this, parcel, n);
    }
}
