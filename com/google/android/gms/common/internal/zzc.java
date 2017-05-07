// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<AuthAccountRequest>
{
    static void zza(final AuthAccountRequest authAccountRequest, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.zzCY);
        zzb.zza(parcel, 2, authAccountRequest.zzZN, false);
        zzb.zza(parcel, 3, authAccountRequest.zzZO, n, false);
        zzb.zzH(parcel, zzac);
    }
    
    public AuthAccountRequest zzU(final Parcel parcel) {
        Scope[] array = null;
        final int zzab = zza.zzab(parcel);
        int zzg = 0;
        IBinder zzp = null;
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
                    zzp = zza.zzp(parcel, zzaa);
                    continue;
                }
                case 3: {
                    array = zza.zzb(parcel, zzaa, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new AuthAccountRequest(zzg, zzp, array);
    }
    
    public AuthAccountRequest[] zzbp(final int n) {
        return new AuthAccountRequest[n];
    }
}
