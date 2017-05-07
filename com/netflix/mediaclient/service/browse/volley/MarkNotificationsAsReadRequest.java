// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class MarkNotificationsAsReadRequest extends FalcorVolleyWebClientRequest<List<SocialNotificationSummary>>
{
    private static final String IS_READ_FIELD = "isRead";
    private static final String MARK_AS_READ_FIELD = "markAsRead";
    private static final String NOTIFICATIONS_FIELD = "notifications";
    private static final String SUMMARY_FIELD = "summary";
    private static final String TAG = "nf_service_browse_marknotificationsasreadrequest";
    private final List<SocialNotificationSummary> mNotifications;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public MarkNotificationsAsReadRequest(final Context context, final List<SocialNotificationSummary> mNotifications, final BrowseAgentCallback responseCallback) {
        super(context);
        this.mNotifications = mNotifications;
        this.responseCallback = responseCallback;
        final StringBuffer sb = new StringBuffer();
        final Iterator<SocialNotificationSummary> iterator = mNotifications.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final SocialNotificationSummary socialNotificationSummary = iterator.next();
            if (n > 0) {
                sb.append(", ");
            }
            sb.append("'").append(socialNotificationSummary.getId()).append("'");
            ++n;
        }
        this.pqlQuery = String.format("['%s', [%s], '%s']", "notifications", sb.toString(), "markAsRead");
        if (Log.isLoggable("nf_service_browse_marknotificationsasreadrequest", 2)) {
            Log.v("nf_service_browse_marknotificationsasreadrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationsMarkedAsRead(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final List<SocialNotificationSummary> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationsMarkedAsRead(list, new NetflixStatus(StatusCode.OK));
        }
    }
    
    @Override
    protected List<SocialNotificationSummary> parseFalcorResponse(final String s) {
        if (Log.isLoggable("nf_service_browse_marknotificationsasreadrequest", 4)) {
            Log.i("nf_service_browse_marknotificationsasreadrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_marknotificationsasreadrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("Notifications list doesn't contain 'value' field!");
        }
        JsonObject asJsonObject;
        while (true) {
            while (true) {
                Label_0329: {
                    try {
                        if (!dataObj.has("notifications")) {
                            break Label_0329;
                        }
                        asJsonObject = dataObj.getAsJsonObject("notifications");
                        if (asJsonObject == null) {
                            Log.v("nf_service_browse_marknotificationsasreadrequest", "While parsing the response got null notificationsList");
                            return this.mNotifications;
                        }
                    }
                    catch (Exception ex) {
                        if (Log.isLoggable("nf_service_browse_marknotificationsasreadrequest", 2)) {
                            Log.v("nf_service_browse_marknotificationsasreadrequest", "While getting recommendations field from the response got an exception: " + ex);
                        }
                        throw new FalcorParseException("response missing notifications object", ex);
                    }
                    break;
                }
                asJsonObject = null;
                continue;
            }
        }
        for (final SocialNotificationSummary socialNotificationSummary : this.mNotifications) {
            final String id = socialNotificationSummary.getId();
            if (asJsonObject.has(id)) {
                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(id);
                if (!asJsonObject2.has("summary") || !asJsonObject2.getAsJsonObject("summary").has("isRead")) {
                    continue;
                }
                final JsonElement value = asJsonObject2.getAsJsonObject("summary").get("isRead");
                if (!value.isJsonPrimitive()) {
                    continue;
                }
                socialNotificationSummary.setWasRead(value.getAsBoolean());
            }
            else {
                if (!Log.isLoggable("nf_service_browse_marknotificationsasreadrequest", 6)) {
                    continue;
                }
                Log.e("nf_service_browse_marknotificationsasreadrequest", "Couldn't find the following notification ID in the response: " + id);
            }
        }
        if (Log.isLoggable("nf_service_browse_marknotificationsasreadrequest", 4)) {
            Log.i("nf_service_browse_marknotificationsasreadrequest", "Updated notifications list: " + this.mNotifications);
        }
        return this.mNotifications;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
