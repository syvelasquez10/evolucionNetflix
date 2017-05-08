// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class LocationAvailabilityCreator implements Parcelable$Creator<LocationAvailability>
{
    static void zza(final LocationAvailability locationAvailability, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationAvailability.zzaEA);
        zzb.zzc(parcel, 1000, locationAvailability.getVersionCode());
        zzb.zzc(parcel, 2, locationAvailability.zzaEB);
        zzb.zza(parcel, 3, locationAvailability.zzaEC);
        zzb.zzc(parcel, 4, locationAvailability.zzaED);
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationAvailability createFromParcel(final Parcel parcel) {
        int zzg = 1;
        final int zzap = zza.zzap(parcel);
        int zzg2 = 0;
        int zzg3 = 1000;
        long zzi = 0L;
        int zzg4 = 1;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg4 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationAvailability(zzg2, zzg3, zzg4, zzg, zzi);
    }
    
    public LocationAvailability[] newArray(final int n) {
        return new LocationAvailability[n];
    }
}
