// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ju implements SafeParcelable
{
    public static final Parcelable$Creator<ju> CREATOR;
    long ado;
    long adp;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jv();
    }
    
    ju() {
        this.xH = 1;
    }
    
    ju(final int xh, final long ado, final long adp) {
        this.xH = xh;
        this.ado = ado;
        this.adp = adp;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jv.a(this, parcel, n);
    }
}
