// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGamesSignInService$Stub extends Binder implements IGamesSignInService
{
    public IGamesSignInService$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesSignInService");
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.games.internal.IGamesSignInService");
                return true;
            }
            case 5001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                final String bi = this.bI(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(bi);
                return true;
            }
            case 5002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                final String bj = this.bJ(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(bj);
                return true;
            }
            case 5009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                final String h = this.h(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeString(h);
                return true;
            }
            case 5003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.a(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.a(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.a(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.a(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.b(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.a(IGamesSignInCallbacks$Stub.aC(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 9001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInService");
                this.w(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
