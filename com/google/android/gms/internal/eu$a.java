// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class eu$a extends Binder implements eu
{
    public eu$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
    }
    
    public static eu B(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof eu) {
            return (eu)queryLocalInterface;
        }
        return new eu$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
                final boolean e = this.e(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                if (e) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.rawhtmlad.client.IRawHtmlPublisherInterstitialAdListener");
                this.a(es$a.z(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
