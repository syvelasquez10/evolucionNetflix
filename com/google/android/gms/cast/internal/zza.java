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
    static void zza(final ApplicationStatus applicationStatus, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, applicationStatus.getVersionCode());
        zzb.zza(parcel, 2, applicationStatus.zzms(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public ApplicationStatus zzU(final Parcel parcel) {
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        int zzg = 0;
        String zzo = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ApplicationStatus(zzg, zzo);
    }
    
    public ApplicationStatus[] zzaS(final int n) {
        return new ApplicationStatus[n];
    }
}
