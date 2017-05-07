// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzft$zza extends Binder implements zzft
{
    public zzft$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
    }
    
    public static zzft zzQ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzft) {
            return (zzft)queryLocalInterface;
        }
        return new zzft$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, int int2) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, int2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                this.onCreate();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                this.onDestroy();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                int1 = parcel.readInt();
                int2 = parcel.readInt();
                Intent intent;
                if (parcel.readInt() != 0) {
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                else {
                    intent = null;
                }
                this.onActivityResult(int1, int2, intent);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
