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
    public static final c CREATOR;
    private final int BR;
    private Boolean aiC;
    private StreetViewPanoramaCamera aiY;
    private String aiZ;
    private Boolean aiw;
    private LatLng aja;
    private Integer ajb;
    private Boolean ajc;
    private Boolean ajd;
    private Boolean aje;
    
    static {
        CREATOR = new c();
    }
    
    public StreetViewPanoramaOptions() {
        this.ajc = true;
        this.aiC = true;
        this.ajd = true;
        this.aje = true;
        this.BR = 1;
    }
    
    StreetViewPanoramaOptions(final int br, final StreetViewPanoramaCamera aiY, final String aiZ, final LatLng aja, final Integer ajb, final byte b, final byte b2, final byte b3, final byte b4, final byte b5) {
        this.ajc = true;
        this.aiC = true;
        this.ajd = true;
        this.aje = true;
        this.BR = br;
        this.aiY = aiY;
        this.aja = aja;
        this.ajb = ajb;
        this.aiZ = aiZ;
        this.ajc = a.a(b);
        this.aiC = a.a(b2);
        this.ajd = a.a(b3);
        this.aje = a.a(b4);
        this.aiw = a.a(b5);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Boolean getPanningGesturesEnabled() {
        return this.ajd;
    }
    
    public String getPanoramaId() {
        return this.aiZ;
    }
    
    public LatLng getPosition() {
        return this.aja;
    }
    
    public Integer getRadius() {
        return this.ajb;
    }
    
    public Boolean getStreetNamesEnabled() {
        return this.aje;
    }
    
    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.aiY;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.aiw;
    }
    
    public Boolean getUserNavigationEnabled() {
        return this.ajc;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.aiC;
    }
    
    byte mC() {
        return a.c(this.ajc);
    }
    
    byte mD() {
        return a.c(this.ajd);
    }
    
    byte mE() {
        return a.c(this.aje);
    }
    
    byte mq() {
        return a.c(this.aiw);
    }
    
    byte mu() {
        return a.c(this.aiC);
    }
    
    public StreetViewPanoramaOptions panningGesturesEnabled(final boolean b) {
        this.ajd = b;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaCamera(final StreetViewPanoramaCamera aiY) {
        this.aiY = aiY;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaId(final String aiZ) {
        this.aiZ = aiZ;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng aja) {
        this.aja = aja;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng aja, final Integer ajb) {
        this.aja = aja;
        this.ajb = ajb;
        return this;
    }
    
    public StreetViewPanoramaOptions streetNamesEnabled(final boolean b) {
        this.aje = b;
        return this;
    }
    
    public StreetViewPanoramaOptions useViewLifecycleInFragment(final boolean b) {
        this.aiw = b;
        return this;
    }
    
    public StreetViewPanoramaOptions userNavigationEnabled(final boolean b) {
        this.ajc = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
    
    public StreetViewPanoramaOptions zoomGesturesEnabled(final boolean b) {
        this.aiC = b;
        return this;
    }
}
