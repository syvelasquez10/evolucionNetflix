// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.cmpevents.legacy;

import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.UserData;

public abstract class BaseCmpEvent
{
    protected static final String DATA = "data";
    protected static final String DATA_ACCOUNT_COUNTRY = "accountCountry";
    protected static final String DATA_ACTION = "action";
    protected static final String DATA_APP_NAME = "appName";
    protected static final String DATA_APP_VERSION = "app_version";
    protected static final String DATA_DEVICE_CAT = "device_cat";
    protected static final String DATA_DEVICE_TYPE = "device_type";
    protected static final String DATA_ESN = "esn";
    protected static final String DATA_EVENTS = "events";
    protected static final String DATA_GEOLOCATION_COUNTRY = "geolocation_country";
    protected static final String DATA_LANGUAGES = "languages";
    protected static final String DATA_NAME = "name";
    protected static final String DATA_NETFLIX_ID = "netflixId";
    protected static final String DATA_ORIENTATION = "orientation";
    protected static final String DATA_OS_VERSION = "os_version";
    protected static final String DATA_SECURE_NETFLIX_ID = "secureNetflixId";
    protected static final String DATA_SENDER_APP = "senderApp";
    protected static final String DATA_TIME = "time";
    protected static final String DATA_UI_VERSION = "ui_version";
    protected static final String DATA_USER_ID = "user_id";
    protected static final String TAG = "nf_rest";
    protected static final String VALUE_APP_NAME = "Android";
    protected UserData mUser;
    
    public BaseCmpEvent(final UserData mUser) {
        this.mUser = mUser;
    }
    
    protected static void addIfNotNull(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
        }
    }
    
    protected CommonRequestParameters getCommonRequestParameters(final Context context) {
        final CommonRequestParameters instanceWithCredentials = CommonRequestParameters.getInstanceWithCredentials();
        instanceWithCredentials.userId = this.mUser.currentProfileToken;
        instanceWithCredentials.osVersion = String.valueOf(AndroidUtils.getAndroidVersion());
        instanceWithCredentials.deviceCategory = this.mUser.deviceCategory;
        instanceWithCredentials.appVersion = AndroidManifestUtils.getVersion(context);
        instanceWithCredentials.uiVersion = instanceWithCredentials.appVersion;
        instanceWithCredentials.country = this.mUser.accountCountry;
        instanceWithCredentials.geolocationCountry = this.mUser.geoLocationCountry;
        instanceWithCredentials.languages = this.mUser.languages;
        return instanceWithCredentials;
    }
}
