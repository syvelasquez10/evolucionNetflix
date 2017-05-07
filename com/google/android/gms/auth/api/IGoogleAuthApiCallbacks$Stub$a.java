// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.IBinder;

class IGoogleAuthApiCallbacks$Stub$a implements IGoogleAuthApiCallbacks
{
    private IBinder lb;
    
    IGoogleAuthApiCallbacks$Stub$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void onConnectionSuccess(final GoogleAuthApiResponse googleAuthApiResponse) {
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
    public void onError(final int n, final String s, final PendingIntent pendingIntent) {
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
