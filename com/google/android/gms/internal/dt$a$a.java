// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class dt$a$a implements dt
{
    private IBinder lb;
    
    dt$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public IBinder a(final d d) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
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
