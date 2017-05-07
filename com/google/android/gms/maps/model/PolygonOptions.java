// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PolygonOptions implements SafeParcelable
{
    public static final m CREATOR;
    private final int BR;
    private float ajA;
    private boolean ajB;
    private float ajx;
    private int ajy;
    private int ajz;
    private final List<LatLng> aka;
    private final List<List<LatLng>> akb;
    private boolean akc;
    
    static {
        CREATOR = new m();
    }
    
    public PolygonOptions() {
        this.ajx = 10.0f;
        this.ajy = -16777216;
        this.ajz = 0;
        this.ajA = 0.0f;
        this.ajB = true;
        this.akc = false;
        this.BR = 1;
        this.aka = new ArrayList<LatLng>();
        this.akb = new ArrayList<List<LatLng>>();
    }
    
    PolygonOptions(final int br, final List<LatLng> aka, final List akb, final float ajx, final int ajy, final int ajz, final float ajA, final boolean ajB, final boolean akc) {
        this.ajx = 10.0f;
        this.ajy = -16777216;
        this.ajz = 0;
        this.ajA = 0.0f;
        this.ajB = true;
        this.akc = false;
        this.BR = br;
        this.aka = aka;
        this.akb = (List<List<LatLng>>)akb;
        this.ajx = ajx;
        this.ajy = ajy;
        this.ajz = ajz;
        this.ajA = ajA;
        this.ajB = ajB;
        this.akc = akc;
    }
    
    public PolygonOptions add(final LatLng latLng) {
        this.aka.add(latLng);
        return this;
    }
    
    public PolygonOptions add(final LatLng... array) {
        this.aka.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolygonOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.aka.add(iterator.next());
        }
        return this;
    }
    
    public PolygonOptions addHole(final Iterable<LatLng> iterable) {
        final ArrayList<LatLng> list = new ArrayList<LatLng>();
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        this.akb.add(list);
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolygonOptions fillColor(final int ajz) {
        this.ajz = ajz;
        return this;
    }
    
    public PolygonOptions geodesic(final boolean akc) {
        this.akc = akc;
        return this;
    }
    
    public int getFillColor() {
        return this.ajz;
    }
    
    public List<List<LatLng>> getHoles() {
        return this.akb;
    }
    
    public List<LatLng> getPoints() {
        return this.aka;
    }
    
    public int getStrokeColor() {
        return this.ajy;
    }
    
    public float getStrokeWidth() {
        return this.ajx;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public float getZIndex() {
        return this.ajA;
    }
    
    public boolean isGeodesic() {
        return this.akc;
    }
    
    public boolean isVisible() {
        return this.ajB;
    }
    
    List mO() {
        return this.akb;
    }
    
    public PolygonOptions strokeColor(final int ajy) {
        this.ajy = ajy;
        return this;
    }
    
    public PolygonOptions strokeWidth(final float ajx) {
        this.ajx = ajx;
        return this;
    }
    
    public PolygonOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            n.a(this, parcel, n);
            return;
        }
        m.a(this, parcel, n);
    }
    
    public PolygonOptions zIndex(final float ajA) {
        this.ajA = ajA;
        return this;
    }
}
