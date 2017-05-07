// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.List;

public class PathMapCreator
{
    public static JsonObject create(final List<PQL> list) {
        new JsonObject().addProperty("_path", Boolean.valueOf(true));
        final JsonObject jsonObject = new JsonObject();
        final Iterator<PQL> iterator = list.iterator();
        while (iterator.hasNext()) {
            createPathMap(jsonObject, iterator.next(), 0);
        }
        return jsonObject;
    }
    
    private static void createPathMap(final JsonObject jsonObject, final PQL pql, final int n) {
        if (n == pql.getKeySegments().size()) {
            jsonObject.addProperty("_path", Boolean.valueOf(true));
        }
        else {
            final List<Object> value = pql.getKeySegments().get(n);
            if (value instanceof List) {
                for (final Object next : value) {
                    if (next instanceof Map) {
                        getOrCreateRangeArray(jsonObject, pql, n, next);
                    }
                    else {
                        createPathMap(getOrCreateJsonObj(jsonObject, next), pql, n + 1);
                    }
                }
            }
            else {
                if (value instanceof Map) {
                    getOrCreateRangeArray(jsonObject, pql, n, value);
                    return;
                }
                createPathMap(getOrCreateJsonObj(jsonObject, value), pql, n + 1);
            }
        }
    }
    
    private static JsonObject createRangeArrayNode(final JsonObject jsonObject, final PQL pql, final int n, final Map map) {
        final Integer n2 = map.get("from");
        final Integer n3 = map.get("to");
        Integer value = n2;
        if (n2 == null) {
            value = 0;
        }
        if (n3 == null) {
            throw new IllegalStateException("No 'to' parameter in map");
        }
        final JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("_rangeFrom", value);
        jsonObject2.addProperty("_rangeTo", n3);
        final JsonObject jsonObject3 = new JsonObject();
        jsonObject2.add("_rangeValue", jsonObject3);
        createPathMap(jsonObject3, pql, n + 1);
        return jsonObject2;
    }
    
    private static JsonObject getOrCreateJsonObj(final JsonObject jsonObject, final Object o) {
        JsonObject jsonObject2;
        if ((jsonObject2 = (JsonObject)jsonObject.get(o.toString())) == null) {
            jsonObject2 = new JsonObject();
            jsonObject.add(o.toString(), jsonObject2);
        }
        return jsonObject2;
    }
    
    private static void getOrCreateRangeArray(final JsonObject jsonObject, final PQL pql, final int n, final Object o) {
        JsonArray asJsonArray;
        if ((asJsonArray = jsonObject.getAsJsonArray("_ranges")) == null) {
            asJsonArray = new JsonArray();
            jsonObject.add("_ranges", asJsonArray);
        }
        asJsonArray.add(createRangeArrayNode(jsonObject, pql, n, (Map)o));
    }
}
