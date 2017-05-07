// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.firstparty.shared;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza$zza;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<ScopeDetail>
{
    static void zza(final ScopeDetail scopeDetail, final Parcel parcel, final int n) {
        final int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, scopeDetail.version);
        zzb.zza(parcel, 2, scopeDetail.description, false);
        zzb.zza(parcel, 3, scopeDetail.zzTH, false);
        zzb.zza(parcel, 4, scopeDetail.zzTI, false);
        zzb.zza(parcel, 5, scopeDetail.zzTJ, false);
        zzb.zza(parcel, 6, scopeDetail.zzTK, false);
        zzb.zzb(parcel, 7, scopeDetail.zzTL, false);
        zzb.zza(parcel, 8, (Parcelable)scopeDetail.zzTM, n, false);
        zzb.zzI(parcel, zzaq);
    }
    
    public ScopeDetail zzV(final Parcel parcel) {
        FACLData faclData = null;
        final int zzap = zza.zzap(parcel);
        int zzg = 0;
        ArrayList<String> zzD = new ArrayList<String>();
        String zzp = null;
        String zzp2 = null;
        String zzp3 = null;
        String zzp4 = null;
        String zzp5 = null;
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
                    zzp5 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 3: {
                    zzp4 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 4: {
                    zzp3 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 5: {
                    zzp2 = zza.zzp(parcel, zzao);
                    continue;
                }
                case 6: {
                    zzp = zza.zzp(parcel, zzao);
                    continue;
                }
                case 7: {
                    zzD = zza.zzD(parcel, zzao);
                    continue;
                }
                case 8: {
                    faclData = zza.zza(parcel, zzao, (android.os.Parcelable$Creator<FACLData>)FACLData.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza$zza("Overread allowed size end=" + zzap, parcel);
        }
        return new ScopeDetail(zzg, zzp5, zzp4, zzp3, zzp2, zzp, zzD, faclData);
    }
    
    public ScopeDetail[] zzaM(final int n) {
        return new ScopeDetail[n];
    }
}
