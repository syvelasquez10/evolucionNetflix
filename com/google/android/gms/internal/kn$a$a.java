// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.fitness.result.DataTypeResult;
import android.os.IBinder;

class kn$a$a implements kn
{
    private IBinder lb;
    
    kn$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final DataTypeResult dataTypeResult) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IDataTypeCallback");
            if (dataTypeResult != null) {
                obtain.writeInt(1);
                dataTypeResult.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
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
