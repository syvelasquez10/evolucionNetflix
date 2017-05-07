// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.Tile;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class i$a extends Binder implements i
{
    public i$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }
    
    public static i by(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof i) {
            return (i)queryLocalInterface;
        }
        return new i$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                final Tile tile = this.getTile(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                if (tile != null) {
                    parcel2.writeInt(1);
                    tile.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
        }
    }
}
