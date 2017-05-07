// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.util.Iterator;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.FutureTask;
import java.util.List;
import com.google.android.gms.wearable.DataApi$DataItemResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$a extends a
{
    private final BaseImplementation$b<DataApi$DataItemResult> De;
    private final List<FutureTask<Boolean>> avL;
    
    aw$a(final BaseImplementation$b<DataApi$DataItemResult> de, final List<FutureTask<Boolean>> avL) {
        this.De = de;
        this.avL = avL;
    }
    
    @Override
    public void a(final ao ao) {
        this.De.b(new f$a(new Status(ao.statusCode), ao.avp));
        if (ao.statusCode != 0) {
            final Iterator<FutureTask<Boolean>> iterator = this.avL.iterator();
            while (iterator.hasNext()) {
                iterator.next().cancel(true);
            }
        }
    }
}
