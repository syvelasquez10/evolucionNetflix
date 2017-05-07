// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface NodeApi
{
    PendingResult<Status> addListener(final GoogleApiClient p0, final NodeApi$NodeListener p1);
    
    PendingResult<NodeApi$GetConnectedNodesResult> getConnectedNodes(final GoogleApiClient p0);
    
    PendingResult<NodeApi$GetLocalNodeResult> getLocalNode(final GoogleApiClient p0);
    
    PendingResult<Status> removeListener(final GoogleApiClient p0, final NodeApi$NodeListener p1);
}
