// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.controller;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class DialogResponse extends MdxMessage
{
    private static final String PROPERTY_data = "data";
    private static final String PROPERTY_uid = "uid";
    private String mData;
    private String mUid;
    
    public DialogResponse(final String mUid, final String mData) {
        super("DIALOG_RESPONSE");
        this.mUid = mUid;
        this.mData = mData;
        this.createObj();
    }
    
    private void createObj() {
        try {
            this.mJson.put("uid", (Object)this.mUid);
            this.mJson.put("data", (Object)this.mData);
        }
        catch (JSONException ex) {
            Log.e("nf_mdx", this.messageName() + " createObj failed " + ex);
        }
    }
}
