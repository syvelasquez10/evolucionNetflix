// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.device;

import android.os.Debug;
import android.os.Debug$MemoryInfo;
import android.app.ActivityManager$MemoryInfo;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.app.ActivityManager;
import android.content.Context;

public class MemoryWarning extends BaseDeviceEvent
{
    private static final String AVAILABLE_MEMORY = "avail";
    private static final String TOTAL_MEMORY = "total";
    public static final String TYPE = "nrdp-device-memory-warning";
    private static final String USED_MEMORY = "used";
    
    public MemoryWarning(Context json) {
        super("nrdp-device-memory-warning");
        final ActivityManager activityManager = (ActivityManager)json.getSystemService("activity");
        json = (Context)new JSONObject();
        while (true) {
            Label_0079: {
                if (activityManager != null) {
                    break Label_0079;
                }
                Log.e("nf_event", "Unable to find activity manager! Unable to get memory data!");
                try {
                    ((JSONObject)json).put("avail", 0);
                    ((JSONObject)json).put("used", 0);
                    ((JSONObject)json).put("total", 0);
                    this.json = (JSONObject)json;
                    return;
                }
                catch (Exception ex) {
                    Log.e("nf_event", "Failed to add property to JSON object", ex);
                    continue;
                }
            }
            final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
            activityManager.getMemoryInfo(activityManager$MemoryInfo);
            final Debug$MemoryInfo debug$MemoryInfo = new Debug$MemoryInfo();
            Debug.getMemoryInfo(debug$MemoryInfo);
            try {
                ((JSONObject)json).put("avail", activityManager$MemoryInfo.availMem);
                ((JSONObject)json).put("used", debug$MemoryInfo.getTotalSharedDirty() * 1024);
                ((JSONObject)json).put("total", debug$MemoryInfo.getTotalPss() * 1024);
                ((JSONObject)json).put("type", (Object)this.getType());
                continue;
            }
            catch (Exception ex2) {
                Log.e("nf_event", "Failed to add property to JSON object", ex2);
                continue;
            }
            continue;
        }
    }
}
