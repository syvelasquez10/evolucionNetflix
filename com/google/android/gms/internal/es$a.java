// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class es$a extends Binder implements es
{
    public es$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
    }
    
    public static es z(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
        if (queryLocalInterface != null && queryLocalInterface instanceof es) {
            return (es)queryLocalInterface;
        }
        return new es$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                final String cv = this.cv();
                parcel2.writeNoException();
                parcel2.writeString(cv);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                final String cw = this.cw();
                parcel2.writeNoException();
                parcel2.writeString(cw);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                this.c(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                this.ar();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlAd");
                this.as();
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
