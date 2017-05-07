// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import java.util.Set;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import android.graphics.Bitmap;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.games.request.GameRequest;
import android.os.Bundle;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.request.Requests;

public final class RequestsImpl implements Requests
{
    @Override
    public PendingResult<UpdateRequestsResult> acceptRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.acceptRequests(googleApiClient, list);
    }
    
    @Override
    public PendingResult<UpdateRequestsResult> acceptRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return googleApiClient.b((PendingResult<UpdateRequestsResult>)new UpdateRequestsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<UpdateRequestsResult>)this, array);
            }
        });
    }
    
    @Override
    public PendingResult<UpdateRequestsResult> dismissRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.dismissRequests(googleApiClient, list);
    }
    
    @Override
    public PendingResult<UpdateRequestsResult> dismissRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return googleApiClient.b((PendingResult<UpdateRequestsResult>)new UpdateRequestsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c((BaseImplementation.b<UpdateRequestsResult>)this, array);
            }
        });
    }
    
    @Override
    public ArrayList<GameRequest> getGameRequestsFromBundle(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("requests")) {
            return new ArrayList<GameRequest>();
        }
        final ArrayList list = (ArrayList)bundle.get("requests");
        final ArrayList<GameRequest> list2 = new ArrayList<GameRequest>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            list2.add(list.get(i));
        }
        return list2;
    }
    
    @Override
    public ArrayList<GameRequest> getGameRequestsFromInboxResponse(final Intent intent) {
        if (intent == null) {
            return new ArrayList<GameRequest>();
        }
        return this.getGameRequestsFromBundle(intent.getExtras());
    }
    
    @Override
    public Intent getInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).ko();
    }
    
    @Override
    public int getMaxLifetimeDays(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kq();
    }
    
    @Override
    public int getMaxPayloadSize(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kp();
    }
    
    @Override
    public Intent getSendIntent(final GoogleApiClient googleApiClient, final int n, final byte[] array, final int n2, final Bitmap bitmap, final String s) {
        return Games.c(googleApiClient).a(n, array, n2, bitmap, s);
    }
    
    @Override
    public PendingResult<LoadRequestsResult> loadRequests(final GoogleApiClient googleApiClient, final int n, final int n2, final int n3) {
        return googleApiClient.a((PendingResult<LoadRequestsResult>)new LoadRequestsImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadRequestsResult>)this, n, n2, n3);
            }
        });
    }
    
    @Override
    public void registerRequestListener(final GoogleApiClient googleApiClient, final OnRequestReceivedListener onRequestReceivedListener) {
        Games.c(googleApiClient).a(onRequestReceivedListener);
    }
    
    @Override
    public void unregisterRequestListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).ki();
    }
    
    private abstract static class LoadRequestSummariesImpl extends BaseGamesApiMethodImpl<LoadRequestSummariesResult>
    {
        public LoadRequestSummariesResult ak(final Status status) {
            return new LoadRequestSummariesResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class LoadRequestsImpl extends BaseGamesApiMethodImpl<LoadRequestsResult>
    {
        public LoadRequestsResult al(final Status status) {
            return new LoadRequestsResult() {
                @Override
                public GameRequestBuffer getRequests(final int n) {
                    return new GameRequestBuffer(DataHolder.as(status.getStatusCode()));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class SendRequestImpl extends BaseGamesApiMethodImpl<SendRequestResult>
    {
        public SendRequestResult am(final Status status) {
            return new SendRequestResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class UpdateRequestsImpl extends BaseGamesApiMethodImpl<UpdateRequestsResult>
    {
        public UpdateRequestsResult an(final Status status) {
            return new UpdateRequestsResult() {
                @Override
                public Set<String> getRequestIds() {
                    return null;
                }
                
                @Override
                public int getRequestOutcome(final String s) {
                    throw new IllegalArgumentException("Unknown request ID " + s);
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
}
