// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import org.json.JSONArray;
import java.util.List;

public interface GraphObjectList<T> extends List<T>
{
    JSONArray getInnerJSONArray();
}
