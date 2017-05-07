// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.f$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class d$a extends Binder implements d
{
    public d$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }
    
    public static d aR(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
        if (queryLocalInterface != null && queryLocalInterface instanceof d) {
            return (d)queryLocalInterface;
        }
        return new d$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                final com.google.android.gms.dynamic.d f = this.f(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                IBinder binder3 = binder2;
                if (f != null) {
                    binder3 = f.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                final com.google.android.gms.dynamic.d g = this.g(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                IBinder binder4 = binder;
                if (g != null) {
                    binder4 = g.asBinder();
                }
                parcel2.writeStrongBinder(binder4);
                return true;
            }
        }
    }
}
