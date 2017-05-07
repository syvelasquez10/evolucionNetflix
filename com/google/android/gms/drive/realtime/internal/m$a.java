// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class m$a extends Binder implements m
{
    public static m ai(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
        if (queryLocalInterface != null && queryLocalInterface instanceof m) {
            return (m)queryLocalInterface;
        }
        return new m$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final DataHolder dataHolder = null;
        final DataHolder dataHolder2 = null;
        final BeginCompoundOperationRequest beginCompoundOperationRequest = null;
        final EndCompoundOperationRequest endCompoundOperationRequest = null;
        final ParcelableIndexReference parcelableIndexReference = null;
        final EndCompoundOperationRequest endCompoundOperationRequest2 = null;
        DataHolder z = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), n$a.aj(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(c$a.Y(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 33: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(c$a.Y(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 35: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 40: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(l$a.ah(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readString(), f$a.ab(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), l$a.ah(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                final String string = parcel.readString();
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.a(string, z, j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), f$a.ab(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readString(), g$a.ac(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(parcel.readString(), l$a.ah(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(parcel.readString(), n$a.aj(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readInt(), parcel.readString(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readInt(), parcel.readInt(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readString(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(parcel.readString(), f$a.ab(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.c(parcel.readString(), l$a.ah(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                final String string2 = parcel.readString();
                n = parcel.readInt();
                DataHolder z2 = dataHolder;
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.a(string2, n, z2, j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                final String string3 = parcel.readString();
                n = parcel.readInt();
                DataHolder z3 = dataHolder2;
                if (parcel.readInt() != 0) {
                    z3 = DataHolder.CREATOR.z(parcel);
                }
                this.a(string3, n, z3, g$a.ac(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readInt(), parcel.readInt(), g$a.ac(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 37: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                BeginCompoundOperationRequest beginCompoundOperationRequest2 = beginCompoundOperationRequest;
                if (parcel.readInt() != 0) {
                    beginCompoundOperationRequest2 = (BeginCompoundOperationRequest)BeginCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(beginCompoundOperationRequest2, o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 41: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                EndCompoundOperationRequest endCompoundOperationRequest3 = endCompoundOperationRequest;
                if (parcel.readInt() != 0) {
                    endCompoundOperationRequest3 = (EndCompoundOperationRequest)EndCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(endCompoundOperationRequest3, j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.c(c$a.Y(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 25: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.d(c$a.Y(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                ParcelableIndexReference parcelableIndexReference2 = parcelableIndexReference;
                if (parcel.readInt() != 0) {
                    parcelableIndexReference2 = (ParcelableIndexReference)ParcelableIndexReference.CREATOR.createFromParcel(parcel);
                }
                this.a(parcelableIndexReference2, n$a.aj(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 27: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), k$a.ag(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 28: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), parcel.readInt(), o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 29: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(l$a.ah(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 30: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readInt(), j$a.af(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 31: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(e$a.aa(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 32: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(d$a.Z(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 34: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(i$a.ae(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 36: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(h$a.ad(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 38: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.a(parcel.readString(), o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 39: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                this.b(parcel.readString(), o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                EndCompoundOperationRequest endCompoundOperationRequest4 = endCompoundOperationRequest2;
                if (parcel.readInt() != 0) {
                    endCompoundOperationRequest4 = (EndCompoundOperationRequest)EndCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(endCompoundOperationRequest4, o$a.ak(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
