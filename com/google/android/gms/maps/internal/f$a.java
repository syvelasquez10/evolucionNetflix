// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.d$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class f$a extends Binder implements f
{
    public f$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }
    
    public static f aW(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof f) {
            return (f)queryLocalInterface;
        }
        return new f$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
                this.onIndoorBuildingFocused();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
                this.a(d$a.bs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
