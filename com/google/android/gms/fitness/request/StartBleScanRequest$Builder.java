// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.DataType;

public class StartBleScanRequest$Builder
{
    private k UF;
    private int UG;
    private DataType[] Un;
    
    public StartBleScanRequest$Builder() {
        this.Un = new DataType[0];
        this.UG = 10;
    }
    
    public StartBleScanRequest$Builder a(final k uf) {
        this.UF = uf;
        return this;
    }
    
    public StartBleScanRequest build() {
        n.a(this.UF != null, (Object)"Must set BleScanCallback");
        return new StartBleScanRequest(this, null);
    }
    
    public StartBleScanRequest$Builder setBleScanCallback(final BleScanCallback bleScanCallback) {
        this.a(a$a.iV().a(bleScanCallback));
        return this;
    }
    
    public StartBleScanRequest$Builder setDataTypes(final DataType... un) {
        this.Un = un;
        return this;
    }
    
    public StartBleScanRequest$Builder setTimeoutSecs(final int ug) {
        final boolean b = true;
        n.b(ug > 0, (Object)"Stop time must be greater than zero");
        n.b(ug <= 60 && b, (Object)"Stop time must be less than 1 minute");
        this.UG = ug;
        return this;
    }
}
