// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.DataSet;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataInsertRequest implements SafeParcelable
{
    public static final Parcelable$Creator<DataInsertRequest> CREATOR;
    private final int BR;
    private final DataSet Th;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    DataInsertRequest(final int br, final DataSet th) {
        this.BR = br;
        this.Th = th;
    }
    
    private DataInsertRequest(final Builder builder) {
        this.BR = 1;
        this.Th = builder.Th;
    }
    
    private boolean a(final DataInsertRequest dataInsertRequest) {
        return m.equal(this.Th, dataInsertRequest.Th);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof DataInsertRequest && this.a((DataInsertRequest)o));
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Th);
    }
    
    public DataSet iP() {
        return this.Th;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("dataSet", this.Th).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private DataSet Th;
        
        public DataInsertRequest build() {
            final boolean b = true;
            n.a(this.Th != null, (Object)"Must set the data set");
            n.a(!this.Th.getDataPoints().isEmpty(), (Object)"Cannot use an empty data set");
            n.a(this.Th.getDataSource().iH() != null && b, (Object)"Must set the app package name for the data source");
            return new DataInsertRequest(this, null);
        }
        
        public Builder setDataSet(final DataSet th) {
            this.Th = th;
            return this;
        }
    }
}
