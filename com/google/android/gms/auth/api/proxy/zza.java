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
    static void zza(final ProxyGrpcRequest proxyGrpcRequest, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zza(parcel, 1, proxyGrpcRequest.hostname, false);
        zzb.zzc(parcel, 1000, proxyGrpcRequest.versionCode);
        zzb.zzc(parcel, 2, proxyGrpcRequest.port);
        zzb.zza(parcel, 3, proxyGrpcRequest.timeoutMillis);
        zzb.zza(parcel, 4, proxyGrpcRequest.body, false);
        zzb.zza(parcel, 5, proxyGrpcRequest.method, false);
        zzb.zzH(parcel, zzak);
    }
    
    public ProxyGrpcRequest zzK(final Parcel parcel) {
        int zzg = 0;
        String zzo = null;
        final int zzaj = com.google.android.gms.common.internal.safeparcel.zza.zzaj(parcel);
        long zzi = 0L;
        byte[] zzr = null;
        String zzo2 = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzaj) {
            final int zzai = com.google.android.gms.common.internal.safeparcel.zza.zzai(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbH(zzai)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzai);
                    continue;
                }
                case 1: {
                    zzo2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzr = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzo = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ProxyGrpcRequest(zzg2, zzo2, zzg, zzi, zzr, zzo);
    }
    
    public ProxyGrpcRequest[] zzaA(final int n) {
        return new ProxyGrpcRequest[n];
    }
}
