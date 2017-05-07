// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Bundle;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ProxyResponse implements SafeParcelable
{
    public static final Parcelable$Creator<ProxyResponse> CREATOR;
    public final byte[] body;
    public final int googlePlayServicesStatusCode;
    public final PendingIntent recoveryAction;
    public final int statusCode;
    final int versionCode;
    final Bundle zzRE;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    ProxyResponse(final int versionCode, final int googlePlayServicesStatusCode, final PendingIntent recoveryAction, final int statusCode, final Bundle zzRE, final byte[] body) {
        this.versionCode = versionCode;
        this.googlePlayServicesStatusCode = googlePlayServicesStatusCode;
        this.statusCode = statusCode;
        this.zzRE = zzRE;
        this.body = body;
        this.recoveryAction = recoveryAction;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
