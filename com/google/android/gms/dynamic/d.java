// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.IInterface;

public interface d extends IInterface
{
    public abstract static class a extends Binder implements d
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.dynamic.IObjectWrapper");
        }
        
        public static d am(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface != null && queryLocalInterface instanceof d) {
                return (d)queryLocalInterface;
            }
            return new d.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.dynamic.IObjectWrapper");
                    return true;
                }
            }
        }
        
        private static class a implements d
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
