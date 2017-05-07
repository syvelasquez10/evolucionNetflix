// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzl implements Parcelable$Creator<InterstitialAdParameterParcel>
{
    static void zza(final InterstitialAdParameterParcel interstitialAdParameterParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, interstitialAdParameterParcel.versionCode);
        zzb.zza(parcel, 2, interstitialAdParameterParcel.zzpt);
        zzb.zza(parcel, 3, interstitialAdParameterParcel.zzpu);
        zzb.zza(parcel, 4, interstitialAdParameterParcel.zzpv, false);
        zzb.zza(parcel, 5, interstitialAdParameterParcel.zzpw);
        zzb.zza(parcel, 6, interstitialAdParameterParcel.zzpx);
        zzb.zzI(parcel, zzaq);
    }
    
    public InterstitialAdParameterParcel zza(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        String zzp = null;
        float zzl = 0.0f;
        boolean zzc2 = false;
        boolean zzc3 = false;
        int zzg = 0;
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
                    zzc3 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzl = zza.zzl(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new InterstitialAdParameterParcel(zzg, zzc3, zzc2, zzp, zzc, zzl);
    }
    
    public InterstitialAdParameterParcel[] zzf(final int n) {
        return new InterstitialAdParameterParcel[n];
    }
}
