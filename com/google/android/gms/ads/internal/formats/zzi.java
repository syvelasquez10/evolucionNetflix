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
    static void zza(final NativeAdOptionsParcel nativeAdOptionsParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, nativeAdOptionsParcel.versionCode);
        zzb.zza(parcel, 2, nativeAdOptionsParcel.zzwn);
        zzb.zzc(parcel, 3, nativeAdOptionsParcel.zzwo);
        zzb.zza(parcel, 4, nativeAdOptionsParcel.zzwp);
        zzb.zzH(parcel, zzak);
    }
    
    public NativeAdOptionsParcel zzf(final Parcel parcel) {
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        boolean zzc2 = false;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzc2 = zza.zzc(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new NativeAdOptionsParcel(zzg2, zzc2, zzg, zzc);
    }
    
    public NativeAdOptionsParcel[] zzp(final int n) {
        return new NativeAdOptionsParcel[n];
    }
}
