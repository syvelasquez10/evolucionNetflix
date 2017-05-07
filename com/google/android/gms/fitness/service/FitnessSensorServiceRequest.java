// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.IBinder;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.fitness.data.DataSource;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FitnessSensorServiceRequest implements SafeParcelable
{
    public static final Parcelable$Creator<FitnessSensorServiceRequest> CREATOR;
    public static final int UNSPECIFIED = -1;
    private final int BR;
    private final DataSource Sh;
    private final long UR;
    private final long US;
    private final k Up;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    FitnessSensorServiceRequest(final int br, final DataSource sh, final IBinder binder, final long ur, final long us) {
        this.BR = br;
        this.Sh = sh;
        this.Up = k.a.an(binder);
        this.UR = ur;
        this.US = us;
    }
    
    private boolean a(final FitnessSensorServiceRequest fitnessSensorServiceRequest) {
        return m.equal(this.Sh, fitnessSensorServiceRequest.Sh) && this.UR == fitnessSensorServiceRequest.UR && this.US == fitnessSensorServiceRequest.US;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof FitnessSensorServiceRequest && this.a((FitnessSensorServiceRequest)o));
    }
    
    public long getBatchIntervalMicros() {
        return this.US;
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    public SensorEventDispatcher getDispatcher() {
        return new b(this.Up);
    }
    
    public long getSamplingRateMicros() {
        return this.UR;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sh, this.UR, this.US);
    }
    
    IBinder jq() {
        return this.Up.asBinder();
    }
    
    @Override
    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", this.Sh);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
