// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.request.DataSourceListener;

public class l extends k$a
{
    private final DataSourceListener SY;
    
    private l(final DataSourceListener dataSourceListener) {
        this.SY = n.i(dataSourceListener);
    }
    
    public void onEvent(final DataPoint dataPoint) {
        this.SY.onEvent(dataPoint);
    }
}
