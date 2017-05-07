// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.discovery;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class DeviceFoundEvent extends JsonBaseNccpEvent
{
    public static final Mdx.Events TYPE;
    private RemoteDevice device;
    
    static {
        TYPE = Mdx.Events.mdx_discovery_devicefound;
    }
    
    public DeviceFoundEvent(final JSONObject jsonObject) throws JSONException {
        super(DeviceFoundEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public RemoteDevice getRemoteDevice() {
        return this.device;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.device = RemoteDevice.toRemoteDevice(jsonObject);
    }
}
