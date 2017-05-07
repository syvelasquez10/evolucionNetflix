// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.common.PlayContext;

public class AuthorizationParams
{
    private static final String PARAM_NET_TYPE = "nettype";
    private static final String PARAM_NET_TYPE_VALUE_MOBILE = "mobile";
    private static final String PARAM_NET_TYPE_VALUE_WIFI = "wifi";
    private static final String PARAM_NET_TYPE_VALUE_WIRED = "wired";
    private static final String PARAM_PIN_VERIFY_CAPABILITY = "pinCapableClient";
    private static final String PARAM_PLAY_CONTEXT = "uiplaycontext";
    private static final String PARAM_PLAY_CONTEXT_LIST_POS = "row";
    private static final String PARAM_PLAY_CONTEXT_REQ_ID = "request_id";
    private static final String PARAM_PLAY_CONTEXT_VIDEO_POS = "rank";
    protected static final String TAG = "nf_invoke";
    private AuthorizationParams$NetType mNetType;
    private PlayContext mPlayContext;
    
    public AuthorizationParams(final PlayContext mPlayContext, final AuthorizationParams$NetType mNetType) {
        if (mPlayContext == null) {
            throw new IllegalArgumentException("Play context can not be null!");
        }
        this.mPlayContext = mPlayContext;
        this.mNetType = mNetType;
    }
    
    public AuthorizationParams$NetType getNetType() {
        return this.mNetType;
    }
    
    public JSONObject getParams() {
        JSONObject jsonObject;
        while (true) {
            try {
                JSONObject jsonObject2;
                while (true) {
                    jsonObject = new JSONObject();
                    while (true) {
                        Label_0239: {
                            try {
                                if (AuthorizationParams$NetType.mobile.equals(this.mNetType)) {
                                    jsonObject.put("nettype", (Object)"mobile");
                                }
                                else {
                                    if (!AuthorizationParams$NetType.wifi.equals(this.mNetType)) {
                                        break Label_0239;
                                    }
                                    jsonObject.put("nettype", (Object)"wifi");
                                }
                                jsonObject.put("pinCapableClient", true);
                                if (this.mPlayContext != null) {
                                    jsonObject2 = new JSONObject();
                                    jsonObject2.put("request_id", (Object)this.mPlayContext.getRequestId());
                                    jsonObject2.put("row", this.mPlayContext.getListPos());
                                    jsonObject2.put("rank", this.mPlayContext.getVideoPos());
                                    jsonObject.put("uiplaycontext", (Object)jsonObject2);
                                    if (Log.isLoggable()) {
                                        Log.d("nf_invoke", String.format("DEBUG info: reqId %s, listPos %d,  videoPos %d", this.mPlayContext.getRequestId(), this.mPlayContext.getListPos(), this.mPlayContext.getVideoPos()));
                                    }
                                }
                                if (Log.isLoggable()) {
                                    Log.d("nf_invoke", String.format("DEBUG info: sessionParams: %s", jsonObject.toString()));
                                    return jsonObject;
                                }
                                return jsonObject;
                            }
                            catch (JSONException ex) {}
                            break;
                        }
                        if (AuthorizationParams$NetType.wired.equals(this.mNetType)) {
                            jsonObject.put("nettype", (Object)"wired");
                            continue;
                        }
                        jsonObject.put("nettype", (Object)"mobile");
                        continue;
                    }
                }
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)jsonObject2);
                return jsonObject;
            }
            catch (JSONException jsonObject2) {
                jsonObject = null;
                continue;
            }
            break;
        }
        return jsonObject;
    }
    
    public PlayContext getPlayContext() {
        return this.mPlayContext;
    }
}
