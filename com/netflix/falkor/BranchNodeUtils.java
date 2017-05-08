// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.HashMap;
import java.util.Date;
import java.io.IOException;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParser;
import com.netflix.mediaclient.Log;

public class BranchNodeUtils
{
    private static final boolean ENABLE_VERBOSE_LOGGING;
    private static final String TAG = "BranchNodeUtils";
    
    static {
        if (Log.isLoggable()) {}
        ENABLE_VERBOSE_LOGGING = false;
    }
    
    public static List<String> getAsStringArray(final JsonParser jsonParser) {
        final ArrayList<String> list = new ArrayList<String>();
        if (jsonParser.getCurrentToken().equals(JsonToken.START_ARRAY)) {
            for (JsonToken jsonToken = jsonParser.nextToken(); !JsonToken.END_ARRAY.equals(jsonToken); jsonToken = jsonParser.nextToken()) {
                list.add(jsonParser.getValueAsString());
            }
        }
        return list;
    }
    
    public static Object merge(final BranchNode branchNode, final JsonParser jsonParser) {
        return merge(branchNode, jsonParser, jsonParser.nextToken(), 0);
    }
    
    public static Object merge(final BranchNode branchNode, final JsonParser jsonParser, JsonToken jsonToken, final int n) {
        BranchNode branchNode2;
        if (jsonToken != null && JsonToken.START_OBJECT.equals(jsonToken)) {
            if (BranchNodeUtils.ENABLE_VERBOSE_LOGGING) {
                Log.v("BranchNodeUtils", "Start Object");
            }
            jsonToken = jsonParser.nextToken();
            while (true) {
                branchNode2 = branchNode;
                if (jsonToken == null) {
                    break;
                }
                branchNode2 = branchNode;
                if (jsonToken.equals(JsonToken.END_OBJECT)) {
                    break;
                }
                final String currentName = jsonParser.getCurrentName();
                if (BranchNodeUtils.ENABLE_VERBOSE_LOGGING) {
                    Log.v("BranchNodeUtils", pad(n, currentName));
                }
                Object orCreate = branchNode.getOrCreate(currentName);
                int n2;
                Object o;
                if (orCreate instanceof Sentinel) {
                    Object orCreate2 = orCreate;
                    if (((Sentinel)orCreate).getValue() == null) {
                        branchNode.remove(currentName);
                        orCreate2 = branchNode.getOrCreate(currentName);
                    }
                    if (orCreate2 instanceof Sentinel) {
                        final Object value = ((Sentinel<Object>)orCreate2).getValue();
                        n2 = 1;
                        orCreate = orCreate2;
                        o = value;
                    }
                    else {
                        final Sentinel<Object> sentinel = (Sentinel<Object>)orCreate2;
                        final Sentinel<Object> sentinel2 = (Sentinel<Object>)orCreate2;
                        n2 = 0;
                        o = sentinel;
                        orCreate = sentinel2;
                    }
                }
                else {
                    o = orCreate;
                    n2 = 0;
                }
                if (o instanceof Ref) {
                    final Ref ref = (Ref)o;
                    final JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken.equals(JsonToken.VALUE_NULL)) {
                        branchNode.set(currentName, Undefined.getInstance());
                    }
                    else if (nextToken.equals(JsonToken.START_ARRAY)) {
                        ref.setRefPath(readPQL(jsonParser, jsonParser.nextToken()));
                    }
                    else if (nextToken.equals(JsonToken.START_OBJECT)) {
                        jsonParser.clearCurrentToken();
                        branchNode.set(currentName, Undefined.getInstance());
                        readValue(jsonParser, nextToken, true);
                    }
                }
                else if (o instanceof BranchNode) {
                    if (merge((BranchNode)o, jsonParser, jsonParser.nextToken(), n + 1) != o) {
                        if (BranchNodeUtils.ENABLE_VERBOSE_LOGGING) {
                            Log.v("BranchNodeUtils", pad(n, currentName));
                        }
                        branchNode.set(currentName, o);
                    }
                }
                else if (o instanceof JsonMerger) {
                    merge((JsonMerger)o, jsonParser, jsonParser.nextToken(), true, n + 1);
                    if (n2 == 0) {
                        orCreate = o;
                    }
                    branchNode.set(currentName, orCreate);
                }
                else {
                    readValue(jsonParser, jsonParser.nextToken(), true);
                }
                jsonToken = jsonParser.nextToken();
            }
        }
        else {
            branchNode2 = null;
        }
        return branchNode2;
    }
    
    public static Object merge(JsonMerger jsonMerger, final JsonParser jsonParser, final JsonToken jsonToken, final boolean b, final int n) {
        if (jsonToken.equals(JsonToken.VALUE_NULL)) {
            jsonMerger = null;
        }
        else if (jsonToken.equals(JsonToken.START_OBJECT)) {
            JsonToken jsonToken2 = jsonParser.nextToken();
            Sentinel<Object> sentinel = null;
            while (jsonToken2 != null && !jsonToken2.equals(JsonToken.END_OBJECT)) {
                if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                    throw new IOException("Expected FIELD_NAME and it was " + jsonParser.getCurrentToken());
                }
                final String currentName = jsonParser.getCurrentName();
                if (BranchNodeUtils.ENABLE_VERBOSE_LOGGING) {
                    Log.v("BranchNodeUtils", pad(n, currentName));
                }
                if (sentinel != null) {
                    if (currentName == "$expires") {
                        sentinel.setExpires(new Date((long)readValue(jsonParser, jsonToken2, false)));
                    }
                    else if (currentName == "value") {
                        sentinel.setValue(merge(jsonMerger, jsonParser, jsonParser.nextToken(), false, n));
                    }
                    jsonToken2 = jsonParser.nextToken();
                }
                else {
                    final JsonToken nextToken = jsonParser.nextToken();
                    if (b && currentName == "_sentinel") {
                        sentinel = new Sentinel<Object>(null);
                    }
                    else if (!jsonMerger.set(currentName, jsonParser)) {
                        readValue(jsonParser, nextToken, false);
                    }
                    jsonToken2 = jsonParser.nextToken();
                }
            }
            if (sentinel != null) {
                return sentinel;
            }
        }
        else {
            if (jsonToken.equals(JsonToken.START_ARRAY)) {
                mergeArrayValue(jsonMerger, jsonParser, jsonParser.nextToken());
                return jsonMerger;
            }
            throw new IOException("Value that is not null, object, or array found for JsonMerger");
        }
        return jsonMerger;
    }
    
    public static void mergeArrayValue(final JsonMerger jsonMerger, final JsonParser jsonParser, JsonToken nextToken) {
        while (!JsonToken.END_ARRAY.equals(nextToken)) {
            jsonMerger.set(new Integer(0).toString(), jsonParser);
            nextToken = jsonParser.nextToken();
        }
    }
    
    private static String pad(final int n, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        sb.append(s);
        return sb.toString();
    }
    
    public static PQL readPQL(final JsonParser jsonParser, JsonToken nextToken) {
        final ArrayList<String> list = new ArrayList<String>();
        while (nextToken != null && !nextToken.equals(JsonToken.END_ARRAY)) {
            if (nextToken.equals(JsonToken.VALUE_STRING)) {
                list.add(jsonParser.getValueAsString());
            }
            else if (nextToken.equals(JsonToken.VALUE_FALSE) || nextToken.equals(JsonToken.VALUE_TRUE)) {
                list.add((String)jsonParser.getValueAsBoolean());
            }
            else if (nextToken.equals(JsonToken.VALUE_NUMBER_INT)) {
                list.add((String)jsonParser.getValueAsInt());
            }
            else if (nextToken.equals(JsonToken.VALUE_NUMBER_FLOAT)) {
                list.add((String)jsonParser.getValueAsDouble());
            }
            else if (nextToken.equals(JsonToken.VALUE_NULL)) {
                list.add(null);
            }
            nextToken = jsonParser.nextToken();
        }
        return PQL.fromList(list);
    }
    
    public static Object readValue(final JsonParser jsonParser, JsonToken jsonToken, final boolean b) {
        Object valueAsString = null;
        if (jsonToken.equals(JsonToken.VALUE_STRING)) {
            valueAsString = jsonParser.getValueAsString();
        }
        else {
            if (jsonToken.equals(JsonToken.VALUE_FALSE) || jsonToken.equals(JsonToken.VALUE_TRUE)) {
                return jsonParser.getValueAsBoolean();
            }
            if (jsonToken.equals(JsonToken.VALUE_NUMBER_INT)) {
                return jsonParser.getValueAsInt();
            }
            if (jsonToken.equals(JsonToken.VALUE_NUMBER_FLOAT)) {
                return jsonParser.getValueAsDouble();
            }
            if (jsonToken.equals(JsonToken.VALUE_NULL)) {
                if (!b) {
                    return Undefined.getInstance();
                }
            }
            else if (jsonToken.equals(JsonToken.START_ARRAY)) {
                final ArrayList<Object> list = new ArrayList<Object>();
                jsonToken = jsonParser.nextToken();
                while (true) {
                    valueAsString = list;
                    if (jsonToken == null) {
                        break;
                    }
                    valueAsString = list;
                    if (jsonToken.equals(JsonToken.END_ARRAY)) {
                        break;
                    }
                    list.add(readValue(jsonParser, jsonToken, b));
                    jsonToken = jsonParser.nextToken();
                }
            }
            else if (jsonToken.equals(JsonToken.START_OBJECT)) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                jsonToken = jsonParser.nextToken();
                while (true) {
                    valueAsString = hashMap;
                    if (jsonToken == null) {
                        break;
                    }
                    valueAsString = hashMap;
                    if (jsonToken.equals(JsonToken.END_OBJECT)) {
                        break;
                    }
                    hashMap.put(jsonParser.getCurrentName(), readValue(jsonParser, jsonParser.nextToken(), b));
                    jsonToken = jsonParser.nextToken();
                }
            }
        }
        return valueAsString;
    }
}
