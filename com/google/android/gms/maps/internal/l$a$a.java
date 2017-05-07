// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.internal.f;
import android.os.IBinder;

class l$a$a implements l
{
    private IBinder lb;
    
    l$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public boolean a(final f f) {
        boolean b = true;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMarkerClickListener");
            IBinder binder;
            if (f != null) {
                binder = f.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                b = false;
            }
            return b;
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
