// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx;

import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.BaseUIEvent;

public class InterfaceChangedEvent extends BaseUIEvent
{
    private static final String ATTR_connected = "connected";
    private static final String ATTR_interface = "interface";
    private static final String ATTR_ipaddress = "ipaddress";
    private static final String ATTR_ssid = "ssid";
    public static final String TYPE = "interfacechange";
    private boolean connected;
    private String ipAddress;
    private String networkInterface;
    private String ssid;
    
    public InterfaceChangedEvent(final boolean connected, final String networkInterface, final String ipAddress, final String ssid) {
        super("interfacechange");
        this.connected = connected;
        this.networkInterface = networkInterface;
        this.ipAddress = ipAddress;
        if (ssid != null) {
            this.ssid = ssid;
            return;
        }
        this.ssid = "";
    }
    
    @Override
    public JSONObject getData() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("connected", this.connected);
        jsonObject.put("interface", (Object)this.networkInterface);
        jsonObject.put("ipaddress", (Object)this.ipAddress);
        jsonObject.put("ssid", (Object)this.ssid);
        jsonObject.put("type", (Object)this.getType());
        return jsonObject;
    }
    
    @Override
    public String getName() {
        return "IMdx";
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
}
