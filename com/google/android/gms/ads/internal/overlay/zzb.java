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
    static void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, final Parcel parcel, int zzak) {
        zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, adLauncherIntentInfoParcel.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, adLauncherIntentInfoParcel.intentAction, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, adLauncherIntentInfoParcel.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, adLauncherIntentInfoParcel.mimeType, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, adLauncherIntentInfoParcel.packageName, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, adLauncherIntentInfoParcel.zzzY, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, adLauncherIntentInfoParcel.zzzZ, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, adLauncherIntentInfoParcel.zzAa, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }
    
    public AdLauncherIntentInfoParcel zzg(final Parcel parcel) {
        String zzo = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        String zzo2 = null;
        String zzo3 = null;
        String zzo4 = null;
        String zzo5 = null;
        String zzo6 = null;
        String zzo7 = null;
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
                    zzo7 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 3: {
                    zzo6 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 4: {
                    zzo5 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzo4 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 6: {
                    zzo3 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 7: {
                    zzo2 = zza.zzo(parcel, zzai);
                    continue;
                }
                case 8: {
                    zzo = zza.zzo(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new AdLauncherIntentInfoParcel(zzg, zzo7, zzo6, zzo5, zzo4, zzo3, zzo2, zzo);
    }
    
    public AdLauncherIntentInfoParcel[] zzs(final int n) {
        return new AdLauncherIntentInfoParcel[n];
    }
}
