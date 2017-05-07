// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import com.google.android.gms.internal.ee;
import android.content.res.TypedArray;
import com.google.android.gms.R;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CameraPosition implements SafeParcelable
{
    public static final CameraPositionCreator CREATOR;
    public final float bearing;
    private final int kg;
    public final LatLng target;
    public final float tilt;
    public final float zoom;
    
    static {
        CREATOR = new CameraPositionCreator();
    }
    
    CameraPosition(final int kg, final LatLng target, float zoom, final float n, final float n2) {
        eg.b(target, "null camera target");
        eg.b(0.0f <= n && n <= 90.0f, "Tilt needs to be between 0 and 90 inclusive");
        this.kg = kg;
        this.target = target;
        this.zoom = zoom;
        this.tilt = n + 0.0f;
        zoom = n2;
        if (n2 <= 0.0) {
            zoom = n2 % 360.0f + 360.0f;
        }
        this.bearing = zoom % 360.0f;
    }
    
    public CameraPosition(final LatLng latLng, final float n, final float n2, final float n3) {
        this(1, latLng, n, n2, n3);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }
    
    public static CameraPosition createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.MapAttrs);
        float float1;
        if (obtainAttributes.hasValue(2)) {
            float1 = obtainAttributes.getFloat(2, 0.0f);
        }
        else {
            float1 = 0.0f;
        }
        float float2;
        if (obtainAttributes.hasValue(3)) {
            float2 = obtainAttributes.getFloat(3, 0.0f);
        }
        else {
            float2 = 0.0f;
        }
        final LatLng latLng = new LatLng(float1, float2);
        final Builder builder = builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(5)) {
            builder.zoom(obtainAttributes.getFloat(5, 0.0f));
        }
        if (obtainAttributes.hasValue(1)) {
            builder.bearing(obtainAttributes.getFloat(1, 0.0f));
        }
        if (obtainAttributes.hasValue(4)) {
            builder.tilt(obtainAttributes.getFloat(4, 0.0f));
        }
        return builder.build();
    }
    
    public static final CameraPosition fromLatLngZoom(final LatLng latLng, final float n) {
        return new CameraPosition(latLng, n, 0.0f, 0.0f);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof CameraPosition)) {
                return false;
            }
            final CameraPosition cameraPosition = (CameraPosition)o;
            if (!this.target.equals(cameraPosition.target) || Float.floatToIntBits(this.zoom) != Float.floatToIntBits(cameraPosition.zoom) || Float.floatToIntBits(this.tilt) != Float.floatToIntBits(cameraPosition.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(cameraPosition.bearing)) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.target, this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("target", this.target).a("zoom", this.zoom).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            a.a(this, parcel, n);
            return;
        }
        CameraPositionCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        private LatLng Cm;
        private float Cn;
        private float Co;
        private float Cp;
        
        public Builder() {
        }
        
        public Builder(final CameraPosition cameraPosition) {
            this.Cm = cameraPosition.target;
            this.Cn = cameraPosition.zoom;
            this.Co = cameraPosition.tilt;
            this.Cp = cameraPosition.bearing;
        }
        
        public Builder bearing(final float cp) {
            this.Cp = cp;
            return this;
        }
        
        public CameraPosition build() {
            return new CameraPosition(this.Cm, this.Cn, this.Co, this.Cp);
        }
        
        public Builder target(final LatLng cm) {
            this.Cm = cm;
            return this;
        }
        
        public Builder tilt(final float co) {
            this.Co = co;
            return this;
        }
        
        public Builder zoom(final float cn) {
            this.Cn = cn;
            return this;
        }
    }
}
