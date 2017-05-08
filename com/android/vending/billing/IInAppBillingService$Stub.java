// 
// Decompiled by Procyon v0.5.30
// 

package com.android.vending.billing;

import java.util.List;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IInAppBillingService$Stub extends Binder implements IInAppBillingService
{
    public static IInAppBillingService asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
        if (queryLocalInterface != null && queryLocalInterface instanceof IInAppBillingService) {
            return (IInAppBillingService)queryLocalInterface;
        }
        return new IInAppBillingService$Stub$Proxy(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        Bundle bundle = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.android.vending.billing.IInAppBillingService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = this.isBillingSupported(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = parcel.readInt();
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                final Bundle skuDetails = this.getSkuDetails(n, string, string2, bundle);
                parcel2.writeNoException();
                if (skuDetails != null) {
                    parcel2.writeInt(1);
                    skuDetails.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                final Bundle buyIntent = this.getBuyIntent(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (buyIntent != null) {
                    parcel2.writeInt(1);
                    buyIntent.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                final Bundle purchases = this.getPurchases(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (purchases != null) {
                    parcel2.writeInt(1);
                    purchases.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = this.consumePurchase(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = this.isPromoEligible(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                final Bundle buyIntentToReplaceSkus = this.getBuyIntentToReplaceSkus(parcel.readInt(), parcel.readString(), parcel.createStringArrayList(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (buyIntentToReplaceSkus != null) {
                    parcel2.writeInt(1);
                    buyIntentToReplaceSkus.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = parcel.readInt();
                final String string3 = parcel.readString();
                final String string4 = parcel.readString();
                final String string5 = parcel.readString();
                final String string6 = parcel.readString();
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                final Bundle buyIntentExtraParams = this.getBuyIntentExtraParams(n, string3, string4, string5, string6, bundle2);
                parcel2.writeNoException();
                if (buyIntentExtraParams != null) {
                    parcel2.writeInt(1);
                    buyIntentExtraParams.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.android.vending.billing.IInAppBillingService");
                n = parcel.readInt();
                final String string7 = parcel.readString();
                final String string8 = parcel.readString();
                final String string9 = parcel.readString();
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                final Bundle purchaseHistory = this.getPurchaseHistory(n, string7, string8, string9, bundle3);
                parcel2.writeNoException();
                if (purchaseHistory != null) {
                    parcel2.writeInt(1);
                    purchaseHistory.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
}
