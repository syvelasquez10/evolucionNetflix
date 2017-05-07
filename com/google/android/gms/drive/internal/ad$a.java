// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ad$a extends Binder implements ad
{
    public ad$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.drive.internal.IEventCallback");
    }
    
    public static ad W(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IEventCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof ad) {
            return (ad)queryLocalInterface;
        }
        return new ad$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.drive.internal.IEventCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IEventCallback");
                OnEventResponse onEventResponse;
                if (parcel.readInt() != 0) {
                    onEventResponse = (OnEventResponse)OnEventResponse.CREATOR.createFromParcel(parcel);
                }
                else {
                    onEventResponse = null;
                }
                this.c(onEventResponse);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
