// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jk implements SafeParcelable
{
    public static final Parcelable$Creator<jk> CREATOR;
    String label;
    String value;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jl();
    }
    
    jk() {
        this.xH = 1;
    }
    
    jk(final int xh, final String label, final String value) {
        this.xH = xh;
        this.label = label;
        this.value = value;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jl.a(this, parcel, n);
    }
}
