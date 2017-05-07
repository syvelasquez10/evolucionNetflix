// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.auth.api.signin.FacebookSignInConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.auth.api.signin.EmailSignInConfig;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SignInConfiguration implements SafeParcelable
{
    public static final Parcelable$Creator<SignInConfiguration> CREATOR;
    private static int zzTr;
    final int versionCode;
    private String zzTl;
    private final String zzTs;
    private EmailSignInConfig zzTt;
    private GoogleSignInConfig zzTu;
    private FacebookSignInConfig zzTv;
    private String zzTw;
    
    static {
        SignInConfiguration.zzTr = 31;
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    SignInConfiguration(final int versionCode, final String s, final String zzTl, final EmailSignInConfig zzTt, final GoogleSignInConfig zzTu, final FacebookSignInConfig zzTv, final String zzTw) {
        this.versionCode = versionCode;
        this.zzTs = zzx.zzcr(s);
        this.zzTl = zzTl;
        this.zzTt = zzTt;
        this.zzTu = zzTu;
        this.zzTv = zzTv;
        this.zzTw = zzTw;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final SignInConfiguration signInConfiguration = (SignInConfiguration)o;
                if (!this.zzTs.equals(signInConfiguration.zzme())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzTl)) {
                    if (!TextUtils.isEmpty((CharSequence)signInConfiguration.zzmb())) {
                        return false;
                    }
                }
                else if (!this.zzTl.equals(signInConfiguration.zzmb())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzTw)) {
                    if (!TextUtils.isEmpty((CharSequence)signInConfiguration.zzmi())) {
                        return false;
                    }
                }
                else if (!this.zzTw.equals(signInConfiguration.zzmi())) {
                    return false;
                }
                if (this.zzTt == null) {
                    if (signInConfiguration.zzmf() != null) {
                        return false;
                    }
                }
                else if (!this.zzTt.equals(signInConfiguration.zzmf())) {
                    return false;
                }
                if (this.zzTv == null) {
                    if (signInConfiguration.zzmh() != null) {
                        return false;
                    }
                }
                else if (!this.zzTv.equals(signInConfiguration.zzmh())) {
                    return false;
                }
                if (this.zzTu == null) {
                    if (signInConfiguration.zzmg() != null) {
                        return false;
                    }
                }
                else if (!this.zzTu.equals(signInConfiguration.zzmg())) {
                    return false;
                }
            }
            catch (ClassCastException ex) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return new zzc().zzl(this.zzTs).zzl(this.zzTl).zzl(this.zzTw).zzl(this.zzTt).zzl(this.zzTu).zzl(this.zzTv).zzmd();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public String zzmb() {
        return this.zzTl;
    }
    
    public String zzme() {
        return this.zzTs;
    }
    
    public EmailSignInConfig zzmf() {
        return this.zzTt;
    }
    
    public GoogleSignInConfig zzmg() {
        return this.zzTu;
    }
    
    public FacebookSignInConfig zzmh() {
        return this.zzTv;
    }
    
    public String zzmi() {
        return this.zzTw;
    }
}
