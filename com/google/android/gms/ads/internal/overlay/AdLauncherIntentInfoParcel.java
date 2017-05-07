// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class AdLauncherIntentInfoParcel implements SafeParcelable
{
    public static final zzb CREATOR;
    public final String intentAction;
    public final String mimeType;
    public final String packageName;
    public final String url;
    public final int versionCode;
    public final String zzAa;
    public final String zzzY;
    public final String zzzZ;
    
    static {
        CREATOR = new zzb();
    }
    
    public AdLauncherIntentInfoParcel(final int versionCode, final String intentAction, final String url, final String mimeType, final String packageName, final String zzzY, final String zzzZ, final String zzAa) {
        this.versionCode = versionCode;
        this.intentAction = intentAction;
        this.url = url;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.zzzY = zzzY;
        this.zzzZ = zzzZ;
        this.zzAa = zzAa;
    }
    
    public AdLauncherIntentInfoParcel(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(1, s, s2, s3, s4, s5, s6, s7);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
