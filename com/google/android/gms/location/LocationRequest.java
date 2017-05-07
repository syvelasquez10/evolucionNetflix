// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationRequest implements SafeParcelable
{
    public static final b CREATOR;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    private final int BR;
    boolean Uz;
    long adX;
    long aeh;
    long aei;
    int aej;
    float aek;
    long ael;
    int mPriority;
    
    static {
        CREATOR = new b();
    }
    
    public LocationRequest() {
        this.BR = 1;
        this.mPriority = 102;
        this.aeh = 3600000L;
        this.aei = 600000L;
        this.Uz = false;
        this.adX = Long.MAX_VALUE;
        this.aej = Integer.MAX_VALUE;
        this.aek = 0.0f;
        this.ael = 0L;
    }
    
    LocationRequest(final int br, final int mPriority, final long aeh, final long aei, final boolean uz, final long adX, final int aej, final float aek, final long ael) {
        this.BR = br;
        this.mPriority = mPriority;
        this.aeh = aeh;
        this.aei = aei;
        this.Uz = uz;
        this.adX = adX;
        this.aej = aej;
        this.aek = aek;
        this.ael = ael;
    }
    
    private static void a(final float n) {
        if (n < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + n);
        }
    }
    
    public static LocationRequest create() {
        return new LocationRequest();
    }
    
    private static void ea(final int n) {
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
    
    public static String eb(final int n) {
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
    
    private static void v(final long n) {
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
            if (this.mPriority != locationRequest.mPriority || this.aeh != locationRequest.aeh || this.aei != locationRequest.aei || this.Uz != locationRequest.Uz || this.adX != locationRequest.adX || this.aej != locationRequest.aej || this.aek != locationRequest.aek) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.adX;
    }
    
    public long getFastestInterval() {
        return this.aei;
    }
    
    public long getInterval() {
        return this.aeh;
    }
    
    public int getNumUpdates() {
        return this.aej;
    }
    
    public int getPriority() {
        return this.mPriority;
    }
    
    public float getSmallestDisplacement() {
        return this.aek;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mPriority, this.aeh, this.aei, this.Uz, this.adX, this.aej, this.aek);
    }
    
    public LocationRequest setExpirationDuration(final long n) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (n > Long.MAX_VALUE - elapsedRealtime) {
            this.adX = Long.MAX_VALUE;
        }
        else {
            this.adX = elapsedRealtime + n;
        }
        if (this.adX < 0L) {
            this.adX = 0L;
        }
        return this;
    }
    
    public LocationRequest setExpirationTime(final long adX) {
        this.adX = adX;
        if (this.adX < 0L) {
            this.adX = 0L;
        }
        return this;
    }
    
    public LocationRequest setFastestInterval(final long aei) {
        v(aei);
        this.Uz = true;
        this.aei = aei;
        return this;
    }
    
    public LocationRequest setInterval(final long aeh) {
        v(aeh);
        this.aeh = aeh;
        if (!this.Uz) {
            this.aei = (long)(this.aeh / 6.0);
        }
        return this;
    }
    
    public LocationRequest setNumUpdates(final int aej) {
        if (aej <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + aej);
        }
        this.aej = aej;
        return this;
    }
    
    public LocationRequest setPriority(final int mPriority) {
        ea(mPriority);
        this.mPriority = mPriority;
        return this;
    }
    
    public LocationRequest setSmallestDisplacement(final float aek) {
        a(aek);
        this.aek = aek;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(eb(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.aeh + "ms");
        }
        sb.append(" fastest=");
        sb.append(this.aei + "ms");
        if (this.adX != Long.MAX_VALUE) {
            final long adX = this.adX;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(adX - elapsedRealtime + "ms");
        }
        if (this.aej != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.aej);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
