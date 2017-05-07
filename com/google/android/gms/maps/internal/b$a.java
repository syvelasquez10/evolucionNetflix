// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class b$a extends Binder implements b
{
    public b$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.ICancelableCallback");
    }
    
    public static b aO(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICancelableCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof b) {
            return (b)queryLocalInterface;
        }
        return new b$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.ICancelableCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICancelableCallback");
                this.onFinish();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICancelableCallback");
                this.onCancel();
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
