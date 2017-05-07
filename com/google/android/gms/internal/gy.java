// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import android.os.IInterface;

public interface gy extends IInterface
{
    void a(final gx p0, final UserAddressRequest p1, final Bundle p2) throws RemoteException;
    
    public abstract static class a extends Binder implements gy
    {
        public static gy T(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
            if (queryLocalInterface != null && queryLocalInterface instanceof gy) {
                return (gy)queryLocalInterface;
            }
            return new gy.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressService");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressService");
                    final gx s = gx.a.S(parcel.readStrongBinder());
                    UserAddressRequest userAddressRequest;
                    if (parcel.readInt() != 0) {
                        userAddressRequest = (UserAddressRequest)UserAddressRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        userAddressRequest = null;
                    }
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.a(s, userAddressRequest, bundle);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements gy
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final gx gx, final UserAddressRequest userAddressRequest, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressService");
                            IBinder binder;
                            if (gx != null) {
                                binder = gx.asBinder();
                            }
                            else {
                                binder = null;
                            }
                            obtain.writeStrongBinder(binder);
                            if (userAddressRequest != null) {
                                obtain.writeInt(1);
                                userAddressRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                this.kn.transact(2, obtain, obtain2, 0);
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
        }
    }
}
