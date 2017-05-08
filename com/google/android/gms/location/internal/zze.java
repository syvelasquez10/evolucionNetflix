// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<FusedLocationProviderResult>
{
    static void zza(final FusedLocationProviderResult fusedLocationProviderResult, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, (Parcelable)fusedLocationProviderResult.getStatus(), n, false);
        zzb.zzc(parcel, 1000, fusedLocationProviderResult.getVersionCode());
        zzb.zzI(parcel, zzaq);
    }
    
    public FusedLocationProviderResult zzeG(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Status status = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    status = zza.zza(parcel, zzao, Status.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new FusedLocationProviderResult(zzg, status);
    }
    
    public FusedLocationProviderResult[] zzgY(final int n) {
        return new FusedLocationProviderResult[n];
    }
}
