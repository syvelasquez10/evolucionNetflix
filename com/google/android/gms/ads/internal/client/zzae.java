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
    static void zza(final SearchAdRequestParcel searchAdRequestParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, searchAdRequestParcel.versionCode);
        zzb.zzc(parcel, 2, searchAdRequestParcel.zztA);
        zzb.zzc(parcel, 3, searchAdRequestParcel.backgroundColor);
        zzb.zzc(parcel, 4, searchAdRequestParcel.zztB);
        zzb.zzc(parcel, 5, searchAdRequestParcel.zztC);
        zzb.zzc(parcel, 6, searchAdRequestParcel.zztD);
        zzb.zzc(parcel, 7, searchAdRequestParcel.zztE);
        zzb.zzc(parcel, 8, searchAdRequestParcel.zztF);
        zzb.zzc(parcel, 9, searchAdRequestParcel.zztG);
        zzb.zza(parcel, 10, searchAdRequestParcel.zztH, false);
        zzb.zzc(parcel, 11, searchAdRequestParcel.zztI);
        zzb.zza(parcel, 12, searchAdRequestParcel.zztJ, false);
        zzb.zzc(parcel, 13, searchAdRequestParcel.zztK);
        zzb.zzc(parcel, 14, searchAdRequestParcel.zztL);
        zzb.zza(parcel, 15, searchAdRequestParcel.zztM, false);
        zzb.zzH(parcel, zzak);
    }
    
    public SearchAdRequestParcel zze(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        int zzg4 = 0;
        int zzg5 = 0;
        int zzg6 = 0;
        int zzg7 = 0;
        int zzg8 = 0;
        int zzg9 = 0;
        String zzo = null;
        int zzg10 = 0;
        String zzo2 = null;
        int zzg11 = 0;
        int zzg12 = 0;
        String zzo3 = null;
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
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg4 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzg5 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzg6 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzg7 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzg8 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 9: {
                    zzg9 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 10: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 11: {
                    zzg10 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 12: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 13: {
                    zzg11 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 14: {
                    zzg12 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 15: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new SearchAdRequestParcel(zzg, zzg2, zzg3, zzg4, zzg5, zzg6, zzg7, zzg8, zzg9, zzo, zzg10, zzo2, zzg11, zzg12, zzo3);
    }
    
    public SearchAdRequestParcel[] zzo(final int n) {
        return new SearchAdRequestParcel[n];
    }
}
