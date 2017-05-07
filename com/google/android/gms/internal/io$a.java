// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class io$a extends Binder implements io
{
    public io$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final boolean b = false;
        boolean b2 = false;
        final ig ig = null;
        final il il = null;
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
                this.ac(parcel.readInt());
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
                this.a(applicationMetadata2, string, string2, b2);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.ad(parcel.readInt());
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
                this.a(string3, double1, b3);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.k(parcel.readString(), parcel.readString());
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.b(parcel.readString(), parcel.createByteArray());
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.af(parcel.readInt());
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.ae(parcel.readInt());
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.onApplicationDisconnected(parcel.readInt());
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.a(parcel.readString(), parcel.readLong(), parcel.readInt());
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                this.a(parcel.readString(), parcel.readLong());
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                ig ig2 = ig;
                if (parcel.readInt() != 0) {
                    ig2 = (ig)com.google.android.gms.internal.ig.CREATOR.createFromParcel(parcel);
                }
                this.b(ig2);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
                il il2 = il;
                if (parcel.readInt() != 0) {
                    il2 = (il)com.google.android.gms.internal.il.CREATOR.createFromParcel(parcel);
                }
                this.b(il2);
                return true;
            }
        }
    }
}
