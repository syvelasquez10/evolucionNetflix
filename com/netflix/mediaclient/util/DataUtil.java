// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;
import java.util.Collections;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class DataUtil
{
    public static final float ASPECT_RATIO_16_9 = 1.778f;
    public static final float ASPECT_RATIO_16_9_INVERTED = 0.5625f;
    public static final float ASPECT_RATIO_4_3 = 1.333f;
    public static final float ASPECT_RATIO_4_3_INVERTED = 0.75f;
    public static final float BOXART_HEIGHT_TO_WIDTH_RATIO = 1.43f;
    public static final float UNDEFINED_FLOAT = -1.0f;
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
    
    public static <T> List<T> filter(final Collection<T> collection, final DataUtil$IFilter<T> dataUtil$IFilter) {
        final Iterator<T> iterator = collection.iterator();
        final ArrayList<T> list = new ArrayList<T>();
        while (iterator.hasNext()) {
            final T next = iterator.next();
            if (dataUtil$IFilter.keepIt(next)) {
                list.add(next);
            }
        }
        return list;
    }
    
    public static String getFirstItemSafely(final List<?> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        if (list.get(0) == null) {
            return "none";
        }
        return list.get(0).toString();
    }
    
    public static void logVerboseUriInfo(final String s, final Uri uri) {
        if (Log.isLoggable()) {
            Log.v(s, "Uri info");
            Log.v(s, "   scheme: " + uri.getScheme());
            Log.v(s, "   host: " + uri.getHost());
            Log.v(s, "   path: " + uri.getPath());
            Log.v(s, "   path segs: " + uri.getPathSegments());
            Log.v(s, "   query: " + uri.getQuery());
            Log.v(s, "   query param names: " + uri.getQueryParameterNames());
        }
    }
    
    public static void sortStringsByLengthThenValue(final List<String> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new DataUtil$1());
    }
}
