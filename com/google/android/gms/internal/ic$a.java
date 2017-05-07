// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ic$a extends Binder implements ic
{
    public ic$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.appstate.internal.IAppStateCallbacks");
    }
    
    public static ic J(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof ic) {
            return (ic)queryLocalInterface;
        }
        return new ic$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        final DataHolder dataHolder = null;
        DataHolder z = null;
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                return true;
            }
            case 5001: {
                parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                int1 = parcel.readInt();
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.a(int1, z);
                parcel2.writeNoException();
                return true;
            }
            case 5002: {
                parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                DataHolder z2 = dataHolder;
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.a(z2);
                parcel2.writeNoException();
                return true;
            }
            case 5003: {
                parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                this.e(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5004: {
                parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                this.fq();
                parcel2.writeNoException();
                return true;
            }
            case 5005: {
                parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                this.S(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
