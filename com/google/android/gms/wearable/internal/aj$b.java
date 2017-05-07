// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Node;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.NodeApi$GetLocalNodeResult;

public class aj$b implements NodeApi$GetLocalNodeResult
{
    private final Status CM;
    private final Node avB;
    
    public aj$b(final Status cm, final Node avB) {
        this.CM = cm;
        this.avB = avB;
    }
    
    @Override
    public Node getNode() {
        return this.avB;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
