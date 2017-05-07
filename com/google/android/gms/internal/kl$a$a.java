// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.fitness.result.DataReadResult;
import android.os.IBinder;

class kl$a$a implements kl
{
    private IBinder lb;
    
    kl$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final DataReadResult dataReadResult) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IDataReadCallback");
            if (dataReadResult != null) {
                obtain.writeInt(1);
                dataReadResult.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
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
