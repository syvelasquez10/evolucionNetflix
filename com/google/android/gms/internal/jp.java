// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jp implements SafeParcelable
{
    public static final Parcelable$Creator<jp> CREATOR;
    int adh;
    String adi;
    double adj;
    String adk;
    long adl;
    int adm;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jq();
    }
    
    jp() {
        this.xH = 1;
        this.adm = -1;
        this.adh = -1;
        this.adj = -1.0;
    }
    
    jp(final int xh, final int adh, final String adi, final double adj, final String adk, final long adl, final int adm) {
        this.xH = xh;
        this.adh = adh;
        this.adi = adi;
        this.adj = adj;
        this.adk = adk;
        this.adl = adl;
        this.adm = adm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jq.a(this, parcel, n);
    }
}
