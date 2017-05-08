// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ActivityRecognitionResultCreator implements Parcelable$Creator<ActivityRecognitionResult>
{
    static void zza(final ActivityRecognitionResult activityRecognitionResult, final Parcel parcel, int zzaq) {
        zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, activityRecognitionResult.zzaEb, false);
        zzb.zzc(parcel, 1000, activityRecognitionResult.getVersionCode());
        zzb.zza(parcel, 2, activityRecognitionResult.zzaEc);
        zzb.zza(parcel, 3, activityRecognitionResult.zzaEd);
        zzb.zzc(parcel, 4, activityRecognitionResult.zzaEe);
        zzb.zzI(parcel, zzaq);
    }
    
    public ActivityRecognitionResult createFromParcel(final Parcel parcel) {
        long zzi = 0L;
        int zzg = 0;
        final int zzap = zza.zzap(parcel);
        List<DetectedActivity> zzc = null;
        long zzi2 = 0L;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzap) {
            final int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                default: {
                    zza.zzb(parcel, zzao);
                    continue;
                }
                case 1: {
                    zzc = zza.zzc(parcel, zzao, (android.os.Parcelable$Creator<DetectedActivity>)DetectedActivity.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzao);
                    continue;
                }
                case 2: {
                    zzi2 = zza.zzi(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzao);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ActivityRecognitionResult(zzg2, zzc, zzi2, zzi, zzg);
    }
    
    public ActivityRecognitionResult[] newArray(final int n) {
        return new ActivityRecognitionResult[n];
    }
}
