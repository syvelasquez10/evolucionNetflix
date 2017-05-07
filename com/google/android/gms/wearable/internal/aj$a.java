// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Node;
import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;

public class aj$a implements NodeApi$GetConnectedNodesResult
{
    private final Status CM;
    private final List<Node> avA;
    
    public aj$a(final Status cm, final List<Node> avA) {
        this.CM = cm;
        this.avA = avA;
    }
    
    @Override
    public List<Node> getNodes() {
        return this.avA;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
