// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface ep extends IInterface
{
    void Y(final String p0) throws RemoteException;
    
    void Z(final String p0) throws RemoteException;
    
    void a(final double p0, final double p1, final boolean p2) throws RemoteException;
    
    void a(final String p0, final String p1, final long p2) throws RemoteException;
    
    void a(final String p0, final byte[] p1, final long p2) throws RemoteException;
    
    void a(final boolean p0, final double p1, final boolean p2) throws RemoteException;
    
    void aa(final String p0) throws RemoteException;
    
    void dH() throws RemoteException;
    
    void dO() throws RemoteException;
    
    void disconnect() throws RemoteException;
    
    void e(final String p0, final String p1) throws RemoteException;
    
    void e(final String p0, final boolean p1) throws RemoteException;
    
    public abstract static class a extends Binder implements ep
    {
        public static ep y(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
            if (queryLocalInterface != null && queryLocalInterface instanceof ep) {
                return (ep)queryLocalInterface;
            }
            return new ep.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean b = false;
            boolean b2 = false;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceController");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.disconnect();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    final String string = parcel.readString();
                    if (parcel.readInt() != 0) {
                        b2 = true;
                    }
                    this.e(string, b2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.e(parcel.readString(), parcel.readString());
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.dO();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.Y(parcel.readString());
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.dH();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.a(parcel.readDouble(), parcel.readDouble(), parcel.readInt() != 0);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    final boolean b3 = parcel.readInt() != 0;
                    final double double1 = parcel.readDouble();
                    if (parcel.readInt() != 0) {
                        b = true;
                    }
                    this.a(b3, double1, b);
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.a(parcel.readString(), parcel.readString(), parcel.readLong());
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.a(parcel.readString(), parcel.createByteArray(), parcel.readLong());
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.Z(parcel.readString());
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.aa(parcel.readString());
                    return true;
                }
            }
        }
        
        private static class a implements ep
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void Y(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    this.kn.transact(5, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void Z(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    this.kn.transact(11, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final double n, final double n2, final boolean b) throws RemoteException {
                int n3 = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeDouble(n);
                    obtain.writeDouble(n2);
                    if (!b) {
                        n3 = 0;
                    }
                    obtain.writeInt(n3);
                    this.kn.transact(7, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final String s2, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeLong(n);
                    this.kn.transact(9, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final byte[] array, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeLong(n);
                    this.kn.transact(10, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final boolean b, final double n, final boolean b2) throws RemoteException {
                final boolean b3 = true;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    int n2;
                    if (b) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    obtain.writeInt(n2);
                    obtain.writeDouble(n);
                    int n3;
                    if (b2) {
                        n3 = (b3 ? 1 : 0);
                    }
                    else {
                        n3 = 0;
                    }
                    obtain.writeInt(n3);
                    this.kn.transact(8, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aa(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    this.kn.transact(12, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void dH() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.kn.transact(6, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void dO() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.kn.transact(4, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void disconnect() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    this.kn.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final String s, final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
                    obtain.writeString(s);
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
