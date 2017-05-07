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
    static void zza(final CheckServerAuthResult checkServerAuthResult, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.zzaVi);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzaVj, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public CheckServerAuthResult zzgC(final Parcel parcel) {
        boolean zzc = false;
        final int zzap = zza.zzap(parcel);
        List<Scope> zzc2 = null;
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
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzao, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new CheckServerAuthResult(zzg, zzc, zzc2);
    }
    
    public CheckServerAuthResult[] zzjp(final int n) {
        return new CheckServerAuthResult[n];
    }
}
