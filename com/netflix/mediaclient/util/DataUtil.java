// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.net.Uri;
import java.util.List;

public class DataUtil
{
    public static final int UNDEFINED_INT = -1;
    
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
