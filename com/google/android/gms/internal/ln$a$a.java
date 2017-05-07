// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import android.os.IBinder;

class ln$a$a implements ln
{
    private IBinder lb;
    
    ln$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final lm lm, final UserAddressRequest userAddressRequest, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressService");
                    IBinder binder;
                    if (lm != null) {
                        binder = lm.asBinder();
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
    
    public IBinder asBinder() {
        return this.lb;
    }
}
