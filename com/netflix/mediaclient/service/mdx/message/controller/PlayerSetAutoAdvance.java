// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerSetAutoAdvance extends MdxMessage
{
    private static final String PROPERTY_autoAdvanceMaxIncrement = "autoAdvanceMaxIncrement";
    private int mAutoAdvanceMaxIncrement;
    private String mXid;
    
    public PlayerSetAutoAdvance(final String mXid, final int mAutoAdvanceMaxIncrement) {
        super("PLAYER_SET_AUTO_ADVANCE");
        this.mXid = mXid;
        this.mAutoAdvanceMaxIncrement = mAutoAdvanceMaxIncrement;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("xid", (Object)this.mXid);
            this.mJson.put("autoAdvanceMaxIncrement", this.mAutoAdvanceMaxIncrement);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
