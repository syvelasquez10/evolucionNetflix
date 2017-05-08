// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableGeofence implements SafeParcelable
{
    public static final zzo CREATOR;
    private final int mVersionCode;
    private final String zzBY;
    private final int zzaEi;
    private final short zzaEk;
    private final double zzaEl;
    private final double zzaEm;
    private final float zzaEn;
    private final int zzaEo;
    private final int zzaEp;
    private final long zzaFO;
    
    static {
        CREATOR = new zzo();
    }
    
    public ParcelableGeofence(final int mVersionCode, final String zzBY, int zzhc, final short zzaEk, final double zzaEl, final double zzaEm, final float zzaEn, final long zzaFO, final int zzaEo, final int zzaEp) {
        zzdx(zzBY);
        zze(zzaEn);
        zza(zzaEl, zzaEm);
        zzhc = zzhc(zzhc);
        this.mVersionCode = mVersionCode;
        this.zzaEk = zzaEk;
        this.zzBY = zzBY;
        this.zzaEl = zzaEl;
        this.zzaEm = zzaEm;
        this.zzaEn = zzaEn;
        this.zzaFO = zzaFO;
        this.zzaEi = zzhc;
        this.zzaEo = zzaEo;
        this.zzaEp = zzaEp;
    }
    
    private static void zza(final double n, final double n2) {
        if (n > 90.0 || n < -90.0) {
            throw new IllegalArgumentException("invalid latitude: " + n);
        }
        if (n2 > 180.0 || n2 < -180.0) {
            throw new IllegalArgumentException("invalid longitude: " + n2);
        }
    }
    
    private static void zzdx(final String s) {
        if (s == null || s.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + s);
        }
    }
    
    private static void zze(final float n) {
        if (n <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + n);
        }
    }
    
    private static int zzhc(final int n) {
        final int n2 = n & 0x7;
        if (n2 == 0) {
            throw new IllegalArgumentException("No supported transition specified: " + n);
        }
        return n2;
    }
    
    private static String zzhd(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return "CIRCLE";
            }
        }
    }
    
    public int describeContents() {
        final zzo creator = ParcelableGeofence.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof ParcelableGeofence)) {
                return false;
            }
            final ParcelableGeofence parcelableGeofence = (ParcelableGeofence)o;
            if (this.zzaEn != parcelableGeofence.zzaEn) {
                return false;
            }
            if (this.zzaEl != parcelableGeofence.zzaEl) {
                return false;
            }
            if (this.zzaEm != parcelableGeofence.zzaEm) {
                return false;
            }
            if (this.zzaEk != parcelableGeofence.zzaEk) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.zzaFO;
    }
    
    public double getLatitude() {
        return this.zzaEl;
    }
    
    public double getLongitude() {
        return this.zzaEm;
    }
    
    public int getNotificationResponsiveness() {
        return this.zzaEo;
    }
    
    public String getRequestId() {
        return this.zzBY;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.zzaEl);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.zzaEm);
        return ((((n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + Float.floatToIntBits(this.zzaEn)) * 31 + this.zzaEk) * 31 + this.zzaEi;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", zzhd(this.zzaEk), this.zzBY, this.zzaEi, this.zzaEl, this.zzaEm, this.zzaEn, this.zzaEo / 1000, this.zzaEp, this.zzaFO);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzo creator = ParcelableGeofence.CREATOR;
        zzo.zza(this, parcel, n);
    }
    
    public short zzwI() {
        return this.zzaEk;
    }
    
    public float zzwJ() {
        return this.zzaEn;
    }
    
    public int zzwK() {
        return this.zzaEi;
    }
    
    public int zzwL() {
        return this.zzaEp;
    }
}
