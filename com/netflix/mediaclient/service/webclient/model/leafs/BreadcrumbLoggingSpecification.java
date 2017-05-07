// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class BreadcrumbLoggingSpecification
{
    private static final boolean DEFAULT_DISABLE = false;
    private static final int DEFAULT_DISABLE_CHANCE_PERCENTAGE = 50;
    private static String TAG;
    @SerializedName("disable")
    private boolean disable;
    @SerializedName("disableChancePercentage")
    private int disableChancePercentage;
    @SerializedName("implementation")
    private String implementation;
    
    static {
        BreadcrumbLoggingSpecification.TAG = "nf_log";
    }
    
    public static BreadcrumbLoggingSpecification getDefault() {
        final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification = new BreadcrumbLoggingSpecification();
        breadcrumbLoggingSpecification.disable = false;
        breadcrumbLoggingSpecification.disableChancePercentage = 50;
        return breadcrumbLoggingSpecification;
    }
    
    public static BreadcrumbLoggingSpecification loadFromPreferences(Context context) {
        final Context context2 = null;
        final Context context3 = null;
        final String stringPref = PreferenceUtils.getStringPref(context, "breadcrumb_log_configuration", null);
        if (StringUtils.isEmpty(stringPref)) {
            Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb specification not found in file system");
            context = context3;
        }
        else {
            context = context2;
            try {
                final Object o = context = (Context)FalcorParseUtils.getGson().fromJson(stringPref, BreadcrumbLoggingSpecification.class);
                Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb logging specification loaded from file system");
                context = (Context)o;
            }
            catch (Throwable t) {
                Log.e(BreadcrumbLoggingSpecification.TAG, "Failed to load Breadcrumb logging specification from file system", t);
            }
        }
        Object default1 = context;
        if (context == null) {
            default1 = getDefault();
        }
        return (BreadcrumbLoggingSpecification)default1;
    }
    
    public static BreadcrumbLoggingSpecification saveToPreferences(final Context context, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        if (breadcrumbLoggingSpecification == null) {
            PreferenceUtils.removePref(context, "breadcrumb_log_configuration");
            Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb logging spec not found, return default");
            return getDefault();
        }
        PreferenceUtils.putStringPref(context, "breadcrumb_log_configuration", FalcorParseUtils.getGson().toJson(breadcrumbLoggingSpecification));
        return breadcrumbLoggingSpecification;
    }
    
    public int getDisableChancePercentage() {
        return this.disableChancePercentage;
    }
    
    public String getImplementation() {
        return this.implementation;
    }
    
    public boolean isDisabled() {
        return this.disable;
    }
}
