// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ks$a extends Binder implements ks
{
    public ks$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IStatusCallback");
    }
    
    public static ks aw(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof ks) {
            return (ks)queryLocalInterface;
        }
        return new ks$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.fitness.internal.IStatusCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IStatusCallback");
                Status fromParcel;
                if (parcel.readInt() != 0) {
                    fromParcel = Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel = null;
                }
                this.k(fromParcel);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
