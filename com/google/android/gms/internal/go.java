// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import android.os.Parcel;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class go implements SafeParcelable, Geofence
{
    public static final gp CREATOR;
    private final int kg;
    private final int xA;
    private final String xs;
    private final int xt;
    private final short xv;
    private final double xw;
    private final double xx;
    private final float xy;
    private final int xz;
    private final long ye;
    
    static {
        CREATOR = new gp();
    }
    
    public go(final int kg, final String xs, int av, final short xv, final double xw, final double xx, final float xy, final long ye, final int xz, final int xa) {
        ap(xs);
        b(xy);
        a(xw, xx);
        av = aV(av);
        this.kg = kg;
        this.xv = xv;
        this.xs = xs;
        this.xw = xw;
        this.xx = xx;
        this.xy = xy;
        this.ye = ye;
        this.xt = av;
        this.xz = xz;
        this.xA = xa;
    }
    
    public go(final String s, final int n, final short n2, final double n3, final double n4, final float n5, final long n6, final int n7, final int n8) {
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
    
    private static int aV(final int n) {
        final int n2 = n & 0x7;
        if (n2 == 0) {
            throw new IllegalArgumentException("No supported transition specified: " + n);
        }
        return n2;
    }
    
    private static String aW(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return "CIRCLE";
            }
        }
    }
    
    private static void ap(final String s) {
        if (s == null || s.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + s);
        }
    }
    
    private static void b(final float n) {
        if (n <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + n);
        }
    }
    
    public static go f(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final go ai = go.CREATOR.ai(obtain);
        obtain.recycle();
        return ai;
    }
    
    public short dK() {
        return this.xv;
    }
    
    public float dL() {
        return this.xy;
    }
    
    public int dM() {
        return this.xt;
    }
    
    public int dN() {
        return this.xA;
    }
    
    public int describeContents() {
        final gp creator = go.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof go)) {
                return false;
            }
            final go go = (go)o;
            if (this.xy != go.xy) {
                return false;
            }
            if (this.xw != go.xw) {
                return false;
            }
            if (this.xx != go.xx) {
                return false;
            }
            if (this.xv != go.xv) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.ye;
    }
    
    public double getLatitude() {
        return this.xw;
    }
    
    public double getLongitude() {
        return this.xx;
    }
    
    public int getNotificationResponsiveness() {
        return this.xz;
    }
    
    @Override
    public String getRequestId() {
        return this.xs;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.xw);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.xx);
        return ((((n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + Float.floatToIntBits(this.xy)) * 31 + this.xv) * 31 + this.xt;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", aW(this.xv), this.xs, this.xt, this.xw, this.xx, this.xy, this.xz / 1000, this.xA, this.ye);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final gp creator = go.CREATOR;
        gp.a(this, parcel, n);
    }
}
