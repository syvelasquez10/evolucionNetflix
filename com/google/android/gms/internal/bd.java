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

public interface bd extends IInterface
{
    d X() throws RemoteException;
    
    ay Y() throws RemoteException;
    
    void a(final ay p0) throws RemoteException;
    
    void a(final bc p0) throws RemoteException;
    
    void a(final bf p0) throws RemoteException;
    
    void a(final eh p0) throws RemoteException;
    
    void a(final el p0, final String p1) throws RemoteException;
    
    void a(final et p0) throws RemoteException;
    
    void a(final eu p0) throws RemoteException;
    
    boolean a(final av p0) throws RemoteException;
    
    void aj() throws RemoteException;
    
    void destroy() throws RemoteException;
    
    String getMediationAdapterClassName() throws RemoteException;
    
    boolean isReady() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void stopLoading() throws RemoteException;
    
    public abstract static class a extends Binder implements bd
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IAdManager");
        }
        
        public static bd f(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface != null && queryLocalInterface instanceof bd) {
                return (bd)queryLocalInterface;
            }
            return new bd.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final av av = null;
            final ay ay = null;
            final IBinder binder = null;
            final int n3 = 0;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManager");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final d x = this.X();
                    parcel2.writeNoException();
                    IBinder binder2 = binder;
                    if (x != null) {
                        binder2 = x.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.destroy();
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final boolean ready = this.isReady();
                    parcel2.writeNoException();
                    if (ready) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    av b = av;
                    if (parcel.readInt() != 0) {
                        b = com.google.android.gms.internal.av.CREATOR.b(parcel);
                    }
                    final boolean a = this.a(b);
                    parcel2.writeNoException();
                    n = n3;
                    if (a) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.pause();
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.resume();
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(bc.a.e(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(bf.a.h(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.showInterstitial();
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.stopLoading();
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.aj();
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final ay y = this.Y();
                    parcel2.writeNoException();
                    if (y != null) {
                        parcel2.writeInt(1);
                        y.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    ay c = ay;
                    if (parcel.readInt() != 0) {
                        c = com.google.android.gms.internal.ay.CREATOR.c(parcel);
                    }
                    this.a(c);
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(eh.a.t(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(el.a.x(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(et.a.A(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.a(eu.a.B(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final String mediationAdapterClassName = this.getMediationAdapterClassName();
                    parcel2.writeNoException();
                    parcel2.writeString(mediationAdapterClassName);
                    return true;
                }
            }
        }
        
        private static class a implements bd
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public d X() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ay Y() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    ay c;
                    if (obtain2.readInt() != 0) {
                        c = ay.CREATOR.c(obtain2);
                    }
                    else {
                        c = null;
                    }
                    return c;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ay ay) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (ay != null) {
                        obtain.writeInt(1);
                        ay.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final bc bc) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (bc != null) {
                        binder = bc.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final bf bf) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (bf != null) {
                        binder = bf.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eh eh) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (eh != null) {
                        binder = eh.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final el el, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (el != null) {
                        binder = el.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final et et) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (et != null) {
                        binder = et.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eu eu) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (eu != null) {
                        binder = eu.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean a(final av av) throws RemoteException {
                while (true) {
                    boolean b = true;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                        if (av != null) {
                            obtain.writeInt(1);
                            av.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.lb.transact(4, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return b;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    b = false;
                    return b;
                }
            }
            
            @Override
            public void aj() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(11, obtain, obtain2, 0);
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
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getMediationAdapterClassName() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isReady() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(5, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(6, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void stopLoading() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.lb.transact(10, obtain, obtain2, 0);
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
