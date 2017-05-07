// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<ProxyResponse>
{
    static void zza(final ProxyResponse proxyResponse, final Parcel parcel, final int n) {
        final int zzac = zzb.zzac(parcel);
        zzb.zzc(parcel, 1, proxyResponse.zzPt);
        zzb.zzc(parcel, 1000, proxyResponse.versionCode);
        zzb.zza(parcel, 2, (Parcelable)proxyResponse.zzPu, n, false);
        zzb.zzc(parcel, 3, proxyResponse.zzPv);
        zzb.zza(parcel, 4, proxyResponse.zzPs, false);
        zzb.zza(parcel, 5, proxyResponse.zzPr, false);
        zzb.zzH(parcel, zzac);
    }
    
    public ProxyResponse zzI(final Parcel parcel) {
        byte[] zzr = null;
        int zzg = 0;
        final int zzab = zza.zzab(parcel);
        Bundle zzq = null;
        PendingIntent pendingIntent = null;
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
                    zzg2 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 1000: {
                    zzg3 = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 2: {
                    pendingIntent = zza.zza(parcel, zzaa, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzaa);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzaa);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzaa);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzab) {
            throw new zza$zza("Overread allowed size end=" + zzab, parcel);
        }
        return new ProxyResponse(zzg3, zzg2, pendingIntent, zzg, zzq, zzr);
    }
    
    public ProxyResponse[] zzay(final int n) {
        return new ProxyResponse[n];
    }
}
