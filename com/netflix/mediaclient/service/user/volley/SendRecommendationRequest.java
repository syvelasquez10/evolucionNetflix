// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import java.util.Set;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class SendRecommendationRequest extends FalkorVolleyWebClientRequest<Set<FriendForRecommendation>>
{
    private static final String FRIEND_VIDEOS_FIELD = "friendVideos";
    public static final int PAGE_FRIENDS_SIZE = 20;
    private static final String RECOMMEND_FIELD = "recommend";
    private static final String TAG = "nf_service_user_fetchfriendsforrecommendationrequest";
    private static final String WAS_RECOMMENDED_FIELD = "wasRecommended";
    private final String mBodyText;
    private final Set<FriendForRecommendation> mFriends;
    private final String mGUID;
    private final String mVideoId;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public SendRecommendationRequest(final Context context, final String mVideoId, final Set<FriendForRecommendation> mFriends, final String s, final String mguid, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.mFriends = mFriends;
        String escapeJsonChars;
        if (s == null) {
            escapeJsonChars = null;
        }
        else {
            escapeJsonChars = StringUtils.escapeJsonChars(s);
        }
        this.mBodyText = escapeJsonChars;
        this.mGUID = mguid;
        final StringBuilder sb = new StringBuilder("[");
        final Iterator<FriendForRecommendation> iterator = mFriends.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final FriendForRecommendation friendForRecommendation = iterator.next();
            if (n > 0) {
                sb.append(", ");
            }
            sb.append("'").append(friendForRecommendation.getFriendProfile().getId()).append("'");
            ++n;
        }
        sb.append("]");
        this.pqlQuery = "['" + "friendVideos" + "', " + sb.toString() + ", '" + mVideoId + "', '" + "recommend" + "']";
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(FalkorVolleyWebClientRequest.urlEncodPQLParam("param", "\"" + this.mBodyText + "\""));
        sb.append("&param=").append("\"").append(this.mGUID).append("\"");
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onRecommendationsSent(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final Set<FriendForRecommendation> set) {
        if (this.responseCallback != null) {
            this.responseCallback.onRecommendationsSent(set, CommonStatus.OK);
        }
    }
    
    @Override
    protected Set<FriendForRecommendation> parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.i("nf_service_user_fetchfriendsforrecommendationrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchfriendsforrecommendationrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("UserProfiles empty!!!");
        }
        JsonObject asJsonObject;
        try {
            asJsonObject = dataObj.getAsJsonObject("friendVideos");
            if (asJsonObject == null) {
                Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "While parsing the response got null friendsListObj");
                return this.mFriends;
            }
        }
        catch (Exception ex) {
            if (Log.isLoggable()) {
                Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "While getting recommendations field from the response got an exception: " + ex);
            }
            throw new FalkorParseException("response missing friends json objects", ex);
        }
        for (final FriendForRecommendation friendForRecommendation : this.mFriends) {
            final String id = friendForRecommendation.getFriendProfile().getId();
            if (asJsonObject.has(id)) {
                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(id);
                if (asJsonObject2.has(this.mVideoId)) {
                    friendForRecommendation.setWasRecommended(asJsonObject2.getAsJsonObject(this.mVideoId).getAsJsonPrimitive("wasRecommended").getAsBoolean());
                }
                else {
                    if (!Log.isLoggable()) {
                        continue;
                    }
                    Log.e("nf_service_user_fetchfriendsforrecommendationrequest", "Wierd profileObj: " + asJsonObject2 + " without videoId: " + this.mVideoId + " field...");
                }
            }
        }
        return this.mFriends;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
