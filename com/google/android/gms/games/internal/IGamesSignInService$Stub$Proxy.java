// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.IBinder;

class IGamesSignInService$Stub$Proxy implements IGamesSignInService
{
    private IBinder lb;
    
    @Override
    public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2) {
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
            this.lb.transact(5006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String s3) {
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
            this.lb.transact(5005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String s3, final String[] array) {
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
            obtain.writeStringArray(array);
            this.lb.transact(5008, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String[] array) {
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
            this.lb.transact(5004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String[] array, final String s3) {
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
            this.lb.transact(5003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void b(final IGamesSignInCallbacks gamesSignInCallbacks, final String s, final String s2, final String s3) {
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
            this.lb.transact(5007, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String bI(String string) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            obtain.writeString(string);
            this.lb.transact(5001, obtain, obtain2, 0);
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
    public String bJ(String string) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            obtain.writeString(string);
            this.lb.transact(5002, obtain, obtain2, 0);
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
    public String h(String string, final boolean b) {
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
            this.lb.transact(5009, obtain, obtain2, 0);
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
    public void w(final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInService");
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(9001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
