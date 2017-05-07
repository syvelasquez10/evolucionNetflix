// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android.registration;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.BaseCommandCompletedEvent;

public class DeactivateCompleteCommand extends BaseCommandCompletedEvent
{
    public static final String NAME = "deactivate";
    public static final String OBJECT = "nrdp.registration";
    
    public DeactivateCompleteCommand(final JSONObject jsonObject) throws JSONException {
        super("deactivate", jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.registration";
    }
}
