// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ou$a extends Binder implements ou
{
    public static ou bN(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletInternalServiceCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof ou) {
            return (ou)queryLocalInterface;
        }
        return new ou$a$a(binder);
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, int int2) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, int2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wallet.internal.IWalletInternalServiceCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletInternalServiceCallbacks");
                int1 = parcel.readInt();
                int2 = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.b(int1, int2, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
