// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class SearchAdRequestParcel implements SafeParcelable
{
    public static final zzae CREATOR;
    public final int backgroundColor;
    public final int versionCode;
    public final int zztP;
    public final int zztQ;
    public final int zztR;
    public final int zztS;
    public final int zztT;
    public final int zztU;
    public final int zztV;
    public final String zztW;
    public final int zztX;
    public final String zztY;
    public final int zztZ;
    public final int zzua;
    public final String zzub;
    
    static {
        CREATOR = new zzae();
    }
    
    SearchAdRequestParcel(final int versionCode, final int zztP, final int backgroundColor, final int zztQ, final int zztR, final int zztS, final int zztT, final int zztU, final int zztV, final String zztW, final int zztX, final String zztY, final int zztZ, final int zzua, final String zzub) {
        this.versionCode = versionCode;
        this.zztP = zztP;
        this.backgroundColor = backgroundColor;
        this.zztQ = zztQ;
        this.zztR = zztR;
        this.zztS = zztS;
        this.zztT = zztT;
        this.zztU = zztU;
        this.zztV = zztV;
        this.zztW = zztW;
        this.zztX = zztX;
        this.zztY = zztY;
        this.zztZ = zztZ;
        this.zzua = zzua;
        this.zzub = zzub;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzae.zza(this, parcel, n);
    }
}
