// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class DataTypeResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<DataTypeResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final DataType SF;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    DataTypeResult(final int br, final Status cm, final DataType sf) {
        this.BR = br;
        this.CM = cm;
        this.SF = sf;
    }
    
    public DataTypeResult(final Status cm, final DataType sf) {
        this.BR = 2;
        this.CM = cm;
        this.SF = sf;
    }
    
    public static DataTypeResult F(final Status status) {
        return new DataTypeResult(status, null);
    }
    
    private boolean b(final DataTypeResult dataTypeResult) {
        return this.CM.equals(dataTypeResult.CM) && m.equal(this.SF, dataTypeResult.SF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataTypeResult && this.b((DataTypeResult)o));
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.CM, this.SF);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("dataType", this.SF).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
