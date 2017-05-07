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

public class zzab implements Parcelable$Creator<ValidateAccountRequest>
{
    static void zza(final ValidateAccountRequest validateAccountRequest, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.zzCY);
        zzb.zzc(parcel, 2, validateAccountRequest.zzob());
        zzb.zza(parcel, 3, validateAccountRequest.zzZN, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzoc(), n, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzod(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzH(parcel, zzac);
    }
    
    public ValidateAccountRequest zzZ(final Parcel parcel) {
        int zzg = 0;
        String zzo = null;
        final int zzab = zza.zzab(parcel);
        Bundle zzq = null;
        Scope[] array = null;
        IBinder zzp = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzaa);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzaa, Scope.CREATOR);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzaa);
                    continue;
                }
                case 6: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ValidateAccountRequest(zzg2, zzg, zzp, array, zzq, zzo);
    }
    
    public ValidateAccountRequest[] zzbz(final int n) {
        return new ValidateAccountRequest[n];
    }
}
