// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.l10n;

import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.res.Configuration;
import com.netflix.mediaclient.Log;
import android.app.Application;
import java.util.Locale;
import android.view.View;
import com.netflix.android.widgetry.utils.UiUtils;

public final class LocalizationUtils
{
    private static final String TAG = "nf_locale";
    
    public static boolean isCurrentLocaleRTL() {
        return UiUtils.isCurrentLocaleRTL();
    }
    
    public static String prependBidiMarkerIfRtl(final String s, final BidiMarker bidiMarker) {
        return UiUtils.prependBidiMarkerIfRtl(s, bidiMarker);
    }
    
    public static void setRtlLayoutDirectionIfApplicable(final View rtlLayoutDirectionIfApplicable) {
        UiUtils.setRtlLayoutDirectionIfApplicable(rtlLayoutDirectionIfApplicable);
    }
    
    public static void updateLocale(final Locale locale, final Application application) {
        if (Log.isLoggable()) {
            Log.d("nf_locale", "refreshLocale with language = " + locale);
        }
        final Locale default1 = Locale.getDefault();
        if (Log.isLoggable()) {
            Log.d("nf_locale", "Changing language from " + default1 + " to " + locale);
        }
        Locale.setDefault(locale);
        final Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        final Resources resources = application.getResources();
        if (resources == null) {
            Log.w("nf_locale", "NA::refreshLocale: Resources are NULL. It should NOT happen!");
            return;
        }
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (displayMetrics == null) {
            Log.w("nf_locale", "NA::refreshLocale: DisplayMetrics is NULL. It should NOT happen!");
            return;
        }
        try {
            resources.updateConfiguration(configuration, displayMetrics);
        }
        catch (Exception ex) {
            Log.e("nf_locale", "NA::updateLocale: Failed to update configuration", ex);
        }
    }
}
