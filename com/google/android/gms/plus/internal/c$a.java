// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class c$a extends Binder implements c
{
    public static c bF(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof c) {
            return (c)queryLocalInterface;
        }
        return new c$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                final d a = this.a(d$a.am(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                IBinder binder2;
                if (a != null) {
                    binder2 = a.asBinder();
                }
                else {
                    binder2 = null;
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
                final d a2 = this.a(d$a.am(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                IBinder binder3 = binder;
                if (a2 != null) {
                    binder3 = a2.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
        }
    }
}
