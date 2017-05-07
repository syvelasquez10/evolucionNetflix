// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class i$a extends Binder implements i
{
    public static i O(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
        if (queryLocalInterface != null && queryLocalInterface instanceof i) {
            return (i)queryLocalInterface;
        }
        return new i$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.ICancelToken");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.ICancelToken");
                this.cancel();
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
