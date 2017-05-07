// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerStop extends MdxMessage
{
    private static final String PROPERTY_disablePostPlay = "disablePostPlay";
    private String mXid;
    
    public PlayerStop(final String mXid) {
        super("PLAYER_STOP");
        this.mXid = mXid;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("xid", (Object)this.mXid);
            this.mJson.put("disablePostPlay", true);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
