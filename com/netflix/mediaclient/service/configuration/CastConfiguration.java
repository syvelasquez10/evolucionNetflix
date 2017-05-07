// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public final class CastConfiguration
{
    private static final String PREFERENCE_CURRENT_CAST_APPID = "preference_key_CURRENT_cast_application_id";
    private static final String PREFERENCE_NEW_CAST_APPID = "preference_key_new_cast_application_id";
    
    public static String getCastApplicationId(final Context context) {
        return PreferenceUtils.getStringPref(context, "preference_key_CURRENT_cast_application_id", null);
    }
    
    public static String getNewCastApplicationId(final Context context) {
        return PreferenceUtils.getStringPref(context, "preference_key_new_cast_application_id", null);
    }
    
    public static void setCastApplicationId(final Context context, final String s) {
        PreferenceUtils.putStringPref(context, "preference_key_CURRENT_cast_application_id", s);
    }
    
    public static void setNewCastApplicationId(final Context context, final String s) {
        PreferenceUtils.putStringPref(context, "preference_key_new_cast_application_id", s);
    }
}
