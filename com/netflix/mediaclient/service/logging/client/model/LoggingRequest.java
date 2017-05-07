// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.util.Iterator;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class LoggingRequest
{
    public static final String APP_NAME = "appName";
    public static final String DEVICE = "device";
    public static final String EVENTS = "events";
    public static final String LOCALE = "locale";
    public static final String NETFLIX_ID = "netflixId";
    public static final String TIME = "time";
    public static final String VERSION = "version";
    @SerializedName("appName")
    @Since(1.0)
    private String appName;
    @SerializedName("device")
    @Since(1.0)
    private Device device;
    @SerializedName("events")
    @Since(1.0)
    private List<Event> events;
    @SerializedName("locale")
    @Since(1.0)
    private String locale;
    @SerializedName("netflixId")
    @Since(1.0)
    private String netflixId;
    @SerializedName("time")
    @Since(1.0)
    private long time;
    @SerializedName("version")
    @Since(1.0)
    private Version version;
    
    public LoggingRequest() {
        this.appName = "android";
        this.time = System.currentTimeMillis();
        this.events = new ArrayList<Event>();
    }
    
    public LoggingRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final ServiceAgent.UserAgentInterface userAgentInterface, final String locale) {
        this.appName = "android";
        this.time = System.currentTimeMillis();
        this.events = new ArrayList<Event>();
        this.version = new Version(context);
        this.device = new Device(configurationAgentInterface);
        this.netflixId = userAgentInterface.getUserCredentialRegistry().getNetflixID();
        this.locale = locale;
    }
    
    public static LoggingRequest createInstance(final JSONObject jsonObject) throws JSONException {
        LoggingRequest loggingRequest;
        if (jsonObject == null) {
            loggingRequest = null;
        }
        else {
            final LoggingRequest loggingRequest2 = new LoggingRequest();
            loggingRequest2.time = JsonUtils.getLong(jsonObject, "time", 0L);
            loggingRequest2.netflixId = JsonUtils.getString(jsonObject, "netflixId", null);
            loggingRequest2.locale = JsonUtils.getString(jsonObject, "locale", null);
            loggingRequest2.version = Version.createInstance(JsonUtils.getJSONObject(jsonObject, "version", null));
            loggingRequest2.device = Device.createInstance(JsonUtils.getJSONObject(jsonObject, "device", null));
            final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "events");
            loggingRequest = loggingRequest2;
            if (jsonArray != null) {
                int n = 0;
                while (true) {
                    loggingRequest = loggingRequest2;
                    if (n >= jsonArray.length()) {
                        break;
                    }
                    final Event event = Event.createEvent(jsonArray.getJSONObject(n));
                    if (event != null) {
                        loggingRequest2.events.add(event);
                    }
                    ++n;
                }
            }
        }
        return loggingRequest;
    }
    
    public void addAllEvent(final List<Event> list) {
        this.events.addAll(list);
    }
    
    public void addEvent(final Event event) {
        this.events.add(event);
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public Device getDevice() {
        return this.device;
    }
    
    public String getLocale() {
        return this.locale;
    }
    
    public String getNetflixId() {
        return this.netflixId;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public Version getVersion() {
        return this.version;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName", (Object)this.appName);
        if (this.version != null) {
            jsonObject.put("version", (Object)this.version.toJSONObject());
        }
        if (this.device != null) {
            jsonObject.put("device", (Object)this.device.toJSONObject());
        }
        jsonObject.put("time", this.time);
        if (this.netflixId != null) {
            jsonObject.put("netflixId", (Object)this.netflixId);
        }
        if (this.locale != null) {
            jsonObject.put("locale", (Object)this.locale);
        }
        if (this.events != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("events", (Object)jsonArray);
            final Iterator<Event> iterator = this.events.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSONObject());
            }
        }
        return jsonObject;
    }
}
