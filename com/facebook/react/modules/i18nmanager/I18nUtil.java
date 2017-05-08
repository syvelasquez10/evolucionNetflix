// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.i18nmanager;

import android.content.SharedPreferences$Editor;
import android.content.Context;
import android.support.v4.text.TextUtilsCompat;
import java.util.Locale;

public class I18nUtil
{
    private static I18nUtil sharedI18nUtilInstance;
    
    static {
        I18nUtil.sharedI18nUtilInstance = null;
    }
    
    public static I18nUtil getInstance() {
        if (I18nUtil.sharedI18nUtilInstance == null) {
            I18nUtil.sharedI18nUtilInstance = new I18nUtil();
        }
        return I18nUtil.sharedI18nUtilInstance;
    }
    
    private boolean isDevicePreferredLanguageRTL() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
    
    private boolean isPrefSet(final Context context, final String s, final boolean b) {
        return context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean(s, b);
    }
    
    private boolean isRTLAllowed(final Context context) {
        return this.isPrefSet(context, "RCTI18nUtil_allowRTL", true);
    }
    
    private boolean isRTLForced(final Context context) {
        return this.isPrefSet(context, "RCTI18nUtil_forceRTL", false);
    }
    
    private void setPref(final Context context, final String s, final boolean b) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
        edit.putBoolean(s, b);
        edit.apply();
    }
    
    public void allowRTL(final Context context, final boolean b) {
        this.setPref(context, "RCTI18nUtil_allowRTL", b);
    }
    
    public void forceRTL(final Context context, final boolean b) {
        this.setPref(context, "RCTI18nUtil_forceRTL", b);
    }
    
    public boolean isRTL(final Context context) {
        return this.isRTLForced(context) || (this.isRTLAllowed(context) && this.isDevicePreferredLanguageRTL());
    }
}
