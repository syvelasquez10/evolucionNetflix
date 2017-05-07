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
        final int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, connectionResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)connectionResult.getResolution(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, connectionResult.getErrorMessage(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public ConnectionResult zzac(final Parcel parcel) {
        String zzp = null;
        int n = 0;
        final int zzap = zza.zzap(parcel);
        PendingIntent pendingIntent = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            int n4 = 0;
            int n5 = 0;
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    final int n3 = n;
                    n4 = n2;
                    n5 = n3;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzao);
                    n5 = n;
                    n4 = zzg;
                    break;
                }
                case 2: {
                    final int zzg2 = zza.zzg(parcel, zzao);
                    n4 = n2;
                    n5 = zzg2;
                    break;
                }
                case 3: {
                    pendingIntent = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    final int n6 = n2;
                    n5 = n;
                    n4 = n6;
                    break;
                }
                case 4: {
                    zzp = zza.zzp(parcel, zzao);
                    final int n7 = n2;
                    n5 = n;
                    n4 = n7;
                    break;
                }
            }
            final int n8 = n4;
            n = n5;
            n2 = n8;
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ConnectionResult(n2, n, pendingIntent, zzp);
    }
    
    public ConnectionResult[] zzbh(final int n) {
        return new ConnectionResult[n];
    }
}
