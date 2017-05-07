// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import android.os.Parcel;
import android.os.Bundle;
import android.os.IBinder;

class os$a$a implements os
{
    private IBinder lb;
    
    os$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final Bundle bundle, final ov ov) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            IBinder binder2 = binder;
            if (ov != null) {
                binder2 = ov.asBinder();
            }
            obtain.writeStrongBinder(binder2);
            this.lb.transact(5, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final om om, final Bundle bundle, final ov ov) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (om != null) {
                        obtain.writeInt(1);
                        om.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        IBinder binder2 = binder;
                        if (ov != null) {
                            binder2 = ov.asBinder();
                        }
                        obtain.writeStrongBinder(binder2);
                        this.lb.transact(8, obtain, (Parcel)null, 1);
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
    public void a(final FullWalletRequest fullWalletRequest, final Bundle bundle, final ov ov) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (fullWalletRequest != null) {
                        obtain.writeInt(1);
                        fullWalletRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        IBinder binder2 = binder;
                        if (ov != null) {
                            binder2 = ov.asBinder();
                        }
                        obtain.writeStrongBinder(binder2);
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
    public void a(final MaskedWalletRequest maskedWalletRequest, final Bundle bundle, final ou ou) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (maskedWalletRequest != null) {
                        obtain.writeInt(1);
                        maskedWalletRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        IBinder binder2 = binder;
                        if (ou != null) {
                            binder2 = ou.asBinder();
                        }
                        obtain.writeStrongBinder(binder2);
                        this.lb.transact(7, obtain, (Parcel)null, 1);
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
    public void a(final MaskedWalletRequest maskedWalletRequest, final Bundle bundle, final ov ov) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (maskedWalletRequest != null) {
                        obtain.writeInt(1);
                        maskedWalletRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        IBinder binder2 = binder;
                        if (ov != null) {
                            binder2 = ov.asBinder();
                        }
                        obtain.writeStrongBinder(binder2);
                        this.lb.transact(1, obtain, (Parcel)null, 1);
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
    public void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (notifyTransactionStatusRequest != null) {
                        obtain.writeInt(1);
                        notifyTransactionStatusRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        this.lb.transact(4, obtain, (Parcel)null, 1);
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
    public void a(final d d, final Bundle bundle, final ov ov) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
                    if (d != null) {
                        obtain.writeInt(1);
                        d.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        IBinder binder2 = binder;
                        if (ov != null) {
                            binder2 = ov.asBinder();
                        }
                        obtain.writeStrongBinder(binder2);
                        this.lb.transact(6, obtain, (Parcel)null, 1);
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
    public void a(final String s, final String s2, final Bundle bundle, final ov ov) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            obtain.writeString(s);
            obtain.writeString(s2);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            IBinder binder2 = binder;
            if (ov != null) {
                binder2 = ov.asBinder();
            }
            obtain.writeStrongBinder(binder2);
            this.lb.transact(3, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void p(final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IOwService");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(9, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
