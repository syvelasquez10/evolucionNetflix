// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Iterator;
import java.io.Serializable;

public class XML
{
    public static final Character AMP;
    public static final Character APOS;
    public static final Character BANG;
    public static final Character EQ;
    public static final Character GT;
    public static final Character LT;
    public static final Character QUEST;
    public static final Character QUOT;
    public static final Character SLASH;
    
    static {
        AMP = new Character('&');
        APOS = new Character('\'');
        BANG = new Character('!');
        EQ = new Character('=');
        GT = new Character('>');
        LT = new Character('<');
        QUEST = new Character('?');
        QUOT = new Character('\"');
        SLASH = new Character('/');
    }
    
    public static String escape(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                default: {
                    sb.append(char1);
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 62: {
                    sb.append("&gt;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
                case 39: {
                    sb.append("&apos;");
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static void noSpace(final String s) {
        final int length = s.length();
        if (length == 0) {
            throw new JSONException("Empty string.");
        }
        for (int i = 0; i < length; ++i) {
            if (Character.isWhitespace(s.charAt(i))) {
                throw new JSONException("'" + s + "' contains a space character.");
            }
        }
    }
    
    private static boolean parse(final XMLTokener xmlTokener, final JSONObject jsonObject, String s) {
        boolean b = true;
        final Object nextToken = xmlTokener.nextToken();
        if (nextToken == XML.BANG) {
            final char next = xmlTokener.next();
            if (next == '-') {
                if (xmlTokener.next() == '-') {
                    xmlTokener.skipPast("-->");
                    b = false;
                    return b;
                }
                xmlTokener.back();
            }
            else if (next == '[') {
                if ("CDATA".equals(xmlTokener.nextToken()) && xmlTokener.next() == '[') {
                    final String nextCDATA = xmlTokener.nextCDATA();
                    if (nextCDATA.length() > 0) {
                        jsonObject.accumulate("content", nextCDATA);
                    }
                    return false;
                }
                throw xmlTokener.syntaxError("Expected 'CDATA['");
            }
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
                    return false;
                }
            }
        }
        else {
            if (nextToken == XML.QUEST) {
                xmlTokener.skipPast("?>");
                return false;
            }
            if (nextToken == XML.SLASH) {
                final Object nextToken2 = xmlTokener.nextToken();
                if (s == null) {
                    throw xmlTokener.syntaxError("Mismatched close tag " + nextToken2);
                }
                if (!nextToken2.equals(s)) {
                    throw xmlTokener.syntaxError("Mismatched " + s + " and " + nextToken2);
                }
                if (xmlTokener.nextToken() != XML.GT) {
                    throw xmlTokener.syntaxError("Misshaped close tag");
                }
            }
            else {
                if (nextToken instanceof Character) {
                    throw xmlTokener.syntaxError("Misshaped tag");
                }
                final String s2 = (String)nextToken;
                final JSONObject jsonObject2 = new JSONObject();
                Object nextToken3 = null;
                while (true) {
                    Object nextToken4 = nextToken3;
                    if (nextToken3 == null) {
                        nextToken4 = xmlTokener.nextToken();
                    }
                    if (nextToken4 instanceof String) {
                        final String s3 = (String)nextToken4;
                        nextToken3 = xmlTokener.nextToken();
                        if (nextToken3 == XML.EQ) {
                            final Object nextToken5 = xmlTokener.nextToken();
                            if (!(nextToken5 instanceof String)) {
                                throw xmlTokener.syntaxError("Missing value");
                            }
                            jsonObject2.accumulate(s3, stringToValue((String)nextToken5));
                            nextToken3 = null;
                        }
                        else {
                            jsonObject2.accumulate(s3, "");
                        }
                    }
                    else if (nextToken4 == XML.SLASH) {
                        if (xmlTokener.nextToken() != XML.GT) {
                            throw xmlTokener.syntaxError("Misshaped tag");
                        }
                        if (jsonObject2.length() > 0) {
                            jsonObject.accumulate(s2, jsonObject2);
                        }
                        else {
                            jsonObject.accumulate(s2, "");
                        }
                        return false;
                    }
                    else {
                        if (nextToken4 != XML.GT) {
                            throw xmlTokener.syntaxError("Misshaped tag");
                        }
                        while (true) {
                            final Object nextContent = xmlTokener.nextContent();
                            if (nextContent == null) {
                                if (s2 != null) {
                                    throw xmlTokener.syntaxError("Unclosed tag " + s2);
                                }
                                return false;
                            }
                            else if (nextContent instanceof String) {
                                s = (String)nextContent;
                                if (s.length() <= 0) {
                                    continue;
                                }
                                jsonObject2.accumulate("content", stringToValue(s));
                            }
                            else {
                                if (nextContent == XML.LT && parse(xmlTokener, jsonObject2, s2)) {
                                    if (jsonObject2.length() == 0) {
                                        jsonObject.accumulate(s2, "");
                                    }
                                    else if (jsonObject2.length() == 1 && jsonObject2.opt("content") != null) {
                                        jsonObject.accumulate(s2, jsonObject2.opt("content"));
                                    }
                                    else {
                                        jsonObject.accumulate(s2, jsonObject2);
                                    }
                                    return false;
                                }
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public static Object stringToValue(final String s) {
        Serializable true;
        if ("true".equalsIgnoreCase(s)) {
            true = Boolean.TRUE;
        }
        else {
            if ("false".equalsIgnoreCase(s)) {
                return Boolean.FALSE;
            }
            if ("null".equalsIgnoreCase(s)) {
                return JSONObject.NULL;
            }
            try {
                final char char1 = s.charAt(0);
                if (char1 != '-') {
                    true = s;
                    if (char1 < '0') {
                        return true;
                    }
                    true = s;
                    if (char1 > '9') {
                        return true;
                    }
                }
                final Long n = new Long(s);
                final boolean equals = n.toString().equals(s);
                true = s;
                if (equals) {
                    return n;
                }
            }
            catch (Exception ex) {
                try {
                    final Double n2 = new Double(s);
                    final boolean equals2 = n2.toString().equals(s);
                    true = s;
                    if (equals2) {
                        return n2;
                    }
                    return true;
                }
                catch (Exception ex2) {
                    return s;
                }
            }
        }
        return true;
    }
    
    public static JSONObject toJSONObject(final String s) {
        final JSONObject jsonObject = new JSONObject();
        final XMLTokener xmlTokener = new XMLTokener(s);
        while (xmlTokener.more() && xmlTokener.skipPast("<")) {
            parse(xmlTokener, jsonObject, null);
        }
        return jsonObject;
    }
    
    public static String toString(final Object o) {
        return toString(o, null);
    }
    
    public static String toString(Object opt, final String s) {
        int i = 0;
        final StringBuffer sb = new StringBuffer();
        if (opt instanceof JSONObject) {
            if (s != null) {
                sb.append('<');
                sb.append(s);
                sb.append('>');
            }
            final JSONObject jsonObject = (JSONObject)opt;
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String string = keys.next().toString();
                if ((opt = jsonObject.opt(string)) == null) {
                    opt = "";
                }
                if (opt instanceof String) {
                    final String s2 = (String)opt;
                }
                if ("content".equals(string)) {
                    if (opt instanceof JSONArray) {
                        final JSONArray jsonArray = (JSONArray)opt;
                        for (int length = jsonArray.length(), j = 0; j < length; ++j) {
                            if (j > 0) {
                                sb.append('\n');
                            }
                            sb.append(escape(jsonArray.get(j).toString()));
                        }
                    }
                    else {
                        sb.append(escape(opt.toString()));
                    }
                }
                else if (opt instanceof JSONArray) {
                    final JSONArray jsonArray2 = (JSONArray)opt;
                    for (int length2 = jsonArray2.length(), k = 0; k < length2; ++k) {
                        final Object value = jsonArray2.get(k);
                        if (value instanceof JSONArray) {
                            sb.append('<');
                            sb.append(string);
                            sb.append('>');
                            sb.append(toString(value));
                            sb.append("</");
                            sb.append(string);
                            sb.append('>');
                        }
                        else {
                            sb.append(toString(value, string));
                        }
                    }
                }
                else if ("".equals(opt)) {
                    sb.append('<');
                    sb.append(string);
                    sb.append("/>");
                }
                else {
                    sb.append(toString(opt, string));
                }
            }
            if (s != null) {
                sb.append("</");
                sb.append(s);
                sb.append('>');
            }
            return sb.toString();
        }
        if (opt.getClass().isArray()) {
            opt = new JSONArray(opt);
        }
        if (opt instanceof JSONArray) {
            for (JSONArray jsonArray3 = (JSONArray)opt; i < jsonArray3.length(); ++i) {
                final Object opt2 = jsonArray3.opt(i);
                String s3;
                if (s == null) {
                    s3 = "array";
                }
                else {
                    s3 = s;
                }
                sb.append(toString(opt2, s3));
            }
            return sb.toString();
        }
        String escape;
        if (opt == null) {
            escape = "null";
        }
        else {
            escape = escape(opt.toString());
        }
        if (s == null) {
            return "\"" + escape + "\"";
        }
        if (escape.length() == 0) {
            return "<" + s + "/>";
        }
        return "<" + s + ">" + escape + "</" + s + ">";
    }
}
