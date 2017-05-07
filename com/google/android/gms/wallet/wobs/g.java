// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class g implements SafeParcelable
{
    public static final Parcelable$Creator<g> CREATOR;
    private final int BR;
    int aus;
    String aut;
    double auu;
    String auv;
    long auw;
    int aux;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    g() {
        this.BR = 1;
        this.aux = -1;
        this.aus = -1;
        this.auu = -1.0;
    }
    
    g(final int br, final int aus, final String aut, final double auu, final String auv, final long auw, final int aux) {
        this.BR = br;
        this.aus = aus;
        this.aut = aut;
        this.auu = auu;
        this.auv = auv;
        this.auw = auw;
        this.aux = aux;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
