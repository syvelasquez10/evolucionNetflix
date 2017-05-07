// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class ErrorLoggingSpecification
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
        ErrorLoggingSpecification.TAG = "nf_log";
    }
    
    public static ErrorLoggingSpecification getDefault() {
        final ErrorLoggingSpecification errorLoggingSpecification = new ErrorLoggingSpecification();
        errorLoggingSpecification.disable = false;
        errorLoggingSpecification.disableChancePercentage = 50;
        return errorLoggingSpecification;
    }
    
    public static ErrorLoggingSpecification loadFromPreferences(Context context) {
        final Context context2 = null;
        final Context context3 = null;
        final String stringPref = PreferenceUtils.getStringPref(context, "error_log_configuration", null);
        if (StringUtils.isEmpty(stringPref)) {
            Log.d(ErrorLoggingSpecification.TAG, "Error specification not found in file system");
            context = context3;
        }
        else {
            context = context2;
            try {
                final Object o = context = (Context)FalcorParseUtils.getGson().fromJson(stringPref, ErrorLoggingSpecification.class);
                Log.d(ErrorLoggingSpecification.TAG, "Error logging specification loaded from file system");
                context = (Context)o;
            }
            catch (Throwable t) {
                Log.e(ErrorLoggingSpecification.TAG, "Failed to load error logging specification from file system", t);
            }
        }
        Object default1 = context;
        if (context == null) {
            default1 = getDefault();
        }
        return (ErrorLoggingSpecification)default1;
    }
    
    public static ErrorLoggingSpecification saveToPreferences(final Context context, final ErrorLoggingSpecification errorLoggingSpecification) {
        if (errorLoggingSpecification == null) {
            PreferenceUtils.removePref(context, "error_log_configuration");
            Log.d(ErrorLoggingSpecification.TAG, "Breadcrumb logging spec not found, return default");
            return getDefault();
        }
        PreferenceUtils.putStringPref(context, "error_log_configuration", FalcorParseUtils.getGson().toJson(errorLoggingSpecification));
        return errorLoggingSpecification;
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
