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

public class zzi implements Parcelable$Creator<SaveRequest>
{
    static void zza(final SaveRequest saveRequest, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, (Parcelable)saveRequest.getCredential(), n, false);
        zzb.zzc(parcel, 1000, saveRequest.mVersionCode);
        zzb.zzI(parcel, zzaq);
    }
    
    public SaveRequest zzK(final Parcel parcel) {
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Credential credential = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    credential = zza.zza(parcel, zzao, Credential.CREATOR);
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
        return new SaveRequest(zzg, credential);
    }
    
    public SaveRequest[] zzaB(final int n) {
        return new SaveRequest[n];
    }
}
