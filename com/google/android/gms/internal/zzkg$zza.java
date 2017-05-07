// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.auth.api.consent.GetConsentIntentRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzkg$zza extends Binder implements zzkg
{
    public static zzkg zzaq(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzkg) {
            return (zzkg)queryLocalInterface;
        }
        return new zzkg$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.consent.internal.IConsentService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
                GetConsentIntentRequest getConsentIntentRequest;
                if (parcel.readInt() != 0) {
                    getConsentIntentRequest = (GetConsentIntentRequest)GetConsentIntentRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    getConsentIntentRequest = null;
                }
                final Intent zza = this.zza(getConsentIntentRequest);
                parcel2.writeNoException();
                if (zza != null) {
                    parcel2.writeInt(1);
                    zza.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
}
