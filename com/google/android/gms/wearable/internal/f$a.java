// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$DataItemResult;

public class f$a implements DataApi$DataItemResult
{
    private final Status CM;
    private final DataItem avh;
    
    public f$a(final Status cm, final DataItem avh) {
        this.CM = cm;
        this.avh = avh;
    }
    
    @Override
    public DataItem getDataItem() {
        return this.avh;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
