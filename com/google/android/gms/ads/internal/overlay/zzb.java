// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<AdLauncherIntentInfoParcel>
{
    static void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, final Parcel parcel, int zzaq) {
        zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, adLauncherIntentInfoParcel.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, adLauncherIntentInfoParcel.intentAction, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, adLauncherIntentInfoParcel.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, adLauncherIntentInfoParcel.mimeType, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, adLauncherIntentInfoParcel.packageName, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, adLauncherIntentInfoParcel.zzAL, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, adLauncherIntentInfoParcel.zzAM, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, adLauncherIntentInfoParcel.zzAN, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }
    
    public AdLauncherIntentInfoParcel zzf(final Parcel parcel) {
        String zzp = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
        String zzp6 = null;
        String zzp7 = null;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzp7 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp6 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp5 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 8: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new AdLauncherIntentInfoParcel(zzg, zzp7, zzp6, zzp5, zzp4, zzp3, zzp2, zzp);
    }
    
    public AdLauncherIntentInfoParcel[] zzs(final int n) {
        return new AdLauncherIntentInfoParcel[n];
    }
}
