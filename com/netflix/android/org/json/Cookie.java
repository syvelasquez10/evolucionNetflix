// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

public class Cookie
{
    public static String escape(String trim) {
        trim = trim.trim();
        final StringBuffer sb = new StringBuffer();
        for (int length = trim.length(), i = 0; i < length; ++i) {
            final char char1 = trim.charAt(i);
            if (char1 < ' ' || char1 == '+' || char1 == '%' || char1 == '=' || char1 == ';') {
                sb.append('%');
                sb.append(Character.forDigit((char)(char1 >>> 4 & '\u000f'), 16));
                sb.append(Character.forDigit((char)(char1 & '\u000f'), 16));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static JSONObject toJSONObject(String s) {
        final JSONObject jsonObject = new JSONObject();
        final JSONTokener jsonTokener = new JSONTokener(s);
        jsonObject.put("name", jsonTokener.nextTo('='));
        jsonTokener.next('=');
        jsonObject.put("value", jsonTokener.nextTo(';'));
        jsonTokener.next();
        while (jsonTokener.more()) {
            final String unescape = unescape(jsonTokener.nextTo("=;"));
            if (jsonTokener.next() != '=') {
                if (!unescape.equals("secure")) {
                    throw jsonTokener.syntaxError("Missing '=' in cookie parameter.");
                }
                s = (String)Boolean.TRUE;
            }
            else {
                s = unescape(jsonTokener.nextTo(';'));
                jsonTokener.next();
            }
            jsonObject.put(unescape, s);
        }
        return jsonObject;
    }
    
    public static String toString(final JSONObject jsonObject) {
        final StringBuffer sb = new StringBuffer();
        sb.append(escape(jsonObject.getString("name")));
        sb.append("=");
        sb.append(escape(jsonObject.getString("value")));
        if (jsonObject.has("expires")) {
            sb.append(";expires=");
            sb.append(jsonObject.getString("expires"));
        }
        if (jsonObject.has("domain")) {
            sb.append(";domain=");
            sb.append(escape(jsonObject.getString("domain")));
        }
        if (jsonObject.has("path")) {
            sb.append(";path=");
            sb.append(escape(jsonObject.getString("path")));
        }
        if (jsonObject.optBoolean("secure")) {
            sb.append(";secure");
        }
        return sb.toString();
    }
    
    public static String unescape(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        int n;
        for (int i = 0; i < length; i = n + 1) {
            final char char1 = s.charAt(i);
            char c;
            if (char1 == '+') {
                c = ' ';
                n = i;
            }
            else {
                c = char1;
                n = i;
                if (char1 == '%') {
                    c = char1;
                    n = i;
                    if (i + 2 < length) {
                        final int dehexchar = JSONTokener.dehexchar(s.charAt(i + 1));
                        final int dehexchar2 = JSONTokener.dehexchar(s.charAt(i + 2));
                        c = char1;
                        n = i;
                        if (dehexchar >= 0) {
                            c = char1;
                            n = i;
                            if (dehexchar2 >= 0) {
                                c = (char)(dehexchar * 16 + dehexchar2);
                                n = i + 2;
                            }
                        }
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
