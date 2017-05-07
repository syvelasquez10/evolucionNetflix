// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzo$zza extends Binder implements zzo
{
    public zzo$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    public static zzo zzaF(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzo) {
            return (zzo)queryLocalInterface;
        }
        return new zzo$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final Bundle bundle = null;
        Bundle bundle2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                n = parcel.readInt();
                final IBinder strongBinder = parcel.readStrongBinder();
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zza(n, strongBinder, bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                n = parcel.readInt();
                Bundle bundle3 = bundle;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzb(n, bundle3);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
