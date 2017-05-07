// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.DataType;

public class DataSourcesRequest$Builder
{
    private boolean Um;
    private DataType[] Un;
    private int[] Uo;
    
    public DataSourcesRequest$Builder() {
        this.Un = new DataType[0];
        this.Uo = new int[] { 0, 1 };
        this.Um = false;
    }
    
    public DataSourcesRequest build() {
        final boolean b = true;
        n.a(this.Un.length > 0, (Object)"Must add at least one data type");
        n.a(this.Uo.length > 0 && b, (Object)"Must add at least one data source type");
        return new DataSourcesRequest(this, null);
    }
    
    public DataSourcesRequest$Builder setDataSourceTypes(final int... uo) {
        this.Uo = uo;
        return this;
    }
    
    public DataSourcesRequest$Builder setDataTypes(final DataType... un) {
        this.Un = un;
        return this;
    }
}
