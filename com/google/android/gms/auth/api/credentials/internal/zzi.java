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
        final int zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1, (Parcelable)saveRequest.getCredential(), n, false);
        zzb.zzc(parcel, 1000, saveRequest.mVersionCode);
        zzb.zzH(parcel, zzak);
    }
    
    public SaveRequest zzJ(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        Credential credential = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    credential = zza.zza(parcel, zzai, Credential.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new SaveRequest(zzg, credential);
    }
    
    public SaveRequest[] zzaz(final int n) {
        return new SaveRequest[n];
    }
}
