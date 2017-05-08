// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

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
        PreferenceUtils.putStringPref(context, "persistent_experience_key", PersistentExperience.NON_KUBRICK.toString());
    }
}
