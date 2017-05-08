// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;
import java.util.Collections;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.Log;

public class DataUtil
{
    public static final float ASPECT_RATIO_16_10 = 1.6f;
    public static final float ASPECT_RATIO_16_10_INVERTED = 0.625f;
    public static final float ASPECT_RATIO_16_9 = 1.778f;
    public static final float ASPECT_RATIO_16_9_INVERTED = 0.5625f;
    public static final float ASPECT_RATIO_4_3 = 1.333f;
    public static final float ASPECT_RATIO_4_3_INVERTED = 0.75f;
    public static final float BOXART_HEIGHT_TO_WIDTH_RATIO = 1.43f;
    private static final String TAG = "DataUtil";
    public static final float UNDEFINED_FLOAT = -1.0f;
    public static final int UNDEFINED_INT = -1;
    
    public static boolean areAnyNull(final String s, final String s2, final Object... array) {
        final boolean b = false;
        if (array != null) {
            int n = 0;
            while (true) {
                final boolean b2 = b;
                if (n >= array.length) {
                    return b2;
                }
                if (array[n] == null) {
                    break;
                }
                ++n;
            }
            Log.v(s, "Object " + n + " is null - " + s2);
            return true;
        }
        Log.v(s, "Objects array is null - " + s2);
        return true;
    }
    
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
    
    public static boolean hasUnavailableEpisodes(final List<EpisodeDetails> list) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= list.size()) {
                break;
            }
            final EpisodeDetails episodeDetails = list.get(n);
            if (episodeDetails != null && !episodeDetails.isAvailableToStream()) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public static void invalidateCachedEpisodes(final ServiceManager serviceManager, final String s, final SeasonDetails seasonDetails) {
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.d("DataUtil", "Manager is not ready");
            return;
        }
        if (seasonDetails == null) {
            Log.v("DataUtil", "No season details yet");
            return;
        }
        final String id = seasonDetails.getId();
        if (Log.isLoggable()) {
            Log.v("DataUtil", "Purging episode data for: " + id);
        }
        if (StringUtils.isEmpty(id)) {
            LogUtils.logEmptySeasonId(serviceManager.getClientLogging(), s, seasonDetails);
            return;
        }
        serviceManager.getBrowse().invalidateCachedEpisodes(id, VideoType.SEASON);
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
