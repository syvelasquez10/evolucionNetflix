// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.repository.UserLocale;
import java.util.List;
import android.content.Context;

public class NflxSupportedLocales
{
    public static final String TAG = "nf_config_locale";
    private static Context mContext;
    public List<UserLocale> locales;
    
    public NflxSupportedLocales() {
    }
    
    public NflxSupportedLocales(final Context mContext) {
        NflxSupportedLocales.mContext = mContext;
        this.locales = fromJson(PreferenceUtils.getStringPref(NflxSupportedLocales.mContext, "nflxLanguages", null));
    }
    
    public static NflxSupportedLocales create(final JsonObject jsonObject) {
        final NflxSupportedLocales nflxSupportedLocales = new NflxSupportedLocales();
        final JsonElement value = jsonObject.get("supportedLanguages");
        if (value != null) {
            Log.d("nf_config_locale", "create: locales" + value.toString());
            nflxSupportedLocales.locales = fromJson(value.toString());
        }
        return nflxSupportedLocales;
    }
    
    private static List<UserLocale> fromJson(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            return FalkorParseUtils.getGson().fromJson(s, new NflxSupportedLocales$1().getType());
        }
        return null;
    }
    
    private void updateInPreference(final Context context, final List<UserLocale> list, final String s) {
        final String json = FalkorParseUtils.getGson().toJson(list);
        if (Log.isLoggable()) {
            Log.d("nf_config_locale", String.format("Persisting to %s  locales: %s ", s, json));
        }
        PreferenceUtils.putStringPref(context, s, json);
    }
    
    public UserLocale getAlertLocale(final String s) {
        for (final UserLocale userLocale : this.locales) {
            if (StringUtils.safeEquals(s, userLocale.getLanguage())) {
                return userLocale;
            }
        }
        return null;
    }
    
    public List<UserLocale> getAlertedLocales() {
        final List<UserLocale> fromJson = fromJson(PreferenceUtils.getStringPref(NflxSupportedLocales.mContext, "alertedLanguages", null));
        Log.d("nf_config_locale", String.format("getAlertedLocales: %s", fromJson));
        return fromJson;
    }
    
    public void persistNflxSupportedLocales(final NflxSupportedLocales nflxSupportedLocales) {
        if (nflxSupportedLocales == null || nflxSupportedLocales.locales == null) {
            return;
        }
        this.updateInPreference(NflxSupportedLocales.mContext, nflxSupportedLocales.locales, "nflxLanguages");
        this.locales = nflxSupportedLocales.locales;
    }
    
    public void setAlertedLocale(final Context context, final UserLocale userLocale) {
        List<UserLocale> alertedLocales = this.getAlertedLocales();
        if (alertedLocales == null) {
            alertedLocales = new ArrayList<UserLocale>();
        }
        else {
            final Iterator<UserLocale> iterator = alertedLocales.iterator();
            while (iterator.hasNext()) {
                if (StringUtils.safeEquals(userLocale.getLanguage(), iterator.next().getLanguage())) {
                    Log.d("nf_config_locale", String.format("skip - locale: %s already exists in alertedLocales", userLocale, alertedLocales));
                    return;
                }
            }
        }
        alertedLocales.add(userLocale);
        this.updateInPreference(context, alertedLocales, "alertedLanguages");
    }
}
