// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.ParcelFileDescriptor;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IRoomServiceCallbacks$Stub extends Binder implements IRoomServiceCallbacks
{
    public IRoomServiceCallbacks$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IRoomServiceCallbacks");
    }
    
    public static IRoomServiceCallbacks aE(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof IRoomServiceCallbacks) {
            return (IRoomServiceCallbacks)queryLocalInterface;
        }
        return new IRoomServiceCallbacks$Stub$Proxy(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final ParcelFileDescriptor parcelFileDescriptor = null;
        final ConnectionInfo connectionInfo = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                return true;
            }
            case 1001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.c(parcel.readInt(), parcel.readInt(), parcel.readString());
                return true;
            }
            case 1002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.a(parcel.readString(), parcel.createByteArray(), parcel.readInt());
                return true;
            }
            case 1003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bM(parcel.readString());
                return true;
            }
            case 1004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bN(parcel.readString());
                return true;
            }
            case 1005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bO(parcel.readString());
                return true;
            }
            case 1006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bP(parcel.readString());
                return true;
            }
            case 1007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bQ(parcel.readString());
                return true;
            }
            case 1008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.a(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.b(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.c(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.d(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.e(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.f(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.onP2PConnected(parcel.readString());
                return true;
            }
            case 1015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.onP2PDisconnected(parcel.readString());
                return true;
            }
            case 1016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.kH();
                return true;
            }
            case 1017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.g(parcel.readString(), parcel.createStringArray());
                return true;
            }
            case 1018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.c(parcel.readString(), parcel.createByteArray());
                return true;
            }
            case 1019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.bR(parcel.readString());
                return true;
            }
            case 1020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.dF(parcel.readInt());
                return true;
            }
            case 1021: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.aD(parcel.readStrongBinder());
                return true;
            }
            case 1022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                ConnectionInfo cf = connectionInfo;
                if (parcel.readInt() != 0) {
                    cf = ConnectionInfo.CREATOR.cf(parcel);
                }
                this.a(cf);
                return true;
            }
            case 1023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.kI();
                return true;
            }
            case 1024: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
                if (parcel.readInt() != 0) {
                    parcelFileDescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                }
                this.a(parcelFileDescriptor2, parcel.readInt());
                return true;
            }
            case 1025: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.v(parcel.readString(), parcel.readInt());
                return true;
            }
            case 1026: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                this.i(parcel.readString(), parcel.readInt() != 0);
                return true;
            }
        }
    }
}
