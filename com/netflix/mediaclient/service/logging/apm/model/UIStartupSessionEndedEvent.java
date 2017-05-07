// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.AndroidUtils;
import org.json.JSONException;
import java.util.Iterator;
import java.util.HashMap;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public final class UIStartupSessionEndedEvent extends SessionEndedEvent
{
    public static final String ACTIVE_AB_TESTS = "activeABTests";
    public static final String DESTIONATION_VIEW = "destinationView";
    public static final String DISPLAY = "display";
    public static final String ERROR = "error";
    public static final String PLAYER_TYPE = "playerType";
    public static final String SEARCH_TERM = "searchTerm";
    public static final String SUCCESS = "success";
    public static final String TRACK_ID = "trackId";
    public static final String TRIGGER = "trigger";
    private static final String UI_STARTUP_SESSION_NAME = "uiStartup";
    public static final String VERSION = "version";
    public static final String VERSION_OS = "os";
    public static final String VOICE_ENABLED = "voiceEnabled";
    private Map<String, Integer> activeABTests;
    private IClientLogging.ModalView destinationView;
    private Display display;
    private UIError error;
    private PlayerType playerType;
    private String searchTerm;
    private boolean success;
    private String trackId;
    private ApplicationPerformanceMetricsLogging.UiStartupTrigger trigger;
    private boolean voiceEnabled;
    
    public UIStartupSessionEndedEvent(final long n, final ApplicationPerformanceMetricsLogging.UiStartupTrigger trigger, final IClientLogging.ModalView destinationView, final boolean success, final PlayerType playerType) {
        super("uiStartup", new DeviceUniqueId(), n);
        this.success = true;
        if (trigger == null) {
            throw new IllegalArgumentException("Trigger is null!");
        }
        this.trigger = trigger;
        if (destinationView == null) {
            throw new IllegalArgumentException("Destination is null!");
        }
        this.destinationView = destinationView;
        this.success = success;
        this.playerType = playerType;
    }
    
    public UIStartupSessionEndedEvent(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.success = true;
        jsonObject = JsonUtils.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "trigger", null);
            if (string != null) {
                this.trigger = ApplicationPerformanceMetricsLogging.UiStartupTrigger.valueOf(string);
            }
            final String string2 = JsonUtils.getString(jsonObject, "destinationView", null);
            if (string2 != null) {
                this.destinationView = IClientLogging.ModalView.valueOf(string2);
            }
            this.display = Display.createInstance(jsonObject.getJSONObject("display"));
            this.success = JsonUtils.getBoolean(jsonObject, "success", true);
            this.voiceEnabled = JsonUtils.getBoolean(jsonObject, "voiceEnabled", false);
            this.error = UIError.createInstance(JsonUtils.getJSONObject(jsonObject, "error", null));
            this.trackId = JsonUtils.getString(jsonObject, "trackId", null);
            this.searchTerm = JsonUtils.getString(jsonObject, "searchTerm", null);
            final JSONObject jsonObject2 = JsonUtils.getJSONObject(jsonObject, "activeABTests", null);
            if (jsonObject2 != null) {
                this.activeABTests = new HashMap<String, Integer>();
                final Iterator keys = jsonObject2.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    this.activeABTests.put(s, this.activeABTests.get(s));
                }
            }
            jsonObject = JsonUtils.getJSONObject(jsonObject, "version", null);
            if (jsonObject != null) {
                this.playerType = PlayerType.toPlayerType(JsonUtils.getInt(jsonObject, "playerType", -1));
            }
        }
    }
    
    public Map<String, Integer> getActiveABTests() {
        return this.activeABTests;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("trigger", (Object)this.trigger.name());
        data.put("destinationView", (Object)this.destinationView.name());
        data.put("success", this.success);
        data.put("voiceEnabled", this.voiceEnabled);
        if (this.display != null) {
            data.put("display", (Object)this.display.toJSONObject());
        }
        if (this.error != null) {
            data.put("error", (Object)this.error.toJSONObject());
        }
        if (this.trackId != null) {
            data.put("trackId", (Object)this.trackId);
        }
        if (this.searchTerm != null) {
            data.put("searchTerm", (Object)this.searchTerm);
        }
        if (this.activeABTests != null) {
            final JSONObject jsonObject = new JSONObject();
            data.put("activeABTests", (Object)jsonObject);
            for (final String s : this.activeABTests.keySet()) {
                jsonObject.put(s, (Object)this.activeABTests.get(s));
            }
        }
        final JSONObject jsonObject2 = new JSONObject();
        data.put("version", (Object)jsonObject2);
        jsonObject2.put("os", (Object)String.valueOf(AndroidUtils.getAndroidVersion()));
        if (this.playerType != null) {
            data.put("playerType", (Object)PlayerType.mapPlayerTypeForLogging(this.playerType));
        }
        return data;
    }
    
    public IClientLogging.ModalView getDestinationView() {
        return this.destinationView;
    }
    
    public Display getDisplay() {
        return this.display;
    }
    
    public UIError getError() {
        return this.error;
    }
    
    public String getSearchTerm() {
        return this.searchTerm;
    }
    
    public String getTrackId() {
        return this.trackId;
    }
    
    public ApplicationPerformanceMetricsLogging.UiStartupTrigger getTrigger() {
        return this.trigger;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    public boolean isVoiceEnabled() {
        return this.voiceEnabled;
    }
    
    public void setActiveABTests(final Map<String, Integer> activeABTests) {
        this.activeABTests = activeABTests;
    }
    
    public void setDestinationView(final IClientLogging.ModalView destinationView) {
        this.destinationView = destinationView;
    }
    
    public void setDisplay(final Display display) {
        this.display = display;
    }
    
    public void setError(final UIError error) {
        this.error = error;
    }
    
    public void setSearchTerm(final String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    public void setSuccess(final boolean success) {
        this.success = success;
    }
    
    public void setTrackId(final String trackId) {
        this.trackId = trackId;
    }
    
    public void setTrigger(final ApplicationPerformanceMetricsLogging.UiStartupTrigger trigger) {
        this.trigger = trigger;
    }
    
    public void setVoiceEnabled(final boolean voiceEnabled) {
        this.voiceEnabled = voiceEnabled;
    }
}
