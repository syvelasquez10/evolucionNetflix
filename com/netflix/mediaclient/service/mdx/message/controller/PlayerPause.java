// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerPause extends MdxMessage
{
    private String mXid;
    
    public PlayerPause(final String mXid) {
        super("PLAYER_PAUSE");
        this.mXid = mXid;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("xid", (Object)this.mXid);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
