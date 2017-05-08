// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;

public final class PrefetchLolomoABTestUtils
{
    public static final long MAP_LOLOMO_CACHE_EXPIRATION_TIME_MS = 36000000L;
    public static final String PREFETCH_JSON_FILE_NAME = "prefetch.json";
    public static final int PREFETCH_LOLOMO_JOB_REQUEST_FREQUENCY_TIMEOUT_MS = 60000;
    public static final int PREFETCH_LOLOMO_JOB_START_DELAY_MS = 3600000;
    public static final int PREFETCH_LOLOMO_JOB_TIMEOUT_MS = 300000;
    public static final long PREFETCH_LOLOMO_METADATA_JOB_INTERVAL_MS = 36000000L;
    
    public static boolean doesJobRequireCharging(final Context context) {
        switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
            default: {
                return false;
            }
            case 2:
            case 4:
            case 6: {
                return true;
            }
        }
    }
    
    public static boolean doesJobRequireDeviceIdle(final Context context) {
        switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
            default: {
                return false;
            }
            case 2:
            case 4:
            case 6: {
                return true;
            }
        }
    }
    
    public static long getJobPeriodicInterval() {
        return 36000000L;
    }
    
    public static long getLastJobStartTime(final Context context) {
        return PreferenceUtils.getLongPref(context, "prefs_prefetch_lolomo_job_last_start_time_ms", -1L);
    }
    
    public static boolean hasJobScheduler(final Context context) {
        if (isInTest(context)) {
            switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
                case 1:
                case 3: {
                    break;
                }
                default: {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isConfigRequestAsync(final Context context) {
        if (isInTest(context)) {
            switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
                case 1:
                case 2: {
                    break;
                }
                default: {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isInTest(final Context context) {
        return context != null && PersistentConfig.getPrefetchLolomoConfig(context) != ABTestConfig$Cell.CELL_ONE && AndroidUtils.getAndroidVersion() >= 21;
    }
    
    public static boolean isJobNetworkTypeUnmetered(final Context context) {
        switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
            default: {
                return false;
            }
            case 2:
            case 4:
            case 5: {
                return true;
            }
        }
    }
    
    public static boolean isJobPeriodic(final Context context) {
        switch (PrefetchLolomoABTestUtils$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[PersistentConfig.getPrefetchLolomoConfig(context).ordinal()]) {
            default: {
                return true;
            }
            case 1:
            case 3: {
                return false;
            }
        }
    }
}
