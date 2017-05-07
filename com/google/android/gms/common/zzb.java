// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<ConnectionResult>
{
    static void zza(final ConnectionResult connectionResult, final Parcel parcel, final int n) {
        final int zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, connectionResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)connectionResult.getResolution(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }
    
    public ConnectionResult zzW(final Parcel parcel) {
        int zzg = 0;
        final int zzaj = zza.zzaj(parcel);
        PendingIntent pendingIntent = null;
        int zzg2 = 0;
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
                case 2: {
                    zzg = zza.zzg(parcel, zzai);
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
        return new ConnectionResult(zzg2, zzg, pendingIntent);
    }
    
    public ConnectionResult[] zzba(final int n) {
        return new ConnectionResult[n];
    }
}
