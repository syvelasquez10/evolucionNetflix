// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<CheckServerAuthResult>
{
    static void zza(final CheckServerAuthResult checkServerAuthResult, final Parcel parcel, int zzac) {
        zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.zzCY);
        zzb.zza(parcel, 2, checkServerAuthResult.zzaJX);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzaJY, false);
        zzb.zzH(parcel, zzac);
    }
    
    public CheckServerAuthResult zzfZ(final Parcel parcel) {
        boolean zzc = false;
        final int zzab = zza.zzab(parcel);
        List<Scope> zzc2 = null;
        int zzg = 0;
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
                    zzc = zza.zzc(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzaa, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new CheckServerAuthResult(zzg, zzc, zzc2);
    }
    
    public CheckServerAuthResult[] zziP(final int n) {
        return new CheckServerAuthResult[n];
    }
}
