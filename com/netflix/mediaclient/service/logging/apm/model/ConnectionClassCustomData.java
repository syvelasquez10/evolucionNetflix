// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONObject;
import com.facebook.network.connectionclass.ConnectionClassManager;

public class ConnectionClassCustomData
{
    public static final String BANDWIDTH_SESSION_AVERAGE = "bandwidthAvg";
    public static final String CONNECTION_CLASS = "connectionClass";
    private final double bwAverage;
    private final String connectionClass;
    
    public ConnectionClassCustomData() {
        this.bwAverage = ConnectionClassManager.getInstance().getDownloadKBitsPerSecond();
        this.connectionClass = ConnectionClassManager.getInstance().getCurrentBandwidthQuality().name();
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("bandwidthAvg", this.bwAverage);
        jsonObject.put("connectionClass", (Object)this.connectionClass);
        return jsonObject;
    }
}
