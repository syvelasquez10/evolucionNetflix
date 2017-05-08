// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzo implements Parcelable$Creator<ParcelableGeofence>
{
    static void zza(final ParcelableGeofence parcelableGeofence, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, parcelableGeofence.getRequestId(), false);
        zzb.zzc(parcel, 1000, parcelableGeofence.getVersionCode());
        zzb.zza(parcel, 2, parcelableGeofence.getExpirationTime());
        zzb.zza(parcel, 3, parcelableGeofence.zzwI());
        zzb.zza(parcel, 4, parcelableGeofence.getLatitude());
        zzb.zza(parcel, 5, parcelableGeofence.getLongitude());
        zzb.zza(parcel, 6, parcelableGeofence.zzwJ());
        zzb.zzc(parcel, 7, parcelableGeofence.zzwK());
        zzb.zzc(parcel, 8, parcelableGeofence.getNotificationResponsiveness());
        zzb.zzc(parcel, 9, parcelableGeofence.zzwL());
        zzb.zzI(parcel, zzaq);
    }
    
    public ParcelableGeofence zzeJ(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        String zzp = null;
        int zzg2 = 0;
        short zzf = 0;
        double zzn = 0.0;
        double zzn2 = 0.0;
        float zzl = 0.0f;
        long zzi = 0L;
        int zzg3 = 0;
        int zzg4 = -1;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzf = zza.zzf(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzn = zza.zzn(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzn2 = zza.zzn(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzl = zza.zzl(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 9: {
                    zzg4 = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ParcelableGeofence(zzg, zzp, zzg2, zzf, zzn, zzn2, zzl, zzi, zzg3, zzg4);
    }
    
    public ParcelableGeofence[] zzhe(final int n) {
        return new ParcelableGeofence[n];
    }
}
