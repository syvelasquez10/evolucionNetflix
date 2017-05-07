// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.UserData;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import org.json.JSONArray;

public final class PushNotificationOptInStatus extends BaseCustomerEvent
{
    private static final String NAME = "UI Push Notification Opt-In Status";
    protected JSONArray event;
    
    public PushNotificationOptInStatus(final String s, final CommonRequestParameters commonRequestParameters, final String s2, final boolean b, final boolean b2, final boolean b3, final AuthorizationCredentials authorizationCredentials, final String s3) throws JSONException {
        super(null);
        final long currentTimeMillis = System.currentTimeMillis();
        this.event = new JSONArray();
        final JSONObject jsonObject = new JSONObject();
        this.event.put((Object)jsonObject);
        jsonObject.put("EventName", (Object)"UI Push Notification Opt-In Status");
        jsonObject.put("EventTime", currentTimeMillis);
        jsonObject.put("Esn", (Object)s);
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject.put("data", (Object)jsonObject2);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "user_id", commonRequestParameters.profileToken);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "profileGuid", s3);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "app_version", commonRequestParameters.appVersion);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "os_version", commonRequestParameters.osVersion);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "ui_version", commonRequestParameters.uiVersion);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "deviceToken", s2);
        jsonObject2.put("pushOptStatus", b);
        jsonObject2.put("infoOptStatus", b2);
        jsonObject2.put("loggedIn", b3);
        jsonObject2.put("timestamp", currentTimeMillis);
        jsonObject2.put("source", (Object)"pn");
        BaseCustomerEvent.addIfNotNull(jsonObject2, "device_cat", commonRequestParameters.deviceCategory);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "device_type", commonRequestParameters.deviceType);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "country", commonRequestParameters.country);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "geolocation_country", commonRequestParameters.geolocationCountry);
        BaseCustomerEvent.addIfNotNull(jsonObject2, "languages", commonRequestParameters.languages);
        if (authorizationCredentials != null) {
            BaseCustomerEvent.addIfNotNull(jsonObject2, "netflixId", authorizationCredentials.netflixId);
            BaseCustomerEvent.addIfNotNull(jsonObject2, "secureNetflixId", authorizationCredentials.secureNetflixId);
        }
    }
    
    @Override
    public String toString() {
        return this.event.toString();
    }
}
