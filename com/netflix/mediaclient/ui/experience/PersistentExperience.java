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
    KUBRICK_HERO_IMGS, 
    KUBRICK_HIGH_DENSITY, 
    KUBRICK_KIDS_CONTROL_FLAT_GENRES, 
    KUBRICK_KIDS_HERO_IMGS, 
    KUBRICK_KIDS_HIGH_DENSITY, 
    KUBRICK_KIDS_HIGH_DENSITY_VERT, 
    LOLOMO_TITLE_ART_LARGE_HORIZONTAL, 
    LOLOMO_TITLE_ART_LARGE_PORTRAIT_BOXART, 
    LOLOMO_TITLE_ART_SMALL_BOXART, 
    LOLOMO_TITLE_ART_SMALL_HORIZONTAL, 
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
        PersistentExperience persistentExperience2;
        final PersistentExperience persistentExperience = persistentExperience2 = PersistentExperience.NON_KUBRICK;
        if (serviceAgent$ConfigurationAgentInterface != null) {
            persistentExperience2 = persistentExperience;
            if (serviceAgent$ConfigurationAgentInterface.getDeviceCategory() == DeviceCategory.TABLET) {
                persistentExperience2 = persistentExperience;
                if (serviceAgent$ConfigurationAgentInterface.getKubrickConfiguration() != null) {
                    persistentExperience2 = updateKubrickConfiguration(serviceAgent$ConfigurationAgentInterface);
                }
                if (serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6538() != null) {
                    persistentExperience2 = updateABTest_6538(serviceAgent$ConfigurationAgentInterface);
                }
                else if (serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6725() != null) {
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
    
    private static PersistentExperience updateABTest_6538(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        switch (PersistentExperience$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfigData$Cell[serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6538().getCell().ordinal()]) {
            default: {
                return PersistentExperience.NON_KUBRICK;
            }
            case 1: {
                return PersistentExperience.KUBRICK_KIDS_CONTROL_FLAT_GENRES;
            }
            case 2: {
                return PersistentExperience.KUBRICK_KIDS_HERO_IMGS;
            }
            case 3: {
                return PersistentExperience.KUBRICK_KIDS_HIGH_DENSITY;
            }
            case 4: {
                return PersistentExperience.KUBRICK_KIDS_HIGH_DENSITY_VERT;
            }
        }
    }
    
    private static PersistentExperience updateABTest_6725(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        switch (PersistentExperience$1.$SwitchMap$com$netflix$mediaclient$service$webclient$model$leafs$ABTestConfigData$Cell[serviceAgent$ConfigurationAgentInterface.getABTestConfiguration_6725().getCell().ordinal()]) {
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
