// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class StreetViewPanoramaOptions implements SafeParcelable
{
    public static final StreetViewPanoramaOptionsCreator CREATOR;
    private Boolean RJ;
    private Boolean RP;
    private StreetViewPanoramaCamera Sl;
    private String Sm;
    private LatLng Sn;
    private Integer So;
    private Boolean Sp;
    private Boolean Sq;
    private Boolean Sr;
    private final int xH;
    
    static {
        CREATOR = new StreetViewPanoramaOptionsCreator();
    }
    
    public StreetViewPanoramaOptions() {
        this.Sp = true;
        this.RP = true;
        this.Sq = true;
        this.Sr = true;
        this.xH = 1;
    }
    
    StreetViewPanoramaOptions(final int xh, final StreetViewPanoramaCamera sl, final String sm, final LatLng sn, final Integer so, final byte b, final byte b2, final byte b3, final byte b4, final byte b5) {
        this.Sp = true;
        this.RP = true;
        this.Sq = true;
        this.Sr = true;
        this.xH = xh;
        this.Sl = sl;
        this.Sn = sn;
        this.So = so;
        this.Sm = sm;
        this.Sp = a.a(b);
        this.RP = a.a(b2);
        this.Sq = a.a(b3);
        this.Sr = a.a(b4);
        this.RJ = a.a(b5);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Boolean getPanningGesturesEnabled() {
        return this.Sq;
    }
    
    public String getPanoramaId() {
        return this.Sm;
    }
    
    public LatLng getPosition() {
        return this.Sn;
    }
    
    public Integer getRadius() {
        return this.So;
    }
    
    public Boolean getStreetNamesEnabled() {
        return this.Sr;
    }
    
    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.Sl;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.RJ;
    }
    
    public Boolean getUserNavigationEnabled() {
        return this.Sp;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.RP;
    }
    
    byte ih() {
        return a.c(this.RJ);
    }
    
    byte il() {
        return a.c(this.RP);
    }
    
    byte it() {
        return a.c(this.Sp);
    }
    
    byte iu() {
        return a.c(this.Sq);
    }
    
    byte iv() {
        return a.c(this.Sr);
    }
    
    public StreetViewPanoramaOptions panningGesturesEnabled(final boolean b) {
        this.Sq = b;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaCamera(final StreetViewPanoramaCamera sl) {
        this.Sl = sl;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaId(final String sm) {
        this.Sm = sm;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng sn) {
        this.Sn = sn;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng sn, final Integer so) {
        this.Sn = sn;
        this.So = so;
        return this;
    }
    
    public StreetViewPanoramaOptions streetNamesEnabled(final boolean b) {
        this.Sr = b;
        return this;
    }
    
    public StreetViewPanoramaOptions useViewLifecycleInFragment(final boolean b) {
        this.RJ = b;
        return this;
    }
    
    public StreetViewPanoramaOptions userNavigationEnabled(final boolean b) {
        this.Sp = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StreetViewPanoramaOptionsCreator.a(this, parcel, n);
    }
    
    public StreetViewPanoramaOptions zoomGesturesEnabled(final boolean b) {
        this.RP = b;
        return this;
    }
}
