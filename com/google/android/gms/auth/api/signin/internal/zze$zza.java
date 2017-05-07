// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zze$zza extends Binder implements zze
{
    public static zze zzaz(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
            return (zze)queryLocalInterface;
        }
        return new zze$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final SignInConfiguration signInConfiguration = null;
        final GoogleSignInConfig googleSignInConfig = null;
        final GoogleSignInConfig googleSignInConfig2 = null;
        final GoogleSignInConfig googleSignInConfig3 = null;
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
                final zzd zzay = zzd$zza.zzay(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    signInConfiguration2 = (SignInConfiguration)SignInConfiguration.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzay, signInConfiguration2);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zzd zzay2 = zzd$zza.zzay(parcel.readStrongBinder());
                SignInConfiguration signInConfiguration3 = signInConfiguration;
                if (parcel.readInt() != 0) {
                    signInConfiguration3 = (SignInConfiguration)SignInConfiguration.CREATOR.createFromParcel(parcel);
                }
                this.zzb(zzay2, signInConfiguration3);
                parcel2.writeNoException();
                return true;
            }
            case 101: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zzd zzay3 = zzd$zza.zzay(parcel.readStrongBinder());
                GoogleSignInConfig googleSignInConfig4 = googleSignInConfig;
                if (parcel.readInt() != 0) {
                    googleSignInConfig4 = (GoogleSignInConfig)GoogleSignInConfig.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzay3, googleSignInConfig4);
                parcel2.writeNoException();
                return true;
            }
            case 102: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zzd zzay4 = zzd$zza.zzay(parcel.readStrongBinder());
                GoogleSignInConfig googleSignInConfig5 = googleSignInConfig2;
                if (parcel.readInt() != 0) {
                    googleSignInConfig5 = (GoogleSignInConfig)GoogleSignInConfig.CREATOR.createFromParcel(parcel);
                }
                this.zzb(zzay4, googleSignInConfig5);
                parcel2.writeNoException();
                return true;
            }
            case 103: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
                final zzd zzay5 = zzd$zza.zzay(parcel.readStrongBinder());
                GoogleSignInConfig googleSignInConfig6 = googleSignInConfig3;
                if (parcel.readInt() != 0) {
                    googleSignInConfig6 = (GoogleSignInConfig)GoogleSignInConfig.CREATOR.createFromParcel(parcel);
                }
                this.zzc(zzay5, googleSignInConfig6);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
