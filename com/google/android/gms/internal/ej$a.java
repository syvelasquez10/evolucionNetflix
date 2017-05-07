// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ej$a extends Binder implements ej
{
    public static ej v(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof ej) {
            return (ej)queryLocalInterface;
        }
        return new ej$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                final IBinder b = this.b(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(b);
                return true;
            }
        }
    }
}
