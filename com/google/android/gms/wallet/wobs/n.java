// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class n implements SafeParcelable
{
    public static final Parcelable$Creator<n> CREATOR;
    private final int BR;
    String auB;
    String description;
    
    static {
        CREATOR = (Parcelable$Creator)new o();
    }
    
    n() {
        this.BR = 1;
    }
    
    n(final int br, final String auB, final String description) {
        this.BR = br;
        this.auB = auB;
        this.description = description;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        o.a(this, parcel, n);
    }
}
