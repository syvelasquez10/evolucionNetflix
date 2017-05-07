// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.location.a;
import android.location.Location;
import android.os.Parcel;
import android.app.PendingIntent;
import android.os.IBinder;

class lw$a$a implements lw
{
    private IBinder lb;
    
    lw$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final long n, final boolean b, final PendingIntent pendingIntent) {
        int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            obtain.writeLong(n);
            if (!b) {
                n2 = 0;
            }
            obtain.writeInt(n2);
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final PendingIntent pendingIntent) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(11, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final PendingIntent pendingIntent, final lv lv, final String s) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (lv != null) {
                        final IBinder binder = lv.asBinder();
                        obtain.writeStrongBinder(binder);
                        obtain.writeString(s);
                        this.lb.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final Location location, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (location != null) {
                obtain.writeInt(1);
                location.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeInt(n);
            this.lb.transact(26, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final lv lv, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            IBinder binder;
            if (lv != null) {
                binder = lv.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final lz lz, final PendingIntent pendingIntent) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (lz != null) {
                        obtain.writeInt(1);
                        lz.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                        this.lb.transact(53, obtain, obtain2, 0);
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
    public void a(final lz lz, final a a) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (lz != null) {
                        obtain.writeInt(1);
                        lz.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (a != null) {
                        final IBinder binder = a.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(52, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final mg mg, final mw mw, final PendingIntent pendingIntent) {
    Label_0068_Outer:
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
            Label_0132:
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                        if (mg != null) {
                            obtain.writeInt(1);
                            mg.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (mw != null) {
                            obtain.writeInt(1);
                            mw.writeToParcel(obtain, 0);
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.lb.transact(48, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                            break Label_0132;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue Label_0068_Outer;
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final mi mi, final mw mw, final mu mu) {
    Label_0062_Outer:
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
            Label_0132:
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                        if (mi != null) {
                            obtain.writeInt(1);
                            mi.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (mw != null) {
                            obtain.writeInt(1);
                            mw.writeToParcel(obtain, 0);
                            if (mu != null) {
                                final IBinder binder = mu.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(17, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                            break Label_0132;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue Label_0062_Outer;
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final mk mk, final mw mw) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (mk != null) {
                        obtain.writeInt(1);
                        mk.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (mw != null) {
                        obtain.writeInt(1);
                        mw.writeToParcel(obtain, 0);
                        this.lb.transact(25, obtain, (Parcel)null, 1);
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
    public void a(final mm mm, final mw mw, final PendingIntent pendingIntent) {
    Label_0068_Outer:
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
            Label_0132:
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                        if (mm != null) {
                            obtain.writeInt(1);
                            mm.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (mw != null) {
                            obtain.writeInt(1);
                            mw.writeToParcel(obtain, 0);
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.lb.transact(18, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                            break Label_0132;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue Label_0068_Outer;
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final mq mq, final mw mw, final mu mu) {
    Label_0062_Outer:
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
            Label_0132:
                while (true) {
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                        if (mq != null) {
                            obtain.writeInt(1);
                            mq.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        if (mw != null) {
                            obtain.writeInt(1);
                            mw.writeToParcel(obtain, 0);
                            if (mu != null) {
                                final IBinder binder = mu.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(46, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                            break Label_0132;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    obtain.writeInt(0);
                    continue Label_0062_Outer;
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final ms ms, final LatLngBounds latLngBounds, final List<String> list, final mw mw, final mu mu) {
        Parcel obtain;
        Parcel obtain2;
        IBinder binder;
        Label_0076_Outer:Label_0089_Outer:
        while (true) {
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            while (true) {
            Label_0168:
                while (true) {
                Label_0159:
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (ms != null) {
                                obtain.writeInt(1);
                                ms.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (latLngBounds != null) {
                                obtain.writeInt(1);
                                latLngBounds.writeToParcel(obtain, 0);
                                obtain.writeStringList((List)list);
                                if (mw == null) {
                                    break Label_0159;
                                }
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                if (mu != null) {
                                    binder = mu.asBinder();
                                    obtain.writeStrongBinder(binder);
                                    this.lb.transact(50, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                break Label_0168;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue Label_0076_Outer;
                    }
                    obtain.writeInt(0);
                    continue Label_0089_Outer;
                }
                binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final mw mw, final PendingIntent pendingIntent) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (mw != null) {
                        obtain.writeInt(1);
                        mw.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                        this.lb.transact(19, obtain, obtain2, 0);
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
    public void a(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                        this.lb.transact(9, obtain, obtain2, 0);
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
    public void a(final LocationRequest locationRequest, final a a) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (a != null) {
                        final IBinder binder = a.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(8, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final LocationRequest locationRequest, final a a, final String s) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (a != null) {
                        final IBinder binder = a.asBinder();
                        obtain.writeStrongBinder(binder);
                        obtain.writeString(s);
                        this.lb.transact(20, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final a a) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            IBinder binder;
            if (a != null) {
                binder = a.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(10, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final LatLng latLng, final mi mi, final mw mw, final mu mu) {
        Parcel obtain;
        Parcel obtain2;
        IBinder binder;
        Label_0068_Outer:Label_0081_Outer:
        while (true) {
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            while (true) {
            Label_0160:
                while (true) {
                Label_0151:
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (latLng != null) {
                                obtain.writeInt(1);
                                latLng.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (mi != null) {
                                obtain.writeInt(1);
                                mi.writeToParcel(obtain, 0);
                                if (mw == null) {
                                    break Label_0151;
                                }
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                if (mu != null) {
                                    binder = mu.asBinder();
                                    obtain.writeStrongBinder(binder);
                                    this.lb.transact(16, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                break Label_0160;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue Label_0068_Outer;
                    }
                    obtain.writeInt(0);
                    continue Label_0081_Outer;
                }
                binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final LatLngBounds latLngBounds, final int n, final mi mi, final mw mw, final mu mu) {
        Parcel obtain;
        Parcel obtain2;
        IBinder binder;
        Label_0076_Outer:Label_0089_Outer:
        while (true) {
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            while (true) {
            Label_0168:
                while (true) {
                Label_0159:
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (latLngBounds != null) {
                                obtain.writeInt(1);
                                latLngBounds.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            obtain.writeInt(n);
                            if (mi != null) {
                                obtain.writeInt(1);
                                mi.writeToParcel(obtain, 0);
                                if (mw == null) {
                                    break Label_0159;
                                }
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                if (mu != null) {
                                    binder = mu.asBinder();
                                    obtain.writeStrongBinder(binder);
                                    this.lb.transact(14, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                break Label_0168;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue Label_0076_Outer;
                    }
                    obtain.writeInt(0);
                    continue Label_0089_Outer;
                }
                binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final LatLngBounds latLngBounds, final int n, final String s, final mi mi, final mw mw, final mu mu) {
        Parcel obtain;
        Parcel obtain2;
        IBinder binder;
        Label_0084_Outer:Label_0097_Outer:
        while (true) {
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            while (true) {
            Label_0176:
                while (true) {
                Label_0167:
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (latLngBounds != null) {
                                obtain.writeInt(1);
                                latLngBounds.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            obtain.writeInt(n);
                            obtain.writeString(s);
                            if (mi != null) {
                                obtain.writeInt(1);
                                mi.writeToParcel(obtain, 0);
                                if (mw == null) {
                                    break Label_0167;
                                }
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                if (mu != null) {
                                    binder = mu.asBinder();
                                    obtain.writeStrongBinder(binder);
                                    this.lb.transact(47, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                break Label_0176;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue Label_0084_Outer;
                    }
                    obtain.writeInt(0);
                    continue Label_0097_Outer;
                }
                binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final String s, final mw mw, final mu mu) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    if (mw != null) {
                        obtain.writeInt(1);
                        mw.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (mu != null) {
                        final IBinder binder = mu.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(15, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final String s, final LatLngBounds latLngBounds, final me me, final mw mw, final mu mu) {
        Parcel obtain;
        Parcel obtain2;
        IBinder binder;
        Label_0076_Outer:Label_0089_Outer:
        while (true) {
            obtain = Parcel.obtain();
            obtain2 = Parcel.obtain();
            while (true) {
            Label_0168:
                while (true) {
                Label_0159:
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (latLngBounds != null) {
                                obtain.writeInt(1);
                                latLngBounds.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (me != null) {
                                obtain.writeInt(1);
                                me.writeToParcel(obtain, 0);
                                if (mw == null) {
                                    break Label_0159;
                                }
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                if (mu != null) {
                                    binder = mu.asBinder();
                                    obtain.writeStrongBinder(binder);
                                    this.lb.transact(55, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                break Label_0168;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue Label_0076_Outer;
                    }
                    obtain.writeInt(0);
                    continue Label_0089_Outer;
                }
                binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final List<mb> list, final PendingIntent pendingIntent, final lv lv, final String s) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeTypedList((List)list);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (lv != null) {
                        final IBinder binder = lv.asBinder();
                        obtain.writeStrongBinder(binder);
                        obtain.writeString(s);
                        this.lb.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final String[] array, final lv lv, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            obtain.writeStringArray(array);
            IBinder binder;
            if (lv != null) {
                binder = lv.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void b(final mw mw, final PendingIntent pendingIntent) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (mw != null) {
                        obtain.writeInt(1);
                        mw.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                        this.lb.transact(49, obtain, obtain2, 0);
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
    public void b(final String s, final mw mw, final mu mu) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    if (mw != null) {
                        obtain.writeInt(1);
                        mw.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (mu != null) {
                        final IBinder binder = mu.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(42, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
    
    @Override
    public Location bT(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            obtain.writeString(s);
            this.lb.transact(21, obtain, obtain2, 0);
            obtain2.readException();
            Location location;
            if (obtain2.readInt() != 0) {
                location = (Location)Location.CREATOR.createFromParcel(obtain2);
            }
            else {
                location = null;
            }
            return location;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public c bU(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            obtain.writeString(s);
            this.lb.transact(34, obtain, obtain2, 0);
            obtain2.readException();
            c ct;
            if (obtain2.readInt() != 0) {
                ct = c.CREATOR.ct(obtain2);
            }
            else {
                ct = null;
            }
            return ct;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Location lT() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            this.lb.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            Location location;
            if (obtain2.readInt() != 0) {
                location = (Location)Location.CREATOR.createFromParcel(obtain2);
            }
            else {
                location = null;
            }
            return location;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IBinder lU() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            this.lb.transact(51, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IBinder lV() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            this.lb.transact(54, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void removeActivityUpdates(final PendingIntent pendingIntent) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (pendingIntent != null) {
                obtain.writeInt(1);
                pendingIntent.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(6, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setMockLocation(final Location location) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (location != null) {
                obtain.writeInt(1);
                location.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(13, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setMockMode(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
