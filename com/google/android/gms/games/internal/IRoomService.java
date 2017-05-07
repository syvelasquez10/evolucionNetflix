// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Binder;
import com.google.android.gms.common.data.DataHolder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IRoomService extends IInterface
{
    void Q(final boolean p0) throws RemoteException;
    
    void a(final IBinder p0, final IRoomServiceCallbacks p1) throws RemoteException;
    
    void a(final DataHolder p0, final boolean p1) throws RemoteException;
    
    void a(final byte[] p0, final String p1, final int p2) throws RemoteException;
    
    void a(final byte[] p0, final String[] p1) throws RemoteException;
    
    void bK(final String p0) throws RemoteException;
    
    void bL(final String p0) throws RemoteException;
    
    void c(final String p0, final String p1, final String p2) throws RemoteException;
    
    void kD() throws RemoteException;
    
    void kE() throws RemoteException;
    
    void kF() throws RemoteException;
    
    void kG() throws RemoteException;
    
    void t(final String p0, final int p1) throws RemoteException;
    
    void u(final String p0, final int p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IRoomService
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IRoomService");
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final boolean b = false;
            boolean b2 = false;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IRoomService");
                    return true;
                }
                case 1001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.a(parcel.readStrongBinder(), IRoomServiceCallbacks.Stub.aE(parcel.readStrongBinder()));
                    return true;
                }
                case 1002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.kD();
                    return true;
                }
                case 1003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.kE();
                    return true;
                }
                case 1004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.c(parcel.readString(), parcel.readString(), parcel.readString());
                    return true;
                }
                case 1005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.kF();
                    return true;
                }
                case 1006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    DataHolder z;
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    else {
                        z = null;
                    }
                    if (parcel.readInt() != 0) {
                        b2 = true;
                    }
                    this.a(z, b2);
                    return true;
                }
                case 1007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.kG();
                    return true;
                }
                case 1008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    boolean b3 = b;
                    if (parcel.readInt() != 0) {
                        b3 = true;
                    }
                    this.Q(b3);
                    return true;
                }
                case 1009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.a(parcel.createByteArray(), parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.a(parcel.createByteArray(), parcel.createStringArray());
                    return true;
                }
                case 1011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.t(parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.u(parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.bK(parcel.readString());
                    return true;
                }
                case 1014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.bL(parcel.readString());
                    return true;
                }
            }
        }
        
        private static class Proxy implements IRoomService
        {
            private IBinder lb;
            
            @Override
            public void Q(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(1008, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(IBinder binder, final IRoomServiceCallbacks roomServiceCallbacks) throws RemoteException {
                final IBinder binder2 = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeStrongBinder(binder);
                    binder = binder2;
                    if (roomServiceCallbacks != null) {
                        binder = roomServiceCallbacks.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(1001, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataHolder dataHolder, final boolean b) throws RemoteException {
            Label_0080_Outer:
                while (true) {
                    int n = 1;
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        while (true) {
                            Label_0085: {
                                try {
                                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                                    if (dataHolder != null) {
                                        obtain.writeInt(1);
                                        dataHolder.writeToParcel(obtain, 0);
                                        break Label_0085;
                                    }
                                    obtain.writeInt(0);
                                    break Label_0085;
                                    obtain.writeInt(n);
                                    this.lb.transact(1006, obtain, (Parcel)null, 1);
                                    return;
                                }
                                finally {
                                    obtain.recycle();
                                }
                                n = 0;
                                continue Label_0080_Outer;
                            }
                            if (b) {
                                continue Label_0080_Outer;
                            }
                            break;
                        }
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final byte[] array, final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeByteArray(array);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.lb.transact(1009, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final byte[] array, final String[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeByteArray(array);
                    obtain.writeStringArray(array2);
                    this.lb.transact(1010, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void bK(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    this.lb.transact(1013, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bL(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    this.lb.transact(1014, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.lb.transact(1004, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kD() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.lb.transact(1002, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kE() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.lb.transact(1003, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kF() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.lb.transact(1005, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kG() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.lb.transact(1007, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void t(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.lb.transact(1011, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void u(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.lb.transact(1012, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
