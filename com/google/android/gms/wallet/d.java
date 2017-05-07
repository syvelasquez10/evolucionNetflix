// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class d implements SafeParcelable
{
    public static final Parcelable$Creator<d> CREATOR;
    LoyaltyWalletObject abg;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    d() {
        this.xH = 1;
    }
    
    d(final int xh, final LoyaltyWalletObject abg) {
        this.xH = xh;
        this.abg = abg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
