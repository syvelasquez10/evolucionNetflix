// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzad implements Parcelable$Creator<ValidateAccountRequest>
{
    static void zza(final ValidateAccountRequest validateAccountRequest, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzpu());
        zzb.zza(parcel, 3, validateAccountRequest.zzaeH, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzpv(), n, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzpw(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public ValidateAccountRequest zzan(final Parcel parcel) {
        int zzg = 0;
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        Bundle zzr = null;
        Scope[] array = null;
        IBinder zzq = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzao);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzao, Scope.CREATOR);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ValidateAccountRequest(zzg2, zzg, zzq, array, zzr, zzp);
    }
    
    public ValidateAccountRequest[] zzbL(final int n) {
        return new ValidateAccountRequest[n];
    }
}
