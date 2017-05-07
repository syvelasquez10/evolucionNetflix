// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import java.util.List;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class d$a extends Binder implements d
{
    public static d bs(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof d) {
            return (d)queryLocalInterface;
        }
        return new d$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final int n3 = 0;
        final int n4 = 0;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                n = this.getActiveLevelIndex();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                n = this.getDefaultLevelIndex();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                final List<IBinder> levels = this.getLevels();
                parcel2.writeNoException();
                parcel2.writeBinderList((List)levels);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                final boolean underground = this.isUnderground();
                parcel2.writeNoException();
                n = n4;
                if (underground) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                final boolean b = this.b(bs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                n = n3;
                if (b) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                n = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
