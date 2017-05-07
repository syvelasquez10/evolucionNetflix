// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Open extends BaseInvoke
{
    private static final String METHOD = "open";
    private static final String PARAMS = "params";
    private static final String PARAM_NET_TYPE = "nettype";
    private static final String PARAM_NET_TYPE_VALUE_MOBILE = "mobile";
    private static final String PARAM_NET_TYPE_VALUE_WIFI = "wifi";
    private static final String PARAM_NET_TYPE_VALUE_WIRED = "wired";
    private static final String PARAM_PIN_VERIFY_CAPABILITY = "pinCapableClient";
    private static final String PARAM_PLAY_CONTEXT = "uiplaycontext";
    private static final String PARAM_PLAY_CONTEXT_LIST_POS = "row";
    private static final String PARAM_PLAY_CONTEXT_REQ_ID = "request_id";
    private static final String PARAM_PLAY_CONTEXT_VIDEO_POS = "rank";
    private static final String PROPERTY_MOVIEID = "movieId";
    private static final String PROPERTY_TRACKID = "trackerId";
    private static final String TARGET = "media";
    
    public Open(final long n, final PlayContext playContext, final Open$NetType open$NetType) {
        super("media", "open");
        this.setArguments(n, playContext, open$NetType);
    }
    
    private void setArguments(final long n, final PlayContext playContext, final Open$NetType open$NetType) {
        if (playContext == null) {
            throw new IllegalArgumentException("Play context can not be null!");
        }
        while (true) {
            while (true) {
                JSONObject jsonObject2 = null;
                Label_0290: {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("movieId", n);
                        jsonObject.put("trackerId", playContext.getTrackId());
                        jsonObject2 = new JSONObject();
                        if (Open$NetType.mobile.equals(open$NetType)) {
                            jsonObject2.put("nettype", (Object)"mobile");
                        }
                        else {
                            if (!Open$NetType.wifi.equals(open$NetType)) {
                                break Label_0290;
                            }
                            jsonObject2.put("nettype", (Object)"wifi");
                        }
                        jsonObject2.put("pinCapableClient", true);
                        final JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("request_id", (Object)playContext.getRequestId());
                        jsonObject3.put("row", playContext.getListPos());
                        jsonObject3.put("rank", playContext.getVideoPos());
                        jsonObject2.put("uiplaycontext", (Object)jsonObject3);
                        if (Log.isLoggable("nf_invoke", 3)) {
                            Log.d("nf_invoke", String.format("DEBUG info: reqId %s, listPos %d,  videoPos %d", playContext.getRequestId(), playContext.getListPos(), playContext.getVideoPos()));
                        }
                        jsonObject.put("params", (Object)jsonObject2);
                        this.arguments = jsonObject.toString();
                        if (Log.isLoggable("nf_invoke", 3)) {
                            Log.d("nf_invoke", String.format("DEBUG info: sessionParams: %s", jsonObject.toString()));
                            return;
                        }
                        break;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                        return;
                    }
                }
                if (Open$NetType.wired.equals(open$NetType)) {
                    jsonObject2.put("nettype", (Object)"wired");
                    continue;
                }
                jsonObject2.put("nettype", (Object)"mobile");
                continue;
            }
        }
    }
}
