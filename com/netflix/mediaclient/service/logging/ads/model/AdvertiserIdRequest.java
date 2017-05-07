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
    protected static final String DATA_DEVICE = "device";
    protected static final String DATA_DEVICE_HEADER = "deviceModelHeader";
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
    private String mDeviceModel;
    private AdvertiserIdLogging.EventType mEventType;
    private boolean mOptedIn;
    
    public AdvertiserIdRequest(final String mAdvertiserId, final boolean mOptedIn, final AdvertiserIdLogging.EventType mEventType, final String mDeviceModel) {
        this.mAdvertiserId = mAdvertiserId;
        this.mOptedIn = mOptedIn;
        this.mEventType = mEventType;
        this.mDeviceModel = mDeviceModel;
        if (mEventType == null) {
            throw new IllegalArgumentException("Event type can not be null!");
        }
    }
    
    private JSONObject getEvent() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName", (Object)"android");
        jsonObject.put("time", (Object)("" + System.currentTimeMillis()));
        if (this.mDeviceModel != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("device", (Object)jsonObject2);
            jsonObject2.put("deviceModelHeader", (Object)this.mDeviceModel);
        }
        final JSONArray jsonArray = new JSONArray();
        jsonObject.put("events", (Object)jsonArray);
        final JSONObject jsonObject3 = new JSONObject();
        jsonArray.put((Object)jsonObject3);
        jsonObject3.put("name", (Object)"advdevtag");
        final JSONObject jsonObject4 = new JSONObject();
        jsonObject3.put("data", (Object)jsonObject4);
        jsonObject4.put("advdevtag_type", (Object)"android");
        if (this.mAdvertiserId != null) {
            jsonObject4.put("advdevtag_id", (Object)this.mAdvertiserId);
        }
        String s;
        if (this.mOptedIn) {
            s = "opt-in";
        }
        else {
            s = "opt-out";
        }
        jsonObject4.put("ad_tracking_preference", (Object)s);
        jsonObject4.put("event_type", (Object)this.mEventType.name());
        jsonObject4.put("os_version", (Object)Build$VERSION.RELEASE);
        final String property = System.getProperty("http.agent");
        if (StringUtils.isNotEmpty(property)) {
            jsonObject4.put("user_agent", (Object)property);
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
}
