// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ny;
import com.google.android.gms.internal.jp;
import com.google.android.gms.common.data.DataHolder;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class b$a extends Binder implements b
{
    public b$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.plus.internal.IPlusCallbacks");
    }
    
    public static b bE(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof b) {
            return (b)queryLocalInterface;
        }
        return new b$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        jp m = null;
        final DataHolder dataHolder = null;
        final ny ny = null;
        final Status status = null;
        DataHolder z = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.plus.internal.IPlusCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                n = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.a(n, bundle, bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                n = parcel.readInt();
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                ParcelFileDescriptor parcelFileDescriptor;
                if (parcel.readInt() != 0) {
                    parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                }
                else {
                    parcelFileDescriptor = null;
                }
                this.a(n, bundle3, parcelFileDescriptor);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                this.cb(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.a(z, parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                n = parcel.readInt();
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                if (parcel.readInt() != 0) {
                    m = jp.CREATOR.M(parcel);
                }
                this.a(n, bundle4, m);
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                DataHolder z2 = dataHolder;
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.a(z2, parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                n = parcel.readInt();
                Bundle bundle5;
                if (parcel.readInt() != 0) {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle5 = null;
                }
                this.h(n, bundle5);
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                this.cc(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                n = parcel.readInt();
                ny dd = ny;
                if (parcel.readInt() != 0) {
                    dd = com.google.android.gms.internal.ny.CREATOR.dd(parcel);
                }
                this.a(n, dd);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusCallbacks");
                Status fromParcel = status;
                if (parcel.readInt() != 0) {
                    fromParcel = Status.CREATOR.createFromParcel(parcel);
                }
                this.aB(fromParcel);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
