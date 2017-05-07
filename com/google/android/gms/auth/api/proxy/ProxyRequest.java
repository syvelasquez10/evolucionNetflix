// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ProxyRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ProxyRequest> CREATOR;
    public static final int zzPg;
    public static final int zzPh;
    public static final int zzPi;
    public static final int zzPj;
    public static final int zzPk;
    public static final int zzPl;
    public static final int zzPm;
    public static final int zzPn;
    public static final int zzPo;
    final int versionCode;
    public final int zzPp;
    public final long zzPq;
    public final byte[] zzPr;
    Bundle zzPs;
    public final String zzzf;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
        zzPg = 0;
        zzPh = 1;
        zzPi = 2;
        zzPj = 3;
        zzPk = 4;
        zzPl = 5;
        zzPm = 6;
        zzPn = 7;
        zzPo = 7;
    }
    
    ProxyRequest(final int versionCode, final String zzzf, final int zzPp, final long zzPq, final byte[] zzPr, final Bundle zzPs) {
        this.versionCode = versionCode;
        this.zzzf = zzzf;
        this.zzPp = zzPp;
        this.zzPq = zzPq;
        this.zzPr = zzPr;
        this.zzPs = zzPs;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "ProxyRequest[ url: " + this.zzzf + ", method: " + this.zzPp + " ]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
