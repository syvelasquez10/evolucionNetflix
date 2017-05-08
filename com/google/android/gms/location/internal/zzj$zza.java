// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.location.LocationSettingsResult;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzj$zza extends Binder implements zzj
{
    public static zzj zzcc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzj) {
            return (zzj)queryLocalInterface;
        }
        return new zzj$zza$zza(binder);
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
                parcel2.writeString("com.google.android.gms.location.internal.ISettingsCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
                LocationSettingsResult locationSettingsResult;
                if (parcel.readInt() != 0) {
                    locationSettingsResult = (LocationSettingsResult)LocationSettingsResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    locationSettingsResult = null;
                }
                this.zza(locationSettingsResult);
                return true;
            }
        }
    }
}
