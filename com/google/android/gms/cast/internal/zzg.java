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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, deviceStatus.getVersionCode());
        zzb.zza(parcel, 2, deviceStatus.zzmy());
        zzb.zza(parcel, 3, deviceStatus.zzmH());
        zzb.zzc(parcel, 4, deviceStatus.zzmz());
        zzb.zza(parcel, 5, (Parcelable)deviceStatus.getApplicationMetadata(), n, false);
        zzb.zzc(parcel, 6, deviceStatus.zzmA());
        zzb.zzH(parcel, zzak);
    }
    
    public DeviceStatus zzV(final Parcel parcel) {
        int zzg = 0;
        final int zzaj = zza.zzaj(parcel);
        double zzm = 0.0;
        ApplicationMetadata applicationMetadata = null;
        int zzg2 = 0;
        boolean zzc = false;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzm = zza.zzm(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 5: {
                    applicationMetadata = zza.zza(parcel, zzai, ApplicationMetadata.CREATOR);
                    continue;
                }
                case 6: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new DeviceStatus(zzg3, zzm, zzc, zzg2, applicationMetadata, zzg);
    }
    
    public DeviceStatus[] zzaY(final int n) {
        return new DeviceStatus[n];
    }
}
