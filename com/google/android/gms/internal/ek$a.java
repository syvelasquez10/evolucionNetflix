// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ek$a extends Binder implements ek
{
    public ek$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
    }
    
    public static ek w(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        if (queryLocalInterface != null && queryLocalInterface instanceof ek) {
            return (ek)queryLocalInterface;
        }
        return new ek$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int resultCode, final Parcel parcel, final Parcel parcel2, final int n) {
        final int n2 = 0;
        switch (resultCode) {
            default: {
                return super.onTransact(resultCode, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                final String productId = this.getProductId();
                parcel2.writeNoException();
                parcel2.writeString(productId);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                final Intent purchaseData = this.getPurchaseData();
                parcel2.writeNoException();
                if (purchaseData != null) {
                    parcel2.writeInt(1);
                    purchaseData.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                resultCode = this.getResultCode();
                parcel2.writeNoException();
                parcel2.writeInt(resultCode);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                final boolean verified = this.isVerified();
                parcel2.writeNoException();
                resultCode = n2;
                if (verified) {
                    resultCode = 1;
                }
                parcel2.writeInt(resultCode);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                this.finishPurchase();
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
