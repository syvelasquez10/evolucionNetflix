// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class lm$a extends Binder implements lm
{
    public lm$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.identity.intents.internal.IAddressCallbacks");
    }
    
    public static lm aG(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof lm) {
            return (lm)queryLocalInterface;
        }
        return new lm$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
                int1 = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.g(int1, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
