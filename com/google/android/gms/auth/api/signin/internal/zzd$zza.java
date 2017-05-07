// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzd$zza extends Binder implements zzd
{
    public static zzd zzay(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzd) {
            return (zzd)queryLocalInterface;
        }
        return new zzd$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                Status status;
                if (parcel.readInt() != 0) {
                    status = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status = null;
                }
                Intent intent;
                if (parcel.readInt() != 0) {
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                else {
                    intent = null;
                }
                this.zza(status, intent);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                Status status2;
                if (parcel.readInt() != 0) {
                    status2 = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status2 = null;
                }
                this.zzk(status2);
                parcel2.writeNoException();
                return true;
            }
            case 101: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                GoogleSignInAccount googleSignInAccount;
                if (parcel.readInt() != 0) {
                    googleSignInAccount = (GoogleSignInAccount)GoogleSignInAccount.CREATOR.createFromParcel(parcel);
                }
                else {
                    googleSignInAccount = null;
                }
                Status status3;
                if (parcel.readInt() != 0) {
                    status3 = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status3 = null;
                }
                this.zza(googleSignInAccount, status3);
                parcel2.writeNoException();
                return true;
            }
            case 102: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                Status status4;
                if (parcel.readInt() != 0) {
                    status4 = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status4 = null;
                }
                this.zzl(status4);
                parcel2.writeNoException();
                return true;
            }
            case 103: {
                parcel.enforceInterface("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
                Status status5;
                if (parcel.readInt() != 0) {
                    status5 = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status5 = null;
                }
                this.zzm(status5);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
