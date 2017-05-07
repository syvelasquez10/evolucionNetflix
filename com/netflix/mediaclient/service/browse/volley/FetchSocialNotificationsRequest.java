// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsListImpl;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSocialNotificationsRequest extends FalcorVolleyWebClientRequest<SocialNotificationsList>
{
    private static final String HORIZONTAL_DISP_FIELD = "horzDispUrl";
    private static final String HORIZONTAL_DISP_OBJECT = "horzArtUrl";
    private static final String IN_QUEUE_FIELD = "inQueue";
    private static final String NOTIFICATIONS_LIST_FIELD = "notificationsList";
    private static final String NOTIFICATION_VIDEO_FIELD = "notificationVideo";
    public static final int PAGE_SOCIAL_NOTIFICATIONS_SIZE = 20;
    private static final String SUMMARY_FIELD = "summary";
    private static final String TAG = "nf_service_user_fetchsocialnotificationsrequest";
    private BrowseWebClientCache browseCache;
    private final int fromIndex;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final String pqlQuery5;
    private final BrowseAgentCallback responseCallback;
    
    public FetchSocialNotificationsRequest(final Context context, final BrowseWebClientCache browseCache, final int fromIndex, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.fromIndex = fromIndex;
        this.browseCache = browseCache;
        this.pqlQuery1 = String.format("['%s', {'from':%d,'to':%d}, '%s']", "notificationsList", fromIndex, fromIndex + 20 - 1, "summary");
        this.pqlQuery2 = String.format("['%s', {'from':%d,'to':%d}, '%s', '%s']", "notificationsList", fromIndex, fromIndex + 20 - 1, "notificationVideo", "summary");
        this.pqlQuery3 = String.format("['%s', {'from':%d,'to':%d}, '%s', '%s']", "notificationsList", fromIndex, fromIndex + 20 - 1, "notificationVideo", "horzArtUrl");
        this.pqlQuery4 = String.format("['%s', {'from':%d,'to':%d}, '%s', '%s']", "notificationsList", fromIndex, fromIndex + 20 - 1, "notificationVideo", "inQueue");
        this.pqlQuery5 = String.format("['%s', '%s']", "notificationsList", "summary");
        if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 2)) {
            Log.v("nf_service_user_fetchsocialnotificationsrequest", "PQL1 = " + this.pqlQuery1);
            Log.v("nf_service_user_fetchsocialnotificationsrequest", "PQL2 = " + this.pqlQuery2);
            Log.v("nf_service_user_fetchsocialnotificationsrequest", "PQL3 = " + this.pqlQuery3);
            Log.v("nf_service_user_fetchsocialnotificationsrequest", "PQL4 = " + this.pqlQuery4);
            Log.v("nf_service_user_fetchsocialnotificationsrequest", "PQL5 = " + this.pqlQuery5);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4, this.pqlQuery5);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationsListFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final SocialNotificationsList list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationsListFetched(list, new NetflixStatus(StatusCode.OK));
        }
    }
    
    @Override
    protected SocialNotificationsList parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 4)) {
            Log.i("nf_service_user_fetchsocialnotificationsrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_user_fetchsocialnotificationsrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("Notifications list doesn't contain 'value' field!");
        }
        JsonObject asJsonObject = null;
        try {
            if (dataObj.has("notificationsList")) {
                asJsonObject = dataObj.getAsJsonObject("notificationsList");
            }
            if (asJsonObject == null) {
                Log.v("nf_service_user_fetchsocialnotificationsrequest", "While parsing the response got null notificationsList");
                return null;
            }
        }
        catch (Exception ex) {
            if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 2)) {
                Log.v("nf_service_user_fetchsocialnotificationsrequest", "While getting recommendations field from the response got an exception: " + ex);
            }
            throw new FalcorParseException("response missing notificationsList object", ex);
        }
        final SocialNotificationsListSummary socialNotificationsListSummary = null;
        final ArrayList<SocialNotificationSummary> list = new ArrayList<SocialNotificationSummary>();
        SocialNotificationsListSummary socialNotificationsListSummary2;
        if (asJsonObject.has("summary")) {
            socialNotificationsListSummary2 = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", SocialNotificationsListSummary.class);
        }
        else {
            socialNotificationsListSummary2 = socialNotificationsListSummary;
            if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 5)) {
                Log.w("nf_service_user_fetchsocialnotificationsrequest", "notificationsListObj doesn't contain 'summary' field: " + asJsonObject);
                socialNotificationsListSummary2 = socialNotificationsListSummary;
            }
        }
        for (int i = this.fromIndex; i < this.fromIndex + 20; ++i) {
            final String string = Integer.toString(i);
            if (asJsonObject.has(string)) {
                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                final SocialNotificationSummary socialNotificationSummary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", SocialNotificationSummary.class);
                if (socialNotificationSummary.getType() == null) {
                    if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 6)) {
                        Log.e("nf_service_user_fetchsocialnotificationsrequest", "Skipping notifications with unsupported type... JSONObject: " + asJsonObject2);
                    }
                }
                else if (asJsonObject2.has("notificationVideo")) {
                    final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("notificationVideo");
                    final Video.Summary videoSummary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", Video.Summary.class);
                    final Video.InQueue inQueue = FalcorParseUtils.getPropertyObject(asJsonObject3, "inQueue", Video.InQueue.class);
                    final String asString = asJsonObject3.getAsJsonObject("horzArtUrl").getAsJsonPrimitive("horzDispUrl").getAsString();
                    final Video.InQueue updateInQueueCacheRecord = this.browseCache.updateInQueueCacheRecord(videoSummary.getId(), inQueue);
                    socialNotificationSummary.setVideoSummary(videoSummary);
                    socialNotificationSummary.setInQueue(updateInQueueCacheRecord);
                    String boxshotURL;
                    if ((boxshotURL = asString) == null) {
                        boxshotURL = videoSummary.getBoxshotURL();
                    }
                    socialNotificationSummary.setHorizontalBoxart(boxshotURL);
                    list.add(socialNotificationSummary);
                }
                else if (Log.isLoggable("nf_service_user_fetchsocialnotificationsrequest", 6)) {
                    Log.e("nf_service_user_fetchsocialnotificationsrequest", "Wierd notificationObj: " + asJsonObject2 + " without 'notificationVideo' field! Skipping...");
                }
            }
        }
        return new SocialNotificationsListImpl(list, socialNotificationsListSummary2);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
