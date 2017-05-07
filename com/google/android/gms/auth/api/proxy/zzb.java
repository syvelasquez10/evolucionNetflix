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
    static void zza(final ProxyRequest proxyRequest, final Parcel parcel, int zzaq) {
        zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, proxyRequest.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, proxyRequest.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, proxyRequest.httpMethod);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, proxyRequest.timeoutMillis);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, proxyRequest.body, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, proxyRequest.zzSK, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public ProxyRequest zzM(final Parcel parcel) {
        int zzg = 0;
        Bundle zzr = null;
        final int zzap = zza.zzap(parcel);
        long zzi = 0L;
        byte[] zzs = null;
        String zzp = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzs = zza.zzs(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ProxyRequest(zzg2, zzp, zzg, zzi, zzs, zzr);
    }
    
    public ProxyRequest[] zzaD(final int n) {
        return new ProxyRequest[n];
    }
}
