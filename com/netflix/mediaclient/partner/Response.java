// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.ComponentName;

public interface Response
{
    String getId();
    
    ComponentName getResponder();
    
    String getService();
    
    JSONObject toJson() throws JSONException;
}
