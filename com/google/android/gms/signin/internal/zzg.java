// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Scope;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<RecordConsentRequest>
{
    static void zza(final RecordConsentRequest recordConsentRequest, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, recordConsentRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)recordConsentRequest.getAccount(), n, false);
        zzb.zza(parcel, 3, recordConsentRequest.zzCj(), n, false);
        zzb.zza(parcel, 4, recordConsentRequest.zzmb(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public RecordConsentRequest zzgD(final Parcel parcel) {
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        Scope[] array = null;
        Account account = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            Account account2 = null;
            Scope[] array3 = null;
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    final Scope[] array2 = array;
                    account2 = account;
                    array3 = array2;
                    break;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    final Account account3 = account;
                    array3 = array;
                    account2 = account3;
                    break;
                }
                case 2: {
                    final Account account4 = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    array3 = array;
                    account2 = account4;
                    break;
                }
                case 3: {
                    final Scope[] array4 = zza.zzb(parcel, zzao, Scope.CREATOR);
                    account2 = account;
                    array3 = array4;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzao);
                    final Account account5 = account;
                    array3 = array;
                    account2 = account5;
                    break;
                }
            }
            final Account account6 = account2;
            array = array3;
            account = account6;
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new RecordConsentRequest(zzg, account, array, zzp);
    }
    
    public RecordConsentRequest[] zzjr(final int n) {
        return new RecordConsentRequest[n];
    }
}
