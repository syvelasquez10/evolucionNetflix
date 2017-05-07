// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<ProxyRequest>
{
    static void zza(final ProxyRequest proxyRequest, final Parcel parcel, int zzac) {
        zzac = com.google.android.gms.common.internal.safeparcel.zzb.zzac(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, proxyRequest.zzzf, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, proxyRequest.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, proxyRequest.zzPp);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, proxyRequest.zzPq);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, proxyRequest.zzPr, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, proxyRequest.zzPs, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzac);
    }
    
    public ProxyRequest zzH(final Parcel parcel) {
        int zzg = 0;
        Bundle zzq = null;
        final int zzab = zza.zzab(parcel);
        long zzi = 0L;
        byte[] zzr = null;
        String zzo = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzab) {
            final int zzaa = zza.zzaa(parcel);
            switch (zza.zzbA(zzaa)) {
                default: {
                    zza.zzb(parcel, zzaa);
                    continue;
                }
                case 1: {
                    zzo = zza.zzo(parcel, zzaa);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzr = zza.zzr(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ProxyRequest(zzg2, zzo, zzg, zzi, zzr, zzq);
    }
    
    public ProxyRequest[] zzax(final int n) {
        return new ProxyRequest[n];
    }
}
