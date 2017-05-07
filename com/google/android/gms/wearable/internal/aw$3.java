// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$3 extends a
{
    final /* synthetic */ aw avI;
    final /* synthetic */ BaseImplementation$b avK;
    
    aw$3(final aw avI, final BaseImplementation$b avK) {
        this.avI = avI;
        this.avK = avK;
    }
    
    @Override
    public void a(final x x) {
        this.avK.b(new f$a(new Status(x.statusCode), x.avp));
    }
}
