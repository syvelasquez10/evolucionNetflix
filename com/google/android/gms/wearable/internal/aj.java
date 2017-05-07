// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.NodeApi$GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.NodeApi$NodeListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.NodeApi;

public final class aj implements NodeApi
{
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final NodeApi$NodeListener nodeApi$NodeListener) {
        return googleApiClient.a((PendingResult<Status>)new aj$3(this, nodeApi$NodeListener));
    }
    
    @Override
    public PendingResult<NodeApi$GetConnectedNodesResult> getConnectedNodes(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<NodeApi$GetConnectedNodesResult>)new aj$2(this));
    }
    
    @Override
    public PendingResult<NodeApi$GetLocalNodeResult> getLocalNode(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<NodeApi$GetLocalNodeResult>)new aj$1(this));
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final NodeApi$NodeListener nodeApi$NodeListener) {
        return googleApiClient.a((PendingResult<Status>)new aj$4(this, nodeApi$NodeListener));
    }
}
