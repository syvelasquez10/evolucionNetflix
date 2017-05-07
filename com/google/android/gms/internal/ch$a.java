// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ch$a extends Binder implements ch
{
    public static ch k(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ch) {
            return (ch)queryLocalInterface;
        }
        return new ch$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
                final Bundle bd = this.bD();
                parcel2.writeNoException();
                if (bd != null) {
                    parcel2.writeInt(1);
                    bd.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
        }
    }
}
