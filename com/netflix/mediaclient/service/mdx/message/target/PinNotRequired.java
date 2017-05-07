// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PinNotRequired extends MdxMessage
{
    private static String PROPERTY_isPinVerified;
    private boolean isPinVerified;
    
    static {
        PinNotRequired.PROPERTY_isPinVerified = "isPinVerified";
    }
    
    public PinNotRequired(final JSONObject jsonObject) throws JSONException {
        super("PIN_VERIFICATION_NOT_REQUIRED");
        this.isPinVerified = jsonObject.optBoolean(PinNotRequired.PROPERTY_isPinVerified);
    }
    
    public boolean getIsPinVerified() {
        return this.isPinVerified;
    }
}
