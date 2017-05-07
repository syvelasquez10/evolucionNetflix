// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public final class ChangePlayer extends BaseInvoke
{
    private static final String METHOD = "changePlayer";
    private static final String PROPERTY_PLAYER_TYPE = "playerType";
    private static final String TARGET = "android";
    
    public ChangePlayer(final PlayerType arguments) {
        super("android", "changePlayer");
        this.setArguments(arguments);
    }
    
    private void setArguments(final PlayerType playerType) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("playerType", playerType.getValue());
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
