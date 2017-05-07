// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.IBinder;

class IRoomServiceCallbacks$Stub$Proxy implements IRoomServiceCallbacks
{
    private IBinder lb;
    
    IRoomServiceCallbacks$Stub$Proxy(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final ParcelFileDescriptor parcelFileDescriptor, final int n) {
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
    public void a(final ConnectionInfo connectionInfo) {
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
    public void a(final String s, final byte[] array, final int n) {
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
    public void a(final String s, final String[] array) {
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
    public void aD(final IBinder binder) {
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
    public void b(final String s, final String[] array) {
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
    public void bM(final String s) {
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
    public void bN(final String s) {
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
    public void bO(final String s) {
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
    public void bP(final String s) {
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
    public void bQ(final String s) {
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
    public void bR(final String s) {
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
    public void c(final int n, final int n2, final String s) {
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
    public void c(final String s, final byte[] array) {
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
    public void c(final String s, final String[] array) {
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
    public void d(final String s, final String[] array) {
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
    public void dF(final int n) {
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
    public void e(final String s, final String[] array) {
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
    public void f(final String s, final String[] array) {
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
    public void g(final String s, final String[] array) {
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
    public void i(final String s, final boolean b) {
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
    public void kH() {
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
    public void kI() {
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
    public void onP2PConnected(final String s) {
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
    public void onP2PDisconnected(final String s) {
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
    public void v(final String s, final int n) {
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
