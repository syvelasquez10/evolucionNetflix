// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface c extends IInterface
{
    void b(final d p0) throws RemoteException;
    
    void c(final d p0) throws RemoteException;
    
    d fX() throws RemoteException;
    
    c fY() throws RemoteException;
    
    d fZ() throws RemoteException;
    
    c ga() throws RemoteException;
    
    Bundle getArguments() throws RemoteException;
    
    int getId() throws RemoteException;
    
    boolean getRetainInstance() throws RemoteException;
    
    String getTag() throws RemoteException;
    
    int getTargetRequestCode() throws RemoteException;
    
    boolean getUserVisibleHint() throws RemoteException;
    
    d getView() throws RemoteException;
    
    boolean isAdded() throws RemoteException;
    
    boolean isDetached() throws RemoteException;
    
    boolean isHidden() throws RemoteException;
    
    boolean isInLayout() throws RemoteException;
    
    boolean isRemoving() throws RemoteException;
    
    boolean isResumed() throws RemoteException;
    
    boolean isVisible() throws RemoteException;
    
    void setHasOptionsMenu(final boolean p0) throws RemoteException;
    
    void setMenuVisibility(final boolean p0) throws RemoteException;
    
    void setRetainInstance(final boolean p0) throws RemoteException;
    
    void setUserVisibleHint(final boolean p0) throws RemoteException;
    
    void startActivity(final Intent p0) throws RemoteException;
    
    void startActivityForResult(final Intent p0, final int p1) throws RemoteException;
    
    public abstract static class a extends Binder implements c
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.dynamic.IFragmentWrapper");
        }
        
        public static c J(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            if (queryLocalInterface != null && queryLocalInterface instanceof c) {
                return (c)queryLocalInterface;
            }
            return new c.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final IBinder binder = null;
            final IBinder binder2 = null;
            final IBinder binder3 = null;
            final IBinder binder4 = null;
            final Intent intent = null;
            final Intent intent2 = null;
            final IBinder binder5 = null;
            final int n3 = 0;
            final int n4 = 0;
            final int n5 = 0;
            final int n6 = 0;
            final int n7 = 0;
            final int n8 = 0;
            final int n9 = 0;
            boolean hasOptionsMenu = false;
            final boolean b = false;
            final boolean b2 = false;
            final boolean b3 = false;
            final int n10 = 0;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.dynamic.IFragmentWrapper");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final d fx = this.fX();
                    parcel2.writeNoException();
                    IBinder binder6 = binder5;
                    if (fx != null) {
                        binder6 = fx.asBinder();
                    }
                    parcel2.writeStrongBinder(binder6);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final Bundle arguments = this.getArguments();
                    parcel2.writeNoException();
                    if (arguments != null) {
                        parcel2.writeInt(1);
                        arguments.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    n = this.getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final c fy = this.fY();
                    parcel2.writeNoException();
                    IBinder binder7 = binder;
                    if (fy != null) {
                        binder7 = fy.asBinder();
                    }
                    parcel2.writeStrongBinder(binder7);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final d fz = this.fZ();
                    parcel2.writeNoException();
                    IBinder binder8 = binder2;
                    if (fz != null) {
                        binder8 = fz.asBinder();
                    }
                    parcel2.writeStrongBinder(binder8);
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean retainInstance = this.getRetainInstance();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final String tag = this.getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final c ga = this.ga();
                    parcel2.writeNoException();
                    IBinder binder9 = binder3;
                    if (ga != null) {
                        binder9 = ga.asBinder();
                    }
                    parcel2.writeStrongBinder(binder9);
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    n = this.getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean userVisibleHint = this.getUserVisibleHint();
                    parcel2.writeNoException();
                    n = n10;
                    if (userVisibleHint) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final d view = this.getView();
                    parcel2.writeNoException();
                    IBinder binder10 = binder4;
                    if (view != null) {
                        binder10 = view.asBinder();
                    }
                    parcel2.writeStrongBinder(binder10);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean added = this.isAdded();
                    parcel2.writeNoException();
                    n = n3;
                    if (added) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean detached = this.isDetached();
                    parcel2.writeNoException();
                    n = n4;
                    if (detached) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean hidden = this.isHidden();
                    parcel2.writeNoException();
                    n = n5;
                    if (hidden) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean inLayout = this.isInLayout();
                    parcel2.writeNoException();
                    n = n6;
                    if (inLayout) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean removing = this.isRemoving();
                    parcel2.writeNoException();
                    n = n7;
                    if (removing) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean resumed = this.isResumed();
                    parcel2.writeNoException();
                    n = n8;
                    if (resumed) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean visible = this.isVisible();
                    parcel2.writeNoException();
                    n = n9;
                    if (visible) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.b(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        hasOptionsMenu = true;
                    }
                    this.setHasOptionsMenu(hasOptionsMenu);
                    parcel2.writeNoException();
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    boolean menuVisibility = b;
                    if (parcel.readInt() != 0) {
                        menuVisibility = true;
                    }
                    this.setMenuVisibility(menuVisibility);
                    parcel2.writeNoException();
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    boolean retainInstance2 = b2;
                    if (parcel.readInt() != 0) {
                        retainInstance2 = true;
                    }
                    this.setRetainInstance(retainInstance2);
                    parcel2.writeNoException();
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    boolean userVisibleHint2 = b3;
                    if (parcel.readInt() != 0) {
                        userVisibleHint2 = true;
                    }
                    this.setUserVisibleHint(userVisibleHint2);
                    parcel2.writeNoException();
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    Intent intent3 = intent;
                    if (parcel.readInt() != 0) {
                        intent3 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.startActivity(intent3);
                    parcel2.writeNoException();
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    Intent intent4 = intent2;
                    if (parcel.readInt() != 0) {
                        intent4 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.startActivityForResult(intent4, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.c(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements c
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void b(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d fX() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
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
            public c fY() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return c.a.J(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d fZ() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.K(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public c ga() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return c.a.J(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle getArguments() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle;
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        bundle = null;
                    }
                    return bundle;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean getRetainInstance() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(7, obtain, obtain2, 0);
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
            public String getTag() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getTargetRequestCode() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean getUserVisibleHint() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(11, obtain, obtain2, 0);
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
            public d getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.K(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isAdded() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(13, obtain, obtain2, 0);
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
            public boolean isDetached() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(14, obtain, obtain2, 0);
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
            public boolean isHidden() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(15, obtain, obtain2, 0);
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
            public boolean isInLayout() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(16, obtain, obtain2, 0);
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
            public boolean isRemoving() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(17, obtain, obtain2, 0);
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
            public boolean isResumed() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(18, obtain, obtain2, 0);
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
            public boolean isVisible() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.kn.transact(19, obtain, obtain2, 0);
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
            public void setHasOptionsMenu(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMenuVisibility(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setRetainInstance(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setUserVisibleHint(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void startActivity(final Intent intent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void startActivityForResult(final Intent intent, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.kn.transact(26, obtain, obtain2, 0);
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
