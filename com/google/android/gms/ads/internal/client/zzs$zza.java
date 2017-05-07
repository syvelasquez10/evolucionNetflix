// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzeh$zza;
import com.google.android.gms.dynamic.zzd$zza;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzs$zza extends Binder implements zzs
{
    public static zzs zzl(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzs) {
            return (zzs)queryLocalInterface;
        }
        return new zzs$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final AdSizeParcel adSizeParcel = null;
        AdSizeParcel zzc = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                final zzd zzbk = zzd$zza.zzbk(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    zzc = AdSizeParcel.CREATOR.zzc(parcel);
                }
                final IBinder zza = this.zza(zzbk, zzc, parcel.readString(), zzeh$zza.zzE(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(zza);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                final zzd zzbk2 = zzd$zza.zzbk(parcel.readStrongBinder());
                AdSizeParcel zzc2 = adSizeParcel;
                if (parcel.readInt() != 0) {
                    zzc2 = AdSizeParcel.CREATOR.zzc(parcel);
                }
                final IBinder zza2 = this.zza(zzbk2, zzc2, parcel.readString(), zzeh$zza.zzE(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(zza2);
                return true;
            }
        }
    }
}
