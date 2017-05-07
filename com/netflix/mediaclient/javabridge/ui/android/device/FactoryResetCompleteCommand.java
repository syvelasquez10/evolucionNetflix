// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android.device;

import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.BaseCommandCompletedEvent;

public class FactoryResetCompleteCommand extends BaseCommandCompletedEvent
{
    public static final String NAME = "factoryReset";
    public static final String OBJECT = "nrdp.device";
    
    public FactoryResetCompleteCommand(final JSONObject jsonObject) {
        super("factoryReset", jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.device";
    }
}
