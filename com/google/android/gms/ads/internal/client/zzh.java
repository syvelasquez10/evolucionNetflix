// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<AdSizeParcel>
{
    static void zza(final AdSizeParcel adSizeParcel, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, adSizeParcel.versionCode);
        zzb.zza(parcel, 2, adSizeParcel.zzsG, false);
        zzb.zzc(parcel, 3, adSizeParcel.height);
        zzb.zzc(parcel, 4, adSizeParcel.heightPixels);
        zzb.zza(parcel, 5, adSizeParcel.zzsH);
        zzb.zzc(parcel, 6, adSizeParcel.width);
        zzb.zzc(parcel, 7, adSizeParcel.widthPixels);
        zzb.zza(parcel, 8, adSizeParcel.zzsI, n, false);
        zzb.zza(parcel, 9, adSizeParcel.zzsJ);
        zzb.zzH(parcel, zzak);
    }
    
    public AdSizeParcel zzc(final Parcel parcel) {
        AdSizeParcel[] array = null;
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        int zzg2 = 0;
        boolean zzc2 = false;
        int zzg3 = 0;
        int zzg4 = 0;
        String zzo = null;
        int zzg5 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg5 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzg4 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzc2 = zza.zzc(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 8: {
                    array = zza.zzb(parcel, zzai, (android.os.Parcelable$Creator<AdSizeParcel>)AdSizeParcel.CREATOR);
                    continue;
                }
                case 9: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new AdSizeParcel(zzg5, zzo, zzg4, zzg3, zzc2, zzg2, zzg, array, zzc);
    }
    
    public AdSizeParcel[] zzl(final int n) {
        return new AdSizeParcel[n];
    }
}
