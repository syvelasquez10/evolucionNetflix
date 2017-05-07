// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.os.Parcel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public class NativeAdOptionsParcel implements SafeParcelable
{
    public static final zzi CREATOR;
    public final int versionCode;
    public final boolean zzwR;
    public final int zzwS;
    public final boolean zzwT;
    
    static {
        CREATOR = new zzi();
    }
    
    public NativeAdOptionsParcel(final int versionCode, final boolean zzwR, final int zzwS, final boolean zzwT) {
        this.versionCode = versionCode;
        this.zzwR = zzwR;
        this.zzwS = zzwS;
        this.zzwT = zzwT;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
}
