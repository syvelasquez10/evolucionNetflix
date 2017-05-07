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

final class AppEventsLogger$7 implements Request$Callback
{
    final /* synthetic */ AppEventsLogger$AccessTokenAppIdPair val$accessTokenAppId;
    final /* synthetic */ AppEventsLogger$FlushStatistics val$flushState;
    final /* synthetic */ Request val$postRequest;
    final /* synthetic */ AppEventsLogger$SessionEventsState val$sessionEventsState;
    
    AppEventsLogger$7(final AppEventsLogger$AccessTokenAppIdPair val$accessTokenAppId, final Request val$postRequest, final AppEventsLogger$SessionEventsState val$sessionEventsState, final AppEventsLogger$FlushStatistics val$flushState) {
        this.val$accessTokenAppId = val$accessTokenAppId;
        this.val$postRequest = val$postRequest;
        this.val$sessionEventsState = val$sessionEventsState;
        this.val$flushState = val$flushState;
    }
    
    @Override
    public void onCompleted(final Response response) {
        handleResponse(this.val$accessTokenAppId, this.val$postRequest, response, this.val$sessionEventsState, this.val$flushState);
    }
}
