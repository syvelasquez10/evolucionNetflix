// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<DeviceStatus>
{
    static void zza(final DeviceStatus deviceStatus, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, deviceStatus.getVersionCode());
        zzb.zza(parcel, 2, deviceStatus.zzmU());
        zzb.zza(parcel, 3, deviceStatus.zznd());
        zzb.zzc(parcel, 4, deviceStatus.zzmV());
        zzb.zza(parcel, 5, (Parcelable)deviceStatus.getApplicationMetadata(), n, false);
        zzb.zzc(parcel, 6, deviceStatus.zzmW());
        zzb.zzI(parcel, zzaq);
    }
    
    public DeviceStatus zzab(final Parcel parcel) {
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        double zzn = 0.0;
        ApplicationMetadata applicationMetadata = null;
        int zzg2 = 0;
        boolean zzc = false;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzn = zza.zzn(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 5: {
                    applicationMetadata = zza.zza(parcel, zzao, ApplicationMetadata.CREATOR);
                    continue;
                }
                case 6: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new DeviceStatus(zzg3, zzn, zzc, zzg2, applicationMetadata, zzg);
    }
    
    public DeviceStatus[] zzbf(final int n) {
        return new DeviceStatus[n];
    }
}
