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
    public static final PolygonOptionsCreator CREATOR;
    private float SK;
    private int SL;
    private int SM;
    private float SN;
    private boolean SO;
    private final List<LatLng> Tn;
    private final List<List<LatLng>> To;
    private boolean Tp;
    private final int xH;
    
    static {
        CREATOR = new PolygonOptionsCreator();
    }
    
    public PolygonOptions() {
        this.SK = 10.0f;
        this.SL = -16777216;
        this.SM = 0;
        this.SN = 0.0f;
        this.SO = true;
        this.Tp = false;
        this.xH = 1;
        this.Tn = new ArrayList<LatLng>();
        this.To = new ArrayList<List<LatLng>>();
    }
    
    PolygonOptions(final int xh, final List<LatLng> tn, final List to, final float sk, final int sl, final int sm, final float sn, final boolean so, final boolean tp) {
        this.SK = 10.0f;
        this.SL = -16777216;
        this.SM = 0;
        this.SN = 0.0f;
        this.SO = true;
        this.Tp = false;
        this.xH = xh;
        this.Tn = tn;
        this.To = (List<List<LatLng>>)to;
        this.SK = sk;
        this.SL = sl;
        this.SM = sm;
        this.SN = sn;
        this.SO = so;
        this.Tp = tp;
    }
    
    public PolygonOptions add(final LatLng latLng) {
        this.Tn.add(latLng);
        return this;
    }
    
    public PolygonOptions add(final LatLng... array) {
        this.Tn.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolygonOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.Tn.add(iterator.next());
        }
        return this;
    }
    
    public PolygonOptions addHole(final Iterable<LatLng> iterable) {
        final ArrayList<LatLng> list = new ArrayList<LatLng>();
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        this.To.add(list);
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolygonOptions fillColor(final int sm) {
        this.SM = sm;
        return this;
    }
    
    public PolygonOptions geodesic(final boolean tp) {
        this.Tp = tp;
        return this;
    }
    
    public int getFillColor() {
        return this.SM;
    }
    
    public List<List<LatLng>> getHoles() {
        return this.To;
    }
    
    public List<LatLng> getPoints() {
        return this.Tn;
    }
    
    public int getStrokeColor() {
        return this.SL;
    }
    
    public float getStrokeWidth() {
        return this.SK;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public float getZIndex() {
        return this.SN;
    }
    
    List iF() {
        return this.To;
    }
    
    public boolean isGeodesic() {
        return this.Tp;
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public PolygonOptions strokeColor(final int sl) {
        this.SL = sl;
        return this;
    }
    
    public PolygonOptions strokeWidth(final float sk) {
        this.SK = sk;
        return this;
    }
    
    public PolygonOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            g.a(this, parcel, n);
            return;
        }
        PolygonOptionsCreator.a(this, parcel, n);
    }
    
    public PolygonOptions zIndex(final float sn) {
        this.SN = sn;
        return this;
    }
}
