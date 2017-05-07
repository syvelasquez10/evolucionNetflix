// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.content.res.TypedArray;
import com.google.android.gms.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CameraPosition implements SafeParcelable
{
    public static final a CREATOR;
    private final int BR;
    public final float bearing;
    public final LatLng target;
    public final float tilt;
    public final float zoom;
    
    static {
        CREATOR = new a();
    }
    
    CameraPosition(final int br, final LatLng target, float zoom, final float n, final float n2) {
        n.b(target, "null camera target");
        n.b(0.0f <= n && n <= 90.0f, (Object)"Tilt needs to be between 0 and 90 inclusive");
        this.BR = br;
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
    
    public static CameraPosition$Builder builder() {
        return new CameraPosition$Builder();
    }
    
    public static CameraPosition$Builder builder(final CameraPosition cameraPosition) {
        return new CameraPosition$Builder(cameraPosition);
    }
    
    public static CameraPosition createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R$styleable.MapAttrs);
        float float1;
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_cameraTargetLat)) {
            float1 = obtainAttributes.getFloat(R$styleable.MapAttrs_cameraTargetLat, 0.0f);
        }
        else {
            float1 = 0.0f;
        }
        float float2;
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_cameraTargetLng)) {
            float2 = obtainAttributes.getFloat(R$styleable.MapAttrs_cameraTargetLng, 0.0f);
        }
        else {
            float2 = 0.0f;
        }
        final LatLng latLng = new LatLng(float1, float2);
        final CameraPosition$Builder builder = builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_cameraZoom)) {
            builder.zoom(obtainAttributes.getFloat(R$styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_cameraBearing)) {
            builder.bearing(obtainAttributes.getFloat(R$styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_cameraTilt)) {
            builder.tilt(obtainAttributes.getFloat(R$styleable.MapAttrs_cameraTilt, 0.0f));
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
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.target, this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("target", this.target).a("zoom", this.zoom).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            b.a(this, parcel, n);
            return;
        }
        a.a(this, parcel, n);
    }
}
