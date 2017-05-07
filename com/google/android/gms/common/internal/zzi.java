// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<GetServiceRequest>
{
    static void zza(final GetServiceRequest getServiceRequest, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzafq);
        zzb.zzc(parcel, 3, getServiceRequest.zzafr);
        zzb.zza(parcel, 4, getServiceRequest.zzafs, false);
        zzb.zza(parcel, 5, getServiceRequest.zzaft, false);
        zzb.zza(parcel, 6, getServiceRequest.zzafu, n, false);
        zzb.zza(parcel, 7, getServiceRequest.zzafv, false);
        zzb.zza(parcel, 8, (Parcelable)getServiceRequest.zzafw, n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public GetServiceRequest zzak(final Parcel parcel) {
        int zzg = 0;
        Account account = null;
        final int zzap = zza.zzap(parcel);
        Bundle zzr = null;
        Scope[] array = null;
        IBinder zzq = null;
        String zzp = null;
        int zzg2 = 0;
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
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzao);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzao, Scope.CREATOR);
                    continue;
                }
                case 7: {
                    zzr = zza.zzr(parcel, zzao);
                    continue;
                }
                case 8: {
                    account = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new GetServiceRequest(zzg3, zzg2, zzg, zzp, zzq, array, zzr, account);
    }
    
    public GetServiceRequest[] zzbD(final int n) {
        return new GetServiceRequest[n];
    }
}
