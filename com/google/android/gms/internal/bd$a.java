// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class bd$a extends Binder implements bd
{
    public bd$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IAdManager");
    }
    
    public static bd f(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        if (queryLocalInterface != null && queryLocalInterface instanceof bd) {
            return (bd)queryLocalInterface;
        }
        return new bd$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final av av = null;
        final ay ay = null;
        final IBinder binder = null;
        final int n3 = 0;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManager");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                final d x = this.X();
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (x != null) {
                    binder2 = x.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.destroy();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                final boolean ready = this.isReady();
                parcel2.writeNoException();
                if (ready) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                av b = av;
                if (parcel.readInt() != 0) {
                    b = com.google.android.gms.internal.av.CREATOR.b(parcel);
                }
                final boolean a = this.a(b);
                parcel2.writeNoException();
                n = n3;
                if (a) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.pause();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.resume();
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(bc$a.e(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(bf$a.h(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.showInterstitial();
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.stopLoading();
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.aj();
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                final ay y = this.Y();
                parcel2.writeNoException();
                if (y != null) {
                    parcel2.writeInt(1);
                    y.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                ay c = ay;
                if (parcel.readInt() != 0) {
                    c = com.google.android.gms.internal.ay.CREATOR.c(parcel);
                }
                this.a(c);
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(eh$a.t(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(el$a.x(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(et$a.A(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                this.a(eu$a.B(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                final String mediationAdapterClassName = this.getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                return true;
            }
        }
    }
}
