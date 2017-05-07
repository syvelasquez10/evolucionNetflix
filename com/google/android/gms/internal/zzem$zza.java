// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzem$zza extends Binder implements zzem
{
    public static zzem zzE(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzem) {
            return (zzem)queryLocalInterface;
        }
        return new zzem$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                final zzen zzae = this.zzae(parcel.readString());
                parcel2.writeNoException();
                IBinder binder;
                if (zzae != null) {
                    binder = zzae.asBinder();
                }
                else {
                    binder = null;
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                final boolean zzaf = this.zzaf(parcel.readString());
                parcel2.writeNoException();
                if (zzaf) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
