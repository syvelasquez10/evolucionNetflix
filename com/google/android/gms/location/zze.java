// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<LocationResult>
{
    static void zza(final LocationResult locationResult, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationResult.getLocations(), false);
        zzb.zzc(parcel, 1000, locationResult.getVersionCode());
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationResult zzeB(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Object o = LocationResult.zzaEJ;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    o = zza.zzc(parcel, zzao, (android.os.Parcelable$Creator<Object>)Location.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationResult(zzg, (List<Location>)o);
    }
    
    public LocationResult[] zzgR(final int n) {
        return new LocationResult[n];
    }
}
