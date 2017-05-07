// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpError;

public class BackgroundEvent extends BaseNrdpEvent
{
    private static final String TAG = "nf-nccp";
    public static final String TYPE = "background";
    
    public BackgroundEvent(final NccpError nccpError) {
        super("background");
        JSONObject json;
        if ((json = nccpError.json) == null) {
            Log.w("nf-nccp", "JSON was null for " + nccpError);
            json = new JSONObject();
        }
        json.put("type", (Object)this.getName());
        this.json = json;
    }
}
