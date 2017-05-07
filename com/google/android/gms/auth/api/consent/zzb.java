// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.consent;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<GetConsentIntentRequest>
{
    static void zza(final GetConsentIntentRequest getConsentIntentRequest, final Parcel parcel, final int n) {
        final int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, getConsentIntentRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, getConsentIntentRequest.getCallingPackage(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, getConsentIntentRequest.getCallingUid());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, getConsentIntentRequest.zzlF(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, (Parcelable)getConsentIntentRequest.getAccount(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, getConsentIntentRequest.zzSe, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, getConsentIntentRequest.zzlG());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, getConsentIntentRequest.zzlH());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public GetConsentIntentRequest zzD(final Parcel parcel) {
        ScopeDetail[] array = null;
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        boolean zzc = false;
        Account account = null;
        String zzp = null;
        int zzg2 = 0;
        String zzp2 = null;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    account = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzao, (android.os.Parcelable$Creator<ScopeDetail>)ScopeDetail.CREATOR);
                    continue;
                }
                case 7: {
                    zzc = zza.zzc(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new GetConsentIntentRequest(zzg3, zzp2, zzg2, zzp, account, array, zzc, zzg);
    }
    
    public GetConsentIntentRequest[] zzau(final int n) {
        return new GetConsentIntentRequest[n];
    }
}
