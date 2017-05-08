// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.abconfig;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class ABTestLoadedEvent extends DiscreteEvent
{
    private static final String NAME = "abTestLoaded";
    private final int cellId;
    private final String testId;
    
    public ABTestLoadedEvent(final String testId, final int cellId) {
        this.testId = testId;
        this.cellId = cellId;
        this.category = "abTest";
        this.name = "abTestLoaded";
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("abTestID", (Object)this.testId);
        data.put("abTestCellID", this.cellId);
        return data;
    }
}
