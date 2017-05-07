// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.wearable.Node;
import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;

class aj$2 extends d<NodeApi$GetConnectedNodesResult>
{
    final /* synthetic */ aj avy;
    
    aj$2(final aj avy) {
        this.avy = avy;
    }
    
    @Override
    protected void a(final aw aw) {
        aw.q((BaseImplementation$b<NodeApi$GetConnectedNodesResult>)this);
    }
    
    protected NodeApi$GetConnectedNodesResult aL(final Status status) {
        return new aj$a(status, null);
    }
}
