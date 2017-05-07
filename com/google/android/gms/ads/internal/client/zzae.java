// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzae implements Parcelable$Creator<SearchAdRequestParcel>
{
    static void zza(final SearchAdRequestParcel searchAdRequestParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, searchAdRequestParcel.versionCode);
        zzb.zzc(parcel, 2, searchAdRequestParcel.zztP);
        zzb.zzc(parcel, 3, searchAdRequestParcel.backgroundColor);
        zzb.zzc(parcel, 4, searchAdRequestParcel.zztQ);
        zzb.zzc(parcel, 5, searchAdRequestParcel.zztR);
        zzb.zzc(parcel, 6, searchAdRequestParcel.zztS);
        zzb.zzc(parcel, 7, searchAdRequestParcel.zztT);
        zzb.zzc(parcel, 8, searchAdRequestParcel.zztU);
        zzb.zzc(parcel, 9, searchAdRequestParcel.zztV);
        zzb.zza(parcel, 10, searchAdRequestParcel.zztW, false);
        zzb.zzc(parcel, 11, searchAdRequestParcel.zztX);
        zzb.zza(parcel, 12, searchAdRequestParcel.zztY, false);
        zzb.zzc(parcel, 13, searchAdRequestParcel.zztZ);
        zzb.zzc(parcel, 14, searchAdRequestParcel.zzua);
        zzb.zza(parcel, 15, searchAdRequestParcel.zzub, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public SearchAdRequestParcel zzd(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        int zzg4 = 0;
        int zzg5 = 0;
        int zzg6 = 0;
        int zzg7 = 0;
        int zzg8 = 0;
        int zzg9 = 0;
        String zzp = null;
        int zzg10 = 0;
        String zzp2 = null;
        int zzg11 = 0;
        int zzg12 = 0;
        String zzp3 = null;
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
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg4 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzg5 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzg6 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzg7 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzg8 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 9: {
                    zzg9 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 10: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 11: {
                    zzg10 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 12: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 13: {
                    zzg11 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 14: {
                    zzg12 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 15: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new SearchAdRequestParcel(zzg, zzg2, zzg3, zzg4, zzg5, zzg6, zzg7, zzg8, zzg9, zzp, zzg10, zzp2, zzg11, zzg12, zzp3);
    }
    
    public SearchAdRequestParcel[] zzn(final int n) {
        return new SearchAdRequestParcel[n];
    }
}
