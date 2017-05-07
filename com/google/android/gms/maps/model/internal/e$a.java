// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class e$a extends Binder implements e
{
    public static e bt(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof e) {
            return (e)queryLocalInterface;
        }
        return new e$a$a(binder);
    }
    
    public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) {
        switch (hashCodeRemote) {
            default: {
                return super.onTransact(hashCodeRemote, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                final String name = this.getName();
                parcel2.writeNoException();
                parcel2.writeString(name);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                final String shortName = this.getShortName();
                parcel2.writeNoException();
                parcel2.writeString(shortName);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                this.activate();
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                final boolean a = this.a(bt(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (a) {
                    hashCodeRemote = 1;
                }
                else {
                    hashCodeRemote = 0;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
        }
    }
}
