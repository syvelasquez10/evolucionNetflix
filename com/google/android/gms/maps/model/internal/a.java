// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import android.graphics.Bitmap;
import android.os.IInterface;

public interface a extends IInterface
{
    d b(final Bitmap p0) throws RemoteException;
    
    d bX(final String p0) throws RemoteException;
    
    d bY(final String p0) throws RemoteException;
    
    d bZ(final String p0) throws RemoteException;
    
    d c(final float p0) throws RemoteException;
    
    d eM(final int p0) throws RemoteException;
    
    d mQ() throws RemoteException;
    
    public abstract static class a extends Binder implements a
    {
        public static a bp(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof a) {
                return (a)queryLocalInterface;
            }
            return new a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
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
        
        private static class a implements com.google.android.gms.maps.model.internal.a
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public d b(final Bitmap bitmap) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    if (bitmap != null) {
                        obtain.writeInt(1);
                        bitmap.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d bX(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(s);
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d bY(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(s);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d bZ(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeString(s);
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d c(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeFloat(n);
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d eM(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    obtain.writeInt(n);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d mQ() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
