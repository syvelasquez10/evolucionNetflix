// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.DataSet;

public class DataInsertRequest$Builder
{
    private DataSet Th;
    
    public DataInsertRequest build() {
        final boolean b = true;
        n.a(this.Th != null, (Object)"Must set the data set");
        n.a(!this.Th.getDataPoints().isEmpty(), (Object)"Cannot use an empty data set");
        n.a(this.Th.getDataSource().iH() != null && b, (Object)"Must set the app package name for the data source");
        return new DataInsertRequest(this, null);
    }
    
    public DataInsertRequest$Builder setDataSet(final DataSet th) {
        this.Th = th;
        return this;
    }
}
