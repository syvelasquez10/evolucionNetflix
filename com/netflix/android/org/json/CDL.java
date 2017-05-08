// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

public class CDL
{
    private static String getValue(final JSONTokener jsonTokener) {
        char next;
        do {
            next = jsonTokener.next();
        } while (next == ' ' || next == '\t');
        switch (next) {
            default: {
                jsonTokener.back();
                return jsonTokener.nextTo(',');
            }
            case 0: {
                return null;
            }
            case 34:
            case 39: {
                final StringBuffer sb = new StringBuffer();
                while (true) {
                    final char next2 = jsonTokener.next();
                    if (next2 == next) {
                        return sb.toString();
                    }
                    if (next2 == '\0' || next2 == '\n' || next2 == '\r') {
                        throw jsonTokener.syntaxError("Missing close quote '" + next + "'.");
                    }
                    sb.append(next2);
                }
                break;
            }
            case 44: {
                jsonTokener.back();
                return "";
            }
        }
    }
    
    public static JSONArray rowToJSONArray(final JSONTokener jsonTokener) {
        final JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray2 = null;
    Label_0044:
        while (true) {
            final String value = getValue(jsonTokener);
            char c = jsonTokener.next();
            if (value == null || (jsonArray.length() == 0 && value.length() == 0 && c != ',')) {
                jsonArray2 = null;
                break;
            }
            jsonArray.put(value);
            while (c != ',') {
                if (c != ' ') {
                    jsonArray2 = jsonArray;
                    if (c == '\n') {
                        break Label_0044;
                    }
                    jsonArray2 = jsonArray;
                    if (c == '\r') {
                        break Label_0044;
                    }
                    jsonArray2 = jsonArray;
                    if (c != '\0') {
                        throw jsonTokener.syntaxError("Bad character '" + c + "' (" + (int)c + ").");
                    }
                    break Label_0044;
                }
                else {
                    c = jsonTokener.next();
                }
            }
        }
        return jsonArray2;
    }
    
    public static JSONObject rowToJSONObject(final JSONArray jsonArray, final JSONTokener jsonTokener) {
        final JSONArray rowToJSONArray = rowToJSONArray(jsonTokener);
        if (rowToJSONArray != null) {
            return rowToJSONArray.toJSONObject(jsonArray);
        }
        return null;
    }
    
    public static String rowToString(final JSONArray jsonArray) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (i > 0) {
                sb.append(',');
            }
            final Object opt = jsonArray.opt(i);
            if (opt != null) {
                final String string = opt.toString();
                if (string.length() > 0 && (string.indexOf(44) >= 0 || string.indexOf(10) >= 0 || string.indexOf(13) >= 0 || string.indexOf(0) >= 0 || string.charAt(0) == '\"')) {
                    sb.append('\"');
                    for (int length = string.length(), j = 0; j < length; ++j) {
                        final char char1 = string.charAt(j);
                        if (char1 >= ' ' && char1 != '\"') {
                            sb.append(char1);
                        }
                    }
                    sb.append('\"');
                }
                else {
                    sb.append(string);
                }
            }
        }
        sb.append('\n');
        return sb.toString();
    }
    
    public static JSONArray toJSONArray(final JSONArray jsonArray, final JSONTokener jsonTokener) {
        if (jsonArray != null && jsonArray.length() != 0) {
            final JSONArray jsonArray2 = new JSONArray();
            while (true) {
                final JSONObject rowToJSONObject = rowToJSONObject(jsonArray, jsonTokener);
                if (rowToJSONObject == null) {
                    break;
                }
                jsonArray2.put(rowToJSONObject);
            }
            if (jsonArray2.length() != 0) {
                return jsonArray2;
            }
        }
        return null;
    }
    
    public static JSONArray toJSONArray(final JSONArray jsonArray, final String s) {
        return toJSONArray(jsonArray, new JSONTokener(s));
    }
    
    public static JSONArray toJSONArray(final JSONTokener jsonTokener) {
        return toJSONArray(rowToJSONArray(jsonTokener), jsonTokener);
    }
    
    public static JSONArray toJSONArray(final String s) {
        return toJSONArray(new JSONTokener(s));
    }
    
    public static String toString(final JSONArray jsonArray) {
        final JSONObject optJSONObject = jsonArray.optJSONObject(0);
        if (optJSONObject != null) {
            final JSONArray names = optJSONObject.names();
            if (names != null) {
                return rowToString(names) + toString(names, jsonArray);
            }
        }
        return null;
    }
    
    public static String toString(final JSONArray jsonArray, final JSONArray jsonArray2) {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonArray2.length(); ++i) {
            final JSONObject optJSONObject = jsonArray2.optJSONObject(i);
            if (optJSONObject != null) {
                sb.append(rowToString(optJSONObject.toJSONArray(jsonArray)));
            }
        }
        return sb.toString();
    }
}
