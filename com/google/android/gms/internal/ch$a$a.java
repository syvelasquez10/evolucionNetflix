// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Bundle;
import android.os.IBinder;

class ch$a$a implements ch
{
    private IBinder lb;
    
    ch$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public Bundle bD() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
            this.lb.transact(1, obtain, obtain2, 0);
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
}
