// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.dynamic.d;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class a$a extends Binder implements a
{
    public static a bp(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof a) {
            return (a)queryLocalInterface;
        }
        return new a$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        final IBinder binder3 = null;
        final IBinder binder4 = null;
        final IBinder binder5 = null;
        final IBinder binder6 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d em = this.eM(parcel.readInt());
                parcel2.writeNoException();
                IBinder binder7;
                if (em != null) {
                    binder7 = em.asBinder();
                }
                else {
                    binder7 = null;
                }
                parcel2.writeStrongBinder(binder7);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d bx = this.bX(parcel.readString());
                parcel2.writeNoException();
                IBinder binder8 = binder6;
                if (bx != null) {
                    binder8 = bx.asBinder();
                }
                parcel2.writeStrongBinder(binder8);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d by = this.bY(parcel.readString());
                parcel2.writeNoException();
                IBinder binder9 = binder;
                if (by != null) {
                    binder9 = by.asBinder();
                }
                parcel2.writeStrongBinder(binder9);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d mq = this.mQ();
                parcel2.writeNoException();
                IBinder binder10 = binder2;
                if (mq != null) {
                    binder10 = mq.asBinder();
                }
                parcel2.writeStrongBinder(binder10);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d c = this.c(parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder11 = binder3;
                if (c != null) {
                    binder11 = c.asBinder();
                }
                parcel2.writeStrongBinder(binder11);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                Bitmap bitmap;
                if (parcel.readInt() != 0) {
                    bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                }
                else {
                    bitmap = null;
                }
                final d b = this.b(bitmap);
                parcel2.writeNoException();
                IBinder binder12 = binder4;
                if (b != null) {
                    binder12 = b.asBinder();
                }
                parcel2.writeStrongBinder(binder12);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                final d bz = this.bZ(parcel.readString());
                parcel2.writeNoException();
                IBinder binder13 = binder5;
                if (bz != null) {
                    binder13 = bz.asBinder();
                }
                parcel2.writeStrongBinder(binder13);
                return true;
            }
        }
    }
}
