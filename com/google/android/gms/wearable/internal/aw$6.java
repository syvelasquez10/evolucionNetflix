// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$6 extends a
{
    final /* synthetic */ aw avI;
    final /* synthetic */ BaseImplementation$b avK;
    
    aw$6(final aw avI, final BaseImplementation$b avK) {
        this.avI = avI;
        this.avK = avK;
    }
    
    @Override
    public void a(final p p) {
        this.avK.b(new f$b(new Status(p.statusCode), p.avl));
    }
}
