// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Iterator;

public class HTTP
{
    public static final String CRLF = "\r\n";
    
    public static JSONObject toJSONObject(final String s) {
        final JSONObject jsonObject = new JSONObject();
        final HTTPTokener httpTokener = new HTTPTokener(s);
        final String nextToken = httpTokener.nextToken();
        if (nextToken.toUpperCase().startsWith("HTTP")) {
            jsonObject.put("HTTP-Version", nextToken);
            jsonObject.put("Status-Code", httpTokener.nextToken());
            jsonObject.put("Reason-Phrase", httpTokener.nextTo('\0'));
            httpTokener.next();
        }
        else {
            jsonObject.put("Method", nextToken);
            jsonObject.put("Request-URI", httpTokener.nextToken());
            jsonObject.put("HTTP-Version", httpTokener.nextToken());
        }
        while (httpTokener.more()) {
            final String nextTo = httpTokener.nextTo(':');
            httpTokener.next(':');
            jsonObject.put(nextTo, httpTokener.nextTo('\0'));
            httpTokener.next();
        }
        return jsonObject;
    }
    
    public static String toString(final JSONObject jsonObject) {
        final Iterator keys = jsonObject.keys();
        final StringBuffer sb = new StringBuffer();
        if (jsonObject.has("Status-Code") && jsonObject.has("Reason-Phrase")) {
            sb.append(jsonObject.getString("HTTP-Version"));
            sb.append(' ');
            sb.append(jsonObject.getString("Status-Code"));
            sb.append(' ');
            sb.append(jsonObject.getString("Reason-Phrase"));
        }
        else {
            if (!jsonObject.has("Method") || !jsonObject.has("Request-URI")) {
                throw new JSONException("Not enough material for an HTTP header.");
            }
            sb.append(jsonObject.getString("Method"));
            sb.append(' ');
            sb.append('\"');
            sb.append(jsonObject.getString("Request-URI"));
            sb.append('\"');
            sb.append(' ');
            sb.append(jsonObject.getString("HTTP-Version"));
        }
        sb.append("\r\n");
        while (keys.hasNext()) {
            final String string = keys.next().toString();
            if (!"HTTP-Version".equals(string) && !"Status-Code".equals(string) && !"Reason-Phrase".equals(string) && !"Method".equals(string) && !"Request-URI".equals(string) && !jsonObject.isNull(string)) {
                sb.append(string);
                sb.append(": ");
                sb.append(jsonObject.getString(string));
                sb.append("\r\n");
            }
        }
        sb.append("\r\n");
        return sb.toString();
    }
}
