// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class cu$a extends Binder implements cu
{
    public cu$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }
    
    public static cu m(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        if (queryLocalInterface != null && queryLocalInterface instanceof cu) {
            return (cu)queryLocalInterface;
        }
        return new cu$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final av av = null;
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                final d am = d$a.am(parcel.readStrongBinder());
                ay c;
                if (parcel.readInt() != 0) {
                    c = ay.CREATOR.c(parcel);
                }
                else {
                    c = null;
                }
                av b;
                if (parcel.readInt() != 0) {
                    b = com.google.android.gms.internal.av.CREATOR.b(parcel);
                }
                else {
                    b = null;
                }
                this.a(am, c, b, parcel.readString(), cv$a.n(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                final d view = this.getView();
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (view != null) {
                    binder2 = view.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                final d am2 = d$a.am(parcel.readStrongBinder());
                av b2 = av;
                if (parcel.readInt() != 0) {
                    b2 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                }
                this.a(am2, b2, parcel.readString(), cv$a.n(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                this.showInterstitial();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                this.destroy();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                final d am3 = d$a.am(parcel.readStrongBinder());
                ay c2;
                if (parcel.readInt() != 0) {
                    c2 = ay.CREATOR.c(parcel);
                }
                else {
                    c2 = null;
                }
                av b3;
                if (parcel.readInt() != 0) {
                    b3 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                }
                else {
                    b3 = null;
                }
                this.a(am3, c2, b3, parcel.readString(), parcel.readString(), cv$a.n(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                final d am4 = d$a.am(parcel.readStrongBinder());
                av b4;
                if (parcel.readInt() != 0) {
                    b4 = com.google.android.gms.internal.av.CREATOR.b(parcel);
                }
                else {
                    b4 = null;
                }
                this.a(am4, b4, parcel.readString(), parcel.readString(), cv$a.n(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                this.pause();
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                this.resume();
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
