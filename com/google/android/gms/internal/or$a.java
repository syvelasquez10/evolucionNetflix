// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class or$a extends Binder implements or
{
    public or$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
    }
    
    public static or bK(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof or) {
            return (or)queryLocalInterface;
        }
        return new or$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
                int1 = parcel.readInt();
                int2 = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(int1, int2, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
