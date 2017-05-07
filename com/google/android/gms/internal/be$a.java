// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class be$a extends Binder implements be
{
    public static be g(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof be) {
            return (be)queryLocalInterface;
        }
        return new be$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                final d am = d$a.am(parcel.readStrongBinder());
                ay c;
                if (parcel.readInt() != 0) {
                    c = ay.CREATOR.c(parcel);
                }
                else {
                    c = null;
                }
                final IBinder a = this.a(am, c, parcel.readString(), ct$a.l(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a);
                return true;
            }
        }
    }
}
