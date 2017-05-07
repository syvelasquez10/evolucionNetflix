// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzj implements Parcelable$Creator<Scope>
{
    static void zza(final Scope scope, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, scope.zzCY);
        zzb.zza(parcel, 2, scope.zzmQ(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public Scope zzP(final Parcel parcel) {
        final int zzab = zza.zzab(parcel);
        int zzg = 0;
        String zzo = null;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new Scope(zzg, zzo);
    }
    
    public Scope[] zzaZ(final int n) {
        return new Scope[n];
    }
}
