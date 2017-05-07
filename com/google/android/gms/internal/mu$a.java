// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class mu$a extends Binder implements mu
{
    public static mu aM(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof mu) {
            return (mu)queryLocalInterface;
        }
        return new mu$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final DataHolder dataHolder = null;
        final DataHolder dataHolder2 = null;
        final DataHolder dataHolder3 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                DataHolder z = dataHolder3;
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.W(z);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                DataHolder z2 = dataHolder;
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.X(z2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                DataHolder z3 = dataHolder2;
                if (parcel.readInt() != 0) {
                    z3 = DataHolder.CREATOR.z(parcel);
                }
                this.Y(z3);
                return true;
            }
        }
    }
}
