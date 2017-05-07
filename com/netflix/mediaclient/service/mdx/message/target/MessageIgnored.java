// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class MessageIgnored extends MdxMessage
{
    private static String PROPERTY_errorCode;
    private static String PROPERTY_errorDesc;
    private static String PROPERTY_transactionId;
    private String mErrorCode;
    private String mErrorDesc;
    private String mTransactionId;
    
    static {
        MessageIgnored.PROPERTY_transactionId = "transactionId";
        MessageIgnored.PROPERTY_errorCode = "errorCode";
        MessageIgnored.PROPERTY_errorDesc = "errorDesc";
    }
    
    public MessageIgnored(final JSONObject mJson) throws JSONException {
        super("MESSAGE_IGNORED");
        this.mJson = mJson;
        this.mTransactionId = mJson.getString(MessageIgnored.PROPERTY_transactionId);
        this.mErrorCode = mJson.getString(MessageIgnored.PROPERTY_errorCode);
        this.mErrorDesc = JsonUtils.getString(mJson, MessageIgnored.PROPERTY_errorDesc, null);
    }
    
    public String getErrorCode() {
        return this.mErrorCode;
    }
    
    public String getErrorDesc() {
        return this.mErrorDesc;
    }
    
    public String getTransactionId() {
        return this.mTransactionId;
    }
}
