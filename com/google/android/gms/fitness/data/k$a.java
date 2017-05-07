// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class k$a extends Binder implements k
{
    public k$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.data.IDataSourceListener");
    }
    
    public static k an(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof k) {
            return (k)queryLocalInterface;
        }
        return new k$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.data.IDataSourceListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.data.IDataSourceListener");
                DataPoint dataPoint;
                if (parcel.readInt() != 0) {
                    dataPoint = (DataPoint)DataPoint.CREATOR.createFromParcel(parcel);
                }
                else {
                    dataPoint = null;
                }
                this.onEvent(dataPoint);
                return true;
            }
        }
    }
}
