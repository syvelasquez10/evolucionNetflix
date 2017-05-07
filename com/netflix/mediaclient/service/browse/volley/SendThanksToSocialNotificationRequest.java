// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class SendThanksToSocialNotificationRequest extends FalcorVolleyWebClientRequest<SocialNotificationSummary>
{
    private static final String IS_THANKED_FIELD = "isThanked";
    private static final String NOTIFICATIONS_FIELD = "notifications";
    private static final String SAY_THANKS_FIELD = "sayThanks";
    private static final String SUMMARY_FIELD = "summary";
    private static final String TAG = "nf_service_browse_sendsendtosocialnotificationrequest";
    private final SocialNotificationSummary mNotification;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public SendThanksToSocialNotificationRequest(final Context context, final SocialNotificationSummary mNotification, final BrowseAgentCallback responseCallback) {
        super(context);
        this.mNotification = mNotification;
        this.responseCallback = responseCallback;
        this.pqlQuery = String.format("['%s', '%s', '%s']", "notifications", mNotification.getId(), "sayThanks");
        if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 2)) {
            Log.v("nf_service_browse_sendsendtosocialnotificationrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final String urlEncodPQLParam = FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), "\"" + this.mNotification.getStoryId() + "\"");
        if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 3)) {
            Log.d("nf_service_browse_sendsendtosocialnotificationrequest", " getOptionalParams: " + urlEncodPQLParam);
        }
        return urlEncodPQLParam;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationWasThanked(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final SocialNotificationSummary socialNotificationSummary) {
        if (this.responseCallback != null) {
            this.responseCallback.onSocialNotificationWasThanked(socialNotificationSummary, new NetflixStatus(StatusCode.OK));
        }
    }
    
    @Override
    protected SocialNotificationSummary parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 4)) {
            Log.i("nf_service_browse_sendsendtosocialnotificationrequest", "Got result: " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_sendsendtosocialnotificationrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("Notifications list doesn't contain 'value' field!");
        }
        JsonObject asJsonObject = null;
        try {
            if (dataObj.has("notifications")) {
                asJsonObject = dataObj.getAsJsonObject("notifications");
            }
            if (asJsonObject == null) {
                Log.v("nf_service_browse_sendsendtosocialnotificationrequest", "While parsing the response got null notificationsList");
                return this.mNotification;
            }
        }
        catch (Exception ex) {
            if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 2)) {
                Log.v("nf_service_browse_sendsendtosocialnotificationrequest", "While getting recommendations field from the response got an exception: " + ex);
            }
            throw new FalcorParseException("response missing notifications object", ex);
        }
        final String id = this.mNotification.getId();
        if (asJsonObject.has(id)) {
            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(id);
            if (asJsonObject2.has("summary") && asJsonObject2.getAsJsonObject("summary").has("isThanked")) {
                final JsonElement value = asJsonObject2.getAsJsonObject("summary").get("isThanked");
                if (value.isJsonPrimitive()) {
                    this.mNotification.setWasThanked(value.getAsBoolean());
                }
            }
        }
        else if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 6)) {
            Log.e("nf_service_browse_sendsendtosocialnotificationrequest", "Couldn't find the following notification ID in the response: " + id);
        }
        if (Log.isLoggable("nf_service_browse_sendsendtosocialnotificationrequest", 4)) {
            Log.i("nf_service_browse_sendsendtosocialnotificationrequest", "Updated notification: " + this.mNotification);
        }
        return this.mNotification;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
