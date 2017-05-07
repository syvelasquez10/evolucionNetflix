// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<DeleteRequest>
{
    static void zza(final DeleteRequest deleteRequest, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zza(parcel, 1, (Parcelable)deleteRequest.getCredential(), n, false);
        zzb.zzc(parcel, 1000, deleteRequest.zzCY);
        zzb.zzH(parcel, zzac);
    }
    
    public DeleteRequest zzF(final Parcel parcel) {
        final int zzab = zza.zzab(parcel);
        int zzg = 0;
        Credential credential = null;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    credential = zza.zza(parcel, zzaa, Credential.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new DeleteRequest(zzg, credential);
    }
    
    public DeleteRequest[] zzav(final int n) {
        return new DeleteRequest[n];
    }
}
