// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import android.os.IBinder;

class IGoogleAuthService$Stub$a implements IGoogleAuthService
{
    private IBinder lb;
    
    IGoogleAuthService$Stub$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void sendConnection(final IGoogleAuthApiCallbacks googleAuthApiCallbacks, final GoogleAuthApiRequest googleAuthApiRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.IGoogleAuthService");
            IBinder binder;
            if (googleAuthApiCallbacks != null) {
                binder = googleAuthApiCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (googleAuthApiRequest != null) {
                obtain.writeInt(1);
                googleAuthApiRequest.writeToParcel(obtain, 0);
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
}
