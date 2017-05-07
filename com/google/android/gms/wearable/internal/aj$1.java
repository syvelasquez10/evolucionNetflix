// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.NodeApi$GetLocalNodeResult;

class aj$1 extends d<NodeApi$GetLocalNodeResult>
{
    final /* synthetic */ aj avy;
    
    aj$1(final aj avy) {
        this.avy = avy;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.p((BaseImplementation$b<NodeApi$GetLocalNodeResult>)this);
    }
    
    protected NodeApi$GetLocalNodeResult aK(final Status status) {
        return new aj$b(status, null);
    }
}
