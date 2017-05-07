// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzf implements Parcelable$Creator<AdOverlayInfoParcel>
{
    static void zza(final AdOverlayInfoParcel adOverlayInfoParcel, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, adOverlayInfoParcel.versionCode);
        zzb.zza(parcel, 2, (Parcelable)adOverlayInfoParcel.zzAO, n, false);
        zzb.zza(parcel, 3, adOverlayInfoParcel.zzeE(), false);
        zzb.zza(parcel, 4, adOverlayInfoParcel.zzeF(), false);
        zzb.zza(parcel, 5, adOverlayInfoParcel.zzeG(), false);
        zzb.zza(parcel, 6, adOverlayInfoParcel.zzeH(), false);
        zzb.zza(parcel, 7, adOverlayInfoParcel.zzAT, false);
        zzb.zza(parcel, 8, adOverlayInfoParcel.zzAU);
        zzb.zza(parcel, 9, adOverlayInfoParcel.zzAV, false);
        zzb.zza(parcel, 10, adOverlayInfoParcel.zzeJ(), false);
        zzb.zzc(parcel, 11, adOverlayInfoParcel.orientation);
        zzb.zzc(parcel, 12, adOverlayInfoParcel.zzAX);
        zzb.zza(parcel, 13, adOverlayInfoParcel.url, false);
        zzb.zza(parcel, 14, (Parcelable)adOverlayInfoParcel.zzqb, n, false);
        zzb.zza(parcel, 15, adOverlayInfoParcel.zzeI(), false);
        zzb.zza(parcel, 17, (Parcelable)adOverlayInfoParcel.zzBa, n, false);
        zzb.zza(parcel, 16, adOverlayInfoParcel.zzAZ, false);
        zzb.zzH(parcel, zzak);
    }
    
    public AdOverlayInfoParcel zzh(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        AdLauncherIntentInfoParcel adLauncherIntentInfoParcel = null;
        IBinder zzp = null;
        IBinder zzp2 = null;
        IBinder zzp3 = null;
        IBinder zzp4 = null;
        String zzo = null;
        boolean zzc = false;
        String zzo2 = null;
        IBinder zzp5 = null;
        int zzg2 = 0;
        int zzg3 = 0;
        String zzo3 = null;
        VersionInfoParcel versionInfoParcel = null;
        IBinder zzp6 = null;
        String zzo4 = null;
        InterstitialAdParameterParcel interstitialAdParameterParcel = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    adLauncherIntentInfoParcel = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<AdLauncherIntentInfoParcel>)AdLauncherIntentInfoParcel.CREATOR);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzp2 = zza.zzp(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzp3 = zza.zzp(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzp4 = zza.zzp(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 9: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 10: {
                    zzp5 = zza.zzp(parcel, zzai);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 12: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 13: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 14: {
                    versionInfoParcel = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<VersionInfoParcel>)VersionInfoParcel.CREATOR);
                    continue;
                }
                case 15: {
                    zzp6 = zza.zzp(parcel, zzai);
                    continue;
                }
                case 17: {
                    interstitialAdParameterParcel = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<InterstitialAdParameterParcel>)InterstitialAdParameterParcel.CREATOR);
                    continue;
                }
                case 16: {
                    zzo4 = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new AdOverlayInfoParcel(zzg, adLauncherIntentInfoParcel, zzp, zzp2, zzp3, zzp4, zzo, zzc, zzo2, zzp5, zzg2, zzg3, zzo3, versionInfoParcel, zzp6, zzo4, interstitialAdParameterParcel);
    }
    
    public AdOverlayInfoParcel[] zzw(final int n) {
        return new AdOverlayInfoParcel[n];
    }
}
