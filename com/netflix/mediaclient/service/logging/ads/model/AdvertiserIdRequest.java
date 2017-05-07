// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Build$VERSION;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;

public final class AdvertiserIdRequest
{
    protected static final String DATA = "data";
    protected static final String DATA_APP_NAME = "appName";
    protected static final String DATA_EVENTS = "events";
    protected static final String DATA_EVENT_TYPE = "event_type";
    protected static final String DATA_ID = "advdevtag_id";
    protected static final String DATA_NAME = "name";
    protected static final String DATA_OPTED_FOR_ADS = "ad_tracking_preference";
    protected static final String DATA_OS_VERSION = "os_version";
    protected static final String DATA_TIME = "time";
    protected static final String DATA_TYPE = "advdevtag_type";
    protected static final String DATA_USER_AGENT = "user_agent";
    protected static final String EVENT_NAME = "advdevtag";
    protected static final String VALUE_APP_NAME = "android";
    protected static final String VALUE_OPT_IN = "opt-in";
    protected static final String VALUE_OPT_OUT = "opt-out";
    private String mAdvertiserId;
    private AdvertiserIdLogging.EventType mEventType;
    private boolean mOptedIn;
    
    public AdvertiserIdRequest(final String mAdvertiserId, final boolean mOptedIn, final AdvertiserIdLogging.EventType mEventType) {
        this.mAdvertiserId = mAdvertiserId;
        this.mOptedIn = mOptedIn;
        this.mEventType = mEventType;
        if (mEventType == null) {
            throw new IllegalArgumentException("Event type can not be null!");
        }
    }
    
    private JSONObject getEvent() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName", (Object)"android");
        jsonObject.put("time", (Object)("" + System.currentTimeMillis()));
        final JSONArray jsonArray = new JSONArray();
        jsonObject.put("events", (Object)jsonArray);
        final JSONObject jsonObject2 = new JSONObject();
        jsonArray.put((Object)jsonObject2);
        jsonObject2.put("name", (Object)"advdevtag");
        final JSONObject jsonObject3 = new JSONObject();
        jsonObject2.put("data", (Object)jsonObject3);
        jsonObject3.put("advdevtag_type", (Object)"android");
        if (this.mAdvertiserId != null) {
            jsonObject3.put("advdevtag_id", (Object)this.mAdvertiserId);
        }
        String s;
        if (this.mOptedIn) {
            s = "opt-in";
        }
        else {
            s = "opt-out";
        }
        jsonObject3.put("ad_tracking_preference", (Object)s);
        jsonObject3.put("event_type", (Object)this.mEventType.name());
        jsonObject3.put("os_version", (Object)Build$VERSION.RELEASE);
        final String property = System.getProperty("http.agent");
        if (StringUtils.isNotEmpty(property)) {
            jsonObject3.put("user_agent", (Object)property);
        }
        return jsonObject;
    }
    
    public String toJson() {
        try {
            return this.getEvent().toString();
        }
        catch (JSONException ex) {
            return "{}";
        }
    }
    
    @Override
    public String toString() {
        return "AdvertiserIdRequest [mAdvertiserId=" + this.mAdvertiserId + ", mOptedIn=" + this.mOptedIn + "]";
    }
}
