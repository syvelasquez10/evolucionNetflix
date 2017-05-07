// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class fm$a extends Binder implements fm
{
    public fm$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }
    
    public static fm D(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        if (queryLocalInterface != null && queryLocalInterface instanceof fm) {
            return (fm)queryLocalInterface;
        }
        return new fm$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                fi h;
                if (parcel.readInt() != 0) {
                    h = fi.CREATOR.h(parcel);
                }
                else {
                    h = null;
                }
                final fk b = this.b(h);
                parcel2.writeNoException();
                if (b != null) {
                    parcel2.writeInt(1);
                    b.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
}
