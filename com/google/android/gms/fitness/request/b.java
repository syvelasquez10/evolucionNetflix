// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.fitness.data.BleDevice;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b implements SafeParcelable
{
    public static final Parcelable$Creator<b> CREATOR;
    private final int BR;
    private final String TX;
    private final BleDevice TY;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    b(final int br, final String tx, final BleDevice ty) {
        this.BR = br;
        this.TX = tx;
        this.TY = ty;
    }
    
    public b(final BleDevice bleDevice) {
        this(2, bleDevice.getAddress(), bleDevice);
    }
    
    public b(final String s) {
        this(2, s, null);
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
    
    public BleDevice iW() {
        return this.TY;
    }
    
    @Override
    public String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", this.TX, this.TY);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
