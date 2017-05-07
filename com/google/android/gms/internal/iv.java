// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class iv implements SafeParcelable
{
    public static final Parcelable$Creator<iv> CREATOR;
    int[] acs;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new iw();
    }
    
    iv() {
        this(1, null);
    }
    
    iv(final int xh, final int[] acs) {
        this.xH = xh;
        this.acs = acs;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        iw.a(this, parcel, n);
    }
}
