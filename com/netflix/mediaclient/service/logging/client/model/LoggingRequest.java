// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.util.Iterator;
import java.util.Collection;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
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
    
    public LoggingRequest(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface, final String locale) {
        this.appName = "android";
        this.time = System.currentTimeMillis();
        this.events = new ArrayList<Event>();
        this.version = new Version(context);
        this.device = new Device(serviceAgent$ConfigurationAgentInterface);
        this.netflixId = serviceAgent$UserAgentInterface.getUserCredentialRegistry().getNetflixID();
        this.locale = locale;
    }
    
    public static LoggingRequest createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final LoggingRequest loggingRequest = new LoggingRequest();
        loggingRequest.time = JsonUtils.getLong(jsonObject, "time", 0L);
        loggingRequest.netflixId = JsonUtils.getString(jsonObject, "netflixId", null);
        loggingRequest.locale = JsonUtils.getString(jsonObject, "locale", null);
        loggingRequest.version = Version.createInstance(JsonUtils.getJSONObject(jsonObject, "version", null));
        loggingRequest.device = Device.createInstance(JsonUtils.getJSONObject(jsonObject, "device", null));
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "events");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                final Event event = Event.createEvent(jsonArray.getJSONObject(i));
                if (event != null) {
                    loggingRequest.events.add(event);
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
    
    public JSONObject toJSONObject() {
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
