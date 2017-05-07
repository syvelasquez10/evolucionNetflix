// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import java.util.List;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface NodeApi
{
    PendingResult<Status> addListener(final GoogleApiClient p0, final NodeListener p1);
    
    PendingResult<GetConnectedNodesResult> getConnectedNodes(final GoogleApiClient p0);
    
    PendingResult<GetLocalNodeResult> getLocalNode(final GoogleApiClient p0);
    
    PendingResult<Status> removeListener(final GoogleApiClient p0, final NodeListener p1);
    
    public interface GetConnectedNodesResult extends Result
    {
        List<Node> getNodes();
    }
    
    public interface GetLocalNodeResult extends Result
    {
        Node getNode();
    }
    
    public interface NodeListener
    {
        void onPeerConnected(final Node p0);
        
        void onPeerDisconnected(final Node p0);
    }
}
