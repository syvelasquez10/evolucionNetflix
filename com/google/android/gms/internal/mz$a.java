// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class mz$a extends Binder implements mz
{
    public mz$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
    }
    
    public static mz bz(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof mz) {
            return (mz)queryLocalInterface;
        }
        return new mz$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, int int2) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, int2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                int1 = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                int2 = parcel.readInt();
                Intent intent;
                if (parcel.readInt() != 0) {
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                else {
                    intent = null;
                }
                this.a(int1, bundle, int2, intent);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
