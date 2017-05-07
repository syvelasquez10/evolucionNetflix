// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PolylineOptions implements SafeParcelable
{
    public static final PolylineOptionsCreator CREATOR;
    private float CB;
    private final List<LatLng> CV;
    private boolean CX;
    private float Cw;
    private boolean Cx;
    private final int kg;
    private int mP;
    
    static {
        CREATOR = new PolylineOptionsCreator();
    }
    
    public PolylineOptions() {
        this.CB = 10.0f;
        this.mP = -16777216;
        this.Cw = 0.0f;
        this.Cx = true;
        this.CX = false;
        this.kg = 1;
        this.CV = new ArrayList<LatLng>();
    }
    
    PolylineOptions(final int kg, final List cv, final float cb, final int mp, final float cw, final boolean cx, final boolean cx2) {
        this.CB = 10.0f;
        this.mP = -16777216;
        this.Cw = 0.0f;
        this.Cx = true;
        this.CX = false;
        this.kg = kg;
        this.CV = (List<LatLng>)cv;
        this.CB = cb;
        this.mP = mp;
        this.Cw = cw;
        this.Cx = cx;
        this.CX = cx2;
    }
    
    public PolylineOptions add(final LatLng latLng) {
        this.CV.add(latLng);
        return this;
    }
    
    public PolylineOptions add(final LatLng... array) {
        this.CV.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolylineOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.CV.add(iterator.next());
        }
        return this;
    }
    
    public PolylineOptions color(final int mp) {
        this.mP = mp;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolylineOptions geodesic(final boolean cx) {
        this.CX = cx;
        return this;
    }
    
    public int getColor() {
        return this.mP;
    }
    
    public List<LatLng> getPoints() {
        return this.CV;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public float getWidth() {
        return this.CB;
    }
    
    public float getZIndex() {
        return this.Cw;
    }
    
    public boolean isGeodesic() {
        return this.CX;
    }
    
    public boolean isVisible() {
        return this.Cx;
    }
    
    public PolylineOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public PolylineOptions width(final float cb) {
        this.CB = cb;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            h.a(this, parcel, n);
            return;
        }
        PolylineOptionsCreator.a(this, parcel, n);
    }
    
    public PolylineOptions zIndex(final float cw) {
        this.Cw = cw;
        return this;
    }
}
