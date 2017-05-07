// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class cu$a$a implements cu
{
    private IBinder lb;
    
    cu$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final d d, final av av, final String s, final cv cv) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            if (av != null) {
                obtain.writeInt(1);
                av.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeString(s);
            IBinder binder3 = binder;
            if (cv != null) {
                binder3 = cv.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final d d, final av av, final String s, final String s2, final cv cv) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            if (av != null) {
                obtain.writeInt(1);
                av.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeString(s);
            obtain.writeString(s2);
            IBinder binder3 = binder;
            if (cv != null) {
                binder3 = cv.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            this.lb.transact(7, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final d d, final ay ay, final av av, final String s, final cv cv) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (ay != null) {
                        obtain.writeInt(1);
                        ay.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (av != null) {
                        obtain.writeInt(1);
                        av.writeToParcel(obtain, 0);
                        obtain.writeString(s);
                        IBinder binder3 = binder;
                        if (cv != null) {
                            binder3 = cv.asBinder();
                        }
                        obtain.writeStrongBinder(binder3);
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
    
    @Override
    public void a(final d d, final ay ay, final av av, final String s, final String s2, final cv cv) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    if (ay != null) {
                        obtain.writeInt(1);
                        ay.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (av != null) {
                        obtain.writeInt(1);
                        av.writeToParcel(obtain, 0);
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        IBinder binder3 = binder;
                        if (cv != null) {
                            binder3 = cv.asBinder();
                        }
                        obtain.writeStrongBinder(binder3);
                        this.lb.transact(6, obtain, obtain2, 0);
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
    
    @Override
    public void destroy() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            this.lb.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d getView() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void pause() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            this.lb.transact(8, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void resume() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            this.lb.transact(9, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void showInterstitial() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            this.lb.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
