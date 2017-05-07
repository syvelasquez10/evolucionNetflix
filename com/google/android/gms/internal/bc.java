// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import android.os.IInterface;

public interface bc extends IInterface
{
    void a(final b p0, final v p1, final String p2, final bd p3) throws RemoteException;
    
    void a(final b p0, final v p1, final String p2, final String p3, final bd p4) throws RemoteException;
    
    void a(final b p0, final x p1, final v p2, final String p3, final bd p4) throws RemoteException;
    
    void a(final b p0, final x p1, final v p2, final String p3, final String p4, final bd p5) throws RemoteException;
    
    void destroy() throws RemoteException;
    
    b getView() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    public abstract static class a extends Binder implements bc
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }
        
        public static bc j(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (queryLocalInterface != null && queryLocalInterface instanceof bc) {
                return (bc)queryLocalInterface;
            }
            return new bc.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final v v = null;
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
                    final b e = b.a.E(parcel.readStrongBinder());
                    x b;
                    if (parcel.readInt() != 0) {
                        b = x.CREATOR.b(parcel);
                    }
                    else {
                        b = null;
                    }
                    v a;
                    if (parcel.readInt() != 0) {
                        a = com.google.android.gms.internal.v.CREATOR.a(parcel);
                    }
                    else {
                        a = null;
                    }
                    this.a(e, b, a, parcel.readString(), bd.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final b view = this.getView();
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
                    final b e2 = b.a.E(parcel.readStrongBinder());
                    v a2 = v;
                    if (parcel.readInt() != 0) {
                        a2 = com.google.android.gms.internal.v.CREATOR.a(parcel);
                    }
                    this.a(e2, a2, parcel.readString(), bd.a.k(parcel.readStrongBinder()));
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
                    final b e3 = b.a.E(parcel.readStrongBinder());
                    x b2;
                    if (parcel.readInt() != 0) {
                        b2 = x.CREATOR.b(parcel);
                    }
                    else {
                        b2 = null;
                    }
                    v a3;
                    if (parcel.readInt() != 0) {
                        a3 = com.google.android.gms.internal.v.CREATOR.a(parcel);
                    }
                    else {
                        a3 = null;
                    }
                    this.a(e3, b2, a3, parcel.readString(), parcel.readString(), bd.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final b e4 = b.a.E(parcel.readStrongBinder());
                    v a4;
                    if (parcel.readInt() != 0) {
                        a4 = com.google.android.gms.internal.v.CREATOR.a(parcel);
                    }
                    else {
                        a4 = null;
                    }
                    this.a(e4, a4, parcel.readString(), parcel.readString(), bd.a.k(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements bc
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void a(final b b, final v v, final String s, final bd bd) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (b != null) {
                        binder2 = b.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (v != null) {
                        obtain.writeInt(1);
                        v.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder3 = binder;
                    if (bd != null) {
                        binder3 = bd.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.dU.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final b b, final v v, final String s, final String s2, final bd bd) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (b != null) {
                        binder2 = b.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (v != null) {
                        obtain.writeInt(1);
                        v.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder3 = binder;
                    if (bd != null) {
                        binder3 = bd.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.dU.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final b b, final x x, final v v, final String s, final bd bd) throws RemoteException {
                while (true) {
                    final IBinder binder = null;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder2;
                            if (b != null) {
                                binder2 = b.asBinder();
                            }
                            else {
                                binder2 = null;
                            }
                            obtain.writeStrongBinder(binder2);
                            if (x != null) {
                                obtain.writeInt(1);
                                x.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (v != null) {
                                obtain.writeInt(1);
                                v.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                IBinder binder3 = binder;
                                if (bd != null) {
                                    binder3 = bd.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.dU.transact(1, obtain, obtain2, 0);
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
            public void a(final b b, final x x, final v v, final String s, final String s2, final bd bd) throws RemoteException {
                while (true) {
                    final IBinder binder = null;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder2;
                            if (b != null) {
                                binder2 = b.asBinder();
                            }
                            else {
                                binder2 = null;
                            }
                            obtain.writeStrongBinder(binder2);
                            if (x != null) {
                                obtain.writeInt(1);
                                x.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (v != null) {
                                obtain.writeInt(1);
                                v.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                obtain.writeString(s2);
                                IBinder binder3 = binder;
                                if (bd != null) {
                                    binder3 = bd.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.dU.transact(6, obtain, obtain2, 0);
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
                return this.dU;
            }
            
            @Override
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.dU.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public b getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.dU.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return b.a.E(obtain2.readStrongBinder());
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
                    this.dU.transact(4, obtain, obtain2, 0);
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
