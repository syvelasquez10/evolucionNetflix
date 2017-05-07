// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public class DialogCancel extends MdxMessage
{
    private static String PROPERTY_uid;
    private String mUid;
    
    static {
        DialogCancel.PROPERTY_uid = "uid";
    }
    
    public DialogCancel(final JSONObject mJson) {
        super("DIALOG_CANCEL");
        this.mJson = mJson;
        this.mUid = mJson.getString(DialogCancel.PROPERTY_uid);
    }
    
    public String getUid() {
        return this.mUid;
    }
}
