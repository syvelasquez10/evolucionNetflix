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
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.netflix.mediaclient.service.logging.JsonSerializer;

public abstract class Event implements JsonSerializer
{
    public static final String ACTIVE_SESSIONS = "activeSessions";
    public static final String CATEGORY = "category";
    public static final String DATA = "data";
    public static final String DATA_CONTEXT = "dataContex";
    public static final String MODAL_VIEW = "modalView";
    public static final String NAME = "name";
    public static final String SEQUENCE = "sequence";
    public static final String SEVERITY = "severity";
    public static final String TIME = "time";
    public static final String TYPE = "type";
    public static final String UI_MODE = "uiMode";
    public static final String UPTIME = "uptime";
    @SerializedName("activeSessions")
    @Since(1.0)
    protected List<SessionKey> activeSessions;
    @SerializedName("category")
    @Since(1.0)
    protected String category;
    @SerializedName("dataContex")
    @Since(1.0)
    protected DataContext dataContex;
    @SerializedName("modalView")
    @Since(1.0)
    protected IClientLogging.ModalView modalView;
    @SerializedName("name")
    @Since(1.0)
    protected String name;
    @SerializedName("sequence")
    @Since(1.0)
    protected long sequence;
    @SerializedName("severity")
    @Since(1.0)
    protected Severity severity;
    @SerializedName("time")
    @Since(1.0)
    protected long time;
    @SerializedName("type")
    @Since(1.0)
    protected EventType type;
    @SerializedName("uiMode")
    @Since(1.0)
    protected UIMode uiMode;
    @SerializedName("uptime")
    @Since(1.0)
    protected long uptime;
    
    public Event() {
        this.activeSessions = new ArrayList<SessionKey>();
        this.time = System.currentTimeMillis();
        this.severity = Severity.debug;
    }
    
    public Event(final JSONObject jsonObject) throws JSONException {
        this.activeSessions = new ArrayList<SessionKey>();
        this.time = System.currentTimeMillis();
        this.severity = Severity.debug;
        final String string = JsonUtils.getString(jsonObject, "type", null);
        if (string != null) {
            this.type = EventType.valueOf(string);
        }
        final String string2 = JsonUtils.getString(jsonObject, "severity", null);
        if (string2 != null) {
            this.severity = Severity.valueOf(string2);
        }
        final String string3 = JsonUtils.getString(jsonObject, "uiMode", null);
        if (string3 != null) {
            this.uiMode = UIMode.valueOf(string3);
        }
        final String string4 = JsonUtils.getString(jsonObject, "modalView", null);
        if (string4 != null) {
            this.modalView = IClientLogging.ModalView.valueOf(string4);
        }
        this.category = JsonUtils.getString(jsonObject, "category", null);
        this.name = JsonUtils.getString(jsonObject, "name", null);
        this.time = JsonUtils.getLong(jsonObject, "time", 0L);
        this.sequence = JsonUtils.getLong(jsonObject, "sequence", 0L);
        this.uptime = JsonUtils.getLong(jsonObject, "uptime", 0L);
        this.dataContex = DataContext.createInstance(JsonUtils.getJSONObject(jsonObject, "dataContex", null));
        final JSONArray jsonArray = JsonUtils.getJSONArray(jsonObject, "activeSessions");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                this.activeSessions.add(SessionKey.createInstance(jsonArray.getJSONObject(i)));
            }
        }
    }
    
    public static Event createEvent(final JSONObject jsonObject) {
        return null;
    }
    
    public void addActiveSession(final SessionKey sessionKey) {
        this.activeSessions.add(sessionKey);
    }
    
    public void addAllActiveSession(final List<SessionKey> list) {
        this.activeSessions.addAll(list);
    }
    
    public String getCategory() {
        return this.category;
    }
    
    protected JSONObject getData() throws JSONException {
        return null;
    }
    
    public DataContext getDataContext() {
        return this.dataContex;
    }
    
    public IClientLogging.ModalView getModalView() {
        return this.modalView;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getSequence() {
        return this.sequence;
    }
    
    public Severity getSeverity() {
        return this.severity;
    }
    
    long getTime() {
        return this.time;
    }
    
    public EventType getType() {
        return this.type;
    }
    
    public UIMode getUIMode() {
        return this.uiMode;
    }
    
    public UIMode getUiMode() {
        return this.uiMode;
    }
    
    public long getUptime() {
        return this.uptime;
    }
    
    public void setCategory(final String category) {
        this.category = category;
    }
    
    public void setDataContext(final DataContext dataContex) {
        this.dataContex = dataContex;
    }
    
    public void setModalView(final IClientLogging.ModalView modalView) {
        this.modalView = modalView;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }
    
    public void setSeverity(final Severity severity) {
        this.severity = severity;
    }
    
    public void setTime(final long time) {
        this.time = time;
    }
    
    public void setUiMode(final UIMode uiMode) {
        this.uiMode = uiMode;
    }
    
    public void setUptime(final long uptime) {
        this.uptime = uptime;
    }
    
    @Override
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.category != null) {
            jsonObject.put("category", (Object)this.category);
        }
        if (this.name != null) {
            jsonObject.put("name", (Object)this.name);
        }
        if (this.type != null) {
            jsonObject.put("type", (Object)this.type.name());
        }
        if (this.uiMode != null) {
            jsonObject.put("uiMode", (Object)this.uiMode.name());
        }
        if (this.modalView != null) {
            jsonObject.put("modalView", (Object)this.modalView.name());
        }
        if (this.severity != null) {
            jsonObject.put("severity", (Object)this.severity.name());
        }
        if (this.dataContex != null) {
            jsonObject.put("dataContex", (Object)this.dataContex.toJSONObject());
        }
        final JSONObject data = this.getData();
        if (data != null) {
            jsonObject.put("data", (Object)data);
        }
        jsonObject.put("time", this.time);
        jsonObject.put("sequence", this.sequence);
        jsonObject.put("uptime", this.uptime);
        if (this.activeSessions != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("activeSessions", (Object)jsonArray);
            final Iterator<SessionKey> iterator = this.activeSessions.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSONArray());
            }
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "Event [type=" + this.type + ", category=" + this.category + ", name=" + this.name + ", time=" + this.time + ", sequence=" + this.sequence + ", severity=" + this.severity + ", uiMode=" + this.uiMode + ", uptime=" + this.uptime + ", modalView=" + this.modalView + ", dataContex=" + this.dataContex + "]";
    }
}
