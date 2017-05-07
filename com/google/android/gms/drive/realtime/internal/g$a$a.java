// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import com.google.android.gms.common.data.DataHolder;
import android.os.IBinder;

class g$a$a implements g
{
    private IBinder lb;
    
    g$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final DataHolder dataHolder, final ParcelableEventList list) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (list != null) {
                        obtain.writeInt(1);
                        list.writeToParcel(obtain, 0);
                        this.lb.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void o(final Status status) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
            if (status != null) {
                obtain.writeInt(1);
                status.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
