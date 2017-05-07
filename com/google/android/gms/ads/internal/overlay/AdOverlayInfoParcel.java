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
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdd;
import com.google.android.gms.internal.zzip;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class AdOverlayInfoParcel implements SafeParcelable
{
    public static final zzf CREATOR;
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final AdLauncherIntentInfoParcel zzAO;
    public final zza zzAP;
    public final zzg zzAQ;
    public final zzip zzAR;
    public final zzdd zzAS;
    public final String zzAT;
    public final boolean zzAU;
    public final String zzAV;
    public final zzn zzAW;
    public final int zzAX;
    public final zzdi zzAY;
    public final String zzAZ;
    public final InterstitialAdParameterParcel zzBa;
    public final VersionInfoParcel zzqb;
    
    static {
        CREATOR = new zzf();
    }
    
    AdOverlayInfoParcel(final int versionCode, final AdLauncherIntentInfoParcel zzAO, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String zzAT, final boolean zzAU, final String zzAV, final IBinder binder5, final int orientation, final int zzAX, final String url, final VersionInfoParcel zzqb, final IBinder binder6, final String zzAZ, final InterstitialAdParameterParcel zzBa) {
        this.versionCode = versionCode;
        this.zzAO = zzAO;
        this.zzAP = zze.zzp(zzd$zza.zzbk(binder));
        this.zzAQ = zze.zzp(zzd$zza.zzbk(binder2));
        this.zzAR = zze.zzp(zzd$zza.zzbk(binder3));
        this.zzAS = zze.zzp(zzd$zza.zzbk(binder4));
        this.zzAT = zzAT;
        this.zzAU = zzAU;
        this.zzAV = zzAV;
        this.zzAW = zze.zzp(zzd$zza.zzbk(binder5));
        this.orientation = orientation;
        this.zzAX = zzAX;
        this.url = url;
        this.zzqb = zzqb;
        this.zzAY = zze.zzp(zzd$zza.zzbk(binder6));
        this.zzAZ = zzAZ;
        this.zzBa = zzBa;
    }
    
    public AdOverlayInfoParcel(final zza zzAP, final zzg zzAQ, final zzn zzAW, final zzip zzAR, final boolean zzAU, final int orientation, final VersionInfoParcel zzqb) {
        this.versionCode = 4;
        this.zzAO = null;
        this.zzAP = zzAP;
        this.zzAQ = zzAQ;
        this.zzAR = zzAR;
        this.zzAS = null;
        this.zzAT = null;
        this.zzAU = zzAU;
        this.zzAV = null;
        this.zzAW = zzAW;
        this.orientation = orientation;
        this.zzAX = 2;
        this.url = null;
        this.zzqb = zzqb;
        this.zzAY = null;
        this.zzAZ = null;
        this.zzBa = null;
    }
    
    public AdOverlayInfoParcel(final zza zzAP, final zzg zzAQ, final zzdd zzAS, final zzn zzAW, final zzip zzAR, final boolean zzAU, final int orientation, final String url, final VersionInfoParcel zzqb, final zzdi zzAY) {
        this.versionCode = 4;
        this.zzAO = null;
        this.zzAP = zzAP;
        this.zzAQ = zzAQ;
        this.zzAR = zzAR;
        this.zzAS = zzAS;
        this.zzAT = null;
        this.zzAU = zzAU;
        this.zzAV = null;
        this.zzAW = zzAW;
        this.orientation = orientation;
        this.zzAX = 3;
        this.url = url;
        this.zzqb = zzqb;
        this.zzAY = zzAY;
        this.zzAZ = null;
        this.zzBa = null;
    }
    
    public AdOverlayInfoParcel(final zza zzAP, final zzg zzAQ, final zzdd zzAS, final zzn zzAW, final zzip zzAR, final boolean zzAU, final int orientation, final String zzAV, final String zzAT, final VersionInfoParcel zzqb, final zzdi zzAY) {
        this.versionCode = 4;
        this.zzAO = null;
        this.zzAP = zzAP;
        this.zzAQ = zzAQ;
        this.zzAR = zzAR;
        this.zzAS = zzAS;
        this.zzAT = zzAT;
        this.zzAU = zzAU;
        this.zzAV = zzAV;
        this.zzAW = zzAW;
        this.orientation = orientation;
        this.zzAX = 3;
        this.url = null;
        this.zzqb = zzqb;
        this.zzAY = zzAY;
        this.zzAZ = null;
        this.zzBa = null;
    }
    
    public AdOverlayInfoParcel(final AdLauncherIntentInfoParcel zzAO, final zza zzAP, final zzg zzAQ, final zzn zzAW, final VersionInfoParcel zzqb) {
        this.versionCode = 4;
        this.zzAO = zzAO;
        this.zzAP = zzAP;
        this.zzAQ = zzAQ;
        this.zzAR = null;
        this.zzAS = null;
        this.zzAT = null;
        this.zzAU = false;
        this.zzAV = null;
        this.zzAW = zzAW;
        this.orientation = -1;
        this.zzAX = 4;
        this.url = null;
        this.zzqb = zzqb;
        this.zzAY = null;
        this.zzAZ = null;
        this.zzBa = null;
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
    
    IBinder zzeE() {
        return zze.zzx(this.zzAP).asBinder();
    }
    
    IBinder zzeF() {
        return zze.zzx(this.zzAQ).asBinder();
    }
    
    IBinder zzeG() {
        return zze.zzx(this.zzAR).asBinder();
    }
    
    IBinder zzeH() {
        return zze.zzx(this.zzAS).asBinder();
    }
    
    IBinder zzeI() {
        return zze.zzx(this.zzAY).asBinder();
    }
    
    IBinder zzeJ() {
        return zze.zzx(this.zzAW).asBinder();
    }
}
