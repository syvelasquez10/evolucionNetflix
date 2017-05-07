// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import java.util.Set;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import android.graphics.Bitmap;
import android.content.Intent;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.List;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Requests
{
    public static final String EXTRA_REQUESTS = "requests";
    public static final int REQUEST_DEFAULT_LIFETIME_DAYS = -1;
    public static final int REQUEST_DIRECTION_INBOUND = 0;
    public static final int REQUEST_DIRECTION_OUTBOUND = 1;
    public static final int REQUEST_UPDATE_OUTCOME_FAIL = 1;
    public static final int REQUEST_UPDATE_OUTCOME_RETRY = 2;
    public static final int REQUEST_UPDATE_OUTCOME_SUCCESS = 0;
    public static final int REQUEST_UPDATE_TYPE_ACCEPT = 0;
    public static final int REQUEST_UPDATE_TYPE_DISMISS = 1;
    public static final int SORT_ORDER_EXPIRING_SOON_FIRST = 0;
    public static final int SORT_ORDER_SOCIAL_AGGREGATION = 1;
    
    PendingResult<UpdateRequestsResult> acceptRequest(final GoogleApiClient p0, final String p1);
    
    PendingResult<UpdateRequestsResult> acceptRequests(final GoogleApiClient p0, final List<String> p1);
    
    PendingResult<UpdateRequestsResult> dismissRequest(final GoogleApiClient p0, final String p1);
    
    PendingResult<UpdateRequestsResult> dismissRequests(final GoogleApiClient p0, final List<String> p1);
    
    ArrayList<GameRequest> getGameRequestsFromBundle(final Bundle p0);
    
    ArrayList<GameRequest> getGameRequestsFromInboxResponse(final Intent p0);
    
    Intent getInboxIntent(final GoogleApiClient p0);
    
    int getMaxLifetimeDays(final GoogleApiClient p0);
    
    int getMaxPayloadSize(final GoogleApiClient p0);
    
    Intent getSendIntent(final GoogleApiClient p0, final int p1, final byte[] p2, final int p3, final Bitmap p4, final String p5);
    
    PendingResult<LoadRequestsResult> loadRequests(final GoogleApiClient p0, final int p1, final int p2, final int p3);
    
    void registerRequestListener(final GoogleApiClient p0, final OnRequestReceivedListener p1);
    
    void unregisterRequestListener(final GoogleApiClient p0);
    
    public interface LoadRequestSummariesResult extends Releasable, Result
    {
    }
    
    public interface LoadRequestsResult extends Releasable, Result
    {
        GameRequestBuffer getRequests(final int p0);
    }
    
    public interface SendRequestResult extends Result
    {
    }
    
    public interface UpdateRequestsResult extends Releasable, Result
    {
        Set<String> getRequestIds();
        
        int getRequestOutcome(final String p0);
    }
}
