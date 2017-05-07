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
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, adOverlayInfoParcel.versionCode);
        zzb.zza(parcel, 2, (Parcelable)adOverlayInfoParcel.zzBA, n, false);
        zzb.zza(parcel, 3, adOverlayInfoParcel.zzeK(), false);
        zzb.zza(parcel, 4, adOverlayInfoParcel.zzeL(), false);
        zzb.zza(parcel, 5, adOverlayInfoParcel.zzeM(), false);
        zzb.zza(parcel, 6, adOverlayInfoParcel.zzeN(), false);
        zzb.zza(parcel, 7, adOverlayInfoParcel.zzBF, false);
        zzb.zza(parcel, 8, adOverlayInfoParcel.zzBG);
        zzb.zza(parcel, 9, adOverlayInfoParcel.zzBH, false);
        zzb.zza(parcel, 10, adOverlayInfoParcel.zzeP(), false);
        zzb.zzc(parcel, 11, adOverlayInfoParcel.orientation);
        zzb.zzc(parcel, 12, adOverlayInfoParcel.zzBJ);
        zzb.zza(parcel, 13, adOverlayInfoParcel.url, false);
        zzb.zza(parcel, 14, (Parcelable)adOverlayInfoParcel.zzqj, n, false);
        zzb.zza(parcel, 15, adOverlayInfoParcel.zzeO(), false);
        zzb.zza(parcel, 17, (Parcelable)adOverlayInfoParcel.zzBM, n, false);
        zzb.zza(parcel, 16, adOverlayInfoParcel.zzBL, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public AdOverlayInfoParcel zzg(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        AdLauncherIntentInfoParcel adLauncherIntentInfoParcel = null;
        IBinder zzq = null;
        IBinder zzq2 = null;
        IBinder zzq3 = null;
        IBinder zzq4 = null;
        String zzp = null;
        boolean zzc = false;
        String zzp2 = null;
        IBinder zzq5 = null;
        int zzg2 = 0;
        int zzg3 = 0;
        String zzp3 = null;
        VersionInfoParcel versionInfoParcel = null;
        IBinder zzq6 = null;
        String zzp4 = null;
        InterstitialAdParameterParcel interstitialAdParameterParcel = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    adLauncherIntentInfoParcel = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<AdLauncherIntentInfoParcel>)AdLauncherIntentInfoParcel.CREATOR);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzq2 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzq3 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzq4 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 9: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 10: {
                    zzq5 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 12: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 13: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 14: {
                    versionInfoParcel = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<VersionInfoParcel>)VersionInfoParcel.CREATOR);
                    continue;
                }
                case 15: {
                    zzq6 = zza.zzq(parcel, zzao);
                    continue;
                }
                case 17: {
                    interstitialAdParameterParcel = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<InterstitialAdParameterParcel>)InterstitialAdParameterParcel.CREATOR);
                    continue;
                }
                case 16: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new AdOverlayInfoParcel(zzg, adLauncherIntentInfoParcel, zzq, zzq2, zzq3, zzq4, zzp, zzc, zzp2, zzq5, zzg2, zzg3, zzp3, versionInfoParcel, zzq6, zzp4, interstitialAdParameterParcel);
    }
    
    public AdOverlayInfoParcel[] zzw(final int n) {
        return new AdOverlayInfoParcel[n];
    }
}
