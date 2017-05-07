// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.app.PendingIntent;
import android.os.RemoteException;
import android.os.IInterface;

public interface IGoogleAuthApiCallbacks extends IInterface
{
    void onConnectionSuccess(final GoogleAuthApiResponse p0) throws RemoteException;
    
    void onError(final int p0, final String p1, final PendingIntent p2) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGoogleAuthApiCallbacks
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        }
        
        public static IGoogleAuthApiCallbacks asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleAuthApiCallbacks) {
                return (IGoogleAuthApiCallbacks)queryLocalInterface;
            }
            return new a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            PendingIntent pendingIntent = null;
            final GoogleAuthApiResponse googleAuthApiResponse = null;
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                    GoogleAuthApiResponse fromParcel = googleAuthApiResponse;
                    if (parcel.readInt() != 0) {
                        fromParcel = GoogleAuthApiResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.onConnectionSuccess(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                    int1 = parcel.readInt();
                    final String string = parcel.readString();
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.onError(int1, string, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements IGoogleAuthApiCallbacks
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onConnectionSuccess(final GoogleAuthApiResponse googleAuthApiResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                    if (googleAuthApiResponse != null) {
                        obtain.writeInt(1);
                        googleAuthApiResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onError(final int n, final String s, final PendingIntent pendingIntent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
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
