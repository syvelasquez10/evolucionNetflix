// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.abconfig;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class ABTestReceivedEvent extends DiscreteEvent
{
    private static final String AB_TEST_CELL_ID_KEY = "ABTestCellID";
    private static final String AB_TEST_ID_KEY = "ABTestID";
    private final int cellId;
    private final String testId;
    
    public ABTestReceivedEvent(final String testId, final int cellId) {
        this.testId = testId;
        this.cellId = cellId;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        data.put("ABTestID", (Object)this.testId);
        data.put("ABTestCellID", this.cellId);
        return data;
    }
}
