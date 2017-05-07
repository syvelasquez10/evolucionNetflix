// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.hd;

public interface Geofence
{
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1L;
    
    String getRequestId();
    
    public static final class Builder
    {
        private String Jo;
        private int NU;
        private long NV;
        private short NW;
        private double NX;
        private double NY;
        private float NZ;
        private int Oa;
        private int Ob;
        
        public Builder() {
            this.Jo = null;
            this.NU = 0;
            this.NV = Long.MIN_VALUE;
            this.NW = -1;
            this.Oa = 0;
            this.Ob = -1;
        }
        
        public Geofence build() {
            if (this.Jo == null) {
                throw new IllegalArgumentException("Request ID not set.");
            }
            if (this.NU == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            }
            if ((this.NU & 0x4) != 0x0 && this.Ob < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            }
            if (this.NV == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            }
            if (this.NW == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            }
            if (this.Oa < 0) {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
            return new hd(this.Jo, this.NU, (short)1, this.NX, this.NY, this.NZ, this.NV, this.Oa, this.Ob);
        }
        
        public Builder setCircularRegion(final double nx, final double ny, final float nz) {
            this.NW = 1;
            this.NX = nx;
            this.NY = ny;
            this.NZ = nz;
            return this;
        }
        
        public Builder setExpirationDuration(final long n) {
            if (n < 0L) {
                this.NV = -1L;
                return this;
            }
            this.NV = SystemClock.elapsedRealtime() + n;
            return this;
        }
        
        public Builder setLoiteringDelay(final int ob) {
            this.Ob = ob;
            return this;
        }
        
        public Builder setNotificationResponsiveness(final int oa) {
            this.Oa = oa;
            return this;
        }
        
        public Builder setRequestId(final String jo) {
            this.Jo = jo;
            return this;
        }
        
        public Builder setTransitionTypes(final int nu) {
            this.NU = nu;
            return this;
        }
    }
}
