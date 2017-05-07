// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import java.io.Serializable;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public enum PersistentExperience
{
    KUBRICK, 
    NON_KUBRICK;
    
    private static final String PERSISTENT_EXPERIENCE_PREFS_KEY = "persistent_experience_key";
    private static final String TAG = "PersistentExperience";
    
    static PersistentExperience get(final Context context) {
        final String stringPref = PreferenceUtils.getStringPref(context, "persistent_experience_key", null);
        PersistentExperience persistentExperience;
        if (StringUtils.isEmpty(stringPref)) {
            Log.w("PersistentExperience", "SPY-7682: found null string for PERSISTENT_EXPERIENCE_PREFS_KEY - falling back to non-kubrick");
            ErrorLoggingManager.logHandledException("SPY-7682: found null string for PERSISTENT_EXPERIENCE_PREFS_KEY - falling back to non-kubrick");
            persistentExperience = PersistentExperience.NON_KUBRICK;
        }
        else {
            final PersistentExperience persistentExperience2 = persistentExperience = valueOf(stringPref);
            if (Log.isLoggable()) {
                Log.v("PersistentExperience", "got experience: " + persistentExperience2);
                return persistentExperience2;
            }
        }
        return persistentExperience;
    }
    
    public static void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        int n;
        if (serviceAgent$ConfigurationAgentInterface != null && serviceAgent$ConfigurationAgentInterface.getDeviceCategory() == DeviceCategory.TABLET && serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() != null && serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().isKubrickEnabled()) {
            n = 1;
        }
        else {
            n = 0;
        }
        PersistentExperience persistentExperience;
        if (n != 0) {
            persistentExperience = PersistentExperience.KUBRICK;
        }
        else {
            persistentExperience = PersistentExperience.NON_KUBRICK;
        }
        if (Log.isLoggable()) {
            if (serviceAgent$ConfigurationAgentInterface == null) {
                Log.v("PersistentExperience", "null config interface - can't update");
            }
            else {
                final DeviceCategory deviceCategory = serviceAgent$ConfigurationAgentInterface.getDeviceCategory();
                Serializable value;
                if (serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() == null) {
                    value = "null Kubrick config";
                }
                else {
                    value = serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().isKubrickEnabled();
                }
                Log.v("PersistentExperience", String.format("Updating - Device cat: %s, Kubrick enabled: %s, experience: %s", deviceCategory, value, persistentExperience));
            }
        }
        PreferenceUtils.putStringPref(context, "persistent_experience_key", persistentExperience.toString());
    }
}
