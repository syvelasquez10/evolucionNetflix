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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, recordConsentRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)recordConsentRequest.getAccount(), n, false);
        zzb.zza(parcel, 3, recordConsentRequest.zzzs(), n, false);
        zzb.zza(parcel, 4, recordConsentRequest.zzlG(), false);
        zzb.zzH(parcel, zzak);
    }
    
    public RecordConsentRequest zzgl(final Parcel parcel) {
        String zzo = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        Scope[] array = null;
        Account account = null;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            Account account2 = null;
            Scope[] array3 = null;
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    final Scope[] array2 = array;
                    account2 = account;
                    array3 = array2;
                    break;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzai);
                    final Account account3 = account;
                    array3 = array;
                    account2 = account3;
                    break;
                }
                case 2: {
                    final Account account4 = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    array3 = array;
                    account2 = account4;
                    break;
                }
                case 3: {
                    final Scope[] array4 = zza.zzb(parcel, zzai, Scope.CREATOR);
                    account2 = account;
                    array3 = array4;
                    break;
                }
                case 4: {
                    zzo = zza.zzo(parcel, zzai);
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
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new RecordConsentRequest(zzg, account, array, zzo);
    }
    
    public RecordConsentRequest[] zzjb(final int n) {
        return new RecordConsentRequest[n];
    }
}
