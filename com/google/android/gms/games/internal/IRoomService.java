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
    void B(final boolean p0) throws RemoteException;
    
    void a(final IBinder p0, final IRoomServiceCallbacks p1) throws RemoteException;
    
    void a(final DataHolder p0, final boolean p1) throws RemoteException;
    
    void a(final String p0, final String p1, final String p2) throws RemoteException;
    
    void a(final byte[] p0, final String p1, final int p2) throws RemoteException;
    
    void a(final byte[] p0, final String[] p1) throws RemoteException;
    
    void aM(final String p0) throws RemoteException;
    
    void aN(final String p0) throws RemoteException;
    
    void gM() throws RemoteException;
    
    void gN() throws RemoteException;
    
    void gO() throws RemoteException;
    
    void gP() throws RemoteException;
    
    void p(final String p0, final int p1) throws RemoteException;
    
    void q(final String p0, final int p1) throws RemoteException;
    
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
                    this.a(parcel.readStrongBinder(), IRoomServiceCallbacks.Stub.Q(parcel.readStrongBinder()));
                    return true;
                }
                case 1002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.gM();
                    return true;
                }
                case 1003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.gN();
                    return true;
                }
                case 1004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.a(parcel.readString(), parcel.readString(), parcel.readString());
                    return true;
                }
                case 1005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.gO();
                    return true;
                }
                case 1006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    DataHolder fromParcel;
                    if (parcel.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel = null;
                    }
                    if (parcel.readInt() != 0) {
                        b2 = true;
                    }
                    this.a(fromParcel, b2);
                    return true;
                }
                case 1007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.gP();
                    return true;
                }
                case 1008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    boolean b3 = b;
                    if (parcel.readInt() != 0) {
                        b3 = true;
                    }
                    this.B(b3);
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
                    this.p(parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.q(parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.aM(parcel.readString());
                    return true;
                }
                case 1014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                    this.aN(parcel.readString());
                    return true;
                }
            }
        }
        
        private static class Proxy implements IRoomService
        {
            private IBinder kn;
            
            @Override
            public void B(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(1008, obtain, (Parcel)null, 1);
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
                    this.kn.transact(1001, obtain, (Parcel)null, 1);
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
                                    this.kn.transact(1006, obtain, (Parcel)null, 1);
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
            public void a(final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.kn.transact(1004, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
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
                    this.kn.transact(1009, obtain, (Parcel)null, 1);
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
                    this.kn.transact(1010, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aM(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    this.kn.transact(1013, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aN(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    this.kn.transact(1014, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void gM() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.kn.transact(1002, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void gN() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.kn.transact(1003, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void gO() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.kn.transact(1005, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void gP() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    this.kn.transact(1007, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void p(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(1011, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void q(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(1012, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
