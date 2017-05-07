// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;

public interface IRoomServiceCallbacks extends IInterface
{
    void P(final IBinder p0) throws RemoteException;
    
    void a(final ParcelFileDescriptor p0, final int p1) throws RemoteException;
    
    void a(final ConnectionInfo p0) throws RemoteException;
    
    void a(final String p0, final byte[] p1, final int p2) throws RemoteException;
    
    void a(final String p0, final String[] p1) throws RemoteException;
    
    void aO(final String p0) throws RemoteException;
    
    void aP(final String p0) throws RemoteException;
    
    void aQ(final String p0) throws RemoteException;
    
    void aR(final String p0) throws RemoteException;
    
    void aS(final String p0) throws RemoteException;
    
    void aT(final String p0) throws RemoteException;
    
    void aU(final String p0) throws RemoteException;
    
    void b(final String p0, final String[] p1) throws RemoteException;
    
    void bb(final int p0) throws RemoteException;
    
    void c(final int p0, final int p1, final String p2) throws RemoteException;
    
    void c(final String p0, final String[] p1) throws RemoteException;
    
    void d(final String p0, final String[] p1) throws RemoteException;
    
    void e(final String p0, final String[] p1) throws RemoteException;
    
    void f(final String p0, final String[] p1) throws RemoteException;
    
    void g(final String p0, final String[] p1) throws RemoteException;
    
    void gQ() throws RemoteException;
    
    void gR() throws RemoteException;
    
    void onP2PConnected(final String p0) throws RemoteException;
    
    void onP2PDisconnected(final String p0) throws RemoteException;
    
    void r(final String p0, final int p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IRoomServiceCallbacks
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IRoomServiceCallbacks");
        }
        
        public static IRoomServiceCallbacks Q(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof IRoomServiceCallbacks) {
                return (IRoomServiceCallbacks)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final ParcelFileDescriptor parcelFileDescriptor = null;
            final ConnectionInfo connectionInfo = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    return true;
                }
                case 1001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.c(parcel.readInt(), parcel.readInt(), parcel.readString());
                    return true;
                }
                case 1002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.a(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                    return true;
                }
                case 1003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aO(parcel.readString());
                    return true;
                }
                case 1004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aP(parcel.readString());
                    return true;
                }
                case 1005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aQ(parcel.readString());
                    return true;
                }
                case 1006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aR(parcel.readString());
                    return true;
                }
                case 1007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aS(parcel.readString());
                    return true;
                }
                case 1008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.a(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.b(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.c(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.d(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.e(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.f(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.onP2PConnected(parcel.readString());
                    return true;
                }
                case 1015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.onP2PDisconnected(parcel.readString());
                    return true;
                }
                case 1016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.gQ();
                    return true;
                }
                case 1017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.g(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aT(parcel.readString());
                    return true;
                }
                case 1019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aU(parcel.readString());
                    return true;
                }
                case 1020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bb(parcel.readInt());
                    return true;
                }
                case 1021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.P(parcel.readStrongBinder());
                    return true;
                }
                case 1022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    ConnectionInfo ap = connectionInfo;
                    if (parcel.readInt() != 0) {
                        ap = ConnectionInfo.CREATOR.ap(parcel);
                    }
                    this.a(ap);
                    return true;
                }
                case 1023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.gR();
                    return true;
                }
                case 1024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    }
                    this.a(parcelFileDescriptor2, parcel.readInt());
                    return true;
                }
                case 1025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.r(parcel.readString(), parcel.readInt());
                    return true;
                }
            }
        }
        
        private static class Proxy implements IRoomServiceCallbacks
        {
            private IBinder kn;
            
            Proxy(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void P(final IBinder binder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(1021, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ParcelFileDescriptor parcelFileDescriptor, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.kn.transact(1024, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ConnectionInfo connectionInfo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    if (connectionInfo != null) {
                        obtain.writeInt(1);
                        connectionInfo.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(1022, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final byte[] array, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeInt(n);
                    this.kn.transact(1002, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1008, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aO(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1003, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aP(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1004, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aQ(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1005, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aR(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1006, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aS(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1007, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aT(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1018, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aU(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1019, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void b(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1009, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bb(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeInt(n);
                    this.kn.transact(1020, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final int n, final int n2, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeString(s);
                    this.kn.transact(1001, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1010, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1011, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1012, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1013, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.kn.transact(1017, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void gQ() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.kn.transact(1016, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void gR() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.kn.transact(1023, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void onP2PConnected(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1014, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void onP2PDisconnected(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(1015, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void r(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(1025, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
