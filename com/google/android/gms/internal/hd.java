// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import android.os.Parcel;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hd implements SafeParcelable, Geofence
{
    public static final he CREATOR;
    private final String Jo;
    private final int NU;
    private final short NW;
    private final double NX;
    private final double NY;
    private final float NZ;
    private final int Oa;
    private final int Ob;
    private final long Oz;
    private final int xH;
    
    static {
        CREATOR = new he();
    }
    
    public hd(final int xh, final String jo, int bb, final short nw, final double nx, final double ny, final float nz, final long oz, final int oa, final int ob) {
        aY(jo);
        b(nz);
        a(nx, ny);
        bb = bB(bb);
        this.xH = xh;
        this.NW = nw;
        this.Jo = jo;
        this.NX = nx;
        this.NY = ny;
        this.NZ = nz;
        this.Oz = oz;
        this.NU = bb;
        this.Oa = oa;
        this.Ob = ob;
    }
    
    public hd(final String s, final int n, final short n2, final double n3, final double n4, final float n5, final long n6, final int n7, final int n8) {
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
    
    private static void aY(final String s) {
        if (s == null || s.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + s);
        }
    }
    
    private static void b(final float n) {
        if (n <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + n);
        }
    }
    
    private static int bB(final int n) {
        final int n2 = n & 0x7;
        if (n2 == 0) {
            throw new IllegalArgumentException("No supported transition specified: " + n);
        }
        return n2;
    }
    
    private static String bC(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return "CIRCLE";
            }
        }
    }
    
    public static hd h(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final hd ac = hd.CREATOR.aC(obtain);
        obtain.recycle();
        return ac;
    }
    
    public int describeContents() {
        final he creator = hd.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof hd)) {
                return false;
            }
            final hd hd = (hd)o;
            if (this.NZ != hd.NZ) {
                return false;
            }
            if (this.NX != hd.NX) {
                return false;
            }
            if (this.NY != hd.NY) {
                return false;
            }
            if (this.NW != hd.NW) {
                return false;
            }
        }
        return true;
    }
    
    public long getExpirationTime() {
        return this.Oz;
    }
    
    public double getLatitude() {
        return this.NX;
    }
    
    public double getLongitude() {
        return this.NY;
    }
    
    public int getNotificationResponsiveness() {
        return this.Oa;
    }
    
    @Override
    public String getRequestId() {
        return this.Jo;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public short hS() {
        return this.NW;
    }
    
    public float hT() {
        return this.NZ;
    }
    
    public int hU() {
        return this.NU;
    }
    
    public int hV() {
        return this.Ob;
    }
    
    @Override
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.NX);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.NY);
        return ((((n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + Float.floatToIntBits(this.NZ)) * 31 + this.NW) * 31 + this.NU;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", bC(this.NW), this.Jo, this.NU, this.NX, this.NY, this.NZ, this.Oa / 1000, this.Ob, this.Oz);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final he creator = hd.CREATOR;
        he.a(this, parcel, n);
    }
}
