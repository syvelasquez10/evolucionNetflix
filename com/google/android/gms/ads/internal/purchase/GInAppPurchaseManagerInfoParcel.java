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
import com.google.android.gms.internal.zzfr;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class GInAppPurchaseManagerInfoParcel implements SafeParcelable
{
    public static final zza CREATOR;
    public final int versionCode;
    public final zzfr zzCw;
    public final Context zzCx;
    public final zzj zzCy;
    public final zzk zzqE;
    
    static {
        CREATOR = new zza();
    }
    
    GInAppPurchaseManagerInfoParcel(final int versionCode, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4) {
        this.versionCode = versionCode;
        this.zzqE = zze.zzp(zzd$zza.zzbk(binder));
        this.zzCw = zze.zzp(zzd$zza.zzbk(binder2));
        this.zzCx = zze.zzp(zzd$zza.zzbk(binder3));
        this.zzCy = zze.zzp(zzd$zza.zzbk(binder4));
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
    
    IBinder zzfi() {
        return zze.zzy(this.zzCy).asBinder();
    }
    
    IBinder zzfj() {
        return zze.zzy(this.zzqE).asBinder();
    }
    
    IBinder zzfk() {
        return zze.zzy(this.zzCw).asBinder();
    }
    
    IBinder zzfl() {
        return zze.zzy(this.zzCx).asBinder();
    }
}
