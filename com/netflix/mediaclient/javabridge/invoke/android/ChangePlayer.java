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
    private static final String PROPERTY_BITRATE_CAP = "bitrateCap";
    private static final String PROPERTY_PLAYER_TYPE = "playerType";
    private static final String TARGET = "android";
    
    public ChangePlayer(final PlayerType playerType, final int n) {
        super("android", "changePlayer");
        this.setArguments(playerType, n);
    }
    
    private void setArguments(final PlayerType ex, final int n) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "playerType";
            final JSONException ex2 = ex;
            final int n2 = ((PlayerType)ex2).getValue();
            jsonObject2.put(s, n2);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "bitrateCap";
            final int n3 = n;
            jsonObject3.put(s2, n3);
            final ChangePlayer changePlayer = this;
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = jsonObject4.toString();
            changePlayer.arguments = s3;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "playerType";
                final JSONException ex2 = ex;
                final int n2 = ((PlayerType)ex2).getValue();
                jsonObject2.put(s, n2);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "bitrateCap";
                final int n3 = n;
                jsonObject3.put(s2, n3);
                final ChangePlayer changePlayer = this;
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = jsonObject4.toString();
                changePlayer.arguments = s3;
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
