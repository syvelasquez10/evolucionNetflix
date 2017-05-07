// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility$FetchedAppSettings;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import com.facebook.internal.Logger;
import java.util.ArrayList;
import java.util.List;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.content.Context;
import java.util.Set;
import java.util.Iterator;
import com.facebook.internal.Utility;
import java.util.HashSet;

final class AppEventsLogger$4 implements Runnable
{
    @Override
    public void run() {
        final HashSet<String> set = new HashSet<String>();
        synchronized (AppEventsLogger.staticLock) {
            final Iterator<AppEventsLogger$AccessTokenAppIdPair> iterator = AppEventsLogger.stateMap.keySet().iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().getApplicationId());
            }
        }
        // monitorexit(o)
        final Set<String> set2;
        final Iterator<String> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Utility.queryAppSettings(iterator2.next(), true);
        }
    }
}
