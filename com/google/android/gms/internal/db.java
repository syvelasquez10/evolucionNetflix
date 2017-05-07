// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface db extends IInterface
{
    cz b(final cx p0) throws RemoteException;
    
    public abstract static class a extends Binder implements db
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.request.IAdRequestService");
        }
        
        public static db s(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (queryLocalInterface != null && queryLocalInterface instanceof db) {
                return (db)queryLocalInterface;
            }
            return new db.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                    cx f;
                    if (parcel.readInt() != 0) {
                        f = cx.CREATOR.f(parcel);
                    }
                    else {
                        f = null;
                    }
                    final cz b = this.b(f);
                    parcel2.writeNoException();
                    if (b != null) {
                        parcel2.writeInt(1);
                        b.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
            }
        }
        
        private static class a implements db
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public cz b(final cx cx) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                        if (cx != null) {
                            obtain.writeInt(1);
                            cx.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.kn.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return cz.CREATOR.g(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
        }
    }
}
