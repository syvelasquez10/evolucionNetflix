// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.f$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class l$a extends Binder implements l
{
    public l$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }
    
    public static l bc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof l) {
            return (l)queryLocalInterface;
        }
        return new l$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
                final boolean a = this.a(f$a.bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (a) {
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
