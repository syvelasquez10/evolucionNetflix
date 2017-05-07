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
    static void zza(final InterstitialAdParameterParcel interstitialAdParameterParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, interstitialAdParameterParcel.versionCode);
        zzb.zza(parcel, 2, interstitialAdParameterParcel.zzpk);
        zzb.zza(parcel, 3, interstitialAdParameterParcel.zzpl);
        zzb.zzH(parcel, zzak);
    }
    
    public InterstitialAdParameterParcel zza(final Parcel parcel) {
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        boolean zzc2 = false;
        int zzg = 0;
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
                    zzc2 = zza.zzc(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new InterstitialAdParameterParcel(zzg, zzc2, zzc);
    }
    
    public InterstitialAdParameterParcel[] zzf(final int n) {
        return new InterstitialAdParameterParcel[n];
    }
}
