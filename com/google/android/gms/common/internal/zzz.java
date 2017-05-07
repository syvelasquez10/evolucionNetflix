// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzz implements Parcelable$Creator<ResolveAccountResponse>
{
    static void zza(final ResolveAccountResponse resolveAccountResponse, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.zzacC, false);
        zzb.zza(parcel, 3, (Parcelable)resolveAccountResponse.zzoP(), n, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzoQ());
        zzb.zza(parcel, 5, resolveAccountResponse.zzoR());
        zzb.zzH(parcel, zzak);
    }
    
    public ResolveAccountResponse zzag(final Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean zzc = false;
        final int zzaj = zza.zzaj(parcel);
        boolean zzc2 = false;
        IBinder zzp = null;
        int zzg = 0;
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
                    zzp = zza.zzp(parcel, zzai);
                    continue;
                }
                case 3: {
                    connectionResult = zza.zza(parcel, zzai, ConnectionResult.CREATOR);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzai);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzai);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new ResolveAccountResponse(zzg, zzp, connectionResult, zzc2, zzc);
    }
    
    public ResolveAccountResponse[] zzbF(final int n) {
        return new ResolveAccountResponse[n];
    }
}
