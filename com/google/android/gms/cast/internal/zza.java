// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ApplicationStatus>
{
    static void zza(final ApplicationStatus applicationStatus, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, applicationStatus.getVersionCode());
        zzb.zza(parcel, 2, applicationStatus.zzlG(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public ApplicationStatus zzM(final Parcel parcel) {
        final int zzab = com.google.android.gms.common.internal.safeparcel.zza.zzab(parcel);
        int zzg = 0;
        String zzo = null;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = com.google.android.gms.common.internal.safeparcel.zza.zzaa(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbA(zzaa)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ApplicationStatus(zzg, zzo);
    }
    
    public ApplicationStatus[] zzaK(final int n) {
        return new ApplicationStatus[n];
    }
}
