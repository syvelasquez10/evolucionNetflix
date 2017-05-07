// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ParcelFileDescriptor;
import android.os.IInterface;

public interface IRoomServiceCallbacks extends IInterface
{
    void a(final ParcelFileDescriptor p0, final int p1) throws RemoteException;
    
    void a(final ConnectionInfo p0) throws RemoteException;
    
    void a(final String p0, final byte[] p1, final int p2) throws RemoteException;
    
    void a(final String p0, final String[] p1) throws RemoteException;
    
    void aD(final IBinder p0) throws RemoteException;
    
    void b(final String p0, final String[] p1) throws RemoteException;
    
    void bM(final String p0) throws RemoteException;
    
    void bN(final String p0) throws RemoteException;
    
    void bO(final String p0) throws RemoteException;
    
    void bP(final String p0) throws RemoteException;
    
    void bQ(final String p0) throws RemoteException;
    
    void bR(final String p0) throws RemoteException;
    
    void c(final int p0, final int p1, final String p2) throws RemoteException;
    
    void c(final String p0, final byte[] p1) throws RemoteException;
    
    void c(final String p0, final String[] p1) throws RemoteException;
    
    void d(final String p0, final String[] p1) throws RemoteException;
    
    void dF(final int p0) throws RemoteException;
    
    void e(final String p0, final String[] p1) throws RemoteException;
    
    void f(final String p0, final String[] p1) throws RemoteException;
    
    void g(final String p0, final String[] p1) throws RemoteException;
    
    void i(final String p0, final boolean p1) throws RemoteException;
    
    void kH() throws RemoteException;
    
    void kI() throws RemoteException;
    
    void onP2PConnected(final String p0) throws RemoteException;
    
    void onP2PDisconnected(final String p0) throws RemoteException;
    
    void v(final String p0, final int p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IRoomServiceCallbacks
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IRoomServiceCallbacks");
        }
        
        public static IRoomServiceCallbacks aE(final IBinder binder) {
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
                    this.bM(parcel.readString());
                    return true;
                }
                case 1004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bN(parcel.readString());
                    return true;
                }
                case 1005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bO(parcel.readString());
                    return true;
                }
                case 1006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bP(parcel.readString());
                    return true;
                }
                case 1007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bQ(parcel.readString());
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
                    this.kH();
                    return true;
                }
                case 1017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.g(parcel.readString(), parcel.createStringArray());
                    return true;
                }
                case 1018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.c(parcel.readString(), parcel.createByteArray());
                    return true;
                }
                case 1019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.bR(parcel.readString());
                    return true;
                }
                case 1020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.dF(parcel.readInt());
                    return true;
                }
                case 1021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.aD(parcel.readStrongBinder());
                    return true;
                }
                case 1022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    ConnectionInfo cf = connectionInfo;
                    if (parcel.readInt() != 0) {
                        cf = ConnectionInfo.CREATOR.cf(parcel);
                    }
                    this.a(cf);
                    return true;
                }
                case 1023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.kI();
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
                    this.v(parcel.readString(), parcel.readInt());
                    return true;
                }
                case 1026: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.i(parcel.readString(), parcel.readInt() != 0);
                    return true;
                }
            }
        }
        
        private static class Proxy implements IRoomServiceCallbacks
        {
            private IBinder lb;
            
            Proxy(final IBinder lb) {
                this.lb = lb;
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
                    this.lb.transact(1024, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1022, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1002, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1008, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void aD(final IBinder binder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(1021, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void b(final String s, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    this.lb.transact(1009, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bM(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1003, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bN(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1004, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bO(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1005, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bP(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1006, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bQ(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1007, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void bR(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(1019, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1001, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final String s, final byte[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    this.lb.transact(1018, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1010, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1011, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void dF(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeInt(n);
                    this.lb.transact(1020, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1012, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1013, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1017, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final String s, final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(1026, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kH() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.lb.transact(1016, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void kI() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.lb.transact(1023, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1014, obtain, (Parcel)null, 1);
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
                    this.lb.transact(1015, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void v(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.lb.transact(1025, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
