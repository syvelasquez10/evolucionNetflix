// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Currency;
import java.math.BigDecimal;
import android.content.Intent;
import android.content.ComponentName;
import bolts.AppLinks;
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
import android.util.Log;
import android.app.Activity;
import java.util.List;
import java.util.Iterator;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.content.Context;

final class AppEventsLogger$1 implements Runnable
{
    final /* synthetic */ long val$eventTime;
    final /* synthetic */ AppEventsLogger val$logger;
    final /* synthetic */ String val$sourceApplicationInfo;
    
    AppEventsLogger$1(final AppEventsLogger val$logger, final long val$eventTime, final String val$sourceApplicationInfo) {
        this.val$logger = val$logger;
        this.val$eventTime = val$eventTime;
        this.val$sourceApplicationInfo = val$sourceApplicationInfo;
    }
    
    @Override
    public void run() {
        this.val$logger.logAppSessionResumeEvent(this.val$eventTime, this.val$sourceApplicationInfo);
    }
}
