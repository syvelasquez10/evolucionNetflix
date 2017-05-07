// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchFriendsForRecommendationRequest extends FalkorVolleyWebClientRequest<List<FriendForRecommendation>>
{
    private static final String FILTERED_RECOMMENDATIONS_FIELD = "filteredPotentialRecommendations";
    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String FRIEND_FIELD = "friend";
    private static final String ID_NAME_FIELD = "id";
    private static final String IMAGE_URL_FIELD = "imageUrl";
    private static final String LAST_NAME_FIELD = "lastName";
    private static final String NETFLIX_CONNECTED_FIELD = "netflixConnected";
    public static final int PAGE_FRIENDS_SIZE = 20;
    private static final String POTENTIAL_RECOMMENDATIONS_FIELD = "potentialRecommendations";
    private static final String SUMMARY_FIELD = "summary";
    private static final String TAG = "nf_service_user_fetchfriendsforrecommendationrequest";
    private static final String VIDEOS_FIELD = "videos";
    private static final String WAS_WATCHED_FIELD = "wasWatched";
    private final int fromIndex;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final UserAgentWebCallback responseCallback;
    private final String searchTerm;
    private final String videoId;
    
    public FetchFriendsForRecommendationRequest(final Context context, final String videoId, final int fromIndex, final String searchTerm, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.videoId = videoId;
        this.searchTerm = searchTerm;
        this.fromIndex = fromIndex;
        if (this.isSearchTermValid()) {
            this.pqlQuery1 = String.format("['%s', '%s', '%s', '%s', {'from':%d,'to':%d}, 'friend', '%s']", "videos", videoId, "filteredPotentialRecommendations", searchTerm, fromIndex, fromIndex + 20 - 1, "summary");
            this.pqlQuery2 = String.format("['%s', '%s', '%s', '%s', {'from':%d,'to':%d}, 'wasWatched']", "videos", videoId, "filteredPotentialRecommendations", searchTerm, fromIndex, fromIndex + 20 - 1);
        }
        else {
            this.pqlQuery1 = String.format("['%s', '%s', '%s', {'from':%d,'to':%d}, 'friend', '%s']", "videos", videoId, "potentialRecommendations", fromIndex, fromIndex + 20 - 1, "summary");
            this.pqlQuery2 = String.format("['%s', '%s', '%s', {'from':%d,'to':%d}, 'wasWatched']", "videos", videoId, "potentialRecommendations", fromIndex, fromIndex + 20 - 1);
        }
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "PQL1 = " + this.pqlQuery1);
            Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "PQL2 = " + this.pqlQuery2);
        }
    }
    
    private boolean isSearchTermValid() {
        return this.searchTerm != null && this.searchTerm.length() > 0;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onFriendsForRecommendationsListFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final List<FriendForRecommendation> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onFriendsForRecommendationsListFetched(list, new NetflixStatus(StatusCode.OK));
        }
    }
    
    @Override
    protected List<FriendForRecommendation> parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.i("nf_service_user_fetchfriendsforrecommendationrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchfriendsforrecommendationrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("UserProfiles empty!!!");
        }
        final JsonObject jsonObject = null;
        JsonObject jsonObject2;
        ArrayList<FriendForRecommendation> list;
        try {
            if (this.isSearchTermValid() && dataObj.has("videos") && dataObj.getAsJsonObject("videos").has(this.videoId) && dataObj.getAsJsonObject("videos").getAsJsonObject(this.videoId).has("filteredPotentialRecommendations") && dataObj.getAsJsonObject("videos").getAsJsonObject(this.videoId).getAsJsonObject("filteredPotentialRecommendations").has(this.searchTerm)) {
                jsonObject2 = dataObj.getAsJsonObject("videos").getAsJsonObject(this.videoId).getAsJsonObject("filteredPotentialRecommendations").getAsJsonObject(this.searchTerm);
            }
            else {
                jsonObject2 = jsonObject;
                if (dataObj.has("videos")) {
                    jsonObject2 = jsonObject;
                    if (dataObj.getAsJsonObject("videos").has(this.videoId)) {
                        jsonObject2 = jsonObject;
                        if (dataObj.getAsJsonObject("videos").getAsJsonObject(this.videoId).has("potentialRecommendations")) {
                            jsonObject2 = dataObj.getAsJsonObject("videos").getAsJsonObject(this.videoId).getAsJsonObject("potentialRecommendations");
                        }
                    }
                }
            }
            list = new ArrayList<FriendForRecommendation>();
            if (jsonObject2 == null) {
                Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "While parsing the response got null friendsListObj");
                return list;
            }
        }
        catch (Exception ex) {
            if (Log.isLoggable()) {
                Log.v("nf_service_user_fetchfriendsforrecommendationrequest", "While getting recommendations field from the response got an exception: " + ex);
            }
            throw new FalkorParseException("response missing user json objects", ex);
        }
        for (int i = this.fromIndex; i < this.fromIndex + 20; ++i) {
            final String string = Integer.toString(i);
            if (jsonObject2.has(string)) {
                final JsonObject asJsonObject = jsonObject2.getAsJsonObject(string);
                if (asJsonObject.has("friend")) {
                    final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("friend").getAsJsonObject("summary");
                    final String asString = asJsonObject2.getAsJsonPrimitive("id").getAsString();
                    final String asString2 = asJsonObject2.getAsJsonPrimitive("firstName").getAsString();
                    final String asString3 = asJsonObject2.getAsJsonPrimitive("lastName").getAsString();
                    final String asString4 = asJsonObject2.getAsJsonPrimitive("imageUrl").getAsString();
                    final boolean equals = "connected".equals(asJsonObject2.getAsJsonPrimitive("netflixConnected").getAsString());
                    final FriendProfile friendProfile = new FriendProfile(asString, asString2, asString3, asString4);
                    if (asJsonObject.has("wasWatched")) {
                        list.add(new FriendForRecommendation(friendProfile, asJsonObject.getAsJsonPrimitive("wasWatched").getAsBoolean(), equals));
                    }
                    else if (Log.isLoggable()) {
                        Log.e("nf_service_user_fetchfriendsforrecommendationrequest", "Wierd profileObj: " + asJsonObject + " without 'wasWatched' field! Skipping...");
                    }
                }
                else if (Log.isLoggable()) {
                    Log.e("nf_service_user_fetchfriendsforrecommendationrequest", "Wierd profileObj: " + asJsonObject + " without 'friend' field! Skipping...");
                }
            }
        }
        return list;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
