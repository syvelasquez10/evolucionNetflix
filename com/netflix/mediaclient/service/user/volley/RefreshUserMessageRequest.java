// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collections;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class RefreshUserMessageRequest extends FalkorVolleyWebClientRequest<UmaAlert>
{
    private static final String FIELD_USER = "user";
    private static final String TAG = "RefreshUserMessageRequest";
    private final User mUser;
    
    public RefreshUserMessageRequest(final Context context, final User mUser) {
        super(context, 0);
        this.mUser = mUser;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Collections.singletonList("['user', 'uma']");
    }
    
    @Override
    protected void onFailure(final Status status) {
        Log.e("RefreshUserMessageRequest", "UMA, failed to refresh : " + status);
    }
    
    @Override
    protected void onSuccess(final UmaAlert umaAlert) {
        if (Log.isLoggable()) {
            Log.d("RefreshUserMessageRequest", "UMA refreshed from server : " + umaAlert);
        }
        this.mUser.setUmaAlert(umaAlert);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent("RefreshUserMessageRequest.ACTION_UMA_MESSAGE_UPDATED"));
    }
    
    @Override
    protected UmaAlert parseFalkorResponse(final String s) {
        final JsonObject dataObj = FalkorParseUtils.getDataObj("RefreshUserMessageRequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return null;
        }
        try {
            return FalkorParseUtils.getPropertyObject(dataObj.getAsJsonObject("user"), "uma", UmaAlert.class);
        }
        catch (Exception ex) {
            if (Log.isLoggable()) {
                Log.v("RefreshUserMessageRequest", "String response to parse = " + s);
            }
            throw new FalkorException("response missing user json objects", ex);
        }
    }
    
    @Override
    protected boolean parsedResponseCanBeNull() {
        return true;
    }
}
