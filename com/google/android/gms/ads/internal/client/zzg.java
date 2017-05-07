// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<AdRequestParcel>
{
    static void zza(final AdRequestParcel adRequestParcel, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zzsB);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zzsC);
        zzb.zzb(parcel, 5, adRequestParcel.zzsD, false);
        zzb.zza(parcel, 6, adRequestParcel.zzsE);
        zzb.zzc(parcel, 7, adRequestParcel.zzsF);
        zzb.zza(parcel, 8, adRequestParcel.zzsG);
        zzb.zza(parcel, 9, adRequestParcel.zzsH, false);
        zzb.zza(parcel, 10, (Parcelable)adRequestParcel.zzsI, n, false);
        zzb.zza(parcel, 11, (Parcelable)adRequestParcel.zzsJ, n, false);
        zzb.zza(parcel, 12, adRequestParcel.zzsK, false);
        zzb.zza(parcel, 13, adRequestParcel.zzsL, false);
        zzb.zza(parcel, 14, adRequestParcel.zzsM, false);
        zzb.zzb(parcel, 15, adRequestParcel.zzsN, false);
        zzb.zza(parcel, 17, adRequestParcel.zzsP, false);
        zzb.zza(parcel, 16, adRequestParcel.zzsO, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public AdRequestParcel zzb(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        long zzi = 0L;
        Bundle zzr = null;
        int zzg2 = 0;
        List<String> zzD = null;
        boolean zzc = false;
        int zzg3 = 0;
        boolean zzc2 = false;
        String zzp = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String zzp2 = null;
        Bundle zzr2 = null;
        Bundle zzr3 = null;
        List<String> zzD2 = null;
        String zzp3 = null;
        String zzp4 = null;
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
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzr = zza.zzr(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzD = zza.zzD(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 9: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 10: {
                    searchAdRequestParcel = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<SearchAdRequestParcel>)SearchAdRequestParcel.CREATOR);
                    continue;
                }
                case 11: {
                    location = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
                case 12: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 13: {
                    zzr2 = zza.zzr(parcel, zzao);
                    continue;
                }
                case 14: {
                    zzr3 = zza.zzr(parcel, zzao);
                    continue;
                }
                case 15: {
                    zzD2 = zza.zzD(parcel, zzao);
                    continue;
                }
                case 17: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 16: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new AdRequestParcel(zzg, zzi, zzr, zzg2, zzD, zzc, zzg3, zzc2, zzp, searchAdRequestParcel, location, zzp2, zzr2, zzr3, zzD2, zzp3, zzp4);
    }
    
    public AdRequestParcel[] zzk(final int n) {
        return new AdRequestParcel[n];
    }
}
