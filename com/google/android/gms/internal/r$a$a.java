// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.Parcel;
import android.os.Bundle;
import android.os.IBinder;

class r$a$a implements r
{
    private IBinder lb;
    
    r$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public Bundle a(final String s, final Bundle bundle) {
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
    public Bundle a(final String s, final String s2, final Bundle bundle) {
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
    public AccountChangeEventsResponse a(final AccountChangeEventsRequest accountChangeEventsRequest) {
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
