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

public class zzh implements Parcelable$Creator<GetServiceRequest>
{
    static void zza(final GetServiceRequest getServiceRequest, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzaac);
        zzb.zzc(parcel, 3, getServiceRequest.zzaad);
        zzb.zza(parcel, 4, getServiceRequest.zzaae, false);
        zzb.zza(parcel, 5, getServiceRequest.zzaaf, false);
        zzb.zza(parcel, 6, getServiceRequest.zzaag, n, false);
        zzb.zza(parcel, 7, getServiceRequest.zzaah, false);
        zzb.zza(parcel, 8, (Parcelable)getServiceRequest.zzaai, n, false);
        zzb.zzH(parcel, zzac);
    }
    
    public GetServiceRequest zzW(final Parcel parcel) {
        int zzg = 0;
        Account account = null;
        final int zzab = zza.zzab(parcel);
        Bundle zzq = null;
        Scope[] array = null;
        IBinder zzp = null;
        String zzo = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzp = zza.zzp(parcel, zzaa);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzaa, Scope.CREATOR);
                    continue;
                }
                case 7: {
                    zzq = zza.zzq(parcel, zzaa);
                    continue;
                }
                case 8: {
                    account = zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new GetServiceRequest(zzg3, zzg2, zzg, zzo, zzp, array, zzq, account);
    }
    
    public GetServiceRequest[] zzbr(final int n) {
        return new GetServiceRequest[n];
    }
}
