// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<AdSizeParcel>
{
    static void zza(final AdSizeParcel adSizeParcel, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, adSizeParcel.versionCode);
        zzb.zza(parcel, 2, adSizeParcel.zzte, false);
        zzb.zzc(parcel, 3, adSizeParcel.height);
        zzb.zzc(parcel, 4, adSizeParcel.heightPixels);
        zzb.zza(parcel, 5, adSizeParcel.zztf);
        zzb.zzc(parcel, 6, adSizeParcel.width);
        zzb.zzc(parcel, 7, adSizeParcel.widthPixels);
        zzb.zza(parcel, 8, adSizeParcel.zztg, n, false);
        zzb.zza(parcel, 9, adSizeParcel.zzth);
        zzb.zza(parcel, 10, adSizeParcel.zzti);
        zzb.zzI(parcel, zzaq);
    }
    
    public AdSizeParcel zzc(final Parcel parcel) {
        AdSizeParcel[] array = null;
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        boolean zzc2 = false;
        int zzg = 0;
        int zzg2 = 0;
        boolean zzc3 = false;
        int zzg3 = 0;
        int zzg4 = 0;
        String zzp = null;
        int zzg5 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg5 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg4 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc3 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 8: {
                    array = zza.zzb(parcel, zzao, (android.os.Parcelable$Creator<AdSizeParcel>)AdSizeParcel.CREATOR);
                    continue;
                }
                case 9: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 10: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new AdSizeParcel(zzg5, zzp, zzg4, zzg3, zzc3, zzg2, zzg, array, zzc2, zzc);
    }
    
    public AdSizeParcel[] zzl(final int n) {
        return new AdSizeParcel[n];
    }
}
