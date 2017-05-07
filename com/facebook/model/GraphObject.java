// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import org.json.JSONObject;
import java.util.Map;

public interface GraphObject
{
    Map<String, Object> asMap();
    
     <T extends GraphObject> T cast(final Class<T> p0);
    
    JSONObject getInnerJSONObject();
    
    Object getProperty(final String p0);
    
    void setProperty(final String p0, final Object p1);
}
