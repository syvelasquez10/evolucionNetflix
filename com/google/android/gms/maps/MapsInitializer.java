// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.internal.a;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.internal.c;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.internal.eg;
import android.content.Context;

public final class MapsInitializer
{
    public static int initialize(final Context context) {
        eg.f(context);
        c c;
        try {
            final c u;
            c = (u = q.u(context));
            final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate = u.ez();
            CameraUpdateFactory.a(cameraUpdateFactoryDelegate);
            final c c2 = c;
            final a a = c2.eA();
            BitmapDescriptorFactory.a(a);
            final boolean b = false;
            return b ? 1 : 0;
        }
        catch (GooglePlayServicesNotAvailableException ex) {
            return ex.errorCode;
        }
        try {
            final c u = c;
            final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate = u.ez();
            CameraUpdateFactory.a(cameraUpdateFactoryDelegate);
            final c c2 = c;
            final a a = c2.eA();
            BitmapDescriptorFactory.a(a);
            final boolean b = false;
            return b ? 1 : 0;
        }
        catch (RemoteException ex2) {
            throw new RuntimeRemoteException(ex2);
        }
    }
}
