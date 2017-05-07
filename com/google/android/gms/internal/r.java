// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface r extends IInterface
{
    Bundle a(final String p0, final Bundle p1) throws RemoteException;
    
    Bundle a(final String p0, final String p1, final Bundle p2) throws RemoteException;
    
    AccountChangeEventsResponse a(final AccountChangeEventsRequest p0) throws RemoteException;
    
    public abstract static class a extends Binder implements r
    {
        public static r a(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
            if (queryLocalInterface != null && queryLocalInterface instanceof r) {
                return (r)queryLocalInterface;
            }
            return new r.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final Bundle bundle = null;
            final AccountChangeEventsRequest accountChangeEventsRequest = null;
            Bundle bundle2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.auth.IAuthManagerService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    final Bundle a = this.a(string, string2, bundle2);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                    final String string3 = parcel.readString();
                    Bundle bundle3 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    final Bundle a2 = this.a(string3, bundle3);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                    AccountChangeEventsRequest fromParcel = accountChangeEventsRequest;
                    if (parcel.readInt() != 0) {
                        fromParcel = AccountChangeEventsRequest.CREATOR.createFromParcel(parcel);
                    }
                    final AccountChangeEventsResponse a3 = this.a(fromParcel);
                    parcel2.writeNoException();
                    if (a3 != null) {
                        parcel2.writeInt(1);
                        a3.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
            }
        }
        
        private static class a implements r
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public Bundle a(final String s, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                        obtain.writeString(s);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.lb.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public Bundle a(final String s, final String s2, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        if (bundle != null) {
                            obtain.writeInt(1);
                            bundle.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.lb.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public AccountChangeEventsResponse a(final AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
                        if (accountChangeEventsRequest != null) {
                            obtain.writeInt(1);
                            accountChangeEventsRequest.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.lb.transact(3, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return AccountChangeEventsResponse.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
