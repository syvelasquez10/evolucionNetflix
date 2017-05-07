// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import android.os.IInterface;

public interface io extends IInterface
{
    void a(final ApplicationMetadata p0, final String p1, final String p2, final boolean p3) throws RemoteException;
    
    void a(final String p0, final double p1, final boolean p2) throws RemoteException;
    
    void a(final String p0, final long p1) throws RemoteException;
    
    void a(final String p0, final long p1, final int p2) throws RemoteException;
    
    void ac(final int p0) throws RemoteException;
    
    void ad(final int p0) throws RemoteException;
    
    void ae(final int p0) throws RemoteException;
    
    void af(final int p0) throws RemoteException;
    
    void b(final ig p0) throws RemoteException;
    
    void b(final il p0) throws RemoteException;
    
    void b(final String p0, final byte[] p1) throws RemoteException;
    
    void k(final String p0, final String p1) throws RemoteException;
    
    void onApplicationDisconnected(final int p0) throws RemoteException;
    
    public abstract static class a extends Binder implements io
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.cast.internal.ICastDeviceControllerListener");
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
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
}
