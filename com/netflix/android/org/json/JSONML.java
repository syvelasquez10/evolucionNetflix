// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Iterator;

public class JSONML
{
    private static Object parse(final XMLTokener xmlTokener, final boolean b, final JSONArray jsonArray) {
    Label_0000:
        while (xmlTokener.more()) {
            final Object nextContent = xmlTokener.nextContent();
            if (nextContent == XML.LT) {
                final Object nextToken = xmlTokener.nextToken();
                Object nextToken2;
                if (nextToken instanceof Character) {
                    if (nextToken == XML.SLASH) {
                        nextToken2 = xmlTokener.nextToken();
                        if (!(nextToken2 instanceof String)) {
                            throw new JSONException("Expected a closing name instead of '" + nextToken2 + "'.");
                        }
                        if (xmlTokener.nextToken() != XML.GT) {
                            throw xmlTokener.syntaxError("Misshaped close tag");
                        }
                    }
                    else if (nextToken == XML.BANG) {
                        final char next = xmlTokener.next();
                        if (next == '-') {
                            if (xmlTokener.next() == '-') {
                                xmlTokener.skipPast("-->");
                                continue;
                            }
                            xmlTokener.back();
                            continue;
                        }
                        else if (next == '[') {
                            if (!xmlTokener.nextToken().equals("CDATA") || xmlTokener.next() != '[') {
                                throw xmlTokener.syntaxError("Expected 'CDATA['");
                            }
                            if (jsonArray != null) {
                                jsonArray.put(xmlTokener.nextCDATA());
                                continue;
                            }
                            continue;
                        }
                        else {
                            int n = 1;
                            while (true) {
                                final Object nextMeta = xmlTokener.nextMeta();
                                if (nextMeta == null) {
                                    throw xmlTokener.syntaxError("Missing '>' after '<!'.");
                                }
                                int n2;
                                if (nextMeta == XML.LT) {
                                    n2 = n + 1;
                                }
                                else {
                                    n2 = n;
                                    if (nextMeta == XML.GT) {
                                        n2 = n - 1;
                                    }
                                }
                                if ((n = n2) <= 0) {
                                    continue Label_0000;
                                }
                            }
                        }
                    }
                    else {
                        if (nextToken == XML.QUEST) {
                            xmlTokener.skipPast("?>");
                            continue;
                        }
                        throw xmlTokener.syntaxError("Misshaped tag");
                    }
                }
                else {
                    if (!(nextToken instanceof String)) {
                        throw xmlTokener.syntaxError("Bad tagName '" + nextToken + "'.");
                    }
                    final String s = (String)nextToken;
                    final JSONArray jsonArray2 = new JSONArray();
                    final JSONObject jsonObject = new JSONObject();
                    if (b) {
                        jsonArray2.put(s);
                        if (jsonArray != null) {
                            jsonArray.put(jsonArray2);
                        }
                    }
                    else {
                        jsonObject.put("tagName", s);
                        if (jsonArray != null) {
                            jsonArray.put(jsonObject);
                        }
                    }
                    Object nextToken3 = null;
                    while (true) {
                        Object nextToken4 = nextToken3;
                        if (nextToken3 == null) {
                            nextToken4 = xmlTokener.nextToken();
                        }
                        if (nextToken4 == null) {
                            throw xmlTokener.syntaxError("Misshaped tag");
                        }
                        if (!(nextToken4 instanceof String)) {
                            if (b && jsonObject.length() > 0) {
                                jsonArray2.put(jsonObject);
                            }
                            if (nextToken4 == XML.SLASH) {
                                if (xmlTokener.nextToken() != XML.GT) {
                                    throw xmlTokener.syntaxError("Misshaped tag");
                                }
                                if (jsonArray != null) {
                                    continue Label_0000;
                                }
                                if (b) {
                                    nextToken2 = jsonArray2;
                                    break;
                                }
                                return jsonObject;
                            }
                            else {
                                if (nextToken4 != XML.GT) {
                                    throw xmlTokener.syntaxError("Misshaped tag");
                                }
                                final String s2 = (String)parse(xmlTokener, b, jsonArray2);
                                if (s2 == null) {
                                    continue Label_0000;
                                }
                                if (!s2.equals(s)) {
                                    throw xmlTokener.syntaxError("Mismatched '" + s + "' and '" + s2 + "'");
                                }
                                if (!b && jsonArray2.length() > 0) {
                                    jsonObject.put("childNodes", jsonArray2);
                                }
                                if (jsonArray != null) {
                                    continue Label_0000;
                                }
                                if (b) {
                                    return jsonArray2;
                                }
                                return jsonObject;
                            }
                        }
                        else {
                            final String s3 = (String)nextToken4;
                            if (!b && ("tagName".equals(s3) || "childNode".equals(s3))) {
                                throw xmlTokener.syntaxError("Reserved attribute.");
                            }
                            nextToken3 = xmlTokener.nextToken();
                            if (nextToken3 == XML.EQ) {
                                final Object nextToken5 = xmlTokener.nextToken();
                                if (!(nextToken5 instanceof String)) {
                                    throw xmlTokener.syntaxError("Missing value");
                                }
                                jsonObject.accumulate(s3, XML.stringToValue((String)nextToken5));
                                nextToken3 = null;
                            }
                            else {
                                jsonObject.accumulate(s3, "");
                            }
                        }
                    }
                }
                return nextToken2;
            }
            if (jsonArray == null) {
                continue;
            }
            Object stringToValue = nextContent;
            if (nextContent instanceof String) {
                stringToValue = XML.stringToValue((String)nextContent);
            }
            jsonArray.put(stringToValue);
        }
        throw xmlTokener.syntaxError("Bad XML");
    }
    
    public static JSONArray toJSONArray(final XMLTokener xmlTokener) {
        return (JSONArray)parse(xmlTokener, true, null);
    }
    
    public static JSONArray toJSONArray(final String s) {
        return toJSONArray(new XMLTokener(s));
    }
    
    public static JSONObject toJSONObject(final XMLTokener xmlTokener) {
        return (JSONObject)parse(xmlTokener, false, null);
    }
    
    public static JSONObject toJSONObject(final String s) {
        return toJSONObject(new XMLTokener(s));
    }
    
    public static String toString(final JSONArray jsonArray) {
        final StringBuffer sb = new StringBuffer();
        final String string = jsonArray.getString(0);
        XML.noSpace(string);
        final String escape = XML.escape(string);
        sb.append('<');
        sb.append(escape);
        final Object opt = jsonArray.opt(1);
        int n;
        if (opt instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)opt;
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String string2 = keys.next().toString();
                XML.noSpace(string2);
                final String optString = jsonObject.optString(string2);
                if (optString != null) {
                    sb.append(' ');
                    sb.append(XML.escape(string2));
                    sb.append('=');
                    sb.append('\"');
                    sb.append(XML.escape(optString));
                    sb.append('\"');
                }
            }
            n = 2;
        }
        else {
            n = 1;
        }
        final int length = jsonArray.length();
        if (n >= length) {
            sb.append('/');
            sb.append('>');
        }
        else {
            sb.append('>');
            int n2;
            do {
                final Object value = jsonArray.get(n);
                n2 = n + 1;
                if (value != null) {
                    if (value instanceof String) {
                        sb.append(XML.escape(value.toString()));
                    }
                    else if (value instanceof JSONObject) {
                        sb.append(toString((JSONObject)value));
                    }
                    else if (value instanceof JSONArray) {
                        sb.append(toString((JSONArray)value));
                    }
                }
            } while ((n = n2) < length);
            sb.append('<');
            sb.append('/');
            sb.append(escape);
            sb.append('>');
        }
        return sb.toString();
    }
    
    public static String toString(final JSONObject jsonObject) {
        final StringBuffer sb = new StringBuffer();
        final String optString = jsonObject.optString("tagName");
        if (optString == null) {
            return XML.escape(jsonObject.toString());
        }
        XML.noSpace(optString);
        final String escape = XML.escape(optString);
        sb.append('<');
        sb.append(escape);
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String string = keys.next().toString();
            if (!"tagName".equals(string) && !"childNodes".equals(string)) {
                XML.noSpace(string);
                final String optString2 = jsonObject.optString(string);
                if (optString2 == null) {
                    continue;
                }
                sb.append(' ');
                sb.append(XML.escape(string));
                sb.append('=');
                sb.append('\"');
                sb.append(XML.escape(optString2));
                sb.append('\"');
            }
        }
        final JSONArray optJSONArray = jsonObject.optJSONArray("childNodes");
        if (optJSONArray == null) {
            sb.append('/');
            sb.append('>');
        }
        else {
            sb.append('>');
            for (int length = optJSONArray.length(), i = 0; i < length; ++i) {
                final Object value = optJSONArray.get(i);
                if (value != null) {
                    if (value instanceof String) {
                        sb.append(XML.escape(value.toString()));
                    }
                    else if (value instanceof JSONObject) {
                        sb.append(toString((JSONObject)value));
                    }
                    else if (value instanceof JSONArray) {
                        sb.append(toString((JSONArray)value));
                    }
                    else {
                        sb.append(value.toString());
                    }
                }
            }
            sb.append('<');
            sb.append('/');
            sb.append(escape);
            sb.append('>');
        }
        return sb.toString();
    }
}
