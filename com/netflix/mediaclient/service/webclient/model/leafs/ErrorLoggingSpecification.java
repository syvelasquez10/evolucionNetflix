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
        final String stringPref = PreferenceUtils.getStringPref(context, "error_log_configuration", null);
        Label_0028: {
            if (StringUtils.isEmpty(stringPref)) {
                Log.d(ErrorLoggingSpecification.TAG, "Error specification not found in file system");
                context = null;
            }
            else {
                while (true) {
                    try {
                        context = (Context)FalcorParseUtils.getGson().fromJson(stringPref, ErrorLoggingSpecification.class);
                        try {
                            Log.d(ErrorLoggingSpecification.TAG, "Error logging specification loaded from file system");
                            break Label_0028;
                        }
                        catch (Throwable t2) {}
                        final Throwable t;
                        Log.e(ErrorLoggingSpecification.TAG, "Failed to load error logging specification from file system", t);
                    }
                    catch (Throwable t) {
                        context = context2;
                        continue;
                    }
                    break;
                }
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
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final ErrorLoggingSpecification errorLoggingSpecification = (ErrorLoggingSpecification)o;
            if (this.disable != errorLoggingSpecification.disable) {
                return false;
            }
            if (this.disableChancePercentage != errorLoggingSpecification.disableChancePercentage) {
                return false;
            }
        }
        return true;
    }
    
    public int getDisableChancePercentage() {
        return this.disableChancePercentage;
    }
    
    public String getImplementation() {
        return this.implementation;
    }
    
    @Override
    public int hashCode() {
        int n;
        if (this.disable) {
            n = 1231;
        }
        else {
            n = 1237;
        }
        return (n + 31) * 31 + this.disableChancePercentage;
    }
    
    public boolean isDisabled() {
        return this.disable;
    }
    
    @Override
    public String toString() {
        return "ErrorLoggingSpecification [implementation=" + this.implementation + ", disable=" + this.disable + ", disableChancePercentage=" + this.disableChancePercentage + "]";
    }
}
