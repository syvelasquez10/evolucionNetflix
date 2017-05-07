// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import android.os.Build;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(4)
public final class ErrorLoggingManager
{
    private static final String CRITTER_VERSION_NAME = "3.9.2";
    private static final boolean ENABLE_CRITTERCISM = true;
    private static final String TAG = "nf_log_crit";
    private static boolean sBreadcrumbLoggingEnabled;
    private static boolean sCrittercismReady;
    private static boolean sErrorLoggingEnabledByConfig;
    
    static {
        ErrorLoggingManager.sErrorLoggingEnabledByConfig = false;
        ErrorLoggingManager.sBreadcrumbLoggingEnabled = false;
    }
    
    private static boolean configureBreadcrumbLogging(final Context context, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        if (breadcrumbLoggingSpecification.isDisabled()) {
            Log.d("nf_log_crit", "Breadcrumb logging is explicitly disabled");
            ErrorLoggingManager.sBreadcrumbLoggingEnabled = false;
        }
        else {
            Log.d("nf_log_crit", "Breadcrumb logging is NOT explicitly disabled, use error logging chance to keep all in sync since we are using only one service, ignore its own disable chance percentage");
            ErrorLoggingManager.sBreadcrumbLoggingEnabled = ErrorLoggingManager.sErrorLoggingEnabledByConfig;
        }
        return ErrorLoggingManager.sBreadcrumbLoggingEnabled;
    }
    
    private static boolean configureErrorLogging(final Context context, final ErrorLoggingSpecification errorLoggingSpecification) {
        if (errorLoggingSpecification.isDisabled()) {
            Log.d("nf_log_crit", "Error logging is explicitly disabled");
            ErrorLoggingManager.sErrorLoggingEnabledByConfig = false;
        }
        else {
            Log.d("nf_log_crit", "Error logging is NOT explicitly disabled, apply disable chance percentage");
            ErrorLoggingManager.sErrorLoggingEnabledByConfig = DeviceUtils.isDeviceEnabled(context, errorLoggingSpecification.getDisableChancePercentage());
        }
        if (Log.isLoggable("nf_log_crit", 3)) {
            Log.d("nf_log_crit", "External logging for this device is enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
        }
        return ErrorLoggingManager.sErrorLoggingEnabledByConfig;
    }
    
    private static void initCrittercism(final Context context) {
        while (true) {
            Label_0044: {
                synchronized (ErrorLoggingManager.class) {
                    if (!isCrittercismEnabled()) {
                        Log.w("nf_log_crit", "Crittercism is NOT enabled in build!");
                    }
                    else {
                        if (!ErrorLoggingManager.sCrittercismReady) {
                            break Label_0044;
                        }
                        Log.w("nf_log_crit", "Crittercism is already initialized");
                    }
                    return;
                }
            }
            Log.d("nf_log_crit", "Crittercism was not initialized before...");
            final CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setNdkCrashReportingEnabled(true);
            crittercismConfig.setCustomVersionName("3.9.2");
            try {
                final Context context2;
                Crittercism.initialize(context2, SecurityRepository.getCrittercismAppId(), crittercismConfig);
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
    
    public static boolean isCrittercismEnabled() {
        return true;
    }
    
    private static boolean isEnabledAndReady() {
        return isCrittercismEnabled() && ErrorLoggingManager.sCrittercismReady;
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
    
    public static void onConfigurationChanged(final Context context, final ErrorLoggingSpecification errorLoggingSpecification, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        final boolean b = true;
        // monitorenter(ErrorLoggingManager.class)
        Label_0050: {
            if (errorLoggingSpecification != null) {
                break Label_0050;
            }
        Label_0030_Outer:
            while (true) {
                while (true) {
                    StringBuilder sb;
                    boolean b2;
                    boolean b3;
                    boolean sErrorLoggingEnabledByConfig;
                    Label_0172:Label_0133_Outer:
                    while (true) {
                    Label_0133:
                        while (true) {
                        Label_0058_Outer:
                            while (true) {
                            Block_12_Outer:
                                while (true) {
                                    try {
                                        Log.e("nf_log_crit", "Error logging specification is missing. It should NOT happen!");
                                        if (breadcrumbLoggingSpecification == null) {
                                            Log.e("nf_log_crit", "Breadcrumb logging specification is missing. It should NOT happen!");
                                            initCrittercism(context);
                                            configureErrorLogging(context, errorLoggingSpecification);
                                            configureBreadcrumbLogging(context, breadcrumbLoggingSpecification);
                                            return;
                                        }
                                        break Label_0172;
                                        Log.d("nf_log_crit", sb.append(b2).append(" now it will be explicitly disabled ").append(errorLoggingSpecification.isDisabled()).toString());
                                        // iftrue(Label_0161:, b3 == errorLoggingSpecification.isDisabled())
                                        // iftrue(Label_0156:, errorLoggingSpecification.isDisabled())
                                        // iftrue(Label_0124:, !Log.isLoggable("nf_log_crit", 3))
                                        // iftrue(Label_0146:, ErrorLoggingManager.sErrorLoggingEnabledByConfig)
                                        // iftrue(Label_0151:, ErrorLoggingManager.sErrorLoggingEnabledByConfig)
                                        Block_13: {
                                            Block_10: {
                                                Label_0124: {
                                                    break Label_0124;
                                                    break Block_10;
                                                }
                                                break Block_13;
                                            }
                                            while (true) {
                                                Block_11: {
                                                    break Block_11;
                                                    b3 = true;
                                                    continue Block_12_Outer;
                                                    b2 = true;
                                                    continue Label_0058_Outer;
                                                    ErrorLoggingManager.sErrorLoggingEnabledByConfig = sErrorLoggingEnabledByConfig;
                                                    continue Label_0030_Outer;
                                                }
                                                sb = new StringBuilder().append("Error logging was explicitly disabled ");
                                                continue Label_0133_Outer;
                                            }
                                        }
                                        sErrorLoggingEnabledByConfig = true;
                                        continue Label_0133;
                                    }
                                    finally {
                                    }
                                    // monitorexit(ErrorLoggingManager.class)
                                    Label_0146: {
                                        b3 = false;
                                    }
                                    continue Label_0133_Outer;
                                }
                                Label_0151: {
                                    b2 = false;
                                }
                                continue Label_0058_Outer;
                            }
                            Label_0156: {
                                sErrorLoggingEnabledByConfig = false;
                            }
                            continue Label_0133;
                        }
                        Label_0161: {
                            Log.d("nf_log_crit", "No change");
                        }
                        continue Label_0030_Outer;
                    }
                    if (!ErrorLoggingManager.sBreadcrumbLoggingEnabled != breadcrumbLoggingSpecification.isDisabled()) {
                        if (Log.isLoggable("nf_log_crit", 3)) {
                            sb = new StringBuilder().append("Breadcrumb logging was explicitly disabled ");
                            Log.d("nf_log_crit", sb.append(!ErrorLoggingManager.sBreadcrumbLoggingEnabled).append(" now it will explicitly disabled ").append(breadcrumbLoggingSpecification.isDisabled()).toString());
                        }
                        ErrorLoggingManager.sBreadcrumbLoggingEnabled = (!breadcrumbLoggingSpecification.isDisabled() && b);
                        continue;
                    }
                    Log.d("nf_log_crit", "No change");
                    continue;
                }
            }
        }
    }
    
    private static void putValueOrNotAvailable(final JSONObject jsonObject, final String s, final String s2) {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
            return;
        }
        jsonObject.put(s, (Object)"N/A");
    }
}
