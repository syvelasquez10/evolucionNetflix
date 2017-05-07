// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Parcel;
import android.os.IBinder;

class ng$a$a implements ng
{
    private IBinder lb;
    
    ng$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final String s, final nl nl, final nh nh) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    obtain.writeString(s);
                    if (nl != null) {
                        obtain.writeInt(1);
                        nl.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (nh != null) {
                        obtain.writeInt(1);
                        nh.writeToParcel(obtain, 0);
                        this.lb.transact(2, obtain, (Parcel)null, 1);
                        return;
                    }
                }
                finally {
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final String s, final nl nl, final List<nh> list) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
            obtain.writeString(s);
            if (nl != null) {
                obtain.writeInt(1);
                nl.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeTypedList((List)list);
            this.lb.transact(3, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final String s, final nl nl, final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
            obtain.writeString(s);
            if (nl != null) {
                obtain.writeInt(1);
                nl.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeByteArray(array);
            this.lb.transact(4, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
