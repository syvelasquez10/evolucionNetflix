// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.content.Intent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGamesSignInCallbacks$Stub extends Binder implements IGamesSignInCallbacks
{
    public IGamesSignInCallbacks$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesSignInCallbacks");
    }
    
    public static IGamesSignInCallbacks aC(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGamesSignInCallbacks) {
            return (IGamesSignInCallbacks)queryLocalInterface;
        }
        return new IGamesSignInCallbacks$Stub$Proxy(binder);
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        final DataHolder dataHolder = null;
        final DataHolder dataHolder2 = null;
        final DataHolder dataHolder3 = null;
        Intent intent = null;
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                return true;
            }
            case 5001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                int1 = parcel.readInt();
                if (parcel.readInt() != 0) {
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                this.b(int1, intent);
                parcel2.writeNoException();
                return true;
            }
            case 5002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                DataHolder z = dataHolder;
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.T(z);
                parcel2.writeNoException();
                return true;
            }
            case 5003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                DataHolder z2 = dataHolder2;
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.U(z2);
                parcel2.writeNoException();
                return true;
            }
            case 5004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                this.dD(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                DataHolder z3 = dataHolder3;
                if (parcel.readInt() != 0) {
                    z3 = DataHolder.CREATOR.z(parcel);
                }
                this.g(z3);
                parcel2.writeNoException();
                return true;
            }
            case 5006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                this.dE(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
