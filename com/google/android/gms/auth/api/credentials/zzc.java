// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<CredentialRequest>
{
    static void zza(final CredentialRequest credentialRequest, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1, credentialRequest.getSupportsPasswordLogin());
        zzb.zzc(parcel, 1000, credentialRequest.mVersionCode);
        zzb.zza(parcel, 2, credentialRequest.getAccountTypes(), false);
        zzb.zza(parcel, 3, (Parcelable)credentialRequest.getCredentialPickerConfig(), n, false);
        zzb.zza(parcel, 4, (Parcelable)credentialRequest.getCredentialHintPickerConfig(), n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public CredentialRequest zzF(final Parcel parcel) {
        boolean zzc = false;
        CredentialPickerConfig credentialPickerConfig = null;
        final int zzaj = zza.zzaj(parcel);
        CredentialPickerConfig credentialPickerConfig2 = null;
        String[] zzA = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzA = zza.zzA(parcel, zzai);
                    continue;
                }
                case 3: {
                    credentialPickerConfig2 = zza.zza(parcel, zzai, CredentialPickerConfig.CREATOR);
                    continue;
                }
                case 4: {
                    credentialPickerConfig = zza.zza(parcel, zzai, CredentialPickerConfig.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new CredentialRequest(zzg, zzc, zzA, credentialPickerConfig2, credentialPickerConfig);
    }
    
    public CredentialRequest[] zzav(final int n) {
        return new CredentialRequest[n];
    }
}
