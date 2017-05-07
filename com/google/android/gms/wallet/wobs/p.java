// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class p implements SafeParcelable
{
    public static final Parcelable$Creator<p> CREATOR;
    private final int BR;
    l auC;
    n auD;
    n auE;
    String auy;
    String tG;
    
    static {
        CREATOR = (Parcelable$Creator)new q();
    }
    
    p() {
        this.BR = 1;
    }
    
    p(final int br, final String auy, final String tg, final l auC, final n auD, final n auE) {
        this.BR = br;
        this.auy = auy;
        this.tG = tg;
        this.auC = auC;
        this.auD = auD;
        this.auE = auE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        q.a(this, parcel, n);
    }
}
