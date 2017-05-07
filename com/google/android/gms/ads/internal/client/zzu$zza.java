// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzu$zza extends Binder implements zzu
{
    public zzu$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
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
                parcel2.writeString("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                final long value = this.getValue();
                parcel2.writeNoException();
                parcel2.writeLong(value);
                return true;
            }
        }
    }
}
