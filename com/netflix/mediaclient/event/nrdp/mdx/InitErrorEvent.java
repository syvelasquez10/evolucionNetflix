// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class InitErrorEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_errorCode = "errorCode";
    private static final String ATTR_errorDesc = "errorDesc";
    public static final Mdx$Events TYPE;
    private int errorCode;
    private String errorDesc;
    
    static {
        TYPE = Mdx$Events.mdx_init;
    }
    
    public InitErrorEvent(final JSONObject jsonObject) {
        super(InitErrorEvent.TYPE.getName(), jsonObject);
    }
    
    public String getError() {
        return String.valueOf(this.errorCode);
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorDesc() {
        return this.errorDesc;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.errorCode = BaseNccpEvent.getInt(jsonObject, "errorCode", -1);
        this.errorDesc = BaseNccpEvent.getString(jsonObject, "errorDesc", null);
    }
}
