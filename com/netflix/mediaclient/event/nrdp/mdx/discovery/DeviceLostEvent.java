// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.discovery;

import org.json.JSONArray;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class DeviceLostEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_devicelist = "devicelist";
    public static final Mdx.Events TYPE;
    private String[] devices;
    
    static {
        TYPE = Mdx.Events.mdx_discovery_devicelost;
    }
    
    public DeviceLostEvent(final JSONObject jsonObject) throws JSONException {
        super(DeviceLostEvent.TYPE.getName(), jsonObject);
    }
    
    public String[] getDevices() {
        return this.devices;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("devicelist")) {
            final JSONArray jsonArray = jsonObject.getJSONArray("devicelist");
            if (jsonArray != null) {
                this.devices = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); ++i) {
                    this.devices[i] = RemoteDevice.decode(jsonArray.getString(i));
                }
                return;
            }
        }
        this.devices = new String[0];
    }
}
