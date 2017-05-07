// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Currency;
import java.math.BigDecimal;
import java.util.List;
import com.facebook.internal.Logger;
import com.facebook.model.GraphObject;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import android.content.Context;

public class InsightsLogger
{
    private static final String EVENT_NAME_LOG_CONVERSION_PIXEL = "fb_log_offsite_pixel";
    private static final String EVENT_NAME_LOG_MOBILE_PURCHASE = "fb_mobile_purchase";
    private static final String EVENT_PARAMETER_CURRENCY = "fb_currency";
    private static final String EVENT_PARAMETER_PIXEL_ID = "fb_offsite_pixel_id";
    private static final String EVENT_PARAMETER_PIXEL_VALUE = "fb_offsite_pixel_value";
    private static Session appAuthSession;
    private final String applicationId;
    private final String clientToken;
    private final Context context;
    private final Session specifiedSession;
    
    static {
        InsightsLogger.appAuthSession = null;
    }
    
    private InsightsLogger(final Context context, final String clientToken, final String s, final Session specifiedSession) {
        Validate.notNull(context, "context");
        Validate.notNullOrEmpty(clientToken, "clientToken");
        String metadataApplicationId = s;
        if (s == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(context);
        }
        this.context = context;
        this.clientToken = clientToken;
        this.applicationId = metadataApplicationId;
        this.specifiedSession = specifiedSession;
    }
    
    private static String buildJSONForEvent(String string, final double n, final Bundle bundle) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("_eventName", (Object)string);
            if (n != 1.0) {
                jsonObject.put("_valueToSum", n);
            }
            if (bundle != null) {
                for (final String s : bundle.keySet()) {
                    final Object value = bundle.get(s);
                    if (!(value instanceof String) && !(value instanceof Number)) {
                        notifyDeveloperError(String.format("Parameter '%s' must be a string or a numeric type.", s));
                    }
                    jsonObject.put(s, value);
                }
            }
        }
        catch (JSONException ex) {
            notifyDeveloperError(ex.toString());
            return null;
        }
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)jsonObject);
        string = jsonArray.toString();
        return string;
    }
    
    private void logEventNow(final String s, final double n, final Bundle bundle) {
        Settings.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final String access$000 = buildJSONForEvent(s, n, bundle);
                if (access$000 != null) {
                    final GraphObject create = GraphObject.Factory.create();
                    create.setProperty("event", "CUSTOM_APP_EVENTS");
                    create.setProperty("custom_events", access$000);
                    if (Utility.queryAppAttributionSupportAndWait(InsightsLogger.this.applicationId)) {
                        final String attributionId = Settings.getAttributionId(InsightsLogger.this.context.getContentResolver());
                        if (attributionId != null) {
                            create.setProperty("attribution", attributionId);
                        }
                    }
                    final String format = String.format("%s/activities", InsightsLogger.this.applicationId);
                    try {
                        final Response executeAndWait = Request.newPostRequest(InsightsLogger.this.sessionToLogTo(), format, create, null).executeAndWait();
                        if (executeAndWait.getError() != null && executeAndWait.getError().getErrorCode() != -1) {
                            notifyDeveloperError(String.format("Error publishing Insights event '%s'\n  Response: %s\n  Error: %s", access$000, executeAndWait.toString(), executeAndWait.getError().toString()));
                        }
                    }
                    catch (Exception ex) {
                        Utility.logd("Insights-exception: ", ex);
                    }
                }
            }
        });
    }
    
    public static InsightsLogger newLogger(final Context context, final String s) {
        return new InsightsLogger(context, s, null, null);
    }
    
    public static InsightsLogger newLogger(final Context context, final String s, final String s2) {
        return new InsightsLogger(context, s, s2, null);
    }
    
    public static InsightsLogger newLogger(final Context context, final String s, final String s2, final Session session) {
        return new InsightsLogger(context, s, s2, session);
    }
    
    private static void notifyDeveloperError(final String s) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "Insights", s);
    }
    
    private Session sessionToLogTo() {
        synchronized (this) {
            final Session specifiedSession = this.specifiedSession;
            Session activeSession = null;
            Label_0024: {
                if (specifiedSession != null) {
                    activeSession = specifiedSession;
                    if (specifiedSession.isOpened()) {
                        break Label_0024;
                    }
                }
                activeSession = Session.getActiveSession();
            }
            if (activeSession != null && activeSession.isOpened()) {
                final Session appAuthSession = activeSession;
                if (activeSession.getAccessToken() != null) {
                    return appAuthSession;
                }
            }
            if (InsightsLogger.appAuthSession == null) {
                (InsightsLogger.appAuthSession = new Session(null, this.applicationId, new NonCachingTokenCachingStrategy(), false)).open(AccessToken.createFromString(String.format("%s|%s", this.applicationId, this.clientToken), null, AccessTokenSource.CLIENT_TOKEN), null);
            }
            return InsightsLogger.appAuthSession;
        }
    }
    
    public void logConversionPixel(final String s, final double n) {
        if (s == null) {
            notifyDeveloperError("pixelID cannot be null");
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("fb_offsite_pixel_id", s);
        bundle.putDouble("fb_offsite_pixel_value", n);
        this.logEventNow("fb_log_offsite_pixel", n, bundle);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency) {
        this.logPurchase(bigDecimal, currency, null);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
            return;
        }
        if (currency == null) {
            notifyDeveloperError("currency cannot be null");
            return;
        }
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("fb_currency", currency.getCurrencyCode());
        this.logEventNow("fb_mobile_purchase", bigDecimal.doubleValue(), bundle2);
    }
}
