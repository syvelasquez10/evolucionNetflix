// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.servicemgr.Trackable;
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
    
    public Open(final long n, final PlayContext playContext, final NetType netType) {
        super("media", "open");
        this.setArguments(n, playContext, netType);
    }
    
    private void setArguments(final long n, final PlayContext ex, final NetType netType) {
        if (ex == null) {
            throw new IllegalArgumentException("Play context can not be null!");
        }
        Label_0014: {
            break Label_0014;
            while (true) {
                try {
                    while (true) {
                        final JSONObject jsonObject = new JSONObject();
                        while (true) {
                            JSONObject jsonObject2 = null;
                            Label_0292: {
                                try {
                                    jsonObject.put("movieId", n);
                                    jsonObject.put("trackerId", ((Trackable)ex).getTrackId());
                                    jsonObject2 = new JSONObject();
                                    if (NetType.mobile.equals(netType)) {
                                        jsonObject2.put("nettype", (Object)"mobile");
                                    }
                                    else {
                                        if (!NetType.wifi.equals(netType)) {
                                            break Label_0292;
                                        }
                                        jsonObject2.put("nettype", (Object)"wifi");
                                    }
                                    jsonObject2.put("pinCapableClient", true);
                                    final JSONObject jsonObject3 = new JSONObject();
                                    jsonObject3.put("request_id", (Object)((Trackable)ex).getRequestId());
                                    jsonObject3.put("row", ((Trackable)ex).getListPos());
                                    jsonObject3.put("rank", ((PlayContext)ex).getVideoPos());
                                    jsonObject2.put("uiplaycontext", (Object)jsonObject3);
                                    if (Log.isLoggable("nf_invoke", 3)) {
                                        Log.d("nf_invoke", String.format("DEBUG info: reqId %s, listPos %d,  videoPos %d", ((Trackable)ex).getRequestId(), ((Trackable)ex).getListPos(), ((PlayContext)ex).getVideoPos()));
                                    }
                                    jsonObject.put("params", (Object)jsonObject2);
                                    this.arguments = jsonObject.toString();
                                    if (Log.isLoggable("nf_invoke", 3)) {
                                        Log.d("nf_invoke", String.format("DEBUG info: sessionParams: %s", jsonObject.toString()));
                                    }
                                    return;
                                }
                                catch (JSONException ex2) {}
                                break;
                            }
                            if (NetType.wired.equals(netType)) {
                                jsonObject2.put("nettype", (Object)"wired");
                                continue;
                            }
                            jsonObject2.put("nettype", (Object)"mobile");
                            continue;
                        }
                    }
                    Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public enum NetType
    {
        mobile, 
        wifi, 
        wired;
    }
}
