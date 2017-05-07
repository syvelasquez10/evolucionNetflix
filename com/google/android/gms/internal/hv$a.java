// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class hv$a extends Binder implements hv
{
    public static hv F(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
        if (queryLocalInterface != null && queryLocalInterface instanceof hv) {
            return (hv)queryLocalInterface;
        }
        return new hv$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                this.a(hw$a.G(parcel.readStrongBinder()), parcel.readString(), (hs[])parcel.createTypedArray((Parcelable$Creator)hs.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                this.a(hw$a.G(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                this.b(hw$a.G(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                this.a(hw$a.G(parcel.readStrongBinder()), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
                hm$a p4;
                if (parcel.readInt() != 0) {
                    p4 = hm$a.CREATOR.p(parcel);
                }
                else {
                    p4 = null;
                }
                this.a(p4, hw$a.G(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
