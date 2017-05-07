// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzw implements Parcelable$Creator<ResolveAccountResponse>
{
    static void zza(final ResolveAccountResponse resolveAccountResponse, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.zzCY);
        zzb.zza(parcel, 2, resolveAccountResponse.zzZN, false);
        zzb.zza(parcel, 3, (Parcelable)resolveAccountResponse.zznY(), n, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zznZ());
        zzb.zza(parcel, 5, resolveAccountResponse.zzoa());
        zzb.zzH(parcel, zzac);
    }
    
    public ResolveAccountResponse zzY(final Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean zzc = false;
        final int zzab = zza.zzab(parcel);
        boolean zzc2 = false;
        IBinder zzp = null;
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
                    zzp = zza.zzp(parcel, zzaa);
                    continue;
                }
                case 3: {
                    connectionResult = zza.zza(parcel, zzaa, ConnectionResult.CREATOR);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ResolveAccountResponse(zzg, zzp, connectionResult, zzc2, zzc);
    }
    
    public ResolveAccountResponse[] zzby(final int n) {
        return new ResolveAccountResponse[n];
    }
}
