// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.IBinder;

class lv$a$a implements lv
{
    private IBinder lb;
    
    lv$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void onAddGeofencesResult(final int n, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(n);
            obtain.writeStringArray(array);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(n);
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
            obtain.writeInt(n);
            obtain.writeStringArray(array);
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
