// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class hw$a extends Binder implements hw
{
    public hw$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
    }
    
    public static hw G(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof hw) {
            return (hw)queryLocalInterface;
        }
        return new hw$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        final Status status = null;
        final hm$b hm$b = null;
        final Status status2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                Status fromParcel = status2;
                if (parcel.readInt() != 0) {
                    fromParcel = Status.CREATOR.createFromParcel(parcel);
                }
                this.a(fromParcel);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                Status fromParcel2;
                if (parcel.readInt() != 0) {
                    fromParcel2 = Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel2 = null;
                }
                if (parcel.readInt() != 0) {
                    parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                }
                this.a(fromParcel2, parcelFileDescriptor);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                Status fromParcel3 = status;
                if (parcel.readInt() != 0) {
                    fromParcel3 = Status.CREATOR.createFromParcel(parcel);
                }
                this.a(fromParcel3, parcel.readInt() != 0);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                hm$b q = hm$b;
                if (parcel.readInt() != 0) {
                    q = com.google.android.gms.internal.hm$b.CREATOR.q(parcel);
                }
                this.a(q);
                return true;
            }
        }
    }
}
