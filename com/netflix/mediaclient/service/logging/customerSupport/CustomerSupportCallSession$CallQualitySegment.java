// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerSupport;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;

public class CustomerSupportCallSession$CallQualitySegment
{
    private static final String QUALITY = "quality";
    private static final String SEGMENT_DURATION = "segmentDuration";
    public int duration;
    public CustomerServiceLogging$CallQuality quality;
    
    public CustomerSupportCallSession$CallQualitySegment(final int duration, final CustomerServiceLogging$CallQuality quality) {
        this.duration = duration;
        this.quality = quality;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("segmentDuration", this.duration);
        jsonObject.put("quality", (Object)this.quality);
        return jsonObject;
    }
}
