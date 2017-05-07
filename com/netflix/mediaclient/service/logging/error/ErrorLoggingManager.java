// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import org.json.JSONException;
import android.os.Build;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.crittercism.app.CrittercismConfig;
import com.netflix.mediaclient.Log;
import android.app.Application;
import android.annotation.TargetApi;

@TargetApi(4)
public final class ErrorLoggingManager
{
    private static final String CRITTER_VERSION_NAME = "3.7.2";
    private static final boolean ENABLE_CRITTERCISM = true;
    private static final String TAG = "nf_log";
    private static boolean sCrittercismReady;
    
    public static void init(final Application application) {
        synchronized (ErrorLoggingManager.class) {
            Log.d("nf_log", "Crittercism init starts...");
            final CrittercismConfig crittercismConfig = new CrittercismConfig();
            crittercismConfig.setNdkCrashReportingEnabled(true);
            crittercismConfig.setCustomVersionName("3.7.2");
            try {
                Crittercism.initialize(application.getApplicationContext(), SecurityRepository.getCrittercismAppId(), crittercismConfig);
                final JSONObject metadata = new JSONObject();
                metadata.put("android", Build$VERSION.SDK_INT);
                putValueOrNotAvailable(metadata, "oem", Build.MANUFACTURER);
                putValueOrNotAvailable(metadata, "model", Build.MODEL);
                Crittercism.setMetadata(metadata);
                ErrorLoggingManager.sCrittercismReady = true;
                Log.d("nf_log", "Init Crittercism done.");
            }
            catch (Throwable t) {
                Log.e("nf_log", "Unable to build crittercism's config json object", t);
            }
        }
    }
    
    public static boolean isCrittercismEnabled() {
        return true;
    }
    
    public static void leaveBreadcrumb(final String s) {
        if (shouldLog()) {
            Crittercism.leaveBreadcrumb(s);
        }
    }
    
    public static void logHandledException(final Exception ex) {
        if (shouldLog()) {
            Crittercism.logHandledException(ex);
        }
    }
    
    public static void logHandledException(final String s) {
        if (shouldLog()) {
            logHandledException(new Exception(s));
        }
    }
    
    private static void putValueOrNotAvailable(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (s2 != null) {
            jsonObject.put(s, (Object)s2);
            return;
        }
        jsonObject.put(s, (Object)"N/A");
    }
    
    public static boolean shouldLog() {
        return isCrittercismEnabled() && ErrorLoggingManager.sCrittercismReady;
    }
}
