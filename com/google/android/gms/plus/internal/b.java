// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ny;
import com.google.android.gms.internal.jp;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface b extends IInterface
{
    void a(final int p0, final Bundle p1, final Bundle p2) throws RemoteException;
    
    void a(final int p0, final Bundle p1, final ParcelFileDescriptor p2) throws RemoteException;
    
    void a(final int p0, final Bundle p1, final jp p2) throws RemoteException;
    
    void a(final int p0, final ny p1) throws RemoteException;
    
    void a(final DataHolder p0, final String p1) throws RemoteException;
    
    void a(final DataHolder p0, final String p1, final String p2) throws RemoteException;
    
    void aB(final Status p0) throws RemoteException;
    
    void cb(final String p0) throws RemoteException;
    
    void cc(final String p0) throws RemoteException;
    
    void h(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class a extends Binder implements b
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.plus.internal.IPlusCallbacks");
        }
        
        public static b bE(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof b) {
                return (b)queryLocalInterface;
            }
            return new b.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            jp m = null;
            final DataHolder dataHolder = null;
            final ny ny = null;
            final Status status = null;
            DataHolder z = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.plus.internal.IPlusCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    n = parcel.readInt();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    Bundle bundle2;
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle2 = null;
                    }
                    this.a(n, bundle, bundle2);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    n = parcel.readInt();
                    Bundle bundle3;
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle3 = null;
                    }
                    ParcelFileDescriptor parcelFileDescriptor;
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        parcelFileDescriptor = null;
                    }
                    this.a(n, bundle3, parcelFileDescriptor);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    this.cb(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(z, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    n = parcel.readInt();
                    Bundle bundle4;
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        m = jp.CREATOR.M(parcel);
                    }
                    this.a(n, bundle4, m);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    DataHolder z2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(z2, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    n = parcel.readInt();
                    Bundle bundle5;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle5 = null;
                    }
                    this.h(n, bundle5);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    this.cc(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    n = parcel.readInt();
                    ny dd = ny;
                    if (parcel.readInt() != 0) {
                        dd = com.google.android.gms.internal.ny.CREATOR.dd(parcel);
                    }
                    this.a(n, dd);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                    Status fromParcel = status;
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.aB(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements b
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final int n, final Bundle bundle, final Bundle bundle2) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                            obtain.writeInt(n);
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle2 != null) {
                                obtain.writeInt(1);
                                bundle2.writeToParcel(obtain, 0);
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
            public void a(final int n, final Bundle bundle, final ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                            obtain.writeInt(n);
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (parcelFileDescriptor != null) {
                                obtain.writeInt(1);
                                parcelFileDescriptor.writeToParcel(obtain, 0);
                                this.lb.transact(2, obtain, obtain2, 0);
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
            public void a(final int n, final Bundle bundle, final jp jp) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                            obtain.writeInt(n);
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (jp != null) {
                                obtain.writeInt(1);
                                jp.writeToParcel(obtain, 0);
                                this.lb.transact(5, obtain, obtain2, 0);
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
            public void a(final int n, final ny ny) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    obtain.writeInt(n);
                    if (ny != null) {
                        obtain.writeInt(1);
                        ny.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataHolder dataHolder, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataHolder dataHolder, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aB(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(10, obtain, obtain2, 0);
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
            public void cb(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void cc(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    obtain.writeString(s);
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(7, obtain, obtain2, 0);
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
