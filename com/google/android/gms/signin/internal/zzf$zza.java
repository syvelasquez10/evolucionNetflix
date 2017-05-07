// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzp$zza;
import android.accounts.Account;
import com.google.android.gms.common.internal.zzt$zza;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.AuthAccountRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzf$zza extends Binder implements zzf
{
    public static zzf zzdN(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzf) {
            return (zzf)queryLocalInterface;
        }
        return new zzf$zza$zza(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        boolean b = false;
        final CheckServerAuthResult checkServerAuthResult = null;
        final ResolveAccountRequest resolveAccountRequest = null;
        final Account account = null;
        final RecordConsentRequest recordConsentRequest = null;
        AuthAccountRequest authAccountRequest = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.signin.internal.ISignInService");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                if (parcel.readInt() != 0) {
                    authAccountRequest = (AuthAccountRequest)AuthAccountRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(authAccountRequest, zze$zza.zzdM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                CheckServerAuthResult checkServerAuthResult2 = checkServerAuthResult;
                if (parcel.readInt() != 0) {
                    checkServerAuthResult2 = (CheckServerAuthResult)CheckServerAuthResult.CREATOR.createFromParcel(parcel);
                }
                this.zza(checkServerAuthResult2);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zzaq(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                ResolveAccountRequest resolveAccountRequest2 = resolveAccountRequest;
                if (parcel.readInt() != 0) {
                    resolveAccountRequest2 = (ResolveAccountRequest)ResolveAccountRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(resolveAccountRequest2, zzt$zza.zzaL(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zzjq(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                n = parcel.readInt();
                Account account2 = account;
                if (parcel.readInt() != 0) {
                    account2 = (Account)Account.CREATOR.createFromParcel(parcel);
                }
                this.zza(n, account2, zze$zza.zzdM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                final zzp zzaH = zzp$zza.zzaH(parcel.readStrongBinder());
                n = parcel.readInt();
                if (parcel.readInt() != 0) {
                    b = true;
                }
                this.zza(zzaH, n, b);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                RecordConsentRequest recordConsentRequest2 = recordConsentRequest;
                if (parcel.readInt() != 0) {
                    recordConsentRequest2 = (RecordConsentRequest)RecordConsentRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(recordConsentRequest2, zze$zza.zzdM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                this.zza(zze$zza.zzdM(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
