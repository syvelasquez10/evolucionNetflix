// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerStateChanged extends MdxMessage
{
    private PlayerState mPlayerState;
    
    public PlayerStateChanged(final JSONObject mJson) throws JSONException {
        super("PLAYER_STATE_CHANGED");
        this.mJson = mJson;
        this.mPlayerState = new PlayerState(mJson);
    }
    
    public PlayerState getPlayerState() {
        return this.mPlayerState;
    }
}
