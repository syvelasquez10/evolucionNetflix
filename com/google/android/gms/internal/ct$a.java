// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ct$a extends Binder implements ct
{
    public ct$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }
    
    public static ct l(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof ct) {
            return (ct)queryLocalInterface;
        }
        return new ct$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                final cu x = this.x(parcel.readString());
                parcel2.writeNoException();
                IBinder binder;
                if (x != null) {
                    binder = x.asBinder();
                }
                else {
                    binder = null;
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                final boolean y = this.y(parcel.readString());
                parcel2.writeNoException();
                if (y) {
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
