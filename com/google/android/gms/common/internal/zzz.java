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

public class zzz implements Parcelable$Creator<ResolveAccountResponse>
{
    static void zza(final ResolveAccountResponse resolveAccountResponse, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.zzaeH, false);
        zzb.zza(parcel, 3, (Parcelable)resolveAccountResponse.zzpr(), n, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzps());
        zzb.zza(parcel, 5, resolveAccountResponse.zzpt());
        zzb.zzI(parcel, zzaq);
    }
    
    public ResolveAccountResponse zzam(final Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        boolean zzc2 = false;
        IBinder zzq = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzq = zza.zzq(parcel, zzao);
                    continue;
                }
                case 3: {
                    connectionResult = zza.zza(parcel, zzao, ConnectionResult.CREATOR);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ResolveAccountResponse(zzg, zzq, connectionResult, zzc2, zzc);
    }
    
    public ResolveAccountResponse[] zzbK(final int n) {
        return new ResolveAccountResponse[n];
    }
}
