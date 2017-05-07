// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationRequest implements SafeParcelable
{
    public static final LocationRequestCreator CREATOR;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    long NV;
    long Oc;
    long Od;
    boolean Oe;
    int Of;
    float Og;
    int mPriority;
    private final int xH;
    
    static {
        CREATOR = new LocationRequestCreator();
    }
    
    public LocationRequest() {
        this.xH = 1;
        this.mPriority = 102;
        this.Oc = 3600000L;
        this.Od = 600000L;
        this.Oe = false;
        this.NV = Long.MAX_VALUE;
        this.Of = Integer.MAX_VALUE;
        this.Og = 0.0f;
    }
    
    LocationRequest(final int xh, final int mPriority, final long oc, final long od, final boolean oe, final long nv, final int of, final float og) {
        this.xH = xh;
        this.mPriority = mPriority;
        this.Oc = oc;
        this.Od = od;
        this.Oe = oe;
        this.NV = nv;
        this.Of = of;
        this.Og = og;
    }
    
    private static void a(final float n) {
        if (n < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + n);
        }
    }
    
    private static void bw(final int n) {
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
    
    public static String bx(final int n) {
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
    
    private static void s(final long n) {
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
            if (this.mPriority != locationRequest.mPriority || this.Oc != locationRequest.Oc || this.Od != locationRequest.Od || this.Oe != locationRequest.Oe || this.NV != locationRequest.NV || this.Of != locationRequest.Of || this.Og != locationRequest.Og) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.NV;
    }
    
    public long getFastestInterval() {
        return this.Od;
    }
    
    public long getInterval() {
        return this.Oc;
    }
    
    public int getNumUpdates() {
        return this.Of;
    }
    
    public int getPriority() {
        return this.mPriority;
    }
    
    public float getSmallestDisplacement() {
        return this.Og;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.mPriority, this.Oc, this.Od, this.Oe, this.NV, this.Of, this.Og);
    }
    
    public LocationRequest setExpirationDuration(final long n) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (n > Long.MAX_VALUE - elapsedRealtime) {
            this.NV = Long.MAX_VALUE;
        }
        else {
            this.NV = elapsedRealtime + n;
        }
        if (this.NV < 0L) {
            this.NV = 0L;
        }
        return this;
    }
    
    public LocationRequest setExpirationTime(final long nv) {
        this.NV = nv;
        if (this.NV < 0L) {
            this.NV = 0L;
        }
        return this;
    }
    
    public LocationRequest setFastestInterval(final long od) {
        s(od);
        this.Oe = true;
        this.Od = od;
        return this;
    }
    
    public LocationRequest setInterval(final long oc) {
        s(oc);
        this.Oc = oc;
        if (!this.Oe) {
            this.Od = (long)(this.Oc / 6.0);
        }
        return this;
    }
    
    public LocationRequest setNumUpdates(final int of) {
        if (of <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + of);
        }
        this.Of = of;
        return this;
    }
    
    public LocationRequest setPriority(final int mPriority) {
        bw(mPriority);
        this.mPriority = mPriority;
        return this;
    }
    
    public LocationRequest setSmallestDisplacement(final float og) {
        a(og);
        this.Og = og;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(bx(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.Oc + "ms");
        }
        sb.append(" fastest=");
        sb.append(this.Od + "ms");
        if (this.NV != Long.MAX_VALUE) {
            final long nv = this.NV;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(nv - elapsedRealtime + "ms");
        }
        if (this.Of != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.Of);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        LocationRequestCreator.a(this, parcel, n);
    }
}
