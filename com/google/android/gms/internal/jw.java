// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jw implements SafeParcelable
{
    public static final Parcelable$Creator<jw> CREATOR;
    String adq;
    String description;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jx();
    }
    
    jw() {
        this.xH = 1;
    }
    
    jw(final int xh, final String adq, final String description) {
        this.xH = xh;
        this.adq = adq;
        this.description = description;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jx.a(this, parcel, n);
    }
}
