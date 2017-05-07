// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementEntity;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGamesService$Stub extends Binder implements IGamesService
{
    public IGamesService$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesService");
    }
    
    public static IGamesService aB(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGamesService) {
            return (IGamesService)queryLocalInterface;
        }
        return new IGamesService$Stub$Proxy(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, int n2) {
        final Bundle bundle = null;
        final Bundle bundle2 = null;
        final Bundle bundle3 = null;
        Uri uri = null;
        final GameRequestCluster gameRequestCluster = null;
        final ZInvitationCluster zInvitationCluster = null;
        final Contents contents = null;
        final AchievementEntity achievementEntity = null;
        Bundle bundle4 = null;
        boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        final boolean b4 = false;
        final boolean b5 = false;
        final int n3 = 0;
        final boolean b6 = false;
        final boolean b7 = false;
        final boolean b8 = false;
        final boolean b9 = false;
        final boolean b10 = false;
        final boolean b11 = false;
        boolean b12 = false;
        final boolean b13 = false;
        final boolean b14 = false;
        final boolean b15 = false;
        final boolean b16 = false;
        final boolean b17 = false;
        final boolean b18 = false;
        final boolean b19 = false;
        final boolean b20 = false;
        final boolean b21 = false;
        final boolean b22 = false;
        final boolean b23 = false;
        final int n4 = 0;
        final boolean b24 = false;
        final boolean b25 = false;
        final boolean b26 = false;
        final boolean b27 = false;
        final boolean b28 = false;
        final boolean b29 = false;
        final boolean b30 = false;
        final boolean b31 = false;
        final boolean b32 = false;
        boolean b33 = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.games.internal.IGamesService");
                return true;
            }
            case 5001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.q(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String km = this.km();
                parcel2.writeNoException();
                parcel2.writeString(km);
                return true;
            }
            case 5004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Bundle fd = this.fD();
                parcel2.writeNoException();
                if (fd != null) {
                    parcel2.writeInt(1);
                    fd.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IBinder strongBinder = parcel.readStrongBinder();
                Bundle bundle5;
                if (parcel.readInt() != 0) {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle5 = null;
                }
                this.a(strongBinder, bundle5);
                parcel2.writeNoException();
                return true;
            }
            case 5006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.ku();
                parcel2.writeNoException();
                return true;
            }
            case 5007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String jx = this.jX();
                parcel2.writeNoException();
                parcel2.writeString(jx);
                return true;
            }
            case 5064: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String bb = this.bB(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(bb);
                return true;
            }
            case 5065: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.u(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String jy = this.jY();
                parcel2.writeNoException();
                parcel2.writeString(jy);
                return true;
            }
            case 5013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final DataHolder kw = this.kw();
                parcel2.writeNoException();
                if (kw != null) {
                    parcel2.writeInt(1);
                    kw.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                final boolean b34 = parcel.readInt() != 0;
                if (parcel.readInt() != 0) {
                    b33 = true;
                }
                this.a(aa, n, b34, b33);
                parcel2.writeNoException();
                return true;
            }
            case 5016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa2 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string = parcel.readString();
                n = parcel.readInt();
                n2 = parcel.readInt();
                this.a(aa2, string, n, n2, parcel.readInt(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa3 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string2 = parcel.readString();
                n = parcel.readInt();
                n2 = parcel.readInt();
                this.b(aa3, string2, n, n2, parcel.readInt(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5021: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa4 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                Bundle bundle6;
                if (parcel.readInt() != 0) {
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle6 = null;
                }
                this.a(aa4, bundle6, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa5 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string3 = parcel.readString();
                final IBinder strongBinder2 = parcel.readStrongBinder();
                Bundle bundle7;
                if (parcel.readInt() != 0) {
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle7 = null;
                }
                this.a(aa5, string3, strongBinder2, bundle7);
                parcel2.writeNoException();
                return true;
            }
            case 5024: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa6 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string4 = parcel.readString();
                final IBinder strongBinder3 = parcel.readStrongBinder();
                Bundle bundle8;
                if (parcel.readInt() != 0) {
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle8 = null;
                }
                this.b(aa6, string4, strongBinder3, bundle8);
                parcel2.writeNoException();
                return true;
            }
            case 5025: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa7 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string5 = parcel.readString();
                n = parcel.readInt();
                final IBinder strongBinder4 = parcel.readStrongBinder();
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.a(aa7, string5, n, strongBinder4, bundle4);
                parcel2.writeNoException();
                return true;
            }
            case 5026: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5027: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.e(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5028: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.p(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5029: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.o(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5058: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5059: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.r(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5030: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa8 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final IBinder strongBinder5 = parcel.readStrongBinder();
                n = parcel.readInt();
                final String[] stringArray = parcel.createStringArray();
                Bundle bundle9 = bundle;
                if (parcel.readInt() != 0) {
                    bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.a(aa8, strongBinder5, n, stringArray, bundle9, parcel.readInt() != 0, parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5031: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readString(), parcel.readInt() != 0, parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 5032: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5033: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 5034: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.b(parcel.createByteArray(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 5035: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String bc = this.bC(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(bc);
                return true;
            }
            case 5036: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.dC(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5037: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5038: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5039: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa9 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string6 = parcel.readString();
                final String string7 = parcel.readString();
                n = parcel.readInt();
                n2 = parcel.readInt();
                final int int1 = parcel.readInt();
                if (parcel.readInt() != 0) {
                    b = true;
                }
                this.a(aa9, string6, string7, n, n2, int1, b);
                parcel2.writeNoException();
                return true;
            }
            case 5040: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa10 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string8 = parcel.readString();
                final String string9 = parcel.readString();
                n = parcel.readInt();
                n2 = parcel.readInt();
                final int int2 = parcel.readInt();
                boolean b35 = b2;
                if (parcel.readInt() != 0) {
                    b35 = true;
                }
                this.b(aa10, string8, string9, n, n2, int2, b35);
                parcel2.writeNoException();
                return true;
            }
            case 5041: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5042: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.e(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5043: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.f(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5044: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa11 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                n2 = parcel.readInt();
                this.a(aa11, n, n2, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5045: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa12 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string10 = parcel.readString();
                n = parcel.readInt();
                this.a(aa12, string10, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5046: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa13 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                final boolean b36 = parcel.readInt() != 0;
                boolean b37 = b3;
                if (parcel.readInt() != 0) {
                    b37 = true;
                }
                this.b(aa13, n, b36, b37);
                parcel2.writeNoException();
                return true;
            }
            case 5047: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.f(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5048: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa14 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                final boolean b38 = parcel.readInt() != 0;
                boolean b39 = b4;
                if (parcel.readInt() != 0) {
                    b39 = true;
                }
                this.c(aa14, n, b38, b39);
                parcel2.writeNoException();
                return true;
            }
            case 5049: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.g(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5050: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.bD(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5051: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5052: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.g(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5053: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final RoomEntity h = this.h(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                if (h != null) {
                    parcel2.writeInt(1);
                    h.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5060: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.bE(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 5054: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa15 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string11 = parcel.readString();
                boolean b40 = b5;
                if (parcel.readInt() != 0) {
                    b40 = true;
                }
                this.a(aa15, string11, b40);
                parcel2.writeNoException();
                return true;
            }
            case 5061: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.i(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5055: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.r(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5067: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final boolean kx = this.kx();
                parcel2.writeNoException();
                n = n3;
                if (kx) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 5068: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean b41 = b6;
                if (parcel.readInt() != 0) {
                    b41 = true;
                }
                this.N(b41);
                parcel2.writeNoException();
                return true;
            }
            case 5056: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.h(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5057: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.j(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5062: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.i(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5063: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa16 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b42 = b7;
                if (parcel.readInt() != 0) {
                    b42 = true;
                }
                Bundle bundle10;
                if (parcel.readInt() != 0) {
                    bundle10 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle10 = null;
                }
                this.a(aa16, b42, bundle10);
                parcel2.writeNoException();
                return true;
            }
            case 5066: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Uri bf = this.bF(parcel.readString());
                parcel2.writeNoException();
                if (bf != null) {
                    parcel2.writeInt(1);
                    bf.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5501: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa17 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string12 = parcel.readString();
                n = parcel.readInt();
                this.b(aa17, string12, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5502: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final DataHolder ky = this.ky();
                parcel2.writeNoException();
                if (ky != null) {
                    parcel2.writeInt(1);
                    ky.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 6001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa18 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b43 = b8;
                if (parcel.readInt() != 0) {
                    b43 = true;
                }
                this.a(aa18, b43);
                parcel2.writeNoException();
                return true;
            }
            case 6002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa19 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string13 = parcel.readString();
                final String string14 = parcel.readString();
                boolean b44 = b9;
                if (parcel.readInt() != 0) {
                    b44 = true;
                }
                this.a(aa19, string13, string14, b44);
                parcel2.writeNoException();
                return true;
            }
            case 6003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa20 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                final boolean b45 = parcel.readInt() != 0;
                boolean b46 = b10;
                if (parcel.readInt() != 0) {
                    b46 = true;
                }
                this.d(aa20, n, b45, b46);
                parcel2.writeNoException();
                return true;
            }
            case 6004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa21 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                final boolean b47 = parcel.readInt() != 0;
                boolean b48 = b11;
                if (parcel.readInt() != 0) {
                    b48 = true;
                }
                this.e(aa21, n, b47, b48);
                parcel2.writeNoException();
                return true;
            }
            case 6501: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa22 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string15 = parcel.readString();
                n = parcel.readInt();
                final boolean b49 = parcel.readInt() != 0;
                final boolean b50 = parcel.readInt() != 0;
                final boolean b51 = parcel.readInt() != 0;
                if (parcel.readInt() != 0) {
                    b12 = true;
                }
                this.a(aa22, string15, n, b49, b50, b51, b12);
                parcel2.writeNoException();
                return true;
            }
            case 6502: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa23 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string16 = parcel.readString();
                boolean b52 = b13;
                if (parcel.readInt() != 0) {
                    b52 = true;
                }
                this.b(aa23, string16, b52);
                parcel2.writeNoException();
                return true;
            }
            case 6503: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa24 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b53 = b14;
                if (parcel.readInt() != 0) {
                    b53 = true;
                }
                this.b(aa24, b53);
                parcel2.writeNoException();
                return true;
            }
            case 6504: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa25 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string17 = parcel.readString();
                boolean b54 = b15;
                if (parcel.readInt() != 0) {
                    b54 = true;
                }
                this.c(aa25, string17, b54);
                parcel2.writeNoException();
                return true;
            }
            case 6505: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa26 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string18 = parcel.readString();
                boolean b55 = b16;
                if (parcel.readInt() != 0) {
                    b55 = true;
                }
                this.d(aa26, string18, b55);
                parcel2.writeNoException();
                return true;
            }
            case 6506: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa27 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string19 = parcel.readString();
                final String string20 = parcel.readString();
                boolean b56 = b17;
                if (parcel.readInt() != 0) {
                    b56 = true;
                }
                this.b(aa27, string19, string20, b56);
                parcel2.writeNoException();
                return true;
            }
            case 6507: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Uri uri2;
                if (parcel.readInt() != 0) {
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                else {
                    uri2 = null;
                }
                final ParcelFileDescriptor h2 = this.h(uri2);
                parcel2.writeNoException();
                if (h2 != null) {
                    parcel2.writeInt(1);
                    h2.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 7001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.k(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 7002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 7003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa28 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string21 = parcel.readString();
                n = parcel.readInt();
                final IBinder strongBinder6 = parcel.readStrongBinder();
                Bundle bundle11 = bundle2;
                if (parcel.readInt() != 0) {
                    bundle11 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.b(aa28, string21, n, strongBinder6, bundle11);
                parcel2.writeNoException();
                return true;
            }
            case 8001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 8002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.bG(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            case 8004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa29 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                n = parcel.readInt();
                n2 = parcel.readInt();
                final String[] stringArray2 = parcel.createStringArray();
                Bundle bundle12 = bundle3;
                if (parcel.readInt() != 0) {
                    bundle12 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.a(aa29, n, n2, stringArray2, bundle12);
                parcel2.writeNoException();
                return true;
            }
            case 8005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.l(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.m(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 8008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 8009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.n(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.o(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 8013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.s(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 8014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.p(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8024: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.kn();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 8025: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.v(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.e(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            case 8026: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 8018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8021: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.kz();
                parcel2.writeNoException();
                return true;
            }
            case 8023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa30 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string22 = parcel.readString();
                n = parcel.readInt();
                boolean b57 = b18;
                if (parcel.readInt() != 0) {
                    b57 = true;
                }
                this.a(aa30, string22, n, b57);
                parcel2.writeNoException();
                return true;
            }
            case 8027: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa31 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b58 = b19;
                if (parcel.readInt() != 0) {
                    b58 = true;
                }
                this.c(aa31, b58);
                parcel2.writeNoException();
                return true;
            }
            case 9001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa32 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string23 = parcel.readString();
                n = parcel.readInt();
                this.c(aa32, string23, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 9002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.q(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 9003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent kb = this.kb();
                parcel2.writeNoException();
                if (kb != null) {
                    parcel2.writeInt(1);
                    kb.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent bu = this.bu(parcel.readString());
                parcel2.writeNoException();
                if (bu != null) {
                    parcel2.writeInt(1);
                    bu.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent kc = this.kc();
                parcel2.writeNoException();
                if (kc != null) {
                    parcel2.writeInt(1);
                    kc.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent kd = this.kd();
                parcel2.writeNoException();
                if (kd != null) {
                    parcel2.writeInt(1);
                    kd.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent ke = this.ke();
                parcel2.writeNoException();
                if (ke != null) {
                    parcel2.writeInt(1);
                    ke.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = parcel.readInt();
                n2 = parcel.readInt();
                final Intent a = this.a(n, n2, parcel.readInt() != 0);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(1);
                    a.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = parcel.readInt();
                n2 = parcel.readInt();
                final Intent b59 = this.b(n, n2, parcel.readInt() != 0);
                parcel2.writeNoException();
                if (b59 != null) {
                    parcel2.writeInt(1);
                    b59.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent kj = this.kj();
                parcel2.writeNoException();
                if (kj != null) {
                    parcel2.writeInt(1);
                    kj.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                RoomEntity roomEntity;
                if (parcel.readInt() != 0) {
                    roomEntity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(parcel);
                }
                else {
                    roomEntity = null;
                }
                final Intent a2 = this.a(roomEntity, parcel.readInt());
                parcel2.writeNoException();
                if (a2 != null) {
                    parcel2.writeInt(1);
                    a2.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent kk = this.kk();
                parcel2.writeNoException();
                if (kk != null) {
                    parcel2.writeInt(1);
                    kk.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent ka = this.kA();
                parcel2.writeNoException();
                if (ka != null) {
                    parcel2.writeInt(1);
                    ka.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9031: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final ParticipantEntity[] array = (ParticipantEntity[])parcel.createTypedArray((Parcelable$Creator)ParticipantEntity.CREATOR);
                final String string24 = parcel.readString();
                final String string25 = parcel.readString();
                Uri uri3;
                if (parcel.readInt() != 0) {
                    uri3 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                else {
                    uri3 = null;
                }
                if (parcel.readInt() != 0) {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                final Intent a3 = this.a(array, string24, string25, uri3, uri);
                parcel2.writeNoException();
                if (a3 != null) {
                    parcel2.writeInt(1);
                    a3.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.kl();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 9020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa33 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string26 = parcel.readString();
                n = parcel.readInt();
                this.d(aa33, string26, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 9028: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa34 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string27 = parcel.readString();
                final String string28 = parcel.readString();
                n = parcel.readInt();
                this.a(aa34, string27, string28, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 9030: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final ParcelFileDescriptor bh = this.bH(parcel.readString());
                parcel2.writeNoException();
                if (bh != null) {
                    parcel2.writeInt(1);
                    bh.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 10001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 10002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.t(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 10003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 10004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 10005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 10007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 10008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 10009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent a4 = this.a(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                if (a4 != null) {
                    parcel2.writeInt(1);
                    a4.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 10013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.kp();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 10023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.kq();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 10015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent ko = this.ko();
                parcel2.writeNoException();
                if (ko != null) {
                    parcel2.writeInt(1);
                    ko.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 10022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                GameRequestCluster ck = gameRequestCluster;
                if (parcel.readInt() != 0) {
                    ck = GameRequestCluster.CREATOR.ck(parcel);
                }
                final Intent a5 = this.a(ck, parcel.readString());
                parcel2.writeNoException();
                if (a5 != null) {
                    parcel2.writeInt(1);
                    a5.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 10014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.s(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa35 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string29 = parcel.readString();
                n = parcel.readInt();
                boolean b60 = b20;
                if (parcel.readInt() != 0) {
                    b60 = true;
                }
                this.b(aa35, string29, n, b60);
                parcel2.writeNoException();
                return true;
            }
            case 10021: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                ZInvitationCluster ci = zInvitationCluster;
                if (parcel.readInt() != 0) {
                    ci = ZInvitationCluster.CREATOR.ci(parcel);
                }
                final Intent a6 = this.a(ci, parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (a6 != null) {
                    parcel2.writeInt(1);
                    a6.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 10018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readInt(), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            case 10019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
                parcel2.writeNoException();
                return true;
            }
            case 10020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 11001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.j(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 11002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.kB();
                parcel2.writeNoException();
                return true;
            }
            case 12001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent a7 = this.a(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt());
                parcel2.writeNoException();
                if (a7 != null) {
                    parcel2.writeInt(1);
                    a7.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 12002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa36 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b61 = b21;
                if (parcel.readInt() != 0) {
                    b61 = true;
                }
                this.d(aa36, b61);
                parcel2.writeNoException();
                return true;
            }
            case 12003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa37 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string30 = parcel.readString();
                final String string31 = parcel.readString();
                boolean b62 = b22;
                if (parcel.readInt() != 0) {
                    b62 = true;
                }
                this.c(aa37, string30, string31, b62);
                parcel2.writeNoException();
                return true;
            }
            case 12006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa38 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string32 = parcel.readString();
                boolean b63 = b23;
                if (parcel.readInt() != 0) {
                    b63 = true;
                }
                this.e(aa38, string32, b63);
                parcel2.writeNoException();
                return true;
            }
            case 12007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa39 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string33 = parcel.readString();
                SnapshotMetadataChange fromParcel;
                if (parcel.readInt() != 0) {
                    fromParcel = SnapshotMetadataChange.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel = null;
                }
                Contents contents2;
                if (parcel.readInt() != 0) {
                    contents2 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                else {
                    contents2 = null;
                }
                this.a(aa39, string33, fromParcel, contents2);
                parcel2.writeNoException();
                return true;
            }
            case 12019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                Contents contents3;
                if (parcel.readInt() != 0) {
                    contents3 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                else {
                    contents3 = null;
                }
                this.a(contents3);
                parcel2.writeNoException();
                return true;
            }
            case 12020: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.r(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12033: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa40 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string34 = parcel.readString();
                final String string35 = parcel.readString();
                SnapshotMetadataChange fromParcel2;
                if (parcel.readInt() != 0) {
                    fromParcel2 = SnapshotMetadataChange.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel2 = null;
                }
                Contents contents4 = contents;
                if (parcel.readInt() != 0) {
                    contents4 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                this.a(aa40, string34, string35, fromParcel2, contents4);
                parcel2.writeNoException();
                return true;
            }
            case 12035: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.kr();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 12036: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                n = this.ks();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 12005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.s(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.b(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12024: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.c(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12021: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa41 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string36 = parcel.readString();
                n = parcel.readInt();
                this.e(aa41, string36, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 12022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa42 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string37 = parcel.readString();
                n = parcel.readInt();
                this.f(aa42, string37, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 12025: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final boolean kc2 = this.kC();
                parcel2.writeNoException();
                n = n4;
                if (kc2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 12026: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean b64 = b24;
                if (parcel.readInt() != 0) {
                    b64 = true;
                }
                this.O(b64);
                parcel2.writeNoException();
                return true;
            }
            case 12027: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.t(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12032: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa43 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b65 = b25;
                if (parcel.readInt() != 0) {
                    b65 = true;
                }
                this.e(aa43, b65);
                parcel2.writeNoException();
                return true;
            }
            case 12016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa44 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b66 = b26;
                if (parcel.readInt() != 0) {
                    b66 = true;
                }
                this.f(aa44, b66);
                parcel2.writeNoException();
                return true;
            }
            case 12031: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa45 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b67 = b27;
                if (parcel.readInt() != 0) {
                    b67 = true;
                }
                this.a(aa45, b67, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 12017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.n(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.u(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.f(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa46 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final int[] intArray = parcel.createIntArray();
                n = parcel.readInt();
                boolean b68 = b28;
                if (parcel.readInt() != 0) {
                    b68 = true;
                }
                this.a(aa46, intArray, n, b68);
                parcel2.writeNoException();
                return true;
            }
            case 12029: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa47 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String[] stringArray3 = parcel.createStringArray();
                boolean b69 = b29;
                if (parcel.readInt() != 0) {
                    b69 = true;
                }
                this.a(aa47, stringArray3, b69);
                parcel2.writeNoException();
                return true;
            }
            case 12015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa48 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string38 = parcel.readString();
                final String string39 = parcel.readString();
                final int[] intArray2 = parcel.createIntArray();
                n = parcel.readInt();
                this.a(aa48, string38, string39, intArray2, n, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 12028: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.a(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 12011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 12013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(IGamesCallbacks$Stub.aA(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.u(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 12014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                this.d(parcel.readLong(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12030: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent b70 = this.b(parcel.createIntArray());
                parcel2.writeNoException();
                if (b70 != null) {
                    parcel2.writeInt(1);
                    b70.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 12034: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final Intent bz = this.bz(parcel.readString());
                parcel2.writeNoException();
                if (bz != null) {
                    parcel2.writeInt(1);
                    bz.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 12018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa49 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                final String string40 = parcel.readString();
                final String string41 = parcel.readString();
                n = parcel.readInt();
                this.b(aa49, string40, string41, n, parcel.readInt() != 0, parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 13001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                boolean b71 = b30;
                if (parcel.readInt() != 0) {
                    b71 = true;
                }
                this.P(b71);
                parcel2.writeNoException();
                return true;
            }
            case 13002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final String string42 = parcel.readString();
                final IBinder strongBinder7 = parcel.readStrongBinder();
                Bundle bundle13;
                if (parcel.readInt() != 0) {
                    bundle13 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle13 = null;
                }
                this.a(string42, strongBinder7, bundle13);
                parcel2.writeNoException();
                return true;
            }
            case 13003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa50 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b72 = b31;
                if (parcel.readInt() != 0) {
                    b72 = true;
                }
                this.g(aa50, b72);
                parcel2.writeNoException();
                return true;
            }
            case 13004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                final IGamesCallbacks aa51 = IGamesCallbacks$Stub.aA(parcel.readStrongBinder());
                boolean b73 = b32;
                if (parcel.readInt() != 0) {
                    b73 = true;
                }
                this.h(aa51, b73);
                parcel2.writeNoException();
                return true;
            }
            case 13005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                AchievementEntity fromParcel3 = achievementEntity;
                if (parcel.readInt() != 0) {
                    fromParcel3 = AchievementEntity.CREATOR.createFromParcel(parcel);
                }
                final Intent a8 = this.a(fromParcel3);
                parcel2.writeNoException();
                if (a8 != null) {
                    parcel2.writeInt(1);
                    a8.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
        }
    }
}
