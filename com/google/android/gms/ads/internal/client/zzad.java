// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzad implements Parcelable$Creator<MobileAdsSettingsParcel>
{
    static void zza(final MobileAdsSettingsParcel mobileAdsSettingsParcel, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, mobileAdsSettingsParcel.versionCode);
        zzb.zza(parcel, 2, mobileAdsSettingsParcel.zzty);
        zzb.zza(parcel, 3, mobileAdsSettingsParcel.zztz, false);
        zzb.zzH(parcel, zzak);
    }
    
    public MobileAdsSettingsParcel zzd(final Parcel parcel) {
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        String zzo = null;
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
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new MobileAdsSettingsParcel(zzg, zzc, zzo);
    }
    
    public MobileAdsSettingsParcel[] zzn(final int n) {
        return new MobileAdsSettingsParcel[n];
    }
}
