// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ng$a extends Binder implements ng
{
    public static ng bC(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ng) {
            return (ng)queryLocalInterface;
        }
        return new ng$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final nl nl = null;
        final nl nl2 = null;
        nh cx = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string = parcel.readString();
                nl cy;
                if (parcel.readInt() != 0) {
                    cy = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                }
                else {
                    cy = null;
                }
                if (parcel.readInt() != 0) {
                    cx = nh.CREATOR.cX(parcel);
                }
                this.a(string, cy, cx);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string2 = parcel.readString();
                nl cy2 = nl;
                if (parcel.readInt() != 0) {
                    cy2 = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                }
                this.a(string2, cy2, parcel.createTypedArrayList((Parcelable$Creator)nh.CREATOR));
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                final String string3 = parcel.readString();
                nl cy3 = nl2;
                if (parcel.readInt() != 0) {
                    cy3 = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                }
                this.a(string3, cy3, parcel.createByteArray());
                return true;
            }
        }
    }
}
