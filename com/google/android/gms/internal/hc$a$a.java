// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.List;
import java.util.Map;
import android.os.IBinder;

class hc$a$a implements hc
{
    private IBinder lb;
    
    hc$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final Map map, final long n, final String s, final List<hb> list) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            obtain.writeMap(map);
            obtain.writeLong(n);
            obtain.writeString(s);
            obtain.writeTypedList((List)list);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void dI() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String getVersion() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
