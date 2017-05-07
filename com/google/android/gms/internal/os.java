// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface os extends IInterface
{
    void a(final Bundle p0, final ov p1) throws RemoteException;
    
    void a(final om p0, final Bundle p1, final ov p2) throws RemoteException;
    
    void a(final FullWalletRequest p0, final Bundle p1, final ov p2) throws RemoteException;
    
    void a(final MaskedWalletRequest p0, final Bundle p1, final ou p2) throws RemoteException;
    
    void a(final MaskedWalletRequest p0, final Bundle p1, final ov p2) throws RemoteException;
    
    void a(final NotifyTransactionStatusRequest p0, final Bundle p1) throws RemoteException;
    
    void a(final d p0, final Bundle p1, final ov p2) throws RemoteException;
    
    void a(final String p0, final String p1, final Bundle p2, final ov p3) throws RemoteException;
    
    void p(final Bundle p0) throws RemoteException;
    
    public abstract static class a extends Binder implements os
    {
        public static os bL(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
            if (queryLocalInterface != null && queryLocalInterface instanceof os) {
                return (os)queryLocalInterface;
            }
            return new os.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wallet.internal.IOwService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    MaskedWalletRequest maskedWalletRequest;
                    if (parcel.readInt() != 0) {
                        maskedWalletRequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        maskedWalletRequest = null;
                    }
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.a(maskedWalletRequest, bundle, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    FullWalletRequest fullWalletRequest;
                    if (parcel.readInt() != 0) {
                        fullWalletRequest = (FullWalletRequest)FullWalletRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fullWalletRequest = null;
                    }
                    Bundle bundle2;
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle2 = null;
                    }
                    this.a(fullWalletRequest, bundle2, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    Bundle bundle3;
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle3 = null;
                    }
                    this.a(string, string2, bundle3, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    NotifyTransactionStatusRequest notifyTransactionStatusRequest;
                    if (parcel.readInt() != 0) {
                        notifyTransactionStatusRequest = (NotifyTransactionStatusRequest)NotifyTransactionStatusRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        notifyTransactionStatusRequest = null;
                    }
                    Bundle bundle4;
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle4 = null;
                    }
                    this.a(notifyTransactionStatusRequest, bundle4);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    Bundle bundle5;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle5 = null;
                    }
                    this.a(bundle5, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    d d;
                    if (parcel.readInt() != 0) {
                        d = (d)com.google.android.gms.wallet.d.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        d = null;
                    }
                    Bundle bundle6;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle6 = null;
                    }
                    this.a(d, bundle6, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    MaskedWalletRequest maskedWalletRequest2;
                    if (parcel.readInt() != 0) {
                        maskedWalletRequest2 = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        maskedWalletRequest2 = null;
                    }
                    Bundle bundle7;
                    if (parcel.readInt() != 0) {
                        bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle7 = null;
                    }
                    this.a(maskedWalletRequest2, bundle7, ou.a.bN(parcel.readStrongBinder()));
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    om om;
                    if (parcel.readInt() != 0) {
                        om = (om)com.google.android.gms.internal.om.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        om = null;
                    }
                    Bundle bundle8;
                    if (parcel.readInt() != 0) {
                        bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle8 = null;
                    }
                    this.a(om, bundle8, ov.a.bO(parcel.readStrongBinder()));
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                    Bundle bundle9;
                    if (parcel.readInt() != 0) {
                        bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle9 = null;
                    }
                    this.p(bundle9);
                    return true;
                }
            }
        }
        
        private static class a implements os
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final Bundle bundle, final ov ov) throws RemoteException {
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
            public void a(final om om, final Bundle bundle, final ov ov) throws RemoteException {
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
            public void a(final FullWalletRequest fullWalletRequest, final Bundle bundle, final ov ov) throws RemoteException {
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
            public void a(final MaskedWalletRequest maskedWalletRequest, final Bundle bundle, final ou ou) throws RemoteException {
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
            public void a(final MaskedWalletRequest maskedWalletRequest, final Bundle bundle, final ov ov) throws RemoteException {
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
            public void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest, final Bundle bundle) throws RemoteException {
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
            public void a(final d d, final Bundle bundle, final ov ov) throws RemoteException {
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
            public void a(final String s, final String s2, final Bundle bundle, final ov ov) throws RemoteException {
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
            public void p(final Bundle bundle) throws RemoteException {
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
    }
}
