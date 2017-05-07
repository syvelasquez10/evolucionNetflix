// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IBinder;

class IRoomService$Stub$Proxy implements IRoomService
{
    private IBinder lb;
    
    @Override
    public void Q(final boolean b) {
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
    public void a(IBinder binder, final IRoomServiceCallbacks roomServiceCallbacks) {
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
    public void a(final DataHolder dataHolder, final boolean b) {
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
    public void a(final byte[] array, final String s, final int n) {
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
    public void a(final byte[] array, final String[] array2) {
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
    public void bK(final String s) {
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
    public void bL(final String s) {
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
    public void c(final String s, final String s2, final String s3) {
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
    public void kD() {
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
    public void kE() {
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
    public void kF() {
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
    public void kG() {
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
    public void t(final String s, final int n) {
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
    public void u(final String s, final int n) {
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
