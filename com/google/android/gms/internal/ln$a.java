// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.identity.intents.UserAddressRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ln$a extends Binder implements ln
{
    public static ln aH(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ln) {
            return (ln)queryLocalInterface;
        }
        return new ln$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressService");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressService");
                final lm ag = lm$a.aG(parcel.readStrongBinder());
                UserAddressRequest userAddressRequest;
                if (parcel.readInt() != 0) {
                    userAddressRequest = (UserAddressRequest)UserAddressRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    userAddressRequest = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(ag, userAddressRequest, bundle);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
