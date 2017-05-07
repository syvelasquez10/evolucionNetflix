// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Currency;
import java.math.BigDecimal;
import android.os.Bundle;
import com.facebook.internal.Logger;
import android.content.Context;

@Deprecated
public class InsightsLogger
{
    private static final String EVENT_NAME_LOG_CONVERSION_PIXEL = "fb_log_offsite_pixel";
    private static final String EVENT_PARAMETER_PIXEL_ID = "fb_offsite_pixel_id";
    private static final String EVENT_PARAMETER_PIXEL_VALUE = "fb_offsite_pixel_value";
    private AppEventsLogger appEventsLogger;
    
    private InsightsLogger(final Context context, final String s, final Session session) {
        this.appEventsLogger = AppEventsLogger.newLogger(context, s, session);
    }
    
    public static InsightsLogger newLogger(final Context context, final String s) {
        return new InsightsLogger(context, null, null);
    }
    
    public static InsightsLogger newLogger(final Context context, final String s, final String s2) {
        return new InsightsLogger(context, s2, null);
    }
    
    public static InsightsLogger newLogger(final Context context, final String s, final String s2, final Session session) {
        return new InsightsLogger(context, s2, session);
    }
    
    public void logConversionPixel(final String s, final double n) {
        if (s == null) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "Insights", "pixelID cannot be null");
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("fb_offsite_pixel_id", s);
        bundle.putDouble("fb_offsite_pixel_value", n);
        this.appEventsLogger.logEvent("fb_log_offsite_pixel", n, bundle);
        AppEventsLogger.eagerFlush();
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency) {
        this.logPurchase(bigDecimal, currency, null);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle) {
        this.appEventsLogger.logPurchase(bigDecimal, currency, bundle);
    }
}
