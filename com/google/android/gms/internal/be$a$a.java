// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class be$a$a implements be
{
    private IBinder lb;
    
    be$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public IBinder a(final d d, final ay ay, final String s, final ct ct, final int n) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            if (ay != null) {
                obtain.writeInt(1);
                ay.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeString(s);
            IBinder binder3 = binder;
            if (ct != null) {
                binder3 = ct.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            obtain.writeInt(n);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
