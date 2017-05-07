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
    static void zza(final CheckServerAuthResult checkServerAuthResult, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.zzaOk);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzaOl, false);
        zzb.zzH(parcel, zzak);
    }
    
    public CheckServerAuthResult zzgk(final Parcel parcel) {
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        List<Scope> zzc2 = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzai, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new CheckServerAuthResult(zzg, zzc, zzc2);
    }
    
    public CheckServerAuthResult[] zziZ(final int n) {
        return new CheckServerAuthResult[n];
    }
}
