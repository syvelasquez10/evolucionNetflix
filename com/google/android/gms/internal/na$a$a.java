// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Bundle;
import android.net.Uri;
import android.os.IBinder;

class na$a$a implements na
{
    private IBinder lb;
    
    na$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final mz mz, final Uri uri, final Bundle bundle, final boolean b) {
        while (true) {
            IBinder binder = null;
            int n = 1;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                Label_0134: {
                    Label_0119: {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaService");
                            if (mz != null) {
                                binder = mz.asBinder();
                            }
                            obtain.writeStrongBinder(binder);
                            if (uri != null) {
                                obtain.writeInt(1);
                                uri.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                break Label_0134;
                            }
                            break Label_0119;
                            obtain.writeInt(n);
                            this.lb.transact(1, obtain, (Parcel)null, 1);
                            return;
                        }
                        finally {
                            obtain.recycle();
                        }
                    }
                    obtain.writeInt(0);
                }
                if (b) {
                    continue;
                }
                n = 0;
                continue;
            }
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
