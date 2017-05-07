// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.internal.r;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.maps.internal.p;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import android.graphics.Point;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;

public class StreetViewPanorama
{
    private final IStreetViewPanoramaDelegate aiQ;
    
    protected StreetViewPanorama(final IStreetViewPanoramaDelegate streetViewPanoramaDelegate) {
        this.aiQ = n.i(streetViewPanoramaDelegate);
    }
    
    public void animateTo(final StreetViewPanoramaCamera streetViewPanoramaCamera, final long n) {
        try {
            this.aiQ.animateTo(streetViewPanoramaCamera, n);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaLocation getLocation() {
        try {
            return this.aiQ.getStreetViewPanoramaLocation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaCamera getPanoramaCamera() {
        try {
            return this.aiQ.getPanoramaCamera();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isPanningGesturesEnabled() {
        try {
            return this.aiQ.isPanningGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isStreetNamesEnabled() {
        try {
            return this.aiQ.isStreetNamesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isUserNavigationEnabled() {
        try {
            return this.aiQ.isUserNavigationEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.aiQ.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    IStreetViewPanoramaDelegate mA() {
        return this.aiQ;
    }
    
    public Point orientationToPoint(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        try {
            final d orientationToPoint = this.aiQ.orientationToPoint(streetViewPanoramaOrientation);
            if (orientationToPoint == null) {
                return null;
            }
            return (Point)e.f(orientationToPoint);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public StreetViewPanoramaOrientation pointToOrientation(final Point point) {
        try {
            return this.aiQ.pointToOrientation(e.k(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setOnStreetViewPanoramaCameraChangeListener(final OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        Label_0015: {
            if (onStreetViewPanoramaCameraChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aiQ.setOnStreetViewPanoramaCameraChangeListener(null);
                return;
                this.aiQ.setOnStreetViewPanoramaCameraChangeListener(new p.a() {
                    public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
                        onStreetViewPanoramaCameraChangeListener.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnStreetViewPanoramaChangeListener(final OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        Label_0015: {
            if (onStreetViewPanoramaChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aiQ.setOnStreetViewPanoramaChangeListener(null);
                return;
                this.aiQ.setOnStreetViewPanoramaChangeListener(new q.a() {
                    public void onStreetViewPanoramaChange(final StreetViewPanoramaLocation streetViewPanoramaLocation) {
                        onStreetViewPanoramaChangeListener.onStreetViewPanoramaChange(streetViewPanoramaLocation);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnStreetViewPanoramaClickListener(final OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        Label_0015: {
            if (onStreetViewPanoramaClickListener != null) {
                break Label_0015;
            }
            try {
                this.aiQ.setOnStreetViewPanoramaClickListener(null);
                return;
                this.aiQ.setOnStreetViewPanoramaClickListener(new r.a() {
                    public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                        onStreetViewPanoramaClickListener.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public void setPanningGesturesEnabled(final boolean b) {
        try {
            this.aiQ.enablePanning(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.aiQ.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng latLng, final int n) {
        try {
            this.aiQ.setPositionWithRadius(latLng, n);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final String positionWithID) {
        try {
            this.aiQ.setPositionWithID(positionWithID);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStreetNamesEnabled(final boolean b) {
        try {
            this.aiQ.enableStreetNames(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setUserNavigationEnabled(final boolean b) {
        try {
            this.aiQ.enableUserNavigation(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean b) {
        try {
            this.aiQ.enableZoom(b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public interface OnStreetViewPanoramaCameraChangeListener
    {
        void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera p0);
    }
    
    public interface OnStreetViewPanoramaChangeListener
    {
        void onStreetViewPanoramaChange(final StreetViewPanoramaLocation p0);
    }
    
    public interface OnStreetViewPanoramaClickListener
    {
        void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation p0);
    }
}
