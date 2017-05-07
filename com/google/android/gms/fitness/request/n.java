// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import android.os.IBinder;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class n implements SafeParcelable
{
    public static final Parcelable$Creator<n> CREATOR;
    private final int BR;
    private final DataType SF;
    private final DataSource Sh;
    private final long Ti;
    private final int Tj;
    private k Up;
    int Uq;
    int Ur;
    private final long Us;
    private final long Ut;
    private final List<LocationRequest> Uu;
    private final long Uv;
    private final List Uw;
    private final PendingIntent mPendingIntent;
    
    static {
        CREATOR = (Parcelable$Creator)new o();
    }
    
    n(final int br, final DataSource sh, final DataType sf, final IBinder binder, final int n, final int n2, long us, final long n3, final PendingIntent mPendingIntent, final long ut, final int tj, final List<LocationRequest> uu, final long uv) {
        this.BR = br;
        this.Sh = sh;
        this.SF = sf;
        k an;
        if (binder == null) {
            an = null;
        }
        else {
            an = k.a.an(binder);
        }
        this.Up = an;
        long ti = us;
        if (us == 0L) {
            ti = n;
        }
        this.Ti = ti;
        this.Ut = ut;
        us = n3;
        if (n3 == 0L) {
            us = n2;
        }
        this.Us = us;
        this.Uu = uu;
        this.mPendingIntent = mPendingIntent;
        this.Tj = tj;
        this.Uw = Collections.emptyList();
        this.Uv = uv;
    }
    
    private n(final DataSource sh, final DataType sf, final k up, final PendingIntent mPendingIntent, final long ti, final long ut, final long us, final int tj, final List uu, final List uw, final long uv) {
        this.BR = 4;
        this.Sh = sh;
        this.SF = sf;
        this.Up = up;
        this.mPendingIntent = mPendingIntent;
        this.Ti = ti;
        this.Ut = ut;
        this.Us = us;
        this.Tj = tj;
        this.Uu = (List<LocationRequest>)uu;
        this.Uw = uw;
        this.Uv = uv;
    }
    
    public n(final SensorRequest sensorRequest, final k k, final PendingIntent pendingIntent) {
        this(sensorRequest.getDataSource(), sensorRequest.getDataType(), k, pendingIntent, sensorRequest.getSamplingRateMicros(), sensorRequest.jm(), sensorRequest.jn(), sensorRequest.iQ(), null, Collections.emptyList(), sensorRequest.jr());
    }
    
    private boolean a(final n n) {
        return m.equal(this.Sh, n.Sh) && m.equal(this.SF, n.SF) && this.Ti == n.Ti && this.Ut == n.Ut && this.Us == n.Us && this.Tj == n.Tj && m.equal(this.Uu, n.Uu);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof n && this.a((n)o));
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    public long getSamplingRateMicros() {
        return this.Ti;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sh, this.SF, this.Up, this.Ti, this.Ut, this.Us, this.Tj, this.Uu);
    }
    
    public int iQ() {
        return this.Tj;
    }
    
    public PendingIntent jl() {
        return this.mPendingIntent;
    }
    
    public long jm() {
        return this.Ut;
    }
    
    public long jn() {
        return this.Us;
    }
    
    public List<LocationRequest> jo() {
        return this.Uu;
    }
    
    public long jp() {
        return this.Uv;
    }
    
    IBinder jq() {
        if (this.Up == null) {
            return null;
        }
        return this.Up.asBinder();
    }
    
    @Override
    public String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.SF, this.Sh, this.Ti, this.Ut, this.Us);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        o.a(this, parcel, n);
    }
}
