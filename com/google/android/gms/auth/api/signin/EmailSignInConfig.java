// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.util.Patterns;
import com.google.android.gms.common.internal.zzx;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class EmailSignInConfig implements SafeParcelable
{
    public static final Parcelable$Creator<EmailSignInConfig> CREATOR;
    final int versionCode;
    private final Uri zzRO;
    private String zzRP;
    private Uri zzRQ;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    EmailSignInConfig(final int versionCode, final Uri zzRO, final String zzRP, final Uri zzRQ) {
        zzx.zzb(zzRO, "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzh(zzRO.toString(), "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzb(Patterns.WEB_URL.matcher(zzRO.toString()).matches(), "Invalid server widget url");
        this.versionCode = versionCode;
        this.zzRO = zzRO;
        this.zzRP = zzRP;
        this.zzRQ = zzRQ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzlA() {
        return this.zzRO;
    }
    
    public Uri zzlB() {
        return this.zzRQ;
    }
    
    public String zzlC() {
        return this.zzRP;
    }
}
