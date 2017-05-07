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

public class zzf implements Parcelable$Creator<AdRequestParcel>
{
    static void zza(final AdRequestParcel adRequestParcel, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zzsq);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zzsr);
        zzb.zzb(parcel, 5, adRequestParcel.zzss, false);
        zzb.zza(parcel, 6, adRequestParcel.zzst);
        zzb.zzc(parcel, 7, adRequestParcel.zzsu);
        zzb.zza(parcel, 8, adRequestParcel.zzsv);
        zzb.zza(parcel, 9, adRequestParcel.zzsw, false);
        zzb.zza(parcel, 10, (Parcelable)adRequestParcel.zzsx, n, false);
        zzb.zza(parcel, 11, (Parcelable)adRequestParcel.zzsy, n, false);
        zzb.zza(parcel, 12, adRequestParcel.zzsz, false);
        zzb.zza(parcel, 13, adRequestParcel.zzsA, false);
        zzb.zza(parcel, 14, adRequestParcel.zzsB, false);
        zzb.zzb(parcel, 15, adRequestParcel.zzsC, false);
        zzb.zza(parcel, 17, adRequestParcel.zzsE, false);
        zzb.zza(parcel, 16, adRequestParcel.zzsD, false);
        zzb.zzH(parcel, zzak);
    }
    
    public AdRequestParcel zzb(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        long zzi = 0L;
        Bundle zzq = null;
        int zzg2 = 0;
        List<String> zzC = null;
        boolean zzc = false;
        int zzg3 = 0;
        boolean zzc2 = false;
        String zzo = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String zzo2 = null;
        Bundle zzq2 = null;
        Bundle zzq3 = null;
        List<String> zzC2 = null;
        String zzo3 = null;
        String zzo4 = null;
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
                    zzi = zza.zzi(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzC = zza.zzC(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzc2 = zza.zzc(parcel, zzai);
                    continue;
                }
                case 9: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 10: {
                    searchAdRequestParcel = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<SearchAdRequestParcel>)SearchAdRequestParcel.CREATOR);
                    continue;
                }
                case 11: {
                    location = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
                case 12: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 13: {
                    zzq2 = zza.zzq(parcel, zzai);
                    continue;
                }
                case 14: {
                    zzq3 = zza.zzq(parcel, zzai);
                    continue;
                }
                case 15: {
                    zzC2 = zza.zzC(parcel, zzai);
                    continue;
                }
                case 17: {
                    zzo4 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 16: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new AdRequestParcel(zzg, zzi, zzq, zzg2, zzC, zzc, zzg3, zzc2, zzo, searchAdRequestParcel, location, zzo2, zzq2, zzq3, zzC2, zzo3, zzo4);
    }
    
    public AdRequestParcel[] zzk(final int n) {
        return new AdRequestParcel[n];
    }
}
