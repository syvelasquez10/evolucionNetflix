// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzep$zza extends Binder implements zzep
{
    public static zzep zzH(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzep) {
            return (zzep)queryLocalInterface;
        }
        return new zzep$zza$zza(binder);
    }
    
    public boolean onTransact(int zzea, final Parcel parcel, final Parcel parcel2, final int n) {
        switch (zzea) {
            default: {
                return super.onTransact(zzea, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                zzea = this.zzea();
                parcel2.writeNoException();
                parcel2.writeInt(zzea);
                return true;
            }
        }
    }
}
