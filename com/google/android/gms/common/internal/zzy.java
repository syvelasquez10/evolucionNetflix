// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzy implements Parcelable$Creator<ResolveAccountRequest>
{
    static void zza(final ResolveAccountRequest resolveAccountRequest, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)resolveAccountRequest.getAccount(), n, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zzI(parcel, zzaq);
    }
    
    public ResolveAccountRequest zzal(final Parcel parcel) {
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        Account account = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    account = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ResolveAccountRequest(zzg2, account, zzg);
    }
    
    public ResolveAccountRequest[] zzbJ(final int n) {
        return new ResolveAccountRequest[n];
    }
}
