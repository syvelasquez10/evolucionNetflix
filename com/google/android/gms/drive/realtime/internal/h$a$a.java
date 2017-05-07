// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;

class h$a$a implements h
{
    private IBinder lb;
    
    h$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void c(final boolean b, final boolean b2) {
        final boolean b3 = true;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
            int n;
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            int n2;
            if (b2) {
                n2 = (b3 ? 1 : 0);
            }
            else {
                n2 = 0;
            }
            obtain.writeInt(n2);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
