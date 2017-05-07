// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd$zza;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.internal.zzdm;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class AdOverlayInfoParcel implements SafeParcelable
{
    public static final zzf CREATOR;
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final AdLauncherIntentInfoParcel zzBA;
    public final zza zzBB;
    public final zzg zzBC;
    public final zziz zzBD;
    public final zzdg zzBE;
    public final String zzBF;
    public final boolean zzBG;
    public final String zzBH;
    public final zzn zzBI;
    public final int zzBJ;
    public final zzdm zzBK;
    public final String zzBL;
    public final InterstitialAdParameterParcel zzBM;
    public final VersionInfoParcel zzqj;
    
    static {
        CREATOR = new zzf();
    }
    
    AdOverlayInfoParcel(final int versionCode, final AdLauncherIntentInfoParcel zzBA, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String zzBF, final boolean zzBG, final String zzBH, final IBinder binder5, final int orientation, final int zzBJ, final String url, final VersionInfoParcel zzqj, final IBinder binder6, final String zzBL, final InterstitialAdParameterParcel zzBM) {
        this.versionCode = versionCode;
        this.zzBA = zzBA;
        this.zzBB = zze.zzp(zzd$zza.zzbk(binder));
        this.zzBC = zze.zzp(zzd$zza.zzbk(binder2));
        this.zzBD = zze.zzp(zzd$zza.zzbk(binder3));
        this.zzBE = zze.zzp(zzd$zza.zzbk(binder4));
        this.zzBF = zzBF;
        this.zzBG = zzBG;
        this.zzBH = zzBH;
        this.zzBI = zze.zzp(zzd$zza.zzbk(binder5));
        this.orientation = orientation;
        this.zzBJ = zzBJ;
        this.url = url;
        this.zzqj = zzqj;
        this.zzBK = zze.zzp(zzd$zza.zzbk(binder6));
        this.zzBL = zzBL;
        this.zzBM = zzBM;
    }
    
    public AdOverlayInfoParcel(final zza zzBB, final zzg zzBC, final zzn zzBI, final zziz zzBD, final boolean zzBG, final int orientation, final VersionInfoParcel zzqj) {
        this.versionCode = 4;
        this.zzBA = null;
        this.zzBB = zzBB;
        this.zzBC = zzBC;
        this.zzBD = zzBD;
        this.zzBE = null;
        this.zzBF = null;
        this.zzBG = zzBG;
        this.zzBH = null;
        this.zzBI = zzBI;
        this.orientation = orientation;
        this.zzBJ = 2;
        this.url = null;
        this.zzqj = zzqj;
        this.zzBK = null;
        this.zzBL = null;
        this.zzBM = null;
    }
    
    public AdOverlayInfoParcel(final zza zzBB, final zzg zzBC, final zzdg zzBE, final zzn zzBI, final zziz zzBD, final boolean zzBG, final int orientation, final String url, final VersionInfoParcel zzqj, final zzdm zzBK) {
        this.versionCode = 4;
        this.zzBA = null;
        this.zzBB = zzBB;
        this.zzBC = zzBC;
        this.zzBD = zzBD;
        this.zzBE = zzBE;
        this.zzBF = null;
        this.zzBG = zzBG;
        this.zzBH = null;
        this.zzBI = zzBI;
        this.orientation = orientation;
        this.zzBJ = 3;
        this.url = url;
        this.zzqj = zzqj;
        this.zzBK = zzBK;
        this.zzBL = null;
        this.zzBM = null;
    }
    
    public AdOverlayInfoParcel(final zza zzBB, final zzg zzBC, final zzdg zzBE, final zzn zzBI, final zziz zzBD, final boolean zzBG, final int orientation, final String zzBH, final String zzBF, final VersionInfoParcel zzqj, final zzdm zzBK) {
        this.versionCode = 4;
        this.zzBA = null;
        this.zzBB = zzBB;
        this.zzBC = zzBC;
        this.zzBD = zzBD;
        this.zzBE = zzBE;
        this.zzBF = zzBF;
        this.zzBG = zzBG;
        this.zzBH = zzBH;
        this.zzBI = zzBI;
        this.orientation = orientation;
        this.zzBJ = 3;
        this.url = null;
        this.zzqj = zzqj;
        this.zzBK = zzBK;
        this.zzBL = null;
        this.zzBM = null;
    }
    
    public AdOverlayInfoParcel(final AdLauncherIntentInfoParcel zzBA, final zza zzBB, final zzg zzBC, final zzn zzBI, final VersionInfoParcel zzqj) {
        this.versionCode = 4;
        this.zzBA = zzBA;
        this.zzBB = zzBB;
        this.zzBC = zzBC;
        this.zzBD = null;
        this.zzBE = null;
        this.zzBF = null;
        this.zzBG = false;
        this.zzBH = null;
        this.zzBI = zzBI;
        this.orientation = -1;
        this.zzBJ = 4;
        this.url = null;
        this.zzqj = zzqj;
        this.zzBK = null;
        this.zzBL = null;
        this.zzBM = null;
    }
    
    public static void zza(final Intent intent, final AdOverlayInfoParcel adOverlayInfoParcel) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    public static AdOverlayInfoParcel zzb(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    IBinder zzeK() {
        return zze.zzy(this.zzBB).asBinder();
    }
    
    IBinder zzeL() {
        return zze.zzy(this.zzBC).asBinder();
    }
    
    IBinder zzeM() {
        return zze.zzy(this.zzBD).asBinder();
    }
    
    IBinder zzeN() {
        return zze.zzy(this.zzBE).asBinder();
    }
    
    IBinder zzeO() {
        return zze.zzy(this.zzBK).asBinder();
    }
    
    IBinder zzeP() {
        return zze.zzy(this.zzBI).asBinder();
    }
}
