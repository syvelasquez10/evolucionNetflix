// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ProxyGrpcRequest>
{
    static void zza(final ProxyGrpcRequest proxyGrpcRequest, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, proxyGrpcRequest.hostname, false);
        zzb.zzc(parcel, 1000, proxyGrpcRequest.versionCode);
        zzb.zzc(parcel, 2, proxyGrpcRequest.port);
        zzb.zza(parcel, 3, proxyGrpcRequest.timeoutMillis);
        zzb.zza(parcel, 4, proxyGrpcRequest.body, false);
        zzb.zza(parcel, 5, proxyGrpcRequest.method, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public ProxyGrpcRequest zzL(final Parcel parcel) {
        int zzg = 0;
        String zzp = null;
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        long zzi = 0L;
        byte[] zzs = null;
        String zzp2 = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 1000: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzs = com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ProxyGrpcRequest(zzg2, zzp2, zzg, zzi, zzs, zzp);
    }
    
    public ProxyGrpcRequest[] zzaC(final int n) {
        return new ProxyGrpcRequest[n];
    }
}
