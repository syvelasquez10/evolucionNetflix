// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.c$a;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ot$a extends Binder implements ot
{
    public static ot bM(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof ot) {
            return (ot)queryLocalInterface;
        }
        return new ot$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                final d am = d$a.am(parcel.readStrongBinder());
                final c al = c$a.al(parcel.readStrongBinder());
                WalletFragmentOptions walletFragmentOptions;
                if (parcel.readInt() != 0) {
                    walletFragmentOptions = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(parcel);
                }
                else {
                    walletFragmentOptions = null;
                }
                final oq a = this.a(am, al, walletFragmentOptions, or$a.bK(parcel.readStrongBinder()));
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (a != null) {
                    binder2 = a.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
        }
    }
}
