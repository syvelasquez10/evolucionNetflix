// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import android.os.Parcel;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class mb implements SafeParcelable, Geofence
{
    public static final mc CREATOR;
    private final int BR;
    private final String Xr;
    private final int adW;
    private final short adY;
    private final double adZ;
    private final double aea;
    private final float aeb;
    private final int aec;
    private final int aed;
    private final long afb;
    
    static {
        CREATOR = new mc();
    }
    
    public mb(final int br, final String xr, int ej, final short adY, final double adZ, final double aea, final float aeb, final long afb, final int aec, final int aed) {
        bV(xr);
        b(aeb);
        a(adZ, aea);
        ej = ej(ej);
        this.BR = br;
        this.adY = adY;
        this.Xr = xr;
        this.adZ = adZ;
        this.aea = aea;
        this.aeb = aeb;
        this.afb = afb;
        this.adW = ej;
        this.aec = aec;
        this.aed = aed;
    }
    
    public mb(final String s, final int n, final short n2, final double n3, final double n4, final float n5, final long n6, final int n7, final int n8) {
        this(1, s, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    private static void a(final double n, final double n2) {
        if (n > 90.0 || n < -90.0) {
            throw new IllegalArgumentException("invalid latitude: " + n);
        }
        if (n2 > 180.0 || n2 < -180.0) {
            throw new IllegalArgumentException("invalid longitude: " + n2);
        }
    }
    
    private static void b(final float n) {
        if (n <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + n);
        }
    }
    
    private static void bV(final String s) {
        if (s == null || s.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + s);
        }
    }
    
    private static int ej(final int n) {
        final int n2 = n & 0x7;
        if (n2 == 0) {
            throw new IllegalArgumentException("No supported transition specified: " + n);
        }
        return n2;
    }
    
    private static String ek(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return "CIRCLE";
            }
        }
    }
    
    public static mb h(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final mb cw = mb.CREATOR.cw(obtain);
        obtain.recycle();
        return cw;
    }
    
    public int describeContents() {
        final mc creator = mb.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof mb)) {
                return false;
            }
            final mb mb = (mb)o;
            if (this.aeb != mb.aeb) {
                return false;
            }
            if (this.adZ != mb.adZ) {
                return false;
            }
            if (this.aea != mb.aea) {
                return false;
            }
            if (this.adY != mb.adY) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.afb;
    }
    
    public double getLatitude() {
        return this.adZ;
    }
    
    public double getLongitude() {
        return this.aea;
    }
    
    public int getNotificationResponsiveness() {
        return this.aec;
    }
    
    @Override
    public String getRequestId() {
        return this.Xr;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.adZ);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.aea);
        return ((((n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + Float.floatToIntBits(this.aeb)) * 31 + this.adY) * 31 + this.adW;
    }
    
    public short lY() {
        return this.adY;
    }
    
    public float lZ() {
        return this.aeb;
    }
    
    public int ma() {
        return this.adW;
    }
    
    public int mb() {
        return this.aed;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", ek(this.adY), this.Xr, this.adW, this.adZ, this.aea, this.aeb, this.aec / 1000, this.aed, this.afb);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mc creator = mb.CREATOR;
        mc.a(this, parcel, n);
    }
}
