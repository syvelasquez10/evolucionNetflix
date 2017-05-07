// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IProjectionDelegate$a extends Binder implements IProjectionDelegate
{
    public static IProjectionDelegate bj(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IProjectionDelegate) {
            return (IProjectionDelegate)queryLocalInterface;
        }
        return new IProjectionDelegate$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                final LatLng fromScreenLocation = this.fromScreenLocation(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (fromScreenLocation != null) {
                    parcel2.writeInt(1);
                    fromScreenLocation.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                LatLng cm;
                if (parcel.readInt() != 0) {
                    cm = LatLng.CREATOR.cM(parcel);
                }
                else {
                    cm = null;
                }
                final d screenLocation = this.toScreenLocation(cm);
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (screenLocation != null) {
                    binder2 = screenLocation.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                final VisibleRegion visibleRegion = this.getVisibleRegion();
                parcel2.writeNoException();
                if (visibleRegion != null) {
                    parcel2.writeInt(1);
                    visibleRegion.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
}
