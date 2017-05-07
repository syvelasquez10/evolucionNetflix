// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import android.content.Context;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.UserData;

public abstract class BaseCustomerEvent
{
    protected static final String DATA = "data";
    protected static final String DATA_APP_VERSION = "app_version";
    protected static final String DATA_COUNTRY = "country";
    protected static final String DATA_DEEPLINK_MSG_PARAMS = "deeplinkMsgParams";
    protected static final String DATA_DEVICETOKEN = "deviceToken";
    protected static final String DATA_DEVICE_CAT = "device_cat";
    protected static final String DATA_DEVICE_TYPE = "device_type";
    protected static final String DATA_GEOLOCATION_COUNTRY = "geolocation_country";
    protected static final String DATA_INFOOPTSTATUS = "infoOptStatus";
    protected static final String DATA_LANGUAGES = "languages";
    protected static final String DATA_LOGGEDIN = "loggedIn";
    protected static final String DATA_NETFLIX_ID = "netflixId";
    protected static final String DATA_OS_VERSION = "os_version";
    protected static final String DATA_PROFILE_GUID = "profileGuid";
    protected static final String DATA_PROFILE_TOKEN = "user_id";
    protected static final String DATA_PUSHOPTSTATUS = "pushOptStatus";
    protected static final String DATA_SECURE_NETFLIX_ID = "secureNetflixId";
    protected static final String DATA_SOURCE = "source";
    protected static final String DATA_TIMESTAMP = "timestamp";
    protected static final String DATA_UI_VERSION = "ui_version";
    protected static final String ESN = "Esn";
    protected static final String EVENT_NAME = "EventName";
    protected static final String EVENT_TIME = "EventTime";
    protected static final String TAG = "nf_rest";
    protected static final String VALUE_SOURCE_PN = "pn";
    protected UserData mUser;
    
    protected BaseCustomerEvent(final UserData mUser) {
        this.mUser = mUser;
    }
    
    protected static void addIfNotNull(final JSONObject jsonObject, final String s, final String s2) {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
        }
    }
    
    protected CommonRequestParameters getCommonRequestParameters(final Context context) {
        final CommonRequestParameters instanceWithCredentials = CommonRequestParameters.getInstanceWithCredentials();
        instanceWithCredentials.profileToken = this.mUser.currentProfileToken;
        instanceWithCredentials.osVersion = String.valueOf(AndroidUtils.getAndroidVersion());
        instanceWithCredentials.deviceCategory = this.mUser.deviceCategory;
        instanceWithCredentials.appVersion = AndroidManifestUtils.getVersion(context);
        instanceWithCredentials.uiVersion = instanceWithCredentials.appVersion;
        instanceWithCredentials.country = this.mUser.accountCountry;
        instanceWithCredentials.geolocationCountry = this.mUser.geoLocationCountry;
        instanceWithCredentials.languages = this.mUser.languages;
        return instanceWithCredentials;
    }
    
    protected JSONObject getEvent(final String s, final CommonRequestParameters commonRequestParameters, final AuthorizationCredentials authorizationCredentials, final long n, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        addIfNotNull(jsonObject, "user_id", commonRequestParameters.profileToken);
        addIfNotNull(jsonObject, "app_version", commonRequestParameters.appVersion);
        addIfNotNull(jsonObject, "os_version", commonRequestParameters.osVersion);
        addIfNotNull(jsonObject, "ui_version", commonRequestParameters.uiVersion);
        jsonObject.put("timestamp", n);
        addIfNotNull(jsonObject, "device_cat", commonRequestParameters.deviceCategory);
        addIfNotNull(jsonObject, "device_type", commonRequestParameters.deviceType);
        addIfNotNull(jsonObject, "country", commonRequestParameters.country);
        addIfNotNull(jsonObject, "geolocation_country", commonRequestParameters.geolocationCountry);
        addIfNotNull(jsonObject, "languages", commonRequestParameters.languages);
        if (authorizationCredentials != null) {
            addIfNotNull(jsonObject, "netflixId", authorizationCredentials.netflixId);
            addIfNotNull(jsonObject, "secureNetflixId", authorizationCredentials.secureNetflixId);
        }
        if (StringUtils.isNotEmpty(s2)) {
            addIfNotNull(jsonObject, "deeplinkMsgParams", s2);
        }
        return jsonObject;
    }
}
