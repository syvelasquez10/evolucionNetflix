// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzt$zza extends Binder implements zzt
{
    public zzt$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
    }
    
    public static zzt zzaL(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzt) {
            return (zzt)queryLocalInterface;
        }
        return new zzt$zza$zza(binder);
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
                parcel2.writeString("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                ResolveAccountResponse resolveAccountResponse;
                if (parcel.readInt() != 0) {
                    resolveAccountResponse = (ResolveAccountResponse)ResolveAccountResponse.CREATOR.createFromParcel(parcel);
                }
                else {
                    resolveAccountResponse = null;
                }
                this.zzb(resolveAccountResponse);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
