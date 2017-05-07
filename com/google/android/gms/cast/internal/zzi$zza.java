// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.LaunchOptions;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzi$zza extends Binder implements zzi
{
    public static zzi zzaB(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzi) {
            return (zzi)queryLocalInterface;
        }
        return new zzi$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        boolean b = false;
        boolean b2 = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceController");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.disconnect();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                final String string = parcel.readString();
                if (parcel.readInt() != 0) {
                    b2 = true;
                }
                this.zzf(string, b2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzt(parcel.readString(), parcel.readString());
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzmI();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzbO(parcel.readString());
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzmx();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zza(parcel.readDouble(), parcel.readDouble(), parcel.readInt() != 0);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                final boolean b3 = parcel.readInt() != 0;
                final double double1 = parcel.readDouble();
                if (parcel.readInt() != 0) {
                    b = true;
                }
                this.zza(b3, double1, b);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zza(parcel.readString(), parcel.readString(), parcel.readLong());
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zza(parcel.readString(), parcel.createByteArray(), parcel.readLong());
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzbP(parcel.readString());
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                this.zzbQ(parcel.readString());
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                final String string2 = parcel.readString();
                LaunchOptions launchOptions;
                if (parcel.readInt() != 0) {
                    launchOptions = (LaunchOptions)LaunchOptions.CREATOR.createFromParcel(parcel);
                }
                else {
                    launchOptions = null;
                }
                this.zza(string2, launchOptions);
                return true;
            }
        }
    }
}
