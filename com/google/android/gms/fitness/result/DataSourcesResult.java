// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataSource;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class DataSourcesResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<DataSourcesResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<DataSource> TZ;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    DataSourcesResult(final int br, final List<DataSource> list, final Status cm) {
        this.BR = br;
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)list);
        this.CM = cm;
    }
    
    public DataSourcesResult(final List<DataSource> list, final Status cm) {
        this.BR = 3;
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)list);
        this.CM = cm;
    }
    
    public static DataSourcesResult E(final Status status) {
        return new DataSourcesResult(Collections.emptyList(), status);
    }
    
    private boolean b(final DataSourcesResult dataSourcesResult) {
        return this.CM.equals(dataSourcesResult.CM) && m.equal(this.TZ, dataSourcesResult.TZ);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataSourcesResult && this.b((DataSourcesResult)o));
    }
    
    public List<DataSource> getDataSources() {
        return this.TZ;
    }
    
    public List<DataSource> getDataSources(final DataType dataType) {
        final ArrayList<DataSource> list = new ArrayList<DataSource>();
        for (final DataSource dataSource : this.TZ) {
            if (dataSource.getDataType().equals(dataType)) {
                list.add(dataSource);
            }
        }
        return (List<DataSource>)Collections.unmodifiableList((List<?>)list);
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
        return m.hashCode(this.CM, this.TZ);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("dataSets", this.TZ).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
