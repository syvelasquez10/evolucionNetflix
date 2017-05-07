// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.net.Uri;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class DataUtil
{
    public static final float ASPECT_RATIO_16_9 = 1.778f;
    public static final float ASPECT_RATIO_16_9_INVERTED = 0.5625f;
    public static final float ASPECT_RATIO_4_3 = 1.333f;
    public static final float ASPECT_RATIO_4_3_INVERTED = 0.75f;
    public static final int UNDEFINED_INT = -1;
    
    public static List<String> createStringListFromList(final List<?> list) {
        final ArrayList<String> list2 = new ArrayList<String>(list.size());
        for (final Object next : list) {
            String string;
            if (next == null) {
                string = null;
            }
            else {
                string = next.toString();
            }
            list2.add(string);
        }
        return list2;
    }
    
    public static String getFirstItemSafely(final List<?> list) {
        if (list == null) {
            return null;
        }
        if (list.size() > 0) {
            return list.get(0).toString();
        }
        return "none";
    }
    
    public static void logVerboseUriInfo(final String s, final Uri uri) {
        if (Log.isLoggable(s, 2)) {
            Log.v(s, "Uri info");
            Log.v(s, "   scheme: " + uri.getScheme());
            Log.v(s, "   host: " + uri.getHost());
            Log.v(s, "   path: " + uri.getPath());
            Log.v(s, "   path segs: " + uri.getPathSegments());
            Log.v(s, "   query: " + uri.getQuery());
            Log.v(s, "   query param names: " + uri.getQueryParameterNames());
        }
    }
}
