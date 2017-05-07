// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Node;
import java.util.List;
import com.google.android.gms.common.api.Status;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$10 extends a
{
    final /* synthetic */ aw avI;
    final /* synthetic */ BaseImplementation$b avK;
    
    aw$10(final aw avI, final BaseImplementation$b avK) {
        this.avI = avI;
        this.avK = avK;
    }
    
    @Override
    public void a(final v v) {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(v.avo);
        this.avK.b(new aj$a(new Status(v.statusCode), (List<Node>)list));
    }
}
