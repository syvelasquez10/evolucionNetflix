// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Node;
import java.util.List;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.NodeApi;

public final class aj implements NodeApi
{
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final NodeListener nodeListener) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<Status>)this, nodeListener);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    @Override
    public PendingResult<GetConnectedNodesResult> getConnectedNodes(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<GetConnectedNodesResult>)new d<GetConnectedNodesResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.q((BaseImplementation.b<GetConnectedNodesResult>)this);
            }
            
            protected GetConnectedNodesResult aL(final Status status) {
                return new aj.a(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<GetLocalNodeResult> getLocalNode(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<GetLocalNodeResult>)new d<GetLocalNodeResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.p((BaseImplementation.b<GetLocalNodeResult>)this);
            }
            
            protected GetLocalNodeResult aK(final Status status) {
                return new aj.b(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final NodeListener nodeListener) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.b((BaseImplementation.b<Status>)this, nodeListener);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    public static class a implements GetConnectedNodesResult
    {
        private final Status CM;
        private final List<Node> avA;
        
        public a(final Status cm, final List<Node> avA) {
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
    
    public static class b implements GetLocalNodeResult
    {
        private final Status CM;
        private final Node avB;
        
        public b(final Status cm, final Node avB) {
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
}
