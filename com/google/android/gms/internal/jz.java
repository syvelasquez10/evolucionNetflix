// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.regex.Matcher;
import android.text.TextUtils;
import java.util.regex.Pattern;

public final class jz
{
    private static final Pattern ML;
    private static final Pattern MM;
    
    static {
        ML = Pattern.compile("\\\\.");
        MM = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }
    
    public static String bf(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Matcher matcher = jz.MM.matcher(s);
            StringBuffer sb = null;
            while (matcher.find()) {
                StringBuffer sb2;
                if ((sb2 = sb) == null) {
                    sb2 = new StringBuffer();
                }
                switch (matcher.group().charAt(0)) {
                    default: {
                        sb = sb2;
                        continue;
                    }
                    case '\b': {
                        matcher.appendReplacement(sb2, "\\\\b");
                        sb = sb2;
                        continue;
                    }
                    case '\"': {
                        matcher.appendReplacement(sb2, "\\\\\\\"");
                        sb = sb2;
                        continue;
                    }
                    case '\\': {
                        matcher.appendReplacement(sb2, "\\\\\\\\");
                        sb = sb2;
                        continue;
                    }
                    case '/': {
                        matcher.appendReplacement(sb2, "\\\\/");
                        sb = sb2;
                        continue;
                    }
                    case '\f': {
                        matcher.appendReplacement(sb2, "\\\\f");
                        sb = sb2;
                        continue;
                    }
                    case '\n': {
                        matcher.appendReplacement(sb2, "\\\\n");
                        sb = sb2;
                        continue;
                    }
                    case '\r': {
                        matcher.appendReplacement(sb2, "\\\\r");
                        sb = sb2;
                        continue;
                    }
                    case '\t': {
                        matcher.appendReplacement(sb2, "\\\\t");
                        sb = sb2;
                        continue;
                    }
                }
            }
            if (sb != null) {
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return s;
    }
    
    public static boolean d(final Object o, final Object o2) {
        Label_0098: {
            if (!(o instanceof JSONObject) || !(o2 instanceof JSONObject)) {
                break Label_0098;
            }
            final JSONObject jsonObject = (JSONObject)o;
            final JSONObject jsonObject2 = (JSONObject)o2;
            Iterator keys = null;
            String s;
            int n;
            JSONArray jsonArray;
            JSONArray jsonArray2;
            Block_9_Outer:Label_0135_Outer:
            while (true) {
                if (jsonObject.length() == jsonObject2.length()) {
                    keys = jsonObject.keys();
                    break Label_0043;
                }
                Label_0035: {
                    return false;
                }
                if (!keys.hasNext()) {
                    return true;
                }
                s = keys.next();
                if (!jsonObject2.has(s)) {
                    return false;
                }
                try {
                    if (!d(jsonObject.get(s), jsonObject2.get(s))) {
                        return false;
                    }
                    continue Block_9_Outer;
                    // iftrue(Label_0035:, jsonArray.length() != jsonArray2.length())
                Label_0135:
                    while (true) {
                        while (true) {
                            while (true) {
                                n = 0;
                                break Label_0135;
                                jsonArray = (JSONArray)o;
                                jsonArray2 = (JSONArray)o2;
                                continue Label_0135_Outer;
                            }
                            continue;
                        }
                        try {
                            if (d(jsonArray.get(n), jsonArray2.get(n))) {
                                ++n;
                                continue Label_0135;
                            }
                            return false;
                            Label_0170:
                            return o.equals(o2);
                            Label_0168:
                            return true;
                        }
                        catch (JSONException ex) {
                            return false;
                        }
                        break;
                    }
                }
                // iftrue(Label_0170:, !o instanceof JSONArray || !o2 instanceof JSONArray)
                // iftrue(Label_0168:, n >= jsonArray.length())
                catch (JSONException ex2) {
                    return false;
                }
                break;
            }
        }
    }
}
