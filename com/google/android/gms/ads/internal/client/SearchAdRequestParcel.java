// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class SearchAdRequestParcel implements SafeParcelable
{
    public static final zzae CREATOR;
    public final int backgroundColor;
    public final int versionCode;
    public final int zztA;
    public final int zztB;
    public final int zztC;
    public final int zztD;
    public final int zztE;
    public final int zztF;
    public final int zztG;
    public final String zztH;
    public final int zztI;
    public final String zztJ;
    public final int zztK;
    public final int zztL;
    public final String zztM;
    
    static {
        CREATOR = new zzae();
    }
    
    SearchAdRequestParcel(final int versionCode, final int zztA, final int backgroundColor, final int zztB, final int zztC, final int zztD, final int zztE, final int zztF, final int zztG, final String zztH, final int zztI, final String zztJ, final int zztK, final int zztL, final String zztM) {
        this.versionCode = versionCode;
        this.zztA = zztA;
        this.backgroundColor = backgroundColor;
        this.zztB = zztB;
        this.zztC = zztC;
        this.zztD = zztD;
        this.zztE = zztE;
        this.zztF = zztF;
        this.zztG = zztG;
        this.zztH = zztH;
        this.zztI = zztI;
        this.zztJ = zztJ;
        this.zztK = zztK;
        this.zztL = zztL;
        this.zztM = zztM;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzae.zza(this, parcel, n);
    }
}
