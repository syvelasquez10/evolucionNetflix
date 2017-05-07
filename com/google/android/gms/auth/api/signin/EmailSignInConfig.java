// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.internal.zzc;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.common.internal.zzx;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class EmailSignInConfig implements SafeParcelable
{
    public static final Parcelable$Creator<EmailSignInConfig> CREATOR;
    final int versionCode;
    private final Uri zzSU;
    private String zzSV;
    private Uri zzSW;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    EmailSignInConfig(final int versionCode, final Uri zzSU, final String zzSV, final Uri zzSW) {
        zzx.zzb(zzSU, "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzh(zzSU.toString(), "Server widget url cannot be null in order to use email/password sign in.");
        zzx.zzb(Patterns.WEB_URL.matcher(zzSU.toString()).matches(), "Invalid server widget url");
        this.versionCode = versionCode;
        this.zzSU = zzSU;
        this.zzSV = zzSV;
        this.zzSW = zzSW;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null) {
            try {
                final EmailSignInConfig emailSignInConfig = (EmailSignInConfig)o;
                if (!this.zzSU.equals((Object)emailSignInConfig.zzlO())) {
                    return false;
                }
                if (this.zzSW == null) {
                    if (emailSignInConfig.zzlP() != null) {
                        return false;
                    }
                }
                else if (!this.zzSW.equals((Object)emailSignInConfig.zzlP())) {
                    return false;
                }
                if (TextUtils.isEmpty((CharSequence)this.zzSV)) {
                    if (!TextUtils.isEmpty((CharSequence)emailSignInConfig.zzlQ())) {
                        return false;
                    }
                }
                else if (!this.zzSV.equals(emailSignInConfig.zzlQ())) {
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
        return new zzc().zzl(this.zzSU).zzl(this.zzSW).zzl(this.zzSV).zzmd();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public Uri zzlO() {
        return this.zzSU;
    }
    
    public Uri zzlP() {
        return this.zzSW;
    }
    
    public String zzlQ() {
        return this.zzSV;
    }
}
