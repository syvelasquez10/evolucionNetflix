// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import android.os.IBinder;

class hw$a$a implements hw
{
    private IBinder lb;
    
    hw$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final Status status) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
            if (status != null) {
                obtain.writeInt(1);
                status.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final Status status, final ParcelFileDescriptor parcelFileDescriptor) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                        this.lb.transact(2, obtain, (Parcel)null, 1);
                        return;
                    }
                }
                finally {
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final Status status, final boolean b) {
    Label_0078_Outer:
        while (true) {
            int n = 1;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                while (true) {
                    Label_0083: {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                            if (status != null) {
                                obtain.writeInt(1);
                                status.writeToParcel(obtain, 0);
                                break Label_0083;
                            }
                            obtain.writeInt(0);
                            break Label_0083;
                            obtain.writeInt(n);
                            this.lb.transact(3, obtain, (Parcel)null, 1);
                            return;
                        }
                        finally {
                            obtain.recycle();
                        }
                        n = 0;
                        continue Label_0078_Outer;
                    }
                    if (b) {
                        continue Label_0078_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public void a(final hm$b hm$b) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
            if (hm$b != null) {
                obtain.writeInt(1);
                hm$b.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(4, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
