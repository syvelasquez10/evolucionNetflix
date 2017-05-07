// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.discovery;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class RemoteDeviceReadyEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_dialUsn = "dialUsn";
    private static final String ATTR_launchStatus = "launchStatus";
    private static final String ATTR_uuid = "uuid";
    private static final String DIALUSN_PREFIX = "uuid:";
    public static final Mdx.Events TYPE;
    private String mDialUsn;
    private int mLaunchStatus;
    private String mUuid;
    
    static {
        TYPE = Mdx.Events.mdx_discovery_remoteDeviceReady;
    }
    
    public RemoteDeviceReadyEvent(final JSONObject jsonObject) throws JSONException {
        super(RemoteDeviceReadyEvent.TYPE.getName(), jsonObject);
    }
    
    public String getDialUsn() {
        return this.mDialUsn;
    }
    
    public int getLaunchStatus() {
        return this.mLaunchStatus;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public String getUuid() {
        return this.mUuid;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.mDialUsn = BaseNccpEvent.getUrlDecodedString(jsonObject, "dialUsn", null);
        this.mUuid = BaseNccpEvent.getUrlDecodedString(jsonObject, "uuid", null);
        this.mLaunchStatus = BaseNccpEvent.getInt(jsonObject, "launchStatus", 0);
        if (this.mUuid == null && this.mDialUsn != null) {
            this.mUuid = this.mDialUsn.substring("uuid:".length());
        }
    }
}
