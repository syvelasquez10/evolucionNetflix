// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<AuthAccountRequest>
{
    static void zza(final AuthAccountRequest authAccountRequest, final Parcel parcel, final int n) {
        final int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.zzacC, false);
        zzb.zza(parcel, 3, authAccountRequest.zzacD, n, false);
        zzb.zzH(parcel, zzak);
    }
    
    public AuthAccountRequest zzac(final Parcel parcel) {
        Scope[] array = null;
        final int zzaj = zza.zzaj(parcel);
        int zzg = 0;
        IBinder zzp = null;
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
                    array = zza.zzb(parcel, zzai, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza$zza("Overread allowed size end=" + zzaj, parcel);
        }
        return new AuthAccountRequest(zzg, zzp, array);
    }
    
    public AuthAccountRequest[] zzbw(final int n) {
        return new AuthAccountRequest[n];
    }
}
