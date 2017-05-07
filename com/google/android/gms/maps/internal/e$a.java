// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.CameraPosition;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class e$a extends Binder implements e
{
    public e$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnCameraChangeListener");
    }
    
    public static e aV(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnCameraChangeListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof e) {
            return (e)queryLocalInterface;
        }
        return new e$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnCameraChangeListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnCameraChangeListener");
                CameraPosition ci;
                if (parcel.readInt() != 0) {
                    ci = CameraPosition.CREATOR.cI(parcel);
                }
                else {
                    ci = null;
                }
                this.onCameraChange(ci);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
