// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzf implements Parcelable$Creator<LocationSettingsRequest>
{
    static void zza(final LocationSettingsRequest locationSettingsRequest, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationSettingsRequest.zztd(), false);
        zzb.zzc(parcel, 1000, locationSettingsRequest.getVersionCode());
        zzb.zza(parcel, 2, locationSettingsRequest.zzwx());
        zzb.zza(parcel, 3, locationSettingsRequest.zzwy());
        zzb.zza(parcel, 4, locationSettingsRequest.zzwz());
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationSettingsRequest zzeC(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        Object zzc2 = null;
        boolean zzc3 = false;
        boolean zzc4 = false;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzc2 = zza.zzc(parcel, zzao, (android.os.Parcelable$Creator<Object>)LocationRequest.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzc4 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc3 = zza.zzc(parcel, zzao);
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
        return new LocationSettingsRequest(zzg, (List<LocationRequest>)zzc2, zzc4, zzc3, zzc);
    }
    
    public LocationSettingsRequest[] zzgS(final int n) {
        return new LocationSettingsRequest[n];
    }
}
