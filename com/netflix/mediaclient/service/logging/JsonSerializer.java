// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonSerializer
{
    JSONObject toJSONObject() throws JSONException;
}
