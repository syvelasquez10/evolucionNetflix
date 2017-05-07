// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;

class fm$a$a implements fm
{
    private IBinder lb;
    
    fm$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public fk b(final fi fi) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                if (fi != null) {
                    obtain.writeInt(1);
                    fi.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.lb.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return fk.CREATOR.i(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
}
