// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<RewardItemParcel>
{
    static void zza(final RewardItemParcel rewardItemParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, rewardItemParcel.versionCode);
        zzb.zza(parcel, 2, rewardItemParcel.type, false);
        zzb.zzc(parcel, 3, rewardItemParcel.zzHv);
        zzb.zzI(parcel, zzaq);
    }
    
    public RewardItemParcel[] zzL(final int n) {
        return new RewardItemParcel[n];
    }
    
    public RewardItemParcel zzo(final Parcel parcel) {
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        String zzp = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new RewardItemParcel(zzg2, zzp, zzg);
    }
}
