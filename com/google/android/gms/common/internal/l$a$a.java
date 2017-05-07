// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class l$a$a implements l
{
    private IBinder lb;
    
    l$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public d a(d am, final int n, final int n2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
            IBinder binder;
            if (am != null) {
                binder = am.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            am = d$a.am(obtain2.readStrongBinder());
            return am;
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
