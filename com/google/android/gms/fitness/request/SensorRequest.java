// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.m;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class SensorRequest
{
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final DataType SF;
    private final DataSource Sh;
    private final long Ti;
    private final int Tj;
    private final long Us;
    private final long Ut;
    private final LocationRequest Ux;
    private final long Uy;
    
    private SensorRequest(final DataSource sh, final LocationRequest ux) {
        this.Ux = ux;
        this.Ti = TimeUnit.MILLISECONDS.toMicros(ux.getInterval());
        this.Ut = TimeUnit.MILLISECONDS.toMicros(ux.getFastestInterval());
        this.Us = this.Ti;
        this.SF = sh.getDataType();
        this.Tj = a(ux);
        this.Sh = sh;
        final long expirationTime = ux.getExpirationTime();
        if (expirationTime == Long.MAX_VALUE) {
            this.Uy = Long.MAX_VALUE;
            return;
        }
        this.Uy = TimeUnit.MILLISECONDS.toMicros(expirationTime - SystemClock.elapsedRealtime());
    }
    
    private SensorRequest(final SensorRequest$Builder sensorRequest$Builder) {
        this.Sh = sensorRequest$Builder.Sh;
        this.SF = sensorRequest$Builder.SF;
        this.Ti = sensorRequest$Builder.Ti;
        this.Ut = sensorRequest$Builder.Ut;
        this.Us = sensorRequest$Builder.Us;
        this.Tj = sensorRequest$Builder.Tj;
        this.Ux = null;
        this.Uy = sensorRequest$Builder.Uy;
    }
    
    private static int a(final LocationRequest locationRequest) {
        switch (locationRequest.getPriority()) {
            default: {
                return 2;
            }
            case 100: {
                return 3;
            }
            case 104: {
                return 1;
            }
        }
    }
    
    private boolean a(final SensorRequest sensorRequest) {
        return m.equal(this.Sh, sensorRequest.Sh) && m.equal(this.SF, sensorRequest.SF) && this.Ti == sensorRequest.Ti && this.Ut == sensorRequest.Ut && this.Us == sensorRequest.Us && this.Tj == sensorRequest.Tj && m.equal(this.Ux, sensorRequest.Ux) && this.Uy == sensorRequest.Uy;
    }
    
    public static int da(final int n) {
        int n2 = n;
        switch (n) {
            default: {
                n2 = 2;
                return n2;
            }
            case 1:
            case 3: {
                return n2;
            }
        }
    }
    
    public static SensorRequest fromLocationRequest(final DataSource dataSource, final LocationRequest locationRequest) {
        return new SensorRequest(dataSource, locationRequest);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof SensorRequest && this.a((SensorRequest)o));
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
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sh, this.SF, this.Ti, this.Ut, this.Us, this.Tj, this.Ux, this.Uy);
    }
    
    public int iQ() {
        return this.Tj;
    }
    
    public long jm() {
        return this.Ut;
    }
    
    public long jn() {
        return this.Us;
    }
    
    public long jr() {
        return this.Uy;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("dataSource", this.Sh).a("dataType", this.SF).a("samplingRateMicros", this.Ti).a("deliveryLatencyMicros", this.Us).a("timeOutMicros", this.Uy).toString();
    }
}
