// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;

public class f$b implements DataApi$DeleteDataItemsResult
{
    private final Status CM;
    private final int avi;
    
    public f$b(final Status cm, final int avi) {
        this.CM = cm;
        this.avi = avi;
    }
    
    @Override
    public int getNumDeleted() {
        return this.avi;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
