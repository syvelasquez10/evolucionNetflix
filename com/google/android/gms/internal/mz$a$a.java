// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

class mz$a$a implements mz
{
    private IBinder lb;
    
    mz$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                        this.lb.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
