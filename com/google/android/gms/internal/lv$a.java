// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class lv$a extends Binder implements lv
{
    public lv$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
    }
    
    public static lv aJ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof lv) {
            return (lv)queryLocalInterface;
        }
        return new lv$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                this.onAddGeofencesResult(parcel.readInt(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                this.onRemoveGeofencesByRequestIdsResult(parcel.readInt(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                int1 = parcel.readInt();
                PendingIntent pendingIntent;
                if (parcel.readInt() != 0) {
                    pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent = null;
                }
                this.onRemoveGeofencesByPendingIntentResult(int1, pendingIntent);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
