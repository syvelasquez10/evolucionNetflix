// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.IBinder;

class IGamesSignInCallbacks$Stub$Proxy implements IGamesSignInCallbacks
{
    private IBinder lb;
    
    IGamesSignInCallbacks$Stub$Proxy(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void T(final DataHolder dataHolder) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            if (dataHolder != null) {
                obtain.writeInt(1);
                dataHolder.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void U(final DataHolder dataHolder) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            if (dataHolder != null) {
                obtain.writeInt(1);
                dataHolder.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
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
    public void b(final int n, final Intent intent) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            obtain.writeInt(n);
            if (intent != null) {
                obtain.writeInt(1);
                intent.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void dD(final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            obtain.writeInt(n);
            this.lb.transact(5004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void dE(final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            obtain.writeInt(n);
            this.lb.transact(5006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void g(final DataHolder dataHolder) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            if (dataHolder != null) {
                obtain.writeInt(1);
                dataHolder.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
