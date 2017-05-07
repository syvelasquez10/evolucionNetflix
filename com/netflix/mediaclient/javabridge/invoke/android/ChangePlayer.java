// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    private void setArguments(final PlayerType ex) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "playerType";
            final JSONException ex2 = ex;
            final int n = ((PlayerType)ex2).getValue();
            jsonObject2.put(s, n);
            final ChangePlayer changePlayer = this;
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = jsonObject3.toString();
            changePlayer.arguments = s2;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "playerType";
                final JSONException ex2 = ex;
                final int n = ((PlayerType)ex2).getValue();
                jsonObject2.put(s, n);
                final ChangePlayer changePlayer = this;
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = jsonObject3.toString();
                changePlayer.arguments = s2;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
}
