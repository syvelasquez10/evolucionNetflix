// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ProxyResponse implements SafeParcelable
{
    public static final Parcelable$Creator<ProxyResponse> CREATOR;
    final int versionCode;
    public final byte[] zzPr;
    final Bundle zzPs;
    public final int zzPt;
    public final PendingIntent zzPu;
    public final int zzPv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    ProxyResponse(final int versionCode, final int zzPt, final PendingIntent zzPu, final int zzPv, final Bundle zzPs, final byte[] zzPr) {
        this.versionCode = versionCode;
        this.zzPt = zzPt;
        this.zzPv = zzPv;
        this.zzPs = zzPs;
        this.zzPr = zzPr;
        this.zzPu = zzPu;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
