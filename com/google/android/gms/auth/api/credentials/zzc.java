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
        final int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, credentialRequest.getSupportsPasswordLogin());
        zzb.zzc(parcel, 1000, credentialRequest.mVersionCode);
        zzb.zza(parcel, 2, credentialRequest.getAccountTypes(), false);
        zzb.zza(parcel, 3, (Parcelable)credentialRequest.getCredentialPickerConfig(), n, false);
        zzb.zza(parcel, 4, (Parcelable)credentialRequest.getCredentialHintPickerConfig(), n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public CredentialRequest zzG(final Parcel parcel) {
        boolean zzc = false;
        CredentialPickerConfig credentialPickerConfig = null;
        final int zzap = zza.zzap(parcel);
        CredentialPickerConfig credentialPickerConfig2 = null;
        String[] zzB = null;
        int zzg = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzB = zza.zzB(parcel, zzao);
                    continue;
                }
                case 3: {
                    credentialPickerConfig2 = zza.zza(parcel, zzao, CredentialPickerConfig.CREATOR);
                    continue;
                }
                case 4: {
                    credentialPickerConfig = zza.zza(parcel, zzao, CredentialPickerConfig.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new CredentialRequest(zzg, zzc, zzB, credentialPickerConfig2, credentialPickerConfig);
    }
    
    public CredentialRequest[] zzax(final int n) {
        return new CredentialRequest[n];
    }
}
