// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.cmpevents.legacy;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import com.netflix.mediaclient.util.DeviceUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import com.netflix.mediaclient.service.logging.UserData;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import android.content.Context;

public class NotificationFeedback extends BaseCmpEvent implements Runnable
{
    private static final String DATA_CHANNEL = "channel";
    private static final String DATA_EVENT_GUID = "eventGuid";
    private static final String DATA_MESSAGE_GUID = "messageGuid";
    private static final String DATA_MESSAGE_ID = "messageId";
    private static final String EVENT_NAME = "CMP_MESSAGE_READ";
    private static final String TAG = "nf_rest";
    private static final String VALUE_CHANNEL = "PUSH";
    private Context mContext;
    private UserFeedbackOnReceivedPushNotification mFeedback;
    private MessageData mMsg;
    
    public NotificationFeedback(final Context mContext, final MessageData mMsg, final UserFeedbackOnReceivedPushNotification mFeedback, final UserData mUser) {
        super(mUser);
        this.mContext = mContext;
        this.mUser = mUser;
        this.mFeedback = mFeedback;
        this.mMsg = mMsg;
    }
    
    private JSONObject getEvent(final String s, final CommonRequestParameters commonRequestParameters, final AuthorizationCredentials authorizationCredentials) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        BaseCmpEvent.addIfNotNull(jsonObject, "appName", "Android");
        BaseCmpEvent.addIfNotNull(jsonObject, "time", "" + System.currentTimeMillis());
        final JSONArray jsonArray = new JSONArray();
        jsonObject.put("events", (Object)jsonArray);
        final JSONObject jsonObject2 = new JSONObject();
        jsonArray.put((Object)jsonObject2);
        BaseCmpEvent.addIfNotNull(jsonObject2, "name", "CMP_MESSAGE_READ");
        final JSONObject jsonObject3 = new JSONObject();
        jsonObject2.put("data", (Object)jsonObject3);
        if (this.mMsg != null) {
            BaseCmpEvent.addIfNotNull(jsonObject3, "messageId", this.mMsg.getGuid());
            BaseCmpEvent.addIfNotNull(jsonObject3, "messageGuid", this.mMsg.getMessageGuid());
            BaseCmpEvent.addIfNotNull(jsonObject3, "eventGuid", this.mMsg.getGuid());
        }
        if (this.mFeedback != null) {
            jsonObject3.put("action", (Object)this.mFeedback.getValue());
        }
        if (this.mMsg.getOriginator() != null) {
            jsonObject3.put("senderApp", (Object)this.mMsg.getOriginator());
        }
        else {
            jsonObject3.put("senderApp", (Object)"Android");
        }
        jsonObject3.put("channel", (Object)"PUSH");
        BaseCmpEvent.addIfNotNull(jsonObject3, "esn", s);
        BaseCmpEvent.addIfNotNull(jsonObject3, "device_type", commonRequestParameters.deviceType);
        BaseCmpEvent.addIfNotNull(jsonObject3, "os_version", commonRequestParameters.osVersion);
        BaseCmpEvent.addIfNotNull(jsonObject3, "device_cat", commonRequestParameters.deviceCategory);
        BaseCmpEvent.addIfNotNull(jsonObject3, "app_version", commonRequestParameters.appVersion);
        BaseCmpEvent.addIfNotNull(jsonObject3, "ui_version", commonRequestParameters.uiVersion);
        BaseCmpEvent.addIfNotNull(jsonObject3, "geolocation_country", commonRequestParameters.geolocationCountry);
        BaseCmpEvent.addIfNotNull(jsonObject3, "languages", commonRequestParameters.languages);
        BaseCmpEvent.addIfNotNull(jsonObject3, "accountCountry", commonRequestParameters.country);
        BaseCmpEvent.addIfNotNull(jsonObject3, "user_id", commonRequestParameters.profileToken);
        if (authorizationCredentials != null) {
            BaseCmpEvent.addIfNotNull(jsonObject3, "netflixId", authorizationCredentials.netflixId);
            BaseCmpEvent.addIfNotNull(jsonObject3, "secureNetflixId", authorizationCredentials.secureNetflixId);
        }
        if (DeviceUtils.isLandscape(this.mContext)) {
            jsonObject3.put("orientation", (Object)"landscape");
            return jsonObject;
        }
        jsonObject3.put("orientation", (Object)"portrait");
        return jsonObject;
    }
    
    @Override
    public void run() {
        try {
            final AuthorizationCredentials authorizationCredentials = new AuthorizationCredentials(this.mUser.netflixId, this.mUser.secureNetflixId);
            final CommonRequestParameters commonRequestParameters = this.getCommonRequestParameters(this.mContext);
            final NotificationFeedbackCommand notificationFeedbackCommand = new NotificationFeedbackCommand(this.mUser.esn, authorizationCredentials, commonRequestParameters, this.getEvent(this.mUser.esn, commonRequestParameters, authorizationCredentials), this.mUser.currentProfileToken);
            Log.d("nf_rest", "Executing NotificationFeedbackCommand WebAPI call start");
            final String execute = notificationFeedbackCommand.execute();
            Log.d("nf_rest", "Executing NotificationFeedbackCommand WebAPI call done");
            if (Log.isLoggable("nf_rest", 3)) {
                Log.d("nf_rest", "NotificationFeedbackCommand response: " + execute);
            }
        }
        catch (Throwable t) {
            Log.e("nf_rest", "Failed to execute both WebAPI call!", t);
        }
    }
}
