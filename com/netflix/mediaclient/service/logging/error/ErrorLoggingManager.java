// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.util.log.ExceptionLogClUtils;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Build;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(4)
public final class ErrorLoggingManager
{
    private static final String CRITTER_VERSION_NAME = "4.16.0";
    private static final boolean ENABLE_CRITTERCISM = true;
    private static final boolean INTERCEPT_EXCEPTIONS_FOR_DEBUGGING = true;
    private static final String TAG = "nf_log_crit";
    private static boolean sBreadcrumbLoggingEnabled;
    private static boolean sCrittercismReady;
    private static boolean sErrorLoggingEnabledByConfig;
    private static boolean sIsUncaughtExceptionHandlerForDebugInitialized;
    
    static {
        ErrorLoggingManager.sErrorLoggingEnabledByConfig = false;
        ErrorLoggingManager.sBreadcrumbLoggingEnabled = false;
        ErrorLoggingManager.sIsUncaughtExceptionHandlerForDebugInitialized = false;
    }
    
    private static void configureBreadcrumbLogging(final Context context, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        if (breadcrumbLoggingSpecification == null) {
            Log.e("nf_log_crit", "Breadcrumb logging specification is missing. It should NOT happen!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log_crit", "Breadcrumb logging was explicitly disabled " + !ErrorLoggingManager.sBreadcrumbLoggingEnabled + " now it will explicitly disabled " + breadcrumbLoggingSpecification.isDisabled());
        }
        if (breadcrumbLoggingSpecification.isDisabled()) {
            Log.d("nf_log_crit", "Breadcrumb logging is explicitly disabled");
            ErrorLoggingManager.sBreadcrumbLoggingEnabled = false;
            return;
        }
        Log.d("nf_log_crit", "Breadcrumb logging is NOT explicitly disabled, use error logging chance to keep all in sync since we are using only one service, ignore its own disable chance percentage");
        ErrorLoggingManager.sBreadcrumbLoggingEnabled = ErrorLoggingManager.sErrorLoggingEnabledByConfig;
    }
    
    private static void configureErrorLogging(final Context context, final ErrorLoggingSpecification errorLoggingSpecification) {
        if (errorLoggingSpecification == null) {
            Log.e("nf_log_crit", "Error logging specification is missing. It should NOT happen!");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "Error logging was explicitly disabled " + !ErrorLoggingManager.sErrorLoggingEnabledByConfig + " now it will be explicitly disabled " + errorLoggingSpecification.isDisabled());
            }
            if (errorLoggingSpecification.isDisabled()) {
                Log.d("nf_log_crit", "Error logging is explicitly disabled");
                ErrorLoggingManager.sErrorLoggingEnabledByConfig = false;
            }
            else {
                Log.d("nf_log_crit", "Error logging is NOT explicitly disabled, apply disable chance percentage");
                ErrorLoggingManager.sErrorLoggingEnabledByConfig = DeviceUtils.isDeviceEnabled(context, errorLoggingSpecification.getDisableChancePercentage());
            }
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "External logging for this device is enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
            }
        }
    }
    
    public static boolean didCrashOnLastLoad() {
        return isEnabledAndReady() && ErrorLoggingManager.sErrorLoggingEnabledByConfig && Crittercism.didCrashOnLastLoad();
    }
    
    private static void initCrittercism(final Context context, final long n) {
        while (true) {
            Label_0058: {
                synchronized (ErrorLoggingManager.class) {
                    if (!isCrittercismEnabled()) {
                        Log.w("nf_log_crit", "Crittercism is NOT enabled in build!");
                        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ErrorLoggingManager$1(context, Thread.getDefaultUncaughtExceptionHandler()));
                    }
                    else {
                        if (!ErrorLoggingManager.sCrittercismReady) {
                            break Label_0058;
                        }
                        Log.w("nf_log_crit", "Crittercism is already initialized");
                    }
                    return;
                }
            }
            Log.d("nf_log_crit", "Crittercism was not initialized before...");
            if (shouldInitializeCrittercism()) {
                Log.d("nf_log_crit", "This device is approved for sampling, initialize Crittercism");
                final CrittercismConfig crittercismConfig = new CrittercismConfig();
                crittercismConfig.setNdkCrashReportingEnabled(false);
                crittercismConfig.setVersionCodeToBeIncludedInVersionString(true);
                crittercismConfig.setServiceMonitoringEnabled(false);
                crittercismConfig.setLogcatReportingEnabled(false);
                crittercismConfig.setCustomVersionName("4.16.0");
                try {
                    final Context context2;
                    Crittercism.initialize(context2, SecurityRepository.getCrittercismAppId(), crittercismConfig);
                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ErrorLoggingManager$2(context2, context2.getApplicationContext(), Thread.getDefaultUncaughtExceptionHandler()));
                    final JSONObject metadata = new JSONObject();
                    metadata.put("android", Build$VERSION.SDK_INT);
                    putValueOrNotAvailable(metadata, "oem", Build.MANUFACTURER);
                    putValueOrNotAvailable(metadata, "model", Build.MODEL);
                    putValueOrNotAvailable(metadata, "critSessionId", n + "");
                    Crittercism.setMetadata(metadata);
                    ErrorLoggingManager.sCrittercismReady = true;
                    Log.d("nf_log_crit", "Init Crittercism done.");
                }
                catch (Throwable t) {
                    Log.e("nf_log_crit", "Unable to init crittercism", t);
                }
                return;
            }
            Log.d("nf_log_crit", "This device is NOT approved for sampling");
        }
    }
    
    private static void initializeUncaughtExceptionHandlerForDebug(final Context context) {
    }
    
    public static boolean isCrittercismEnabled() {
        return true;
    }
    
    private static boolean isEnabledAndReady() {
        return isCrittercismEnabled() && ErrorLoggingManager.sCrittercismReady;
    }
    
    public static void leaveBreadcrumb(final String s) {
        if (!StringUtils.isEmpty(s)) {
            if (isEnabledAndReady() && ErrorLoggingManager.sBreadcrumbLoggingEnabled) {
                Crittercism.leaveBreadcrumb(s);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", breadcrumb logging enabled: " + ErrorLoggingManager.sBreadcrumbLoggingEnabled + ", breadcrumb: " + s);
            }
        }
    }
    
    public static void logHandledException(final String s) {
        if (!StringUtils.isEmpty(s)) {
            ExceptionLogClUtils.reportHandledExceptionToCL(NetflixApplication.getContext(), (Throwable)new Exception(s));
            if (isEnabledAndReady() && ErrorLoggingManager.sErrorLoggingEnabledByConfig) {
                logHandledException(new Exception(s));
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", error logging enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
            }
        }
    }
    
    public static void logHandledException(final Throwable t) {
        if (t != null) {
            ExceptionLogClUtils.reportHandledExceptionToCL(NetflixApplication.getContext(), t);
            if (isEnabledAndReady() && ErrorLoggingManager.sErrorLoggingEnabledByConfig) {
                Crittercism.logHandledException(t);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", error logging enabled " + ErrorLoggingManager.sErrorLoggingEnabledByConfig);
            }
        }
    }
    
    public static void onConfigurationChanged(final Context context, final long n, final ErrorLoggingSpecification errorLoggingSpecification, final BreadcrumbLoggingSpecification breadcrumbLoggingSpecification) {
        synchronized (ErrorLoggingManager.class) {
            configureErrorLogging(context, errorLoggingSpecification);
            configureBreadcrumbLogging(context, breadcrumbLoggingSpecification);
            initCrittercism(context, n);
        }
    }
    
    private static void putValueOrNotAvailable(final JSONObject jsonObject, final String s, final String s2) {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
            return;
        }
        jsonObject.put(s, (Object)"N/A");
    }
    
    public static void setUsername(final String username) {
        if (username != null) {
            if (isEnabledAndReady()) {
                Crittercism.setUsername(username);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_log_crit", "Should log: " + isEnabledAndReady() + ", username: " + username);
            }
        }
    }
    
    private static boolean shouldInitializeCrittercism() {
        return ErrorLoggingManager.sErrorLoggingEnabledByConfig;
    }
    
    private static Exception wrapThrowableWithPrefix(final String s, final Throwable t) {
        final ArrayList<StackTraceElement> list = new ArrayList<StackTraceElement>(Arrays.asList(t.getStackTrace()));
        list.add(new StackTraceElement(s, "version", "n/a", 0));
        t.setStackTrace(list.toArray(new StackTraceElement[list.size()]));
        return new IOException(s + t.getMessage(), t);
    }
}
