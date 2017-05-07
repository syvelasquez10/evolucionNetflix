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

public interface cu extends IInterface
{
    void a(final d p0, final av p1, final String p2, final cv p3) throws RemoteException;
    
    void a(final d p0, final av p1, final String p2, final String p3, final cv p4) throws RemoteException;
    
    void a(final d p0, final ay p1, final av p2, final String p3, final cv p4) throws RemoteException;
    
    void a(final d p0, final ay p1, final av p2, final String p3, final String p4, final cv p5) throws RemoteException;
    
    void destroy() throws RemoteException;
    
    d getView() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    public abstract static class a extends Binder implements cu
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }
        
        public static cu m(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (queryLocalInterface != null && queryLocalInterface instanceof cu) {
                return (cu)queryLocalInterface;
            }
            return new cu.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final av av = null;
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
                    final d am = d.a.am(parcel.readStrongBinder());
                    ay c;
                    if (parcel.readInt() != 0) {
                        c = ay.CREATOR.c(parcel);
                    }
                    else {
                        c = null;
                    }
                    av b;
                    if (parcel.readInt() != 0) {
                        b = com.google.android.gms.internal.av.CREATOR.b(parcel);
                    }
                    else {
                        b = null;
                    }
                    this.a(am, c, b, parcel.readString(), cv.a.n(parcel.readStrongBinder()));
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
                    final d am2 = d.a.am(parcel.readStrongBinder());
                    av b2 = av;
                    if (parcel.readInt() != 0) {
                        b2 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                    }
                    this.a(am2, b2, parcel.readString(), cv.a.n(parcel.readStrongBinder()));
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
                    final d am3 = d.a.am(parcel.readStrongBinder());
                    ay c2;
                    if (parcel.readInt() != 0) {
                        c2 = ay.CREATOR.c(parcel);
                    }
                    else {
                        c2 = null;
                    }
                    av b3;
                    if (parcel.readInt() != 0) {
                        b3 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                    }
                    else {
                        b3 = null;
                    }
                    this.a(am3, c2, b3, parcel.readString(), parcel.readString(), cv.a.n(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final d am4 = d.a.am(parcel.readStrongBinder());
                    av b4;
                    if (parcel.readInt() != 0) {
                        b4 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                    }
                    else {
                        b4 = null;
                    }
                    this.a(am4, b4, parcel.readString(), parcel.readString(), cv.a.n(parcel.readStrongBinder()));
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
        
        private static class a implements cu
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final d d, final av av, final String s, final cv cv) throws RemoteException {
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
                    if (av != null) {
                        obtain.writeInt(1);
                        av.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder3 = binder;
                    if (cv != null) {
                        binder3 = cv.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d, final av av, final String s, final String s2, final cv cv) throws RemoteException {
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
                    if (av != null) {
                        obtain.writeInt(1);
                        av.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder3 = binder;
                    if (cv != null) {
                        binder3 = cv.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d, final ay ay, final av av, final String s, final cv cv) throws RemoteException {
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
                            if (ay != null) {
                                obtain.writeInt(1);
                                ay.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (av != null) {
                                obtain.writeInt(1);
                                av.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                IBinder binder3 = binder;
                                if (cv != null) {
                                    binder3 = cv.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.lb.transact(1, obtain, obtain2, 0);
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
            public void a(final d d, final ay ay, final av av, final String s, final String s2, final cv cv) throws RemoteException {
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
                            if (ay != null) {
                                obtain.writeInt(1);
                                ay.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (av != null) {
                                obtain.writeInt(1);
                                av.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                obtain.writeString(s2);
                                IBinder binder3 = binder;
                                if (cv != null) {
                                    binder3 = cv.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.lb.transact(6, obtain, obtain2, 0);
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
                return this.lb;
            }
            
            @Override
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.lb.transact(5, obtain, obtain2, 0);
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
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
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
                    this.lb.transact(8, obtain, obtain2, 0);
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
                    this.lb.transact(9, obtain, obtain2, 0);
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
                    this.lb.transact(4, obtain, obtain2, 0);
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
