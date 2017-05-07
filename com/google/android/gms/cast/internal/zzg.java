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
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, deviceStatus.getVersionCode());
        zzb.zza(parcel, 2, deviceStatus.zzlM());
        zzb.zza(parcel, 3, deviceStatus.zzlV());
        zzb.zzc(parcel, 4, deviceStatus.zzlN());
        zzb.zza(parcel, 5, (Parcelable)deviceStatus.getApplicationMetadata(), n, false);
        zzb.zzc(parcel, 6, deviceStatus.zzlO());
        zzb.zzH(parcel, zzac);
    }
    
    public DeviceStatus zzN(final Parcel parcel) {
        int zzg = 0;
        final int zzab = zza.zzab(parcel);
        double zzm = 0.0;
        ApplicationMetadata applicationMetadata = null;
        int zzg2 = 0;
        boolean zzc = false;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzm = zza.zzm(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzc = zza.zzc(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 5: {
                    applicationMetadata = zza.zza(parcel, zzaa, ApplicationMetadata.CREATOR);
                    continue;
                }
                case 6: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new DeviceStatus(zzg3, zzm, zzc, zzg2, applicationMetadata, zzg);
    }
    
    public DeviceStatus[] zzaQ(final int n) {
        return new DeviceStatus[n];
    }
}
