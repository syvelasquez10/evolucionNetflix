// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.f$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class m$a extends Binder implements m
{
    public m$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }
    
    public static m bd(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof m) {
            return (m)queryLocalInterface;
        }
        return new m$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                this.b(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                this.d(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerDragListener");
                this.c(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
