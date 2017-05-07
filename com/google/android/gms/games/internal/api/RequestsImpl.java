// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;
import android.graphics.Bitmap;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.games.request.GameRequest;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.request.Requests;

public final class RequestsImpl implements Requests
{
    @Override
    public PendingResult<Requests$UpdateRequestsResult> acceptRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.acceptRequests(googleApiClient, list);
    }
    
    @Override
    public PendingResult<Requests$UpdateRequestsResult> acceptRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return googleApiClient.b((PendingResult<Requests$UpdateRequestsResult>)new RequestsImpl$1(this, array));
    }
    
    @Override
    public PendingResult<Requests$UpdateRequestsResult> dismissRequest(final GoogleApiClient googleApiClient, final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        return this.dismissRequests(googleApiClient, list);
    }
    
    @Override
    public PendingResult<Requests$UpdateRequestsResult> dismissRequests(final GoogleApiClient googleApiClient, final List<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[list.size()]);
        }
        return googleApiClient.b((PendingResult<Requests$UpdateRequestsResult>)new RequestsImpl$2(this, array));
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
    public PendingResult<Requests$LoadRequestsResult> loadRequests(final GoogleApiClient googleApiClient, final int n, final int n2, final int n3) {
        return googleApiClient.a((PendingResult<Requests$LoadRequestsResult>)new RequestsImpl$3(this, n, n2, n3));
    }
    
    @Override
    public void registerRequestListener(final GoogleApiClient googleApiClient, final OnRequestReceivedListener onRequestReceivedListener) {
        Games.c(googleApiClient).a(onRequestReceivedListener);
    }
    
    @Override
    public void unregisterRequestListener(final GoogleApiClient googleApiClient) {
        Games.c(googleApiClient).ki();
    }
}
