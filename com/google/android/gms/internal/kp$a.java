// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.ListSubscriptionsResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class kp$a extends Binder implements kp
{
    public kp$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }
    
    public static kp at(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof kp) {
            return (kp)queryLocalInterface;
        }
        return new kp$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
                ListSubscriptionsResult listSubscriptionsResult;
                if (parcel.readInt() != 0) {
                    listSubscriptionsResult = (ListSubscriptionsResult)ListSubscriptionsResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    listSubscriptionsResult = null;
                }
                this.a(listSubscriptionsResult);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
