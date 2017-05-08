// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class OfflineConfig
{
    private static final int DEFAULT_MAINTENANCE_JOB_PERIOD_IN_HRS = 48;
    private static final String TAG = "nf_log";
    @SerializedName("disableOfflineFeature")
    private boolean disableOfflineFeature;
    @SerializedName("maintenanceJobPeriodInHrs")
    private int maintenanceJobPeriodInHrs;
    
    private static OfflineConfig createDefault() {
        final OfflineConfig offlineConfig = new OfflineConfig();
        offlineConfig.disableOfflineFeature = false;
        offlineConfig.maintenanceJobPeriodInHrs = 48;
        return offlineConfig;
    }
    
    public static OfflineConfig loadFromPreferences(Context context) {
        final Context context2 = null;
        final String stringPref = PreferenceUtils.getStringPref(context, "offline_config", null);
        Label_0027: {
            if (StringUtils.isEmpty(stringPref)) {
                Log.d("nf_log", "loadFromPreferences offlineConfig not found in file system");
                context = null;
            }
            else {
                while (true) {
                    try {
                        context = (Context)FalkorParseUtils.getGson().fromJson(stringPref, OfflineConfig.class);
                        try {
                            Log.d("nf_log", "loadFromPreferences offlineConfig loaded from file system");
                            break Label_0027;
                        }
                        catch (Throwable t2) {}
                        final Throwable t;
                        Log.e("nf_log", "loadFromPreferences Failed to load offlineConfig from file system", t);
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
            default1 = createDefault();
        }
        return (OfflineConfig)default1;
    }
    
    public static OfflineConfig saveToPreferences(final NetflixPreference netflixPreference, final OfflineConfig offlineConfig) {
        if (offlineConfig == null) {
            netflixPreference.removePref("offline_config");
            Log.d("nf_log", "Subtitle retry policy not found, return default");
            return createDefault();
        }
        netflixPreference.putStringPref("offline_config", FalkorParseUtils.getGson().toJson(offlineConfig));
        return offlineConfig;
    }
    
    public int getMaintenanceJobPeriodInHrs() {
        return this.maintenanceJobPeriodInHrs;
    }
    
    public boolean isOfflineFeatureDisabled() {
        return this.disableOfflineFeature;
    }
}
