// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public class PlayerCurrentState extends MdxMessage
{
    private PlayerState mPlayerState;
    
    public PlayerCurrentState(final JSONObject mJson) throws JSONException {
        super("PLAYER_CURRENT_STATE");
        this.mJson = mJson;
        this.mPlayerState = new PlayerState(mJson);
    }
    
    public PlayerState getPlayerState() {
        return this.mPlayerState;
    }
}
