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

public final class PolylineOptions implements SafeParcelable
{
    public static final o CREATOR;
    private final int BR;
    private float ajA;
    private boolean ajB;
    private float ajF;
    private final List<LatLng> aka;
    private boolean akc;
    private int mColor;
    
    static {
        CREATOR = new o();
    }
    
    public PolylineOptions() {
        this.ajF = 10.0f;
        this.mColor = -16777216;
        this.ajA = 0.0f;
        this.ajB = true;
        this.akc = false;
        this.BR = 1;
        this.aka = new ArrayList<LatLng>();
    }
    
    PolylineOptions(final int br, final List aka, final float ajF, final int mColor, final float ajA, final boolean ajB, final boolean akc) {
        this.ajF = 10.0f;
        this.mColor = -16777216;
        this.ajA = 0.0f;
        this.ajB = true;
        this.akc = false;
        this.BR = br;
        this.aka = (List<LatLng>)aka;
        this.ajF = ajF;
        this.mColor = mColor;
        this.ajA = ajA;
        this.ajB = ajB;
        this.akc = akc;
    }
    
    public PolylineOptions add(final LatLng latLng) {
        this.aka.add(latLng);
        return this;
    }
    
    public PolylineOptions add(final LatLng... array) {
        this.aka.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolylineOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.aka.add(iterator.next());
        }
        return this;
    }
    
    public PolylineOptions color(final int mColor) {
        this.mColor = mColor;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolylineOptions geodesic(final boolean akc) {
        this.akc = akc;
        return this;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public List<LatLng> getPoints() {
        return this.aka;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public float getWidth() {
        return this.ajF;
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
    
    public PolylineOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public PolylineOptions width(final float ajF) {
        this.ajF = ajF;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            p.a(this, parcel, n);
            return;
        }
        o.a(this, parcel, n);
    }
    
    public PolylineOptions zIndex(final float ajA) {
        this.ajA = ajA;
        return this;
    }
}
