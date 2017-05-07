// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface h extends IInterface
{
    void c(final boolean p0, final boolean p1) throws RemoteException;
    
    public abstract static class a extends Binder implements h
    {
        public static h ad(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof h) {
                return (h)queryLocalInterface;
            }
            return new h.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean b = false;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
                    final boolean b2 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        b = true;
                    }
                    this.c(b2, b);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements h
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void c(final boolean b, final boolean b2) throws RemoteException {
                final boolean b3 = true;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
                    int n;
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    int n2;
                    if (b2) {
                        n2 = (b3 ? 1 : 0);
                    }
                    else {
                        n2 = 0;
                    }
                    obtain.writeInt(n2);
                    this.lb.transact(1, obtain, obtain2, 0);
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
