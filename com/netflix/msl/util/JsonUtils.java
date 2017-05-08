// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import com.netflix.android.org.json.JSONException;
import com.netflix.android.org.json.JSONString;
import com.netflix.android.org.json.JSONObject;
import com.netflix.android.org.json.JSONArray;
import java.util.Collection;
import java.nio.charset.Charset;

public class JsonUtils
{
    private static final char CHAR_EQUALS = '=';
    private static final char CHAR_MINUS = '-';
    private static final char CHAR_PLUS = '+';
    private static final char CHAR_SLASH = '/';
    private static final char CHAR_UNDERSCORE = '_';
    private static final Charset UTF_8;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
    
    public static byte[] b64urlDecode(String replace) {
        replace = replace.replace('-', '+').replace('_', '/');
        try {
            final int n = 4 - replace.length() % 4;
            if (n == 0 || n == 4) {
                return Base64.decode(replace);
            }
            final StringBuilder sb = new StringBuilder(replace);
            for (int i = 0; i < n; ++i) {
                sb.append('=');
            }
            return Base64.decode(sb.toString());
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public static String b64urlDecodeToString(final String s) {
        return new String(b64urlDecode(s), JsonUtils.UTF_8);
    }
    
    public static String b64urlEncode(final String s) {
        return b64urlEncode(s.getBytes(JsonUtils.UTF_8));
    }
    
    public static String b64urlEncode(final byte[] array) {
        final String replace = Base64.encode(array).replace('+', '-').replace('/', '_');
        final int index = replace.indexOf(61);
        String substring = replace;
        if (index != -1) {
            substring = replace.substring(0, index);
        }
        return substring;
    }
    
    public static JSONArray createArray(final Collection<?> collection) {
        final JSONArray jsonArray = new JSONArray();
        for (final Object next : collection) {
            if (next instanceof Boolean || next instanceof JSONArray || next instanceof JSONObject || next instanceof Number || next instanceof String || next == JSONObject.NULL) {
                jsonArray.put(next);
            }
            else if (next instanceof JSONString) {
                jsonArray.put(new JSONObject(((JSONString)next).toJSONString()));
            }
            else {
                if (!(next instanceof Enum)) {
                    throw new JSONException("Class " + ((Enum)next).getClass().getName() + " is not JSON-compatible.");
                }
                jsonArray.put(((Enum)next).name());
            }
        }
        return jsonArray;
    }
    
    public static boolean equalSets(final JSONArray jsonArray, final JSONArray jsonArray2) {
        int i = 0;
        final boolean b = false;
        boolean b2;
        if (jsonArray == jsonArray2) {
            b2 = true;
        }
        else {
            b2 = b;
            if (jsonArray != null) {
                b2 = b;
                if (jsonArray2 != null) {
                    b2 = b;
                    if (jsonArray.length() == jsonArray2.length()) {
                        final HashSet<Object> set = new HashSet<Object>();
                        final HashSet<Object> set2 = new HashSet<Object>();
                        while (i < jsonArray.length()) {
                            set.add(jsonArray.get(i));
                            set2.add(jsonArray2.get(i));
                            ++i;
                        }
                        return set.equals(set2);
                    }
                }
            }
        }
        return b2;
    }
    
    public static boolean equals(final JSONArray jsonArray, final JSONArray jsonArray2) {
        final boolean b = false;
        boolean b2;
        if (jsonArray == jsonArray2) {
            b2 = true;
        }
        else {
            b2 = b;
            if (jsonArray != null) {
                b2 = b;
                if (jsonArray2 != null) {
                    b2 = b;
                    if (jsonArray.length() == jsonArray2.length()) {
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            final Object value = jsonArray.get(i);
                            final Object value2 = jsonArray2.get(i);
                            if (value != value2) {
                                b2 = b;
                                if (value == null) {
                                    return b2;
                                }
                                b2 = b;
                                if (value2 == null) {
                                    return b2;
                                }
                                b2 = b;
                                if (((JSONObject)value).getClass() != ((JSONObject)value2).getClass()) {
                                    return b2;
                                }
                                if (value instanceof JSONObject) {
                                    if (!equals((JSONObject)value, (JSONObject)value2)) {
                                        return false;
                                    }
                                }
                                else if (value instanceof JSONArray) {
                                    if (!equals((JSONArray)value, (JSONArray)value2)) {
                                        return false;
                                    }
                                }
                                else if (!value.equals(value2)) {
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return b2;
    }
    
    public static boolean equals(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject == jsonObject2) {
            return true;
        }
        if (jsonObject == null || jsonObject2 == null) {
            return false;
        }
        final String[] names = JSONObject.getNames(jsonObject);
        final String[] names2 = JSONObject.getNames(jsonObject2);
        if (names == names2) {
            return true;
        }
        if (names == null || names2 == null || names.length != names2.length) {
            return false;
        }
        final HashSet set = new HashSet(Arrays.asList(names));
        final HashSet set2 = new HashSet(Arrays.asList(names2));
        if (set.size() != names.length || set.size() != names2.length) {
            return false;
        }
        if (!set.equals(set2)) {
            return false;
        }
        for (int length = names.length, i = 0; i < length; ++i) {
            final String s = names[i];
            final Object value = jsonObject.get(s);
            final Object value2 = jsonObject2.get(s);
            if (value != value2) {
                if (value == null || value2 == null) {
                    return false;
                }
                if (((JSONObject)value).getClass() != ((JSONObject)value2).getClass()) {
                    return false;
                }
                if (value instanceof JSONObject) {
                    if (!equals((JSONObject)value, (JSONObject)value2)) {
                        return false;
                    }
                }
                else if (value instanceof JSONArray) {
                    if (!equals((JSONArray)value, (JSONArray)value2)) {
                        return false;
                    }
                }
                else if (!value.equals(value2)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static JSONObject merge(JSONObject jsonObject, final JSONObject jsonObject2) {
        JSONObject jsonObject3;
        if (jsonObject == null && jsonObject2 == null) {
            jsonObject3 = null;
        }
        else {
            if (jsonObject != null) {
                jsonObject = new JSONObject(jsonObject, JSONObject.getNames(jsonObject));
            }
            else {
                jsonObject = new JSONObject();
            }
            jsonObject3 = jsonObject;
            if (jsonObject2 != null) {
                final String[] names = JSONObject.getNames(jsonObject2);
                final int length = names.length;
                int n = 0;
                while (true) {
                    jsonObject3 = jsonObject;
                    if (n >= length) {
                        break;
                    }
                    final String s = names[n];
                    jsonObject.put(s, jsonObject2.get(s));
                    ++n;
                }
            }
        }
        return jsonObject3;
    }
    
    public static boolean objectEquals(final String s, final String s2) {
        return equals(new JSONObject(s), new JSONObject(s2));
    }
}
