// 
// Decompiled by Procyon v0.5.30
// 

package com.android.vending.billing;

import java.util.List;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;

class IInAppBillingService$Stub$Proxy implements IInAppBillingService
{
    private IBinder mRemote;
    
    IInAppBillingService$Stub$Proxy(final IBinder mRemote) {
        this.mRemote = mRemote;
    }
    
    public IBinder asBinder() {
        return this.mRemote;
    }
    
    @Override
    public int consumePurchase(int int1, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(int1);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.mRemote.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            int1 = obtain2.readInt();
            return int1;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle getBuyIntent(final int n, final String s, final String s2, final String s3, final String s4) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(n);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeString(s3);
            obtain.writeString(s4);
            this.mRemote.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            Bundle bundle;
            if (obtain2.readInt() != 0) {
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
            }
            else {
                bundle = null;
            }
            return bundle;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle getBuyIntentExtraParams(final int n, final String s, final String s2, final String s3, final String s4, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                obtain.writeInt(n);
                obtain.writeString(s);
                obtain.writeString(s2);
                obtain.writeString(s3);
                obtain.writeString(s4);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(8, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Bundle getBuyIntentToReplaceSkus(final int n, final String s, final List<String> list, final String s2, final String s3, final String s4) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(n);
            obtain.writeString(s);
            obtain.writeStringList((List)list);
            obtain.writeString(s2);
            obtain.writeString(s3);
            obtain.writeString(s4);
            this.mRemote.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            Bundle bundle;
            if (obtain2.readInt() != 0) {
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
            }
            else {
                bundle = null;
            }
            return bundle;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle getPurchaseHistory(final int n, final String s, final String s2, final String s3, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                obtain.writeInt(n);
                obtain.writeString(s);
                obtain.writeString(s2);
                obtain.writeString(s3);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(9, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Bundle getPurchases(final int n, final String s, final String s2, final String s3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(n);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeString(s3);
            this.mRemote.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            Bundle bundle;
            if (obtain2.readInt() != 0) {
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
            }
            else {
                bundle = null;
            }
            return bundle;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle getSkuDetails(final int n, final String s, final String s2, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
                obtain.writeInt(n);
                obtain.writeString(s);
                obtain.writeString(s2);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public int isBillingSupported(int int1, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(int1);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            int1 = obtain2.readInt();
            return int1;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int isPromoEligible(int int1, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            obtain.writeInt(int1);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.mRemote.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            int1 = obtain2.readInt();
            return int1;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
