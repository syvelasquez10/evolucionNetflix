// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd$zza;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzfu$zza extends Binder implements zzfu
{
    public static zzfu zzR(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzfu) {
            return (zzfu)queryLocalInterface;
        }
        return new zzfu$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
                final IBinder zzf = this.zzf(zzd$zza.zzbk(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(zzf);
                return true;
            }
        }
    }
}
