// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class Device
{
    public static final String ESN = "esn";
    public static final String MODEL = "model";
    public static final String TYPE = "type";
    @SerializedName("esn")
    @Since(1.0)
    private String esn;
    @SerializedName("model")
    @Since(1.0)
    private String model;
    @SerializedName("type")
    @Since(1.0)
    private String type;
    
    public Device() {
        this.type = EsnProvider.ESN_PREFIX;
    }
    
    Device(final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface) {
        this.type = EsnProvider.ESN_PREFIX;
        if (configurationAgentInterface == null) {
            throw new IllegalArgumentException("Configuration is null!");
        }
        this.model = configurationAgentInterface.getEsnProvider().getDeviceModel();
        this.esn = configurationAgentInterface.getEsnProvider().getEsn();
    }
    
    public static Device createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final Device device = new Device();
        device.esn = JsonUtils.getString(jsonObject, "esn", null);
        device.model = JsonUtils.getString(jsonObject, "model", null);
        device.type = JsonUtils.getString(jsonObject, "type", null);
        return device;
    }
    
    public String getEsn() {
        return this.esn;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public String getType() {
        return this.type;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.esn != null) {
            jsonObject.put("esn", (Object)this.esn);
        }
        if (this.model != null) {
            jsonObject.put("model", (Object)this.model);
        }
        if (this.type != null) {
            jsonObject.put("type", (Object)this.type);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "Device [esn=" + this.esn + ", type=" + this.type + ", model=" + this.model + "]";
    }
}
