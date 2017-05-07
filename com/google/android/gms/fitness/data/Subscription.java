// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Subscription implements SafeParcelable
{
    public static final Parcelable$Creator<Subscription> CREATOR;
    private final int BR;
    private final DataType SF;
    private final DataSource Sh;
    private final long Ti;
    private final int Tj;
    
    static {
        CREATOR = (Parcelable$Creator)new s();
    }
    
    Subscription(final int br, final DataSource sh, final DataType sf, final long ti, final int tj) {
        this.BR = br;
        this.Sh = sh;
        this.SF = sf;
        this.Ti = ti;
        this.Tj = tj;
    }
    
    private Subscription(final a a) {
        this.BR = 1;
        this.SF = a.SF;
        this.Sh = a.Sh;
        this.Ti = a.Ti;
        this.Tj = a.Tj;
    }
    
    private boolean a(final Subscription subscription) {
        return m.equal(this.Sh, subscription.Sh) && m.equal(this.SF, subscription.SF) && this.Ti == subscription.Ti && this.Tj == subscription.Tj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Subscription && this.a((Subscription)o));
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
        return m.hashCode(this.Sh, this.Sh, this.Ti, this.Tj);
    }
    
    public int iQ() {
        return this.Tj;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("dataSource", this.Sh).a("dataType", this.SF).a("samplingIntervalMicros", this.Ti).a("accuracyMode", this.Tj).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        s.a(this, parcel, n);
    }
    
    public static class a
    {
        private DataType SF;
        private DataSource Sh;
        private long Ti;
        private int Tj;
        
        public a() {
            this.Ti = -1L;
            this.Tj = 2;
        }
        
        public a b(final DataSource sh) {
            this.Sh = sh;
            return this;
        }
        
        public a b(final DataType sf) {
            this.SF = sf;
            return this;
        }
        
        public Subscription iR() {
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
            return new Subscription(this, null);
        }
    }
}
