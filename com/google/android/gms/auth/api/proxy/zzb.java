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
    static void zza(final ProxyRequest proxyRequest, final Parcel parcel, int zzak) {
        zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, proxyRequest.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, proxyRequest.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, proxyRequest.httpMethod);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, proxyRequest.timeoutMillis);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, proxyRequest.body, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, proxyRequest.zzRE, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }
    
    public ProxyRequest zzL(final Parcel parcel) {
        int zzg = 0;
        Bundle zzq = null;
        final int zzaj = zza.zzaj(parcel);
        long zzi = 0L;
        byte[] zzr = null;
        String zzo = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                default: {
                    zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzr = zza.zzr(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ProxyRequest(zzg2, zzo, zzg, zzi, zzr, zzq);
    }
    
    public ProxyRequest[] zzaB(final int n) {
        return new ProxyRequest[n];
    }
}
