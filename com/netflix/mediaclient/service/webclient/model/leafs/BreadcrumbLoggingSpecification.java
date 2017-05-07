// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
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
        final String stringPref = PreferenceUtils.getStringPref(context, "breadcrumb_log_configuration", null);
        Label_0028: {
            if (StringUtils.isEmpty(stringPref)) {
                Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb specification not found in file system");
                context = null;
            }
            else {
                while (true) {
                    try {
                        context = (Context)FalkorParseUtils.getGson().fromJson(stringPref, BreadcrumbLoggingSpecification.class);
                        try {
                            Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb logging specification loaded from file system");
                            break Label_0028;
                        }
                        catch (Throwable t2) {}
                        final Throwable t;
                        Log.e(BreadcrumbLoggingSpecification.TAG, "Failed to load Breadcrumb logging specification from file system", t);
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
        return (BreadcrumbLoggingSpecification)default1;
    }
    
    public static BreadcrumbLoggingSpecification saveToPreferences(final Context context, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        if (breadcrumbLoggingSpecification == null) {
            PreferenceUtils.removePref(context, "breadcrumb_log_configuration");
            Log.d(BreadcrumbLoggingSpecification.TAG, "Breadcrumb logging spec not found, return default");
            return getDefault();
        }
        PreferenceUtils.putStringPref(context, "breadcrumb_log_configuration", FalkorParseUtils.getGson().toJson(breadcrumbLoggingSpecification));
        return breadcrumbLoggingSpecification;
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
            final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification = (BreadcrumbLoggingSpecification)o;
            if (this.disable != breadcrumbLoggingSpecification.disable) {
                return false;
            }
            if (this.disableChancePercentage != breadcrumbLoggingSpecification.disableChancePercentage) {
                return false;
            }
            if (this.implementation == null) {
                if (breadcrumbLoggingSpecification.implementation != null) {
                    return false;
                }
            }
            else if (!this.implementation.equals(breadcrumbLoggingSpecification.implementation)) {
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
        final int disableChancePercentage = this.disableChancePercentage;
        int hashCode;
        if (this.implementation == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.implementation.hashCode();
        }
        return hashCode + ((n + 31) * 31 + disableChancePercentage) * 31;
    }
    
    public boolean isDisabled() {
        return this.disable;
    }
    
    @Override
    public String toString() {
        return "BreadcrumbLoggingSpecification [implementation=" + this.implementation + ", disable=" + this.disable + ", disableChancePercentage=" + this.disableChancePercentage + "]";
    }
}
