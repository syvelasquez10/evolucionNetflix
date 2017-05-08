// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class LocationRequestCreator implements Parcelable$Creator<LocationRequest>
{
    static void zza(final LocationRequest locationRequest, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationRequest.mPriority);
        zzb.zzc(parcel, 1000, locationRequest.getVersionCode());
        zzb.zza(parcel, 2, locationRequest.zzaEE);
        zzb.zza(parcel, 3, locationRequest.zzaEF);
        zzb.zza(parcel, 4, locationRequest.zzasP);
        zzb.zza(parcel, 5, locationRequest.zzaEj);
        zzb.zzc(parcel, 6, locationRequest.zzaEG);
        zzb.zza(parcel, 7, locationRequest.zzaEH);
        zzb.zza(parcel, 8, locationRequest.zzaEI);
        zzb.zzI(parcel, zzaq);
    }
    
    public LocationRequest createFromParcel(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        int zzg2 = 102;
        long zzi = 3600000L;
        long zzi2 = 600000L;
        boolean zzc = false;
        long zzi3 = Long.MAX_VALUE;
        int zzg3 = Integer.MAX_VALUE;
        float zzl = 0.0f;
        long zzi4 = 0L;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzao);
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
                    zzi2 = zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzi3 = zza.zzi(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzl = zza.zzl(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzi4 = zza.zzi(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new LocationRequest(zzg, zzg2, zzi, zzi2, zzc, zzi3, zzg3, zzl, zzi4);
    }
    
    public LocationRequest[] newArray(final int n) {
        return new LocationRequest[n];
    }
}
