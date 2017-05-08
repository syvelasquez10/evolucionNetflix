// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public enum PersistentExperience
{
    KUBRICK_HERO_IMGS, 
    KUBRICK_HIGH_DENSITY, 
    LOLOMO_TITLE_ART_LARGE_HORIZONTAL, 
    LOLOMO_TITLE_ART_LARGE_PORTRAIT_BOXART, 
    LOLOMO_TITLE_ART_SMALL_BOXART, 
    LOLOMO_TITLE_ART_SMALL_HORIZONTAL, 
    NON_KUBRICK;
    
    private static final String PERSISTENT_EXPERIENCE_PREFS_KEY = "persistent_experience_key";
    private static final String TAG = "PersistentExperience";
    
    static PersistentExperience get(final Context context) {
        Serializable s = PreferenceUtils.getStringPref(context, "persistent_experience_key", null);
        if (StringUtils.isEmpty((String)s)) {
            Log.w("PersistentExperience", "SPY-7682: found null string for PERSISTENT_EXPERIENCE_PREFS_KEY - falling back to non-kubrick");
            s = PersistentExperience.NON_KUBRICK;
        }
        else {
            while (true) {
                try {
                    final PersistentExperience persistentExperience = valueOf((String)s);
                    s = persistentExperience;
                    if (Log.isLoggable()) {
                        Log.v("PersistentExperience", "got experience: " + persistentExperience);
                        return persistentExperience;
                    }
                }
                catch (IllegalArgumentException ex) {
                    if (Log.isLoggable()) {
                        Log.d("PersistentExperience", "Couldn't parse experience string: " + (String)s + ". Setting to default experience...");
                    }
                    final PersistentExperience persistentExperience = PersistentExperience.NON_KUBRICK;
                    continue;
                }
                break;
            }
        }
        return (PersistentExperience)s;
    }
    
    public static void update(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        PersistentExperience persistentExperience2;
        final PersistentExperience persistentExperience = persistentExperience2 = PersistentExperience.NON_KUBRICK;
        if (serviceAgent$ConfigurationAgentInterface != null) {
            persistentExperience2 = persistentExperience;
            if (serviceAgent$ConfigurationAgentInterface.getDeviceCategory() == DeviceCategory.TABLET) {
                persistentExperience2 = persistentExperience;
                if (serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() != null) {
                    persistentExperience2 = updateKubrickConfiguration(serviceAgent$ConfigurationAgentInterface);
                }
                if (serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6725() != null) {
                    persistentExperience2 = updateABTest_6725(serviceAgent$ConfigurationAgentInterface);
                }
            }
        }
        if (Log.isLoggable()) {
            if (serviceAgent$ConfigurationAgentInterface == null) {
                Log.v("PersistentExperience", "null config interface - can't update");
            }
            else {
                final DeviceCategory deviceCategory = serviceAgent$ConfigurationAgentInterface.getDeviceCategory();
                Serializable cell;
                if (serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() == null) {
                    cell = "null Kubrick config";
                }
                else {
                    cell = serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().getCell();
                }
                Log.v("PersistentExperience", String.format("Updating - Device cat: %s, Kubrick cell: %s, experience: %s", deviceCategory, cell, persistentExperience2));
            }
        }
        PreferenceUtils.putStringPref(context, "persistent_experience_key", persistentExperience2.toString());
    }
    
    private static PersistentExperience updateABTest_6725(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        switch (PersistentExperience$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfig$Cell[serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6725().getCell().ordinal()]) {
            default: {
                return PersistentExperience.NON_KUBRICK;
            }
            case 1: {
                return PersistentExperience.LOLOMO_TITLE_ART_SMALL_BOXART;
            }
            case 2: {
                return PersistentExperience.LOLOMO_TITLE_ART_LARGE_HORIZONTAL;
            }
            case 3: {
                return PersistentExperience.LOLOMO_TITLE_ART_SMALL_HORIZONTAL;
            }
            case 4: {
                return PersistentExperience.LOLOMO_TITLE_ART_LARGE_PORTRAIT_BOXART;
            }
        }
    }
    
    private static PersistentExperience updateKubrickConfiguration(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        switch (PersistentExperience$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$KubrickConfigData$KubrickCell[serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration().getCell().ordinal()]) {
            default: {
                return PersistentExperience.NON_KUBRICK;
            }
            case 1: {
                return PersistentExperience.KUBRICK_HERO_IMGS;
            }
            case 2: {
                return PersistentExperience.KUBRICK_HIGH_DENSITY;
            }
        }
    }
}
