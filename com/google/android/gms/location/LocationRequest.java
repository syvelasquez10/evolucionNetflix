// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.internal.ee;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationRequest implements SafeParcelable
{
    public static final LocationRequestCreator CREATOR;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    private final int kg;
    int mPriority;
    long xB;
    long xC;
    boolean xD;
    int xE;
    float xF;
    long xu;
    
    static {
        CREATOR = new LocationRequestCreator();
    }
    
    public LocationRequest() {
        this.kg = 1;
        this.mPriority = 102;
        this.xB = 3600000L;
        this.xC = 600000L;
        this.xD = false;
        this.xu = Long.MAX_VALUE;
        this.xE = Integer.MAX_VALUE;
        this.xF = 0.0f;
    }
    
    LocationRequest(final int kg, final int mPriority, final long xb, final long xc, final boolean xd, final long xu, final int xe, final float xf) {
        this.kg = kg;
        this.mPriority = mPriority;
        this.xB = xb;
        this.xC = xc;
        this.xD = xd;
        this.xu = xu;
        this.xE = xe;
        this.xF = xf;
    }
    
    private static void a(final float n) {
        if (n < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + n);
        }
    }
    
    private static void aO(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("invalid quality: " + n);
            }
            case 100:
            case 102:
            case 104:
            case 105: {}
        }
    }
    
    public static String aP(final int n) {
        switch (n) {
            default: {
                return "???";
            }
            case 100: {
                return "PRIORITY_HIGH_ACCURACY";
            }
            case 102: {
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            }
            case 104: {
                return "PRIORITY_LOW_POWER";
            }
            case 105: {
                return "PRIORITY_NO_POWER";
            }
        }
    }
    
    public static LocationRequest create() {
        return new LocationRequest();
    }
    
    private static void m(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("invalid interval: " + n);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LocationRequest)) {
                return false;
            }
            final LocationRequest locationRequest = (LocationRequest)o;
            if (this.mPriority != locationRequest.mPriority || this.xB != locationRequest.xB || this.xC != locationRequest.xC || this.xD != locationRequest.xD || this.xu != locationRequest.xu || this.xE != locationRequest.xE || this.xF != locationRequest.xF) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.xu;
    }
    
    public long getFastestInterval() {
        return this.xC;
    }
    
    public long getInterval() {
        return this.xB;
    }
    
    public int getNumUpdates() {
        return this.xE;
    }
    
    public int getPriority() {
        return this.mPriority;
    }
    
    public float getSmallestDisplacement() {
        return this.xF;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.mPriority, this.xB, this.xC, this.xD, this.xu, this.xE, this.xF);
    }
    
    public LocationRequest setExpirationDuration(final long n) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (n > Long.MAX_VALUE - elapsedRealtime) {
            this.xu = Long.MAX_VALUE;
        }
        else {
            this.xu = elapsedRealtime + n;
        }
        if (this.xu < 0L) {
            this.xu = 0L;
        }
        return this;
    }
    
    public LocationRequest setExpirationTime(final long xu) {
        this.xu = xu;
        if (this.xu < 0L) {
            this.xu = 0L;
        }
        return this;
    }
    
    public LocationRequest setFastestInterval(final long xc) {
        m(xc);
        this.xD = true;
        this.xC = xc;
        return this;
    }
    
    public LocationRequest setInterval(final long xb) {
        m(xb);
        this.xB = xb;
        if (!this.xD) {
            this.xC = (long)(this.xB / 6.0);
        }
        return this;
    }
    
    public LocationRequest setNumUpdates(final int xe) {
        if (xe <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + xe);
        }
        this.xE = xe;
        return this;
    }
    
    public LocationRequest setPriority(final int mPriority) {
        aO(mPriority);
        this.mPriority = mPriority;
        return this;
    }
    
    public LocationRequest setSmallestDisplacement(final float xf) {
        a(xf);
        this.xF = xf;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(aP(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.xB + "ms");
        }
        sb.append(" fastest=");
        sb.append(this.xC + "ms");
        if (this.xu != Long.MAX_VALUE) {
            final long xu = this.xu;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(xu - elapsedRealtime + "ms");
        }
        if (this.xE != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.xE);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        LocationRequestCreator.a(this, parcel, n);
    }
}
