// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface be extends IInterface
{
    IBinder a(final d p0, final ay p1, final String p2, final ct p3, final int p4) throws RemoteException;
    
    public abstract static class a extends Binder implements be
    {
        public static be g(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            if (queryLocalInterface != null && queryLocalInterface instanceof be) {
                return (be)queryLocalInterface;
            }
            return new be.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    final d am = d.a.am(parcel.readStrongBinder());
                    ay c;
                    if (parcel.readInt() != 0) {
                        c = ay.CREATOR.c(parcel);
                    }
                    else {
                        c = null;
                    }
                    final IBinder a = this.a(am, c, parcel.readString(), ct.a.l(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a);
                    return true;
                }
            }
        }
        
        private static class a implements be
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public IBinder a(final d d, final ay ay, final String s, final ct ct, final int n) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (ay != null) {
                        obtain.writeInt(1);
                        ay.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder3 = binder;
                    if (ct != null) {
                        binder3 = ct.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    obtain.writeInt(n);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
