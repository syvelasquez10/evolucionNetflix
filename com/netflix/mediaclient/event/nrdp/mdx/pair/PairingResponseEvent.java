// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.pair;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class PairingResponseEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_errorCode = "errorCode";
    private static final String ATTR_errorMessage = "errorMessage";
    private static final String ATTR_match = "match";
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_remoteDevice = "remoteDevice";
    public static final Mdx$Events TYPE;
    private String errorCode;
    private String errorDesc;
    private int match;
    private String pairingContext;
    private String remoteDevice;
    
    static {
        TYPE = Mdx$Events.mdx_pair_pairingresponse;
    }
    
    public PairingResponseEvent(final JSONObject jsonObject) {
        super(PairingResponseEvent.TYPE.getName(), jsonObject);
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
    
    public boolean isMatch() {
        return this.match > 0;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.errorCode = BaseNccpEvent.getString(jsonObject, "errorCode", null);
        this.errorDesc = BaseNccpEvent.getString(jsonObject, "errorMessage", null);
        this.match = BaseNccpEvent.getInt(jsonObject, "match", 0);
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.remoteDevice = BaseNccpEvent.getUrlDecodedString(jsonObject, "remoteDevice", null);
    }
}
