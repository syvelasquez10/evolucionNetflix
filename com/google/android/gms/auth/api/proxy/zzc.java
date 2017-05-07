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
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, proxyResponse.googlePlayServicesStatusCode);
        zzb.zzc(parcel, 1000, proxyResponse.versionCode);
        zzb.zza(parcel, 2, (Parcelable)proxyResponse.recoveryAction, n, false);
        zzb.zzc(parcel, 3, proxyResponse.statusCode);
        zzb.zza(parcel, 4, proxyResponse.zzRE, false);
        zzb.zza(parcel, 5, proxyResponse.body, false);
        zzb.zzH(parcel, zzak);
    }
    
    public ProxyResponse zzM(final Parcel parcel) {
        byte[] zzr = null;
        int zzg = 0;
        final int zzaj = zza.zzaj(parcel);
        Bundle zzq = null;
        PendingIntent pendingIntent = null;
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
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    pendingIntent = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ProxyResponse(zzg3, zzg2, pendingIntent, zzg, zzq, zzr);
    }
    
    public ProxyResponse[] zzaC(final int n) {
        return new ProxyResponse[n];
    }
}
