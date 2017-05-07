// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class SensorRequest$Builder
{
    private DataType SF;
    private DataSource Sh;
    private long Ti;
    private int Tj;
    private long Us;
    private long Ut;
    private long Uy;
    private boolean Uz;
    
    public SensorRequest$Builder() {
        this.Ti = -1L;
        this.Ut = 0L;
        this.Us = 0L;
        this.Uz = false;
        this.Tj = 2;
        this.Uy = Long.MAX_VALUE;
    }
    
    public SensorRequest build() {
        final boolean b = false;
        n.a(this.Sh != null || this.SF != null, (Object)"Must call setDataSource() or setDataType()");
        boolean b2 = false;
        Label_0059: {
            if (this.SF != null && this.Sh != null) {
                b2 = b;
                if (!this.SF.equals(this.Sh.getDataType())) {
                    break Label_0059;
                }
            }
            b2 = true;
        }
        n.a(b2, (Object)"Specified data type is incompatible with specified data source");
        return new SensorRequest(this, null);
    }
    
    public SensorRequest$Builder setAccuracyMode(final int n) {
        this.Tj = SensorRequest.da(n);
        return this;
    }
    
    public SensorRequest$Builder setDataSource(final DataSource sh) {
        this.Sh = sh;
        return this;
    }
    
    public SensorRequest$Builder setDataType(final DataType sf) {
        this.SF = sf;
        return this;
    }
    
    public SensorRequest$Builder setFastestRate(final int n, final TimeUnit timeUnit) {
        n.b(n >= 0, (Object)"Cannot use a negative interval");
        this.Uz = true;
        this.Ut = timeUnit.toMicros(n);
        return this;
    }
    
    public SensorRequest$Builder setMaxDeliveryLatency(final int n, final TimeUnit timeUnit) {
        n.b(n >= 0, (Object)"Cannot use a negative delivery interval");
        this.Us = timeUnit.toMicros(n);
        return this;
    }
    
    public SensorRequest$Builder setSamplingRate(final long n, final TimeUnit timeUnit) {
        n.b(n >= 0L, (Object)"Cannot use a negative sampling interval");
        this.Ti = timeUnit.toMicros(n);
        if (!this.Uz) {
            this.Ut = this.Ti / 2L;
        }
        return this;
    }
    
    public SensorRequest$Builder setTimeout(final long n, final TimeUnit timeUnit) {
        final boolean b = true;
        n.b(n > 0L, "Invalid time out value specified: %d", n);
        n.b(timeUnit != null && b, (Object)"Invalid time unit specified");
        this.Uy = timeUnit.toMicros(n);
        return this;
    }
}
