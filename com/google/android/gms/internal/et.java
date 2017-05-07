// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface et extends IInterface
{
    void a(final es p0) throws RemoteException;
    
    boolean e(final String p0, final String p1) throws RemoteException;
    
    public abstract static class a extends Binder implements et
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
        }
        
        public static et A(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof et) {
                return (et)queryLocalInterface;
            }
            return new et.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
                    final boolean e = this.e(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (e) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
                    this.a(es.a.z(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements et
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final es es) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
                    IBinder binder;
                    if (es != null) {
                        binder = es.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(2, obtain, obtain2, 0);
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
            public boolean e(final String s, final String s2) throws RemoteException {
                boolean b = true;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherAdViewListener");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        b = false;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
