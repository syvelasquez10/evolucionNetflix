// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzc$zza extends Binder implements zzc
{
    public static zzc zzbW(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzc) {
            return (zzc)queryLocalInterface;
        }
        return new zzc$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final LocationAvailability locationAvailability = null;
        final LocationResult locationResult = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.location.ILocationCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.ILocationCallback");
                LocationResult locationResult2 = locationResult;
                if (parcel.readInt() != 0) {
                    locationResult2 = (LocationResult)LocationResult.CREATOR.createFromParcel(parcel);
                }
                this.onLocationResult(locationResult2);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.location.ILocationCallback");
                LocationAvailability fromParcel = locationAvailability;
                if (parcel.readInt() != 0) {
                    fromParcel = LocationAvailability.CREATOR.createFromParcel(parcel);
                }
                this.onLocationAvailability(fromParcel);
                return true;
            }
        }
    }
}
