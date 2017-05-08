// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<LocationSettingsResult>
{
    static void zza(final LocationSettingsResult locationSettingsResult, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, (Parcelable)locationSettingsResult.getStatus(), n, false);
        zzb.zzc(parcel, 1000, locationSettingsResult.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable)locationSettingsResult.getLocationSettingsStates(), n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationSettingsResult zzeD(final Parcel parcel) {
        LocationSettingsStates locationSettingsStates = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Status status = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    status = zza.zza(parcel, zzao, Status.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    locationSettingsStates = zza.zza(parcel, zzao, LocationSettingsStates.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationSettingsResult(zzg, status, locationSettingsStates);
    }
    
    public LocationSettingsResult[] zzgT(final int n) {
        return new LocationSettingsResult[n];
    }
}
