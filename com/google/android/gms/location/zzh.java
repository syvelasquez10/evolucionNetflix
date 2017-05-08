// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<LocationSettingsStates>
{
    static void zza(final LocationSettingsStates locationSettingsStates, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, locationSettingsStates.isGpsUsable());
        zzb.zzc(parcel, 1000, locationSettingsStates.getVersionCode());
        zzb.zza(parcel, 2, locationSettingsStates.isNetworkLocationUsable());
        zzb.zza(parcel, 3, locationSettingsStates.isBleUsable());
        zzb.zza(parcel, 4, locationSettingsStates.isGpsPresent());
        zzb.zza(parcel, 5, locationSettingsStates.isNetworkLocationPresent());
        zzb.zza(parcel, 6, locationSettingsStates.isBlePresent());
        zzb.zza(parcel, 7, locationSettingsStates.zzwA());
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationSettingsStates zzeE(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        boolean zzc2 = false;
        boolean zzc3 = false;
        boolean zzc4 = false;
        boolean zzc5 = false;
        boolean zzc6 = false;
        boolean zzc7 = false;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzc7 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzc6 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc5 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc4 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc3 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationSettingsStates(zzg, zzc7, zzc6, zzc5, zzc4, zzc3, zzc2, zzc);
    }
    
    public LocationSettingsStates[] zzgU(final int n) {
        return new LocationSettingsStates[n];
    }
}
