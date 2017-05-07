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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzoS());
        zzb.zza(parcel, 3, validateAccountRequest.zzacC, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzoT(), n, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzoU(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public ValidateAccountRequest zzah(final Parcel parcel) {
        int zzg = 0;
        String zzo = null;
        final int zzaj = zza.zzaj(parcel);
        Bundle zzq = null;
        Scope[] array = null;
        IBinder zzp = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzp = zza.zzp(parcel, zzai);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzai, Scope.CREATOR);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ValidateAccountRequest(zzg2, zzg, zzp, array, zzq, zzo);
    }
    
    public ValidateAccountRequest[] zzbG(final int n) {
        return new ValidateAccountRequest[n];
    }
}
