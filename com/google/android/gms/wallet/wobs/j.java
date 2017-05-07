// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class j implements SafeParcelable
{
    public static final Parcelable$Creator<j> CREATOR;
    private final int BR;
    String auy;
    String tG;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    j() {
        this.BR = 1;
    }
    
    j(final int br, final String auy, final String tg) {
        this.BR = br;
        this.auy = auy;
        this.tG = tg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
