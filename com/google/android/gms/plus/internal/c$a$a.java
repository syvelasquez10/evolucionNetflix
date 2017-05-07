// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class c$a$a implements c
{
    private IBinder lb;
    
    c$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public d a(d am, final int n, final int n2, final String s, final int n3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
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
            obtain.writeString(s);
            obtain.writeInt(n3);
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
    
    @Override
    public d a(d am, final int n, final int n2, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
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
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(2, obtain, obtain2, 0);
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
