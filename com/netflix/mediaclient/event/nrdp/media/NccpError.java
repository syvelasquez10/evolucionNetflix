// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public abstract class NccpError extends BaseMediaEvent
{
    protected static final String ATTR_ACTION_ID = "actionId";
    protected static final String ATTR_BCP47 = "bcp47";
    protected static final String ATTR_END_POINT = "endpoint";
    protected static final String ATTR_MESSAGE = "message";
    protected static final String ATTR_ORIGIN = "origin";
    protected static final String ATTR_REASON_CODE = "reasonCode";
    public static final String ATTR_RESULT = "result";
    protected static final String ATTR_STACK = "stack";
    protected static final String ATTR_TRANSACTION = "transaction";
    protected static final String ATTR_TYPE = "type";
    public static final String ORIGIN_ACTION_ID = "handleActionId";
    public static final String ORIGIN_NETWORKING_ERROR = "reportNetworkingError";
    public static final String ORIGIN_STOP_TRANSACTION = "stopTransaction";
    public static final String RESULT_ACTION_ID = "ACTION_ID";
    public static final String RESULT_NETWORKING_ERROR = "NETWORK_ERROR";
    private static final String TAG = "nf-nccp";
    public static final String TRANSACTION_HEARTBEAT = "heartbeat";
    public static final String TRANSACTION_LOGBLOB = "logblob";
    public static final String TRANSACTION_PING = "ping";
    public static final String TRANSACTION_PLAYDATA = "playdata";
    public static final String TYPE = "nccperror";
    public static final String TYPE_BACKGROUND = "background";
    public static final String TYPE_NCCP = "Nccp";
    protected String origin;
    protected String result;
    protected String transaction;
    protected String type;
    
    public NccpError() {
        super("nccperror");
    }
    
    public NccpError(final JSONObject jsonObject) {
        super("nccperror", jsonObject);
    }
    
    public static NccpError toNccpError(final JSONObject jsonObject) {
        final String string = BaseNccpEvent.getString(jsonObject, "origin", null);
        if ("handleActionId".equalsIgnoreCase(string)) {
            Log.d("nf-nccp", "NCCP Action ID");
            return new NccpActionId(jsonObject);
        }
        if ("stopTransaction".equalsIgnoreCase(string)) {
            Log.d("nf-nccp", "NCCP stop transaction");
            return new NccpActionId(jsonObject);
        }
        if ("reportNetworkingError".equalsIgnoreCase(string)) {
            Log.d("nf-nccp", "NCCP networking error");
            return new NccpNetworkingError(jsonObject);
        }
        Log.w("nf-nccp", "Not handled NCCP event type!");
        return null;
    }
    
    public String getOrigin() {
        return this.origin;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public String getTransaction() {
        return this.transaction;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
}
