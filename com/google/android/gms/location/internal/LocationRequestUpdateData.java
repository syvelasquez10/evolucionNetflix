// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.location.zzc$zza;
import com.google.android.gms.location.zzd$zza;
import android.os.IBinder;
import com.google.android.gms.location.zzc;
import com.google.android.gms.location.zzd;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LocationRequestUpdateData implements SafeParcelable
{
    public static final zzn CREATOR;
    PendingIntent mPendingIntent;
    private final int mVersionCode;
    int zzaFJ;
    LocationRequestInternal zzaFK;
    zzd zzaFL;
    zzc zzaFM;
    zzg zzaFN;
    
    static {
        CREATOR = new zzn();
    }
    
    LocationRequestUpdateData(final int mVersionCode, final int zzaFJ, final LocationRequestInternal zzaFK, final IBinder binder, final PendingIntent mPendingIntent, final IBinder binder2, final IBinder binder3) {
        final zzg zzg = null;
        this.mVersionCode = mVersionCode;
        this.zzaFJ = zzaFJ;
        this.zzaFK = zzaFK;
        zzd zzbX;
        if (binder == null) {
            zzbX = null;
        }
        else {
            zzbX = zzd$zza.zzbX(binder);
        }
        this.zzaFL = zzbX;
        this.mPendingIntent = mPendingIntent;
        zzc zzbW;
        if (binder2 == null) {
            zzbW = null;
        }
        else {
            zzbW = zzc$zza.zzbW(binder2);
        }
        this.zzaFM = zzbW;
        zzg zzbZ;
        if (binder3 == null) {
            zzbZ = zzg;
        }
        else {
            zzbZ = zzg$zza.zzbZ(binder3);
        }
        this.zzaFN = zzbZ;
    }
    
    public static LocationRequestUpdateData zza(final zzc zzc, final zzg zzg) {
        final IBinder binder = zzc.asBinder();
        IBinder binder2;
        if (zzg != null) {
            binder2 = zzg.asBinder();
        }
        else {
            binder2 = null;
        }
        return new LocationRequestUpdateData(1, 2, null, null, null, binder, binder2);
    }
    
    public static LocationRequestUpdateData zza(final zzd zzd, final zzg zzg) {
        final IBinder binder = zzd.asBinder();
        IBinder binder2;
        if (zzg != null) {
            binder2 = zzg.asBinder();
        }
        else {
            binder2 = null;
        }
        return new LocationRequestUpdateData(1, 2, null, binder, null, null, binder2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzn.zza(this, parcel, n);
    }
    
    IBinder zzwF() {
        if (this.zzaFL == null) {
            return null;
        }
        return this.zzaFL.asBinder();
    }
    
    IBinder zzwG() {
        if (this.zzaFM == null) {
            return null;
        }
        return this.zzaFM.asBinder();
    }
    
    IBinder zzwH() {
        if (this.zzaFN == null) {
            return null;
        }
        return this.zzaFN.asBinder();
    }
}
