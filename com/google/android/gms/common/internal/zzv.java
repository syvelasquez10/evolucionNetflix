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

public class zzv implements Parcelable$Creator<ResolveAccountRequest>
{
    static void zza(final ResolveAccountRequest resolveAccountRequest, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.zzCY);
        zzb.zza(parcel, 2, (Parcelable)resolveAccountRequest.getAccount(), n, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zzH(parcel, zzac);
    }
    
    public ResolveAccountRequest zzX(final Parcel parcel) {
        int zzg = 0;
        final int zzab = zza.zzab(parcel);
        Account account = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    account = zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ResolveAccountRequest(zzg2, account, zzg);
    }
    
    public ResolveAccountRequest[] zzbx(final int n) {
        return new ResolveAccountRequest[n];
    }
}
