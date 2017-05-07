// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.go;

public interface Geofence
{
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1L;
    
    String getRequestId();
    
    public static final class Builder
    {
        private int xA;
        private String xs;
        private int xt;
        private long xu;
        private short xv;
        private double xw;
        private double xx;
        private float xy;
        private int xz;
        
        public Builder() {
            this.xs = null;
            this.xt = 0;
            this.xu = Long.MIN_VALUE;
            this.xv = -1;
            this.xz = 0;
            this.xA = -1;
        }
        
        public Geofence build() {
            if (this.xs == null) {
                throw new IllegalArgumentException("Request ID not set.");
            }
            if (this.xt == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            }
            if ((this.xt & 0x4) != 0x0 && this.xA < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            }
            if (this.xu == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            }
            if (this.xv == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            }
            if (this.xz < 0) {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
            return new go(this.xs, this.xt, (short)1, this.xw, this.xx, this.xy, this.xu, this.xz, this.xA);
        }
        
        public Builder setCircularRegion(final double xw, final double xx, final float xy) {
            this.xv = 1;
            this.xw = xw;
            this.xx = xx;
            this.xy = xy;
            return this;
        }
        
        public Builder setExpirationDuration(final long n) {
            if (n < 0L) {
                this.xu = -1L;
                return this;
            }
            this.xu = SystemClock.elapsedRealtime() + n;
            return this;
        }
        
        public Builder setLoiteringDelay(final int xa) {
            this.xA = xa;
            return this;
        }
        
        public Builder setNotificationResponsiveness(final int xz) {
            this.xz = xz;
            return this;
        }
        
        public Builder setRequestId(final String xs) {
            this.xs = xs;
            return this;
        }
        
        public Builder setTransitionTypes(final int xt) {
            this.xt = xt;
            return this;
        }
    }
}
