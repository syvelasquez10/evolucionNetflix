// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface br extends IInterface
{
    void a(final d p0, final ah p1, final String p2, final bs p3) throws RemoteException;
    
    void a(final d p0, final ah p1, final String p2, final String p3, final bs p4) throws RemoteException;
    
    void a(final d p0, final ak p1, final ah p2, final String p3, final bs p4) throws RemoteException;
    
    void a(final d p0, final ak p1, final ah p2, final String p3, final String p4, final bs p5) throws RemoteException;
    
    void destroy() throws RemoteException;
    
    d getView() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    public abstract static class a extends Binder implements br
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }
        
        public static br j(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (queryLocalInterface != null && queryLocalInterface instanceof br) {
                return (br)queryLocalInterface;
            }
            return new br.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final ah ah = null;
            final IBinder binder = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d k = d.a.K(parcel.readStrongBinder());
                    ak b;
                    if (parcel.readInt() != 0) {
                        b = ak.CREATOR.b(parcel);
                    }
                    else {
                        b = null;
                    }
                    ah a;
                    if (parcel.readInt() != 0) {
                        a = com.google.android.gms.internal.ah.CREATOR.a(parcel);
                    }
                    else {
                        a = null;
                    }
                    this.a(k, b, a, parcel.readString(), bs.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d view = this.getView();
                    parcel2.writeNoException();
                    IBinder binder2 = binder;
                    if (view != null) {
                        binder2 = view.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d i = d.a.K(parcel.readStrongBinder());
                    ah a2 = ah;
                    if (parcel.readInt() != 0) {
                        a2 = com.google.android.gms.internal.ah.CREATOR.a(parcel);
                    }
                    this.a(i, a2, parcel.readString(), bs.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.showInterstitial();
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.destroy();
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d j = d.a.K(parcel.readStrongBinder());
                    ak b2;
                    if (parcel.readInt() != 0) {
                        b2 = ak.CREATOR.b(parcel);
                    }
                    else {
                        b2 = null;
                    }
                    ah a3;
                    if (parcel.readInt() != 0) {
                        a3 = com.google.android.gms.internal.ah.CREATOR.a(parcel);
                    }
                    else {
                        a3 = null;
                    }
                    this.a(j, b2, a3, parcel.readString(), parcel.readString(), bs.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d l = d.a.K(parcel.readStrongBinder());
                    ah a4;
                    if (parcel.readInt() != 0) {
                        a4 = com.google.android.gms.internal.ah.CREATOR.a(parcel);
                    }
                    else {
                        a4 = null;
                    }
                    this.a(l, a4, parcel.readString(), parcel.readString(), bs.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.pause();
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.resume();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements br
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final d d, final ah ah, final String s, final bs bs) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (ah != null) {
                        obtain.writeInt(1);
                        ah.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder3 = binder;
                    if (bs != null) {
                        binder3 = bs.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d, final ah ah, final String s, final String s2, final bs bs) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (ah != null) {
                        obtain.writeInt(1);
                        ah.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder3 = binder;
                    if (bs != null) {
                        binder3 = bs.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.kn.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d, final ak ak, final ah ah, final String s, final bs bs) throws RemoteException {
                while (true) {
                    final IBinder binder = null;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder2;
                            if (d != null) {
                                binder2 = d.asBinder();
                            }
                            else {
                                binder2 = null;
                            }
                            obtain.writeStrongBinder(binder2);
                            if (ak != null) {
                                obtain.writeInt(1);
                                ak.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (ah != null) {
                                obtain.writeInt(1);
                                ah.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                IBinder binder3 = binder;
                                if (bs != null) {
                                    binder3 = bs.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.kn.transact(1, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final d d, final ak ak, final ah ah, final String s, final String s2, final bs bs) throws RemoteException {
                while (true) {
                    final IBinder binder = null;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder2;
                            if (d != null) {
                                binder2 = d.asBinder();
                            }
                            else {
                                binder2 = null;
                            }
                            obtain.writeStrongBinder(binder2);
                            if (ak != null) {
                                obtain.writeInt(1);
                                ak.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (ah != null) {
                                obtain.writeInt(1);
                                ah.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                obtain.writeString(s2);
                                IBinder binder3 = binder;
                                if (bs != null) {
                                    binder3 = bs.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.kn.transact(6, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.kn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.kn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.K(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void pause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.kn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void resume() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.kn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void showInterstitial() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.kn.transact(4, obtain, obtain2, 0);
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
