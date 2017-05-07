// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class j$a extends Binder implements j
{
    public j$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    public static j P(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof j) {
            return (j)queryLocalInterface;
        }
        return new j$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                int1 = parcel.readInt();
                final IBinder strongBinder = parcel.readStrongBinder();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.b(int1, strongBinder, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
