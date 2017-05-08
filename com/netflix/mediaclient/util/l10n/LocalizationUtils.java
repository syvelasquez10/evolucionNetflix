// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.l10n;

import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.app.Application;
import android.view.View;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.annotation.TargetApi;
import java.util.Locale;

public final class LocalizationUtils
{
    public static final char EMBEDDING_LTR = '\u202a';
    public static final char EMBEDDING_RTL = '\u202b';
    public static final char FORCED_LTR = '\u200e';
    public static final char FORCED_RTL = '\u200f';
    public static final char POP_DIRECTIONAL_FOMATTING = '\u202c';
    private static final String TAG = "nf_locale";
    
    public static StringBuilder addMarkerForRtLocale(final StringBuilder sb, final char c) {
        if (sb != null && isCurrentLocaleRTL()) {
            sb.append(c);
        }
        return sb;
    }
    
    public static CharSequence forceLayoutDirectionIfNeeded(final CharSequence charSequence) {
        if (charSequence == null || !isCurrentLocaleRTL()) {
            return charSequence;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('\u200f');
        sb.append(charSequence);
        return sb.toString();
    }
    
    public static boolean isCurrentLocaleRTL() {
        return isLocaleRTL(Locale.getDefault());
    }
    
    @TargetApi(17)
    public static boolean isLocaleLTR(final Locale locale) {
        return !isLocaleRTL(locale);
    }
    
    @TargetApi(17)
    public static boolean isLocaleRTL(final Locale locale) {
        boolean b = true;
        if (AndroidUtils.getAndroidVersion() < 17) {
            Log.d("nf_locale", "Device does not support RTL, return false by default");
            return false;
        }
        Log.d("nf_locale", "Device does support RTL, return what locale supports.");
        if (TextUtils.getLayoutDirectionFromLocale(locale) != 1) {
            b = false;
        }
        return b;
    }
    
    @TargetApi(17)
    public static void setLayoutDirection(final View view) {
        if (view != null && AndroidUtils.getAndroidVersion() >= 17 && isLocaleRTL(Locale.getDefault())) {
            view.setLayoutDirection(1);
        }
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
