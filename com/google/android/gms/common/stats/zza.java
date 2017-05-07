// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ConnectionEvent>
{
    static void zza(final ConnectionEvent connectionEvent, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, connectionEvent.mVersionCode);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 4, connectionEvent.zzpX(), false);
        zzb.zza(parcel, 5, connectionEvent.zzpY(), false);
        zzb.zza(parcel, 6, connectionEvent.zzpZ(), false);
        zzb.zza(parcel, 7, connectionEvent.zzqa(), false);
        zzb.zza(parcel, 8, connectionEvent.zzqb(), false);
        zzb.zza(parcel, 10, connectionEvent.zzqf());
        zzb.zza(parcel, 11, connectionEvent.zzqe());
        zzb.zzc(parcel, 12, connectionEvent.getEventType());
        zzb.zza(parcel, 13, connectionEvent.zzqc(), false);
        zzb.zzI(parcel, zzaq);
    }
    
    public ConnectionEvent zzaA(final Parcel parcel) {
        final int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        String zzp6 = null;
        long zzi2 = 0L;
        long zzi3 = 0L;
        while (parcel.dataPosition() < zzap) {
            final int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzp4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzp5 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
                case 10: {
                    zzi2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzao);
                    continue;
                }
                case 11: {
                    zzi3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzao);
                    continue;
                }
                case 12: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    continue;
                }
                case 13: {
                    zzp6 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ConnectionEvent(zzg, zzi, zzg2, zzp, zzp2, zzp3, zzp4, zzp5, zzp6, zzi2, zzi3);
    }
    
    public ConnectionEvent[] zzbY(final int n) {
        return new ConnectionEvent[n];
    }
}
