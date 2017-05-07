// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;

class ae$a$a implements ae
{
    private IBinder lb;
    
    ae$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void L(final boolean b) {
        int n = 1;
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IEventReleaseCallback");
            if (!b) {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
