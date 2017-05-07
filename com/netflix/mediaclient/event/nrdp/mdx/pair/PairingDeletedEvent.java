// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.pair;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class PairingDeletedEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_pairingContext = "pairingContext";
    public static final Mdx.Events TYPE;
    private String pairingContext;
    
    static {
        TYPE = Mdx.Events.mdx_pair_pairingdeleted;
    }
    
    public PairingDeletedEvent(final JSONObject jsonObject) throws JSONException {
        super(PairingDeletedEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public String getPairingContext() {
        return this.pairingContext;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
    }
}
