// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public class PlayerSetCurrentTime extends MdxMessage
{
    private static final String PROPERTY_time = "time";
    private int mTime;
    private String mXid;
    
    public PlayerSetCurrentTime(final String mXid, final int mTime) {
        super("PLAYER_SET_CURRENT_TIME");
        this.mXid = mXid;
        this.mTime = mTime;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("xid", (Object)this.mXid);
            this.mJson.put("time", this.mTime);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
