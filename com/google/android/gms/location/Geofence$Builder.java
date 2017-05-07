// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.mb;

public final class Geofence$Builder
{
    private String Xr;
    private int adW;
    private long adX;
    private short adY;
    private double adZ;
    private double aea;
    private float aeb;
    private int aec;
    private int aed;
    
    public Geofence$Builder() {
        this.Xr = null;
        this.adW = 0;
        this.adX = Long.MIN_VALUE;
        this.adY = -1;
        this.aec = 0;
        this.aed = -1;
    }
    
    public Geofence build() {
        if (this.Xr == null) {
            throw new IllegalArgumentException("Request ID not set.");
        }
        if (this.adW == 0) {
            throw new IllegalArgumentException("Transitions types not set.");
        }
        if ((this.adW & 0x4) != 0x0 && this.aed < 0) {
            throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
        }
        if (this.adX == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Expiration not set.");
        }
        if (this.adY == -1) {
            throw new IllegalArgumentException("Geofence region not set.");
        }
        if (this.aec < 0) {
            throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
        }
        return new mb(this.Xr, this.adW, (short)1, this.adZ, this.aea, this.aeb, this.adX, this.aec, this.aed);
    }
    
    public Geofence$Builder setCircularRegion(final double adZ, final double aea, final float aeb) {
        this.adY = 1;
        this.adZ = adZ;
        this.aea = aea;
        this.aeb = aeb;
        return this;
    }
    
    public Geofence$Builder setExpirationDuration(final long n) {
        if (n < 0L) {
            this.adX = -1L;
            return this;
        }
        this.adX = SystemClock.elapsedRealtime() + n;
        return this;
    }
    
    public Geofence$Builder setLoiteringDelay(final int aed) {
        this.aed = aed;
        return this;
    }
    
    public Geofence$Builder setNotificationResponsiveness(final int aec) {
        this.aec = aec;
        return this;
    }
    
    public Geofence$Builder setRequestId(final String xr) {
        this.Xr = xr;
        return this;
    }
    
    public Geofence$Builder setTransitionTypes(final int adW) {
        this.adW = adW;
        return this;
    }
}
