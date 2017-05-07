// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerSetVolume extends MdxMessage
{
    private static final String PROPERTY_previous = "previous";
    public static final String PROPERTY_volume = "volume";
    private Integer mPrevious;
    private int mVolume;
    private String mXid;
    
    public PlayerSetVolume(final String mXid, final int mVolume) {
        super("PLAYER_SET_VOLUME");
        this.mXid = mXid;
        this.mVolume = mVolume;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("xid", (Object)this.mXid);
            this.mJson.put("volume", this.mVolume);
            if (this.mPrevious != null) {
                this.mJson.put("previous", (Object)this.mPrevious);
            }
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
