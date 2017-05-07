// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ae$a extends Binder implements ae
{
    public ae$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.wearable.internal.IWearableListener");
    }
    
    public static ae bS(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof ae) {
            return (ae)queryLocalInterface;
        }
        return new ae$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final ah ah = null;
        final ak ak = null;
        final ak ak2 = null;
        final DataHolder dataHolder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wearable.internal.IWearableListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                DataHolder z = dataHolder;
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.Z(z);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                ah ah2 = ah;
                if (parcel.readInt() != 0) {
                    ah2 = (ah)com.google.android.gms.wearable.internal.ah.CREATOR.createFromParcel(parcel);
                }
                this.a(ah2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                ak ak3 = ak;
                if (parcel.readInt() != 0) {
                    ak3 = (ak)com.google.android.gms.wearable.internal.ak.CREATOR.createFromParcel(parcel);
                }
                this.a(ak3);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                ak ak4 = ak2;
                if (parcel.readInt() != 0) {
                    ak4 = (ak)com.google.android.gms.wearable.internal.ak.CREATOR.createFromParcel(parcel);
                }
                this.b(ak4);
                return true;
            }
        }
    }
}
