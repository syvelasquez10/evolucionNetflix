// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzb$zza extends Binder implements zzb
{
    public static zzb zzay(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzb) {
            return (zzb)queryLocalInterface;
        }
        return new zzb$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final SignInConfiguration signInConfiguration = null;
        SignInConfiguration signInConfiguration2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.signin.internal.ISignInService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zza zzax = zza$zza.zzax(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    signInConfiguration2 = (SignInConfiguration)SignInConfiguration.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzax, signInConfiguration2);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zza zzax2 = zza$zza.zzax(parcel.readStrongBinder());
                SignInConfiguration signInConfiguration3 = signInConfiguration;
                if (parcel.readInt() != 0) {
                    signInConfiguration3 = (SignInConfiguration)SignInConfiguration.CREATOR.createFromParcel(parcel);
                }
                this.zzb(zzax2, signInConfiguration3);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
