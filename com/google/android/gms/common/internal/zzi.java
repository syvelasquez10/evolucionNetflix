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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzadn);
        zzb.zzc(parcel, 3, getServiceRequest.zzado);
        zzb.zza(parcel, 4, getServiceRequest.zzadp, false);
        zzb.zza(parcel, 5, getServiceRequest.zzadq, false);
        zzb.zza(parcel, 6, getServiceRequest.zzadr, n, false);
        zzb.zza(parcel, 7, getServiceRequest.zzads, false);
        zzb.zza(parcel, 8, (Parcelable)getServiceRequest.zzadt, n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public GetServiceRequest zzae(final Parcel parcel) {
        int zzg = 0;
        Account account = null;
        final int zzaj = zza.zzaj(parcel);
        Bundle zzq = null;
        Scope[] array = null;
        IBinder zzp = null;
        String zzo = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzp = zza.zzp(parcel, zzai);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzai, Scope.CREATOR);
                    continue;
                }
                case 7: {
                    zzq = zza.zzq(parcel, zzai);
                    continue;
                }
                case 8: {
                    account = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new GetServiceRequest(zzg3, zzg2, zzg, zzo, zzp, array, zzq, account);
    }
    
    public GetServiceRequest[] zzby(final int n) {
        return new GetServiceRequest[n];
    }
}
