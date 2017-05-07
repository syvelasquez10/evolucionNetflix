// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<CredentialRequest>
{
    static void zza(final CredentialRequest credentialRequest, final Parcel parcel, int zzac) {
        zzac = com.google.android.gms.common.internal.safeparcel.zzb.zzac(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, credentialRequest.getSupportsPasswordLogin());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, credentialRequest.zzCY);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, credentialRequest.getAccountTypes(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzac);
    }
    
    public CredentialRequest zzD(final Parcel parcel) {
        boolean zzc = false;
        final int zzab = zza.zzab(parcel);
        String[] zzA = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzc = zza.zzc(parcel, zzaa);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzA = zza.zzA(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new CredentialRequest(zzg, zzc, zzA);
    }
    
    public CredentialRequest[] zzat(final int n) {
        return new CredentialRequest[n];
    }
}
