// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class hc$a extends Binder implements hc
{
    public static hc E(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
        if (queryLocalInterface != null && queryLocalInterface instanceof hc) {
            return (hc)queryLocalInterface;
        }
        return new hc$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.analytics.internal.IAnalyticsService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                this.a(parcel.readHashMap(this.getClass().getClassLoader()), parcel.readLong(), parcel.readString(), parcel.createTypedArrayList((Parcelable$Creator)hb.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                this.dI();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                final String version = this.getVersion();
                parcel2.writeNoException();
                parcel2.writeString(version);
                return true;
            }
        }
    }
}
