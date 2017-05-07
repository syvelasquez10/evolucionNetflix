// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class bs$a extends Binder implements bs
{
    public bs$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                this.i(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                this.as();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final String bt = this.bt();
                parcel2.writeNoException();
                parcel2.writeString(bt);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final d bu = this.bu();
                parcel2.writeNoException();
                IBinder binder3 = binder2;
                if (bu != null) {
                    binder3 = bu.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final String body = this.getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final d ba = this.bA();
                parcel2.writeNoException();
                IBinder binder4 = binder;
                if (ba != null) {
                    binder4 = ba.asBinder();
                }
                parcel2.writeStrongBinder(binder4);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final String bw = this.bw();
                parcel2.writeNoException();
                parcel2.writeString(bw);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                final String bb = this.bB();
                parcel2.writeNoException();
                parcel2.writeString(bb);
                return true;
            }
        }
    }
}
