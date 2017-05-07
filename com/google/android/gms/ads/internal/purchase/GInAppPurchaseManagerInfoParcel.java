// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.Parcel;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd$zza;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.internal.zzfl;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class GInAppPurchaseManagerInfoParcel implements SafeParcelable
{
    public static final zza CREATOR;
    public final int versionCode;
    public final zzfl zzBJ;
    public final Context zzBK;
    public final zzj zzBL;
    public final zzk zzqw;
    
    static {
        CREATOR = new zza();
    }
    
    GInAppPurchaseManagerInfoParcel(final int versionCode, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4) {
        this.versionCode = versionCode;
        this.zzqw = zze.zzp(zzd$zza.zzbk(binder));
        this.zzBJ = zze.zzp(zzd$zza.zzbk(binder2));
        this.zzBK = zze.zzp(zzd$zza.zzbk(binder3));
        this.zzBL = zze.zzp(zzd$zza.zzbk(binder4));
    }
    
    public static GInAppPurchaseManagerInfoParcel zzc(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(GInAppPurchaseManagerInfoParcel.class.getClassLoader());
            return (GInAppPurchaseManagerInfoParcel)bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    IBinder zzfc() {
        return zze.zzx(this.zzBL).asBinder();
    }
    
    IBinder zzfd() {
        return zze.zzx(this.zzqw).asBinder();
    }
    
    IBinder zzfe() {
        return zze.zzx(this.zzBJ).asBinder();
    }
    
    IBinder zzff() {
        return zze.zzx(this.zzBK).asBinder();
    }
}
