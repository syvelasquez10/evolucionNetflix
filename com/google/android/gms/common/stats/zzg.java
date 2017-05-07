// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<WakeLockEvent>
{
    static void zza(final WakeLockEvent wakeLockEvent, final Parcel parcel, int zzak) {
        zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, wakeLockEvent.mVersionCode);
        zzb.zza(parcel, 2, wakeLockEvent.getTimeMillis());
        zzb.zza(parcel, 4, wakeLockEvent.zzpE(), false);
        zzb.zzc(parcel, 5, wakeLockEvent.zzpG());
        zzb.zzb(parcel, 6, wakeLockEvent.zzpH(), false);
        zzb.zza(parcel, 8, wakeLockEvent.zzpC());
        zzb.zza(parcel, 10, wakeLockEvent.zzpF(), false);
        zzb.zzc(parcel, 11, wakeLockEvent.getEventType());
        zzb.zza(parcel, 12, wakeLockEvent.zzpA(), false);
        zzb.zza(parcel, 13, wakeLockEvent.zzpJ(), false);
        zzb.zzc(parcel, 14, wakeLockEvent.zzpI());
        zzb.zza(parcel, 15, wakeLockEvent.zzpK());
        zzb.zzH(parcel, zzak);
    }
    
    public WakeLockEvent zzav(final Parcel parcel) {
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzo = null;
        int zzg3 = 0;
        List<String> zzC = null;
        String zzo2 = null;
        long zzi2 = 0L;
        int zzg4 = 0;
        String zzo3 = null;
        String zzo4 = null;
        float zzl = 0.0f;
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
                case 2: {
                    zzi = zza.zzi(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzg3 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzC = zza.zzC(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzi2 = zza.zzi(parcel, zzai);
                    continue;
                }
                case 10: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 12: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 13: {
                    zzo4 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 14: {
                    zzg4 = zza.zzg(parcel, zzai);
                    continue;
                }
                case 15: {
                    zzl = zza.zzl(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new WakeLockEvent(zzg, zzi, zzg2, zzo, zzg3, zzC, zzo2, zzi2, zzg4, zzo3, zzo4, zzl);
    }
    
    public WakeLockEvent[] zzbU(final int n) {
        return new WakeLockEvent[n];
    }
}
