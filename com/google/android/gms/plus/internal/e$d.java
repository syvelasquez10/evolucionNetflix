// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.internal.d$b;

final class e$d extends d$b<BaseImplementation$b<Status>>
{
    private final Status CM;
    final /* synthetic */ e all;
    
    public e$d(final e all, final BaseImplementation$b<Status> baseImplementation$b, final Status cm) {
        this.all = all;
        super(baseImplementation$b);
        this.CM = cm;
    }
    
    @Override
    protected void gT() {
    }
    
    protected void n(final BaseImplementation$b<Status> baseImplementation$b) {
        if (baseImplementation$b != null) {
            baseImplementation$b.b(this.CM);
        }
    }
}
