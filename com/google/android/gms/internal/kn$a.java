// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.DataTypeResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class kn$a extends Binder implements kn
{
    public kn$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IDataTypeCallback");
    }
    
    public static kn ar(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataTypeCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof kn) {
            return (kn)queryLocalInterface;
        }
        return new kn$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.fitness.internal.IDataTypeCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IDataTypeCallback");
                DataTypeResult dataTypeResult;
                if (parcel.readInt() != 0) {
                    dataTypeResult = (DataTypeResult)DataTypeResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    dataTypeResult = null;
                }
                this.a(dataTypeResult);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
