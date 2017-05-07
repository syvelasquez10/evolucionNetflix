// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<NativeAdOptionsParcel>
{
    static void zza(final NativeAdOptionsParcel nativeAdOptionsParcel, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, nativeAdOptionsParcel.versionCode);
        zzb.zza(parcel, 2, nativeAdOptionsParcel.zzwR);
        zzb.zzc(parcel, 3, nativeAdOptionsParcel.zzwS);
        zzb.zza(parcel, 4, nativeAdOptionsParcel.zzwT);
        zzb.zzI(parcel, zzaq);
    }
    
    public NativeAdOptionsParcel zze(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        boolean zzc2 = false;
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
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new NativeAdOptionsParcel(zzg2, zzc2, zzg, zzc);
    }
    
    public NativeAdOptionsParcel[] zzo(final int n) {
        return new NativeAdOptionsParcel[n];
    }
}
