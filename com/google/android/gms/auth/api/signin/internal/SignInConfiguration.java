// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.auth.api.signin.FacebookSignInConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.auth.api.signin.EmailSignInConfig;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SignInConfiguration implements SafeParcelable
{
    public static final Parcelable$Creator<SignInConfiguration> CREATOR;
    final int versionCode;
    private final String zzRT;
    private String zzRU;
    private EmailSignInConfig zzRV;
    private GoogleSignInConfig zzRW;
    private FacebookSignInConfig zzRX;
    private String zzRY;
    
    static {
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    SignInConfiguration(final int versionCode, final String s, final String zzRU, final EmailSignInConfig zzRV, final GoogleSignInConfig zzRW, final FacebookSignInConfig zzRX, final String zzRY) {
        this.versionCode = versionCode;
        this.zzRT = zzx.zzcs(s);
        this.zzRU = zzRU;
        this.zzRV = zzRV;
        this.zzRW = zzRW;
        this.zzRX = zzRX;
        this.zzRY = zzRY;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    public String zzlF() {
        return this.zzRT;
    }
    
    public String zzlG() {
        return this.zzRU;
    }
    
    public EmailSignInConfig zzlH() {
        return this.zzRV;
    }
    
    public GoogleSignInConfig zzlI() {
        return this.zzRW;
    }
    
    public FacebookSignInConfig zzlJ() {
        return this.zzRX;
    }
    
    public String zzlK() {
        return this.zzRY;
    }
}
