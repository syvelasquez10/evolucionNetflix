// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class br$a extends Binder implements br
{
    public br$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
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
                parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                this.i(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                this.as();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final String bt = this.bt();
                parcel2.writeNoException();
                parcel2.writeString(bt);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
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
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final String body = this.getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final d bv = this.bv();
                parcel2.writeNoException();
                IBinder binder4 = binder;
                if (bv != null) {
                    binder4 = bv.asBinder();
                }
                parcel2.writeStrongBinder(binder4);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final String bw = this.bw();
                parcel2.writeNoException();
                parcel2.writeString(bw);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final double bx = this.bx();
                parcel2.writeNoException();
                parcel2.writeDouble(bx);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final String by = this.by();
                parcel2.writeNoException();
                parcel2.writeString(by);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                final String bz = this.bz();
                parcel2.writeNoException();
                parcel2.writeString(bz);
                return true;
            }
        }
    }
}
