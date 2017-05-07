// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IGamesSignInService extends IInterface
{
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String[] p3) throws RemoteException;
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String[] p3, final String p4) throws RemoteException;
    
    String aK(final String p0) throws RemoteException;
    
    String aL(final String p0) throws RemoteException;
    
    void b(final IGamesSignInCallbacks p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    void b(final IGamesSignInCallbacks p0, final String p1, final String p2, final String[] p3) throws RemoteException;
    
    String f(final String p0, final boolean p1) throws RemoteException;
    
    void l(final String p0, final String p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGamesSignInService
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesSignInService");
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IGamesSignInService");
                    return true;
                }
                case 5001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    final String ak = this.aK(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ak);
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    final String al = this.aL(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(al);
                    return true;
                }
                case 5009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    final String f = this.f(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeString(f);
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.a(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.a(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.a(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.a(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.b(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.b(IGamesSignInCallbacks.Stub.O(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 9001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                    this.l(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class Proxy implements IGamesSignInService
        {
            private IBinder kn;
            
            @Override
            public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.kn.transact(5005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.kn.transact(5004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String[] array, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    obtain.writeString(s3);
                    this.kn.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String aK(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    obtain.writeString(string);
                    this.kn.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String aL(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    obtain.writeString(string);
                    this.kn.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void b(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String s3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeString(s3);
                    this.kn.transact(5007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    IBinder binder;
                    if (gamesSignInCallbacks != null) {
                        binder = gamesSignInCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.kn.transact(5008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String f(String string, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    obtain.writeString(string);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5009, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(9001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
