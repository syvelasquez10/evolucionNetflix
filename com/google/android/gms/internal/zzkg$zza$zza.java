// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.content.Intent;
import com.google.android.gms.auth.api.consent.GetConsentIntentRequest;
import android.os.IBinder;

class zzkg$zza$zza implements zzkg
{
    private IBinder zznJ;
    
    zzkg$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public Intent zza(final GetConsentIntentRequest getConsentIntentRequest) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.auth.api.consent.internal.IConsentService");
                if (getConsentIntentRequest != null) {
                    obtain.writeInt(1);
                    getConsentIntentRequest.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.zznJ.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
}
