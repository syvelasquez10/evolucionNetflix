// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import org.json.JSONException;
import java.util.Random;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import android.os.Build;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.app.Application;
import android.annotation.TargetApi;

@TargetApi(4)
public final class ErrorLoggingManager
{
    private static final String CRITTER_VERSION_NAME = "3.9.1";
    private static final boolean ENABLE_CRITTERCISM = true;
    private static final String TAG = "nf_log_crit";
    private static boolean sBreadcrumbLoggingEnabled;
    private static boolean sBreadcrumbLoggingExplicitlyDisabled;
    private static boolean sCrittercismReady;
    private static boolean sErrorLoggingEnabledByConfig;
    private static boolean sErrorLoggingExplicitlyDisabled;
    
    static {
        ErrorLoggingManager.sErrorLoggingEnabledByConfig = true;
        ErrorLoggingManager.sErrorLoggingExplicitlyDisabled = false;
        ErrorLoggingManager.sBreadcrumbLoggingEnabled = true;
        ErrorLoggingManager.sBreadcrumbLoggingExplicitlyDisabled = false;
    }
    
    public static void init(final Application application) {
        synchronized (ErrorLoggingManager.class) {
            Log.d("nf_log_crit", "Crittercism init starts...");
            initErrorLogging((Context)application);
            initBreadcrumbLogging((Context)application);
            final CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setNdkCrashReportingEnabled(true);
            crittercismConfig.setCustomVersionName("3.9.1");
            try {
                Crittercism.initialize(application.getApplicationContext(), SecurityRepository.getCrittercismAppId(), crittercismConfig);
                final JSONObject metadata = new JSONObject();
                metadata.put("android", Build$VERSION.SDK_INT);
                putValueOrNotAvailable(metadata, "oem", Build.MANUFACTURER);
                putValueOrNotAvailable(metadata, "model", Build.MODEL);
                Crittercism.setMetadata(metadata);
                ErrorLoggingManager.sCrittercismReady = true;
                Log.d("nf_log_crit", "Init Crittercism done.");
            }
            catch (Throwable t) {
                Log.e("nf_log_crit", "Unable to build crittercism's config json object", t);
            }
        }
    }
    
    private static void initBreadcrumbLogging(final Context context) {
        final BreadcrumbLoggingSpecification loadFromPreferences = BreadcrumbLoggingSpecification.loadFromPreferences(context);
        ErrorLoggingManager.sBreadcrumbLoggingExplicitlyDisabled = loadFromPreferences.isDisabled();
        if (loadFromPreferences.isDisabled()) {
            Log.d("nf_log_crit", "Breadcrumb logging is explicitly disabled");
            ErrorLoggingManager.sBreadcrumbLoggingEnabled = false;
            return;
        }
        Log.d("nf_log_crit", "Breadcrumb logging is NOT explicitly disabled, use error logging chance to keep all in sync since we are using only one service, ignore its own disable chance percentage");
        ErrorLoggingManager.sBreadcrumbLoggingEnabled = ErrorLoggingManager.sErrorLoggingEnabledByConfig;
    }
    
    private static void initErrorLogging(final Context context) {
        final ErrorLoggingSpecification loadFromPreferences = ErrorLoggingSpecification.loadFromPreferences(context);
        ErrorLoggingManager.sErrorLoggingExplicitlyDisabled = loadFromPreferences.isDisabled();
        if (loadFromPreferences.isDisabled()) {
            Log.d("nf_log_crit", "Error logging is explicitly disabled");
            ErrorLoggingManager.sErrorLoggingEnabledByConfig = false;
            return;
        }
        Log.d("nf_log_crit", "Error logging is NOT explicitly disabled, apply disable chance percentage");
        ErrorLoggingManager.sErrorLoggingEnabledByConfig = isLoggingSessionEnabled(loadFromPreferences.getDisableChancePercentage());
    }
    
    public static boolean isCrittercismEnabled() {
        return true;
    }
    
    private static boolean isEnabledAndReady() {
        return isCrittercismEnabled() && ErrorLoggingManager.sCrittercismReady;
    }
    
    private static boolean isLoggingSessionEnabled(final int n) {
        boolean b = true;
        boolean b2 = true;
        if (n <= 0) {
            Log.d("nf_log_crit", "Logging session is enabled without restrictions");
        }
        else {
            if (n >= 100) {
                Log.d("nf_log_crit", "Logging session is disabled");
                return true;
            }
            if (Log.isLoggable("nf_log_crit", 3)) {
                Log.d("nf_log_crit", "Logging session is enabled with restriction that " + n + " of app sessions will not be logged.");
            }
            final int nextInt = new Random().nextInt(100);
            if (nextInt < n) {
                b = false;
            }
            b2 = b;
            if (Log.isLoggable("nf_log_crit", 3)) {
                Log.d("nf_log_crit", "Rnd value " + nextInt + ", session is enabled " + b);
                return b;
            }
        }
        return b2;
    }
    
    public static void leaveBreadcrumb(final String s) {
        if (isEnabledAndReady() && ErrorLoggingManager.sBreadcrumbLoggingEnabled) {
            Crittercism.leaveBreadcrumb(s);
        }
        else if (Log.isLoggable("nf_log_crit", 3)) {
            Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", breadcrumb logging enabled " + ErrorLoggingManager.sBreadcrumbLoggingEnabled);
        }
    }
    
    public static void logHandledException(final Exception ex) {
        if (isEnabledAndReady() && ErrorLoggingManager.sErrorLoggingEnabledByConfig) {
            Crittercism.logHandledException(ex);
        }
        else if (Log.isLoggable("nf_log_crit", 3)) {
            Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", error logging enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
        }
    }
    
    public static void logHandledException(final String s) {
        if (isEnabledAndReady() && ErrorLoggingManager.sErrorLoggingEnabledByConfig) {
            logHandledException(new Exception(s));
        }
        else if (Log.isLoggable("nf_log_crit", 3)) {
            Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", error logging enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
        }
    }
    
    public static void onConfigurationChanged(final ErrorLoggingSpecification errorLoggingSpecification, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        final boolean b = true;
        if (errorLoggingSpecification == null) {
            Log.e("nf_log_crit", "Error logging specification is missing. It should NOT happen!");
        }
        else {
            if (Log.isLoggable("nf_log_crit", 3)) {
                Log.d("nf_log_crit", "Error logging was explicitly disabled" + ErrorLoggingManager.sErrorLoggingExplicitlyDisabled + " now it will be explicitly disabled " + errorLoggingSpecification.isDisabled());
            }
            if (ErrorLoggingManager.sErrorLoggingExplicitlyDisabled != errorLoggingSpecification.isDisabled()) {
                Log.d("nf_log_crit", "Error logging is now explicitly disabled!");
                ErrorLoggingManager.sErrorLoggingEnabledByConfig = !errorLoggingSpecification.isDisabled();
                ErrorLoggingManager.sErrorLoggingExplicitlyDisabled = errorLoggingSpecification.isDisabled();
            }
            else {
                Log.d("nf_log_crit", "No change");
            }
        }
        if (breadcrumbLoggingSpecification == null) {
            Log.e("nf_log_crit", "Breadcrumb logging specification is missing. It should NOT happen!");
            return;
        }
        if (Log.isLoggable("nf_log_crit", 3)) {
            Log.d("nf_log_crit", "Breadcrumb logging was explicitly disabled " + ErrorLoggingManager.sBreadcrumbLoggingExplicitlyDisabled + " now it will explicitly disabled " + breadcrumbLoggingSpecification.isDisabled());
        }
        if (!ErrorLoggingManager.sBreadcrumbLoggingExplicitlyDisabled && errorLoggingSpecification.isDisabled()) {
            Log.d("nf_log_crit", "Breadcrumb logging is now explicitly disabled!");
            ErrorLoggingManager.sBreadcrumbLoggingEnabled = (!breadcrumbLoggingSpecification.isDisabled() && b);
            ErrorLoggingManager.sBreadcrumbLoggingExplicitlyDisabled = errorLoggingSpecification.isDisabled();
            return;
        }
        Log.d("nf_log_crit", "No change");
    }
    
    private static void putValueOrNotAvailable(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
            return;
        }
        jsonObject.put(s, (Object)"N/A");
    }
}
