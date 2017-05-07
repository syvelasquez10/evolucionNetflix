// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzx;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleSignInAccount implements SafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInAccount> CREATOR;
    final int versionCode;
    private String zzRn;
    private String zzaNX;
    private Uri zzaNY;
    private String zzahh;
    private String zzwj;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    GoogleSignInAccount(final int versionCode, final String s, final String zzRn, final String zzaNX, final String zzahh, final Uri zzaNY) {
        this.versionCode = versionCode;
        this.zzwj = zzx.zzcs(s);
        this.zzRn = zzRn;
        this.zzaNX = zzaNX;
        this.zzahh = zzahh;
        this.zzaNY = zzaNY;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getDisplayName() {
        return this.zzahh;
    }
    
    public String getEmail() {
        return this.zzaNX;
    }
    
    public String getId() {
        return this.zzwj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzlv() {
        return this.zzRn;
    }
    
    public Uri zzzm() {
        return this.zzaNY;
    }
}
