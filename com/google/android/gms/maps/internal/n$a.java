// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class n$a extends Binder implements n
{
    public n$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }
    
    public static n be(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof n) {
            return (n)queryLocalInterface;
        }
        return new n$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
                final boolean onMyLocationButtonClick = this.onMyLocationButtonClick();
                parcel2.writeNoException();
                if (onMyLocationButtonClick) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
