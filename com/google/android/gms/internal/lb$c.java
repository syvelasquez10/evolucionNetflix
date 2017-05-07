// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.l$a;
import com.google.android.gms.fitness.request.DataSourceListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class lb$c extends ks$a
{
    private final BaseImplementation$b<Status> De;
    private final DataSourceListener TM;
    
    private lb$c(final BaseImplementation$b<Status> de, final DataSourceListener tm) {
        this.De = de;
        this.TM = tm;
    }
    
    public void k(final Status status) {
        if (this.TM != null && status.isSuccess()) {
            l$a.iO().c(this.TM);
        }
        this.De.b(status);
    }
}
