// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IInterface;
import android.os.Binder;

public abstract class IRoomService$Stub extends Binder implements IRoomService
{
    public IRoomService$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IRoomService");
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final boolean b = false;
        boolean b2 = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.games.internal.IRoomService");
                return true;
            }
            case 1001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.a(parcel.readStrongBinder(), IRoomServiceCallbacks$Stub.aE(parcel.readStrongBinder()));
                return true;
            }
            case 1002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.kD();
                return true;
            }
            case 1003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.kE();
                return true;
            }
            case 1004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.c(parcel.readString(), parcel.readString(), parcel.readString());
                return true;
            }
            case 1005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.kF();
                return true;
            }
            case 1006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                DataHolder z;
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                else {
                    z = null;
                }
                if (parcel.readInt() != 0) {
                    b2 = true;
                }
                this.a(z, b2);
                return true;
            }
            case 1007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.kG();
                return true;
            }
            case 1008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                boolean b3 = b;
                if (parcel.readInt() != 0) {
                    b3 = true;
                }
                this.Q(b3);
                return true;
            }
            case 1009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.a(parcel.createByteArray(), parcel.readString(), parcel.readInt());
                return true;
            }
            case 1010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.a(parcel.createByteArray(), parcel.createStringArray());
                return true;
            }
            case 1011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.t(parcel.readString(), parcel.readInt());
                return true;
            }
            case 1012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.u(parcel.readString(), parcel.readInt());
                return true;
            }
            case 1013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.bK(parcel.readString());
                return true;
            }
            case 1014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomService");
                this.bL(parcel.readString());
                return true;
            }
        }
    }
}
