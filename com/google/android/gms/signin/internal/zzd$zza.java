// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Scope;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzd$zza extends Binder implements zzd
{
    public zzd$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
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
                parcel2.writeString("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                this.zza(parcel.readString(), parcel.createTypedArrayList((Parcelable$Creator)Scope.CREATOR), zzf$zza.zzdN(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.IOfflineAccessCallbacks");
                this.zza(parcel.readString(), parcel.readString(), zzf$zza.zzdN(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
