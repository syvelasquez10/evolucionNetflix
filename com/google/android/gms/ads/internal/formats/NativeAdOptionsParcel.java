// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public class NativeAdOptionsParcel implements SafeParcelable
{
    public static final zzi CREATOR;
    public final int versionCode;
    public final boolean zzwn;
    public final int zzwo;
    public final boolean zzwp;
    
    static {
        CREATOR = new zzi();
    }
    
    public NativeAdOptionsParcel(final int versionCode, final boolean zzwn, final int zzwo, final boolean zzwp) {
        this.versionCode = versionCode;
        this.zzwn = zzwn;
        this.zzwo = zzwo;
        this.zzwp = zzwp;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
}
