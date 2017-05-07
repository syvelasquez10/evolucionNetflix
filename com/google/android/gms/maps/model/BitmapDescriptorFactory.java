// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.a;

public final class BitmapDescriptorFactory
{
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static a ajp;
    
    public static void a(final a a) {
        if (BitmapDescriptorFactory.ajp != null) {
            return;
        }
        BitmapDescriptorFactory.ajp = n.i(a);
    }
    
    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(mL().mQ());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor defaultMarker(final float n) {
        try {
            return new BitmapDescriptor(mL().c(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromAsset(final String s) {
        try {
            return new BitmapDescriptor(mL().bX(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromBitmap(final Bitmap bitmap) {
        try {
            return new BitmapDescriptor(mL().b(bitmap));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromFile(final String s) {
        try {
            return new BitmapDescriptor(mL().bY(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromPath(final String s) {
        try {
            return new BitmapDescriptor(mL().bZ(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromResource(final int n) {
        try {
            return new BitmapDescriptor(mL().eM(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static a mL() {
        return n.b(BitmapDescriptorFactory.ajp, "IBitmapDescriptorFactory is not initialized");
    }
}
