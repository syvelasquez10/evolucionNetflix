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
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.content.Context;

final class AppEventsLogger$5 implements Runnable
{
    final /* synthetic */ AppEventsLogger$AccessTokenAppIdPair val$accessTokenAppId;
    final /* synthetic */ Context val$context;
    final /* synthetic */ AppEventsLogger$AppEvent val$event;
    
    AppEventsLogger$5(final Context val$context, final AppEventsLogger$AccessTokenAppIdPair val$accessTokenAppId, final AppEventsLogger$AppEvent val$event) {
        this.val$context = val$context;
        this.val$accessTokenAppId = val$accessTokenAppId;
        this.val$event = val$event;
    }
    
    @Override
    public void run() {
        getSessionEventsState(this.val$context, this.val$accessTokenAppId).addEvent(this.val$event);
        flushIfNecessary();
    }
}
