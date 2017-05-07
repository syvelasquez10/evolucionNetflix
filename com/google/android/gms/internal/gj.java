// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface gj extends IInterface
{
    void I(final Status p0) throws RemoteException;
    
    void a(final int p0, final gh p1) throws RemoteException;
    
    void a(final int p0, final boolean p1) throws RemoteException;
    
    void b(final int p0, final gh p1) throws RemoteException;
    
    public abstract static class a extends Binder implements gj
    {
        public static gj J(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.ICopresenceCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof gj) {
                return (gj)queryLocalInterface;
            }
            return new gj.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final gh gh = null;
            final gh gh2 = null;
            Status fromParcel = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.I(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    n = parcel.readInt();
                    gh ah = gh;
                    if (parcel.readInt() != 0) {
                        ah = com.google.android.gms.internal.gh.CREATOR.ah(parcel);
                    }
                    this.a(n, ah);
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    n = parcel.readInt();
                    gh ah2 = gh2;
                    if (parcel.readInt() != 0) {
                        ah2 = com.google.android.gms.internal.gh.CREATOR.ah(parcel);
                    }
                    this.b(n, ah2);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    n = parcel.readInt();
                    this.a(n, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements gj
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void I(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final int n, final gh gh) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    obtain.writeInt(n);
                    if (gh != null) {
                        obtain.writeInt(1);
                        gh.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(int n, final boolean b) throws RemoteException {
                final int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    obtain.writeInt(n);
                    n = n2;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
            
            @Override
            public void b(final int n, final gh gh) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ICopresenceCallbacks");
                    obtain.writeInt(n);
                    if (gh != null) {
                        obtain.writeInt(1);
                        gh.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(5, obtain, obtain2, 0);
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
