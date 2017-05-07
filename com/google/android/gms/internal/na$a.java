// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.net.Uri;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class na$a extends Binder implements na
{
    public static na bA(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService");
        if (queryLocalInterface != null && queryLocalInterface instanceof na) {
            return (na)queryLocalInterface;
        }
        return new na$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaService");
                final mz bz = mz$a.bz(parcel.readStrongBinder());
                Uri uri;
                if (parcel.readInt() != 0) {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                else {
                    uri = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(bz, uri, bundle, parcel.readInt() != 0);
                return true;
            }
        }
    }
}
