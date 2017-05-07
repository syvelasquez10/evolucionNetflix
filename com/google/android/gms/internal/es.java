// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.d;
import android.os.RemoteException;
import android.os.IInterface;

public interface es extends IInterface
{
    void ar() throws RemoteException;
    
    void as() throws RemoteException;
    
    void c(final d p0) throws RemoteException;
    
    String cv() throws RemoteException;
    
    String cw() throws RemoteException;
    
    public abstract static class a extends Binder implements es
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
        }
        
        public static es z(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
            if (queryLocalInterface != null && queryLocalInterface instanceof es) {
                return (es)queryLocalInterface;
            }
            return new es.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    final String cv = this.cv();
                    parcel2.writeNoException();
                    parcel2.writeString(cv);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    final String cw = this.cw();
                    parcel2.writeNoException();
                    parcel2.writeString(cw);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.c(d.a.am(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.ar();
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.as();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements es
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void ar() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void as() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.lb.transact(5, obtain, obtain2, 0);
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
            public void c(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String cv() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String cw() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
