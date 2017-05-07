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
    public static final PolylineOptionsCreator CREATOR;
    private int Av;
    private float SN;
    private boolean SO;
    private float SS;
    private final List<LatLng> Tn;
    private boolean Tp;
    private final int xH;
    
    static {
        CREATOR = new PolylineOptionsCreator();
    }
    
    public PolylineOptions() {
        this.SS = 10.0f;
        this.Av = -16777216;
        this.SN = 0.0f;
        this.SO = true;
        this.Tp = false;
        this.xH = 1;
        this.Tn = new ArrayList<LatLng>();
    }
    
    PolylineOptions(final int xh, final List tn, final float ss, final int av, final float sn, final boolean so, final boolean tp) {
        this.SS = 10.0f;
        this.Av = -16777216;
        this.SN = 0.0f;
        this.SO = true;
        this.Tp = false;
        this.xH = xh;
        this.Tn = (List<LatLng>)tn;
        this.SS = ss;
        this.Av = av;
        this.SN = sn;
        this.SO = so;
        this.Tp = tp;
    }
    
    public PolylineOptions add(final LatLng latLng) {
        this.Tn.add(latLng);
        return this;
    }
    
    public PolylineOptions add(final LatLng... array) {
        this.Tn.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolylineOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.Tn.add(iterator.next());
        }
        return this;
    }
    
    public PolylineOptions color(final int av) {
        this.Av = av;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolylineOptions geodesic(final boolean tp) {
        this.Tp = tp;
        return this;
    }
    
    public int getColor() {
        return this.Av;
    }
    
    public List<LatLng> getPoints() {
        return this.Tn;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public float getWidth() {
        return this.SS;
    }
    
    public float getZIndex() {
        return this.SN;
    }
    
    public boolean isGeodesic() {
        return this.Tp;
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public PolylineOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public PolylineOptions width(final float ss) {
        this.SS = ss;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            h.a(this, parcel, n);
            return;
        }
        PolylineOptionsCreator.a(this, parcel, n);
    }
    
    public PolylineOptions zIndex(final float sn) {
        this.SN = sn;
        return this;
    }
}
