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

final class AppEventsLogger$6 implements Runnable
{
    final /* synthetic */ AppEventsLogger$FlushReason val$reason;
    
    AppEventsLogger$6(final AppEventsLogger$FlushReason val$reason) {
        this.val$reason = val$reason;
    }
    
    @Override
    public void run() {
        flushAndWait(this.val$reason);
    }
}
