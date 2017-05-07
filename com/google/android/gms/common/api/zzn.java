// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzn implements Parcelable$Creator<Status>
{
    static void zza(final Status status, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, (Parcelable)status.zznH(), n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public Status zzY(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int zzg = 0;
        final int zzaj = zza.zzaj(parcel);
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
                    zzg = zza.zzg(parcel, zzai);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 2: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    pendingIntent = zza.zza(parcel, zzai, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new Status(zzg2, zzg, zzo, pendingIntent);
    }
    
    public Status[] zzbh(final int n) {
        return new Status[n];
    }
}
