// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzj$zza extends Binder implements zzj
{
    public zzj$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final boolean b = false;
        boolean b2 = false;
        final ApplicationStatus applicationStatus = null;
        final DeviceStatus deviceStatus = null;
        final ApplicationMetadata applicationMetadata = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzaM(parcel.readInt());
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                ApplicationMetadata applicationMetadata2 = applicationMetadata;
                if (parcel.readInt() != 0) {
                    applicationMetadata2 = (ApplicationMetadata)ApplicationMetadata.CREATOR.createFromParcel(parcel);
                }
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    b2 = true;
                }
                this.zza(applicationMetadata2, string, string2, b2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzaN(parcel.readInt());
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                final String string3 = parcel.readString();
                final double double1 = parcel.readDouble();
                boolean b3 = b;
                if (parcel.readInt() != 0) {
                    b3 = true;
                }
                this.zza(string3, double1, b3);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzq(parcel.readString(), parcel.readString());
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzb(parcel.readString(), parcel.createByteArray());
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzaP(parcel.readInt());
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzaO(parcel.readInt());
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.onApplicationDisconnected(parcel.readInt());
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zza(parcel.readString(), parcel.readLong(), parcel.readInt());
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.zzd(parcel.readString(), parcel.readLong());
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                ApplicationStatus applicationStatus2 = applicationStatus;
                if (parcel.readInt() != 0) {
                    applicationStatus2 = (ApplicationStatus)ApplicationStatus.CREATOR.createFromParcel(parcel);
                }
                this.zzb(applicationStatus2);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                DeviceStatus deviceStatus2 = deviceStatus;
                if (parcel.readInt() != 0) {
                    deviceStatus2 = (DeviceStatus)DeviceStatus.CREATOR.createFromParcel(parcel);
                }
                this.zzb(deviceStatus2);
                return true;
            }
        }
    }
}
