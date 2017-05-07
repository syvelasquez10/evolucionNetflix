// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzkc$zza extends Binder implements zzkc
{
    public static zzkc zzan(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzkc) {
            return (zzkc)queryLocalInterface;
        }
        return new zzkc$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusCallbacks");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusCallbacks");
                Status status;
                if (parcel.readInt() != 0) {
                    status = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status = null;
                }
                this.zzf(status);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
