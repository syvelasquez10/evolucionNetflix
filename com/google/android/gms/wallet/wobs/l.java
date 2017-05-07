// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class l implements SafeParcelable
{
    public static final Parcelable$Creator<l> CREATOR;
    private final int BR;
    long auA;
    long auz;
    
    static {
        CREATOR = (Parcelable$Creator)new m();
    }
    
    l() {
        this.BR = 1;
    }
    
    l(final int br, final long auz, final long auA) {
        this.BR = br;
        this.auz = auz;
        this.auA = auA;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        m.a(this, parcel, n);
    }
}
