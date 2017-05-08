// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.util.Iterator;

public class CookieList
{
    public static JSONObject toJSONObject(final String s) {
        final JSONObject jsonObject = new JSONObject();
        final JSONTokener jsonTokener = new JSONTokener(s);
        while (jsonTokener.more()) {
            final String unescape = Cookie.unescape(jsonTokener.nextTo('='));
            jsonTokener.next('=');
            jsonObject.put(unescape, Cookie.unescape(jsonTokener.nextTo(';')));
            jsonTokener.next();
        }
        return jsonObject;
    }
    
    public static String toString(final JSONObject jsonObject) {
        int n = 0;
        final Iterator keys = jsonObject.keys();
        final StringBuffer sb = new StringBuffer();
        while (keys.hasNext()) {
            final String string = keys.next().toString();
            if (!jsonObject.isNull(string)) {
                if (n != 0) {
                    sb.append(';');
                }
                sb.append(Cookie.escape(string));
                sb.append("=");
                sb.append(Cookie.escape(jsonObject.getString(string)));
                n = 1;
            }
        }
        return sb.toString();
    }
}
