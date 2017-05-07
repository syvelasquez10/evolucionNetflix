// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.internal.eg;
import com.google.android.gms.maps.model.internal.a;

public final class BitmapDescriptorFactory
{
    private static a Cl;
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
    
    public static void a(final a a) {
        if (BitmapDescriptorFactory.Cl != null) {
            return;
        }
        BitmapDescriptorFactory.Cl = eg.f(a);
    }
    
    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(eE().eJ());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor defaultMarker(final float n) {
        try {
            return new BitmapDescriptor(eE().c(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static a eE() {
        return eg.b(BitmapDescriptorFactory.Cl, "IBitmapDescriptorFactory is not initialized");
    }
    
    public static BitmapDescriptor fromAsset(final String s) {
        try {
            return new BitmapDescriptor(eE().at(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromBitmap(final Bitmap bitmap) {
        try {
            return new BitmapDescriptor(eE().a(bitmap));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromFile(final String s) {
        try {
            return new BitmapDescriptor(eE().au(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromPath(final String s) {
        try {
            return new BitmapDescriptor(eE().av(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromResource(final int n) {
        try {
            return new BitmapDescriptor(eE().bh(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
