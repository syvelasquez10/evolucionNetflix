// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchAvailableAvatarsRequest extends FalkorVolleyWebClientRequest<List<AvatarInfo>>
{
    private static final String AVATARS_FIELD = "availableAvatarsList";
    private static final String NAME_FIELD = "name";
    private static final String TAG = "nf_service_user_fetchavailableavatarsrequest";
    private static final String URL_FIELD = "url";
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public FetchAvailableAvatarsRequest(final Context context, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.pqlQuery = "['availableAvatarsList']";
        if (Log.isLoggable("nf_service_user_fetchavailableavatarsrequest", 2)) {
            Log.v("nf_service_user_fetchavailableavatarsrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onAvatarsListFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final List<AvatarInfo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onAvatarsListFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<AvatarInfo> parseFalkorResponse(String s) {
        if (Log.isLoggable("nf_service_user_fetchavailableavatarsrequest", 2)) {
            Log.i("nf_service_user_fetchavailableavatarsrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchavailableavatarsrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("Avatars list empty!!!");
        }
        try {
            final JsonArray asJsonArray = dataObj.getAsJsonArray("availableAvatarsList");
            s = (String)new ArrayList();
            final Iterator<JsonElement> iterator = asJsonArray.iterator();
            while (iterator.hasNext()) {
                final JsonObject asJsonObject = iterator.next().getAsJsonObject();
                ((ArrayList<AvatarInfo>)s).add(new AvatarInfo(asJsonObject.getAsJsonPrimitive("name").getAsString(), asJsonObject.getAsJsonPrimitive("url").getAsString()));
            }
        }
        catch (Exception ex) {
            if (Log.isLoggable("nf_service_user_fetchavailableavatarsrequest", 6)) {
                Log.e("nf_service_user_fetchavailableavatarsrequest", "Got exception for string response to parse: " + s);
            }
            throw new FalkorParseException("response missing avatars json objects", ex);
        }
        return (List<AvatarInfo>)s;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
