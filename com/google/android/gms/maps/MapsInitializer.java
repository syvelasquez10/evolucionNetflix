// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.common.internal.n;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.internal.c;

public final class MapsInitializer
{
    public static void a(final c c) {
        try {
            CameraUpdateFactory.a(c.mG());
            BitmapDescriptorFactory.a(c.mH());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static int initialize(final Context context) {
        n.i(context);
        try {
            a(u.R(context));
            return 0;
        }
        catch (GooglePlayServicesNotAvailableException ex) {
            return ex.errorCode;
        }
    }
}
