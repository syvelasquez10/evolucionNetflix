// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import com.google.android.gms.internal.jr;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class d implements SafeParcelable
{
    public static final Parcelable$Creator<d> CREATOR;
    private final int BR;
    String auo;
    String aup;
    ArrayList<b> auq;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    d() {
        this.BR = 1;
        this.auq = jr.hz();
    }
    
    d(final int br, final String auo, final String aup, final ArrayList<b> auq) {
        this.BR = br;
        this.auo = auo;
        this.aup = aup;
        this.auq = auq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
