// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.pair;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class RegPairResponseEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_cookies = "cookies";
    private static final String ATTR_errorCode = "errorCode";
    private static final String ATTR_errorDesc = "errorDesc";
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_remoteDevice = "remoteDevice";
    public static final Mdx.Events TYPE;
    private String cookies;
    private String errorCode;
    private String errorDesc;
    private String pairingContext;
    private String remoteDevice;
    
    static {
        TYPE = Mdx.Events.mdx_pair_regpairresponse;
    }
    
    public RegPairResponseEvent(final JSONObject jsonObject) throws JSONException {
        super(RegPairResponseEvent.TYPE.getName(), jsonObject);
    }
    
    public String getCookies() {
        return this.cookies;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorDesc() {
        return this.errorDesc;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public String getPairingContext() {
        return this.pairingContext;
    }
    
    public String getRemoteDevice() {
        return this.remoteDevice;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.errorCode = BaseNccpEvent.getString(jsonObject, "errorCode", null);
        this.errorDesc = BaseNccpEvent.getString(jsonObject, "errorDesc", null);
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.remoteDevice = BaseNccpEvent.getUrlDecodedString(jsonObject, "remoteDevice", null);
        this.cookies = BaseNccpEvent.getString(jsonObject, "cookies", null);
    }
}
