// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerMessageIgnored extends MdxMessage
{
    private static String PROPERTY_errorCode;
    private static String PROPERTY_errorDesc;
    private static String PROPERTY_transactionId;
    private String mErrorCode;
    private String mErrorDesc;
    private String mTransactionId;
    
    static {
        PlayerMessageIgnored.PROPERTY_transactionId = "transactionId";
        PlayerMessageIgnored.PROPERTY_errorCode = "errorCode";
        PlayerMessageIgnored.PROPERTY_errorDesc = "errorDesc";
    }
    
    public PlayerMessageIgnored(final String mTransactionId, final String mErrorCode, final String mErrorDesc) {
        super("MESSAGE_IGNORED");
        this.mTransactionId = mTransactionId;
        this.mErrorCode = mErrorCode;
        this.mErrorDesc = mErrorDesc;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put(PlayerMessageIgnored.PROPERTY_transactionId, (Object)this.mTransactionId);
            this.mJson.put(PlayerMessageIgnored.PROPERTY_errorCode, (Object)this.mErrorCode);
            this.mJson.put(PlayerMessageIgnored.PROPERTY_errorDesc, (Object)this.mErrorDesc);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
