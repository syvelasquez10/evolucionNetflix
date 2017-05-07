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

public final class PolygonOptions implements SafeParcelable
{
    public static final PolygonOptionsCreator CREATOR;
    private final List<LatLng> CV;
    private final List<List<LatLng>> CW;
    private boolean CX;
    private float Ct;
    private int Cu;
    private int Cv;
    private float Cw;
    private boolean Cx;
    private final int kg;
    
    static {
        CREATOR = new PolygonOptionsCreator();
    }
    
    public PolygonOptions() {
        this.Ct = 10.0f;
        this.Cu = -16777216;
        this.Cv = 0;
        this.Cw = 0.0f;
        this.Cx = true;
        this.CX = false;
        this.kg = 1;
        this.CV = new ArrayList<LatLng>();
        this.CW = new ArrayList<List<LatLng>>();
    }
    
    PolygonOptions(final int kg, final List<LatLng> cv, final List cw, final float ct, final int cu, final int cv2, final float cw2, final boolean cx, final boolean cx2) {
        this.Ct = 10.0f;
        this.Cu = -16777216;
        this.Cv = 0;
        this.Cw = 0.0f;
        this.Cx = true;
        this.CX = false;
        this.kg = kg;
        this.CV = cv;
        this.CW = (List<List<LatLng>>)cw;
        this.Ct = ct;
        this.Cu = cu;
        this.Cv = cv2;
        this.Cw = cw2;
        this.Cx = cx;
        this.CX = cx2;
    }
    
    public PolygonOptions add(final LatLng latLng) {
        this.CV.add(latLng);
        return this;
    }
    
    public PolygonOptions add(final LatLng... array) {
        this.CV.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolygonOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.CV.add(iterator.next());
        }
        return this;
    }
    
    public PolygonOptions addHole(final Iterable<LatLng> iterable) {
        final ArrayList<LatLng> list = new ArrayList<LatLng>();
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        this.CW.add(list);
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    List eH() {
        return this.CW;
    }
    
    public PolygonOptions fillColor(final int cv) {
        this.Cv = cv;
        return this;
    }
    
    public PolygonOptions geodesic(final boolean cx) {
        this.CX = cx;
        return this;
    }
    
    public int getFillColor() {
        return this.Cv;
    }
    
    public List<List<LatLng>> getHoles() {
        return this.CW;
    }
    
    public List<LatLng> getPoints() {
        return this.CV;
    }
    
    public int getStrokeColor() {
        return this.Cu;
    }
    
    public float getStrokeWidth() {
        return this.Ct;
    }
    
    int getVersionCode() {
        return this.kg;
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
    
    public PolygonOptions strokeColor(final int cu) {
        this.Cu = cu;
        return this;
    }
    
    public PolygonOptions strokeWidth(final float ct) {
        this.Ct = ct;
        return this;
    }
    
    public PolygonOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            g.a(this, parcel, n);
            return;
        }
        PolygonOptionsCreator.a(this, parcel, n);
    }
    
    public PolygonOptions zIndex(final float cw) {
        this.Cw = cw;
        return this;
    }
}
