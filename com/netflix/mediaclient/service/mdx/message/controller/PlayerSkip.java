// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerSkip extends MdxMessage
{
    private static final String PROPERTY_seconds = "seconds";
    private String mXid;
    private int seconds;
    
    public PlayerSkip(final String mXid, final int seconds) {
        super("PLAYER_SKIP");
        this.mXid = mXid;
        this.seconds = seconds;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("seconds", this.seconds);
            this.mJson.put("xid", (Object)this.mXid);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
