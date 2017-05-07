// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.DataReadResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class kl$a extends Binder implements kl
{
    public kl$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IDataReadCallback");
    }
    
    public static kl ap(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof kl) {
            return (kl)queryLocalInterface;
        }
        return new kl$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.fitness.internal.IDataReadCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
                DataReadResult dataReadResult;
                if (parcel.readInt() != 0) {
                    dataReadResult = (DataReadResult)DataReadResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    dataReadResult = null;
                }
                this.a(dataReadResult);
                return true;
            }
        }
    }
}
