// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzg$zza extends Binder implements zzg
{
    public static zzg zzbZ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzg) {
            return (zzg)queryLocalInterface;
        }
        return new zzg$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                FusedLocationProviderResult fusedLocationProviderResult;
                if (parcel.readInt() != 0) {
                    fusedLocationProviderResult = (FusedLocationProviderResult)FusedLocationProviderResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    fusedLocationProviderResult = null;
                }
                this.zza(fusedLocationProviderResult);
                return true;
            }
        }
    }
}
