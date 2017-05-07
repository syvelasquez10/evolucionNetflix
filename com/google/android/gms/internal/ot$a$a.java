// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class ot$a$a implements ot
{
    private IBinder lb;
    
    ot$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public oq a(final d d, final c c, final WalletFragmentOptions walletFragmentOptions, final or or) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            IBinder binder3;
            if (c != null) {
                binder3 = c.asBinder();
            }
            else {
                binder3 = null;
            }
            obtain.writeStrongBinder(binder3);
            if (walletFragmentOptions != null) {
                obtain.writeInt(1);
                walletFragmentOptions.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            IBinder binder4 = binder;
            if (or != null) {
                binder4 = or.asBinder();
            }
            obtain.writeStrongBinder(binder4);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return oq$a.bJ(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
}
