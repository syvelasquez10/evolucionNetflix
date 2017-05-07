// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class e$a extends Binder implements e
{
    public static e aa(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof e) {
            return (e)queryLocalInterface;
        }
        return new e$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
                this.a((ParcelableCollaborator[])parcel.createTypedArray((Parcelable$Creator)ParcelableCollaborator.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorsCallback");
                Status fromParcel;
                if (parcel.readInt() != 0) {
                    fromParcel = Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel = null;
                }
                this.o(fromParcel);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
