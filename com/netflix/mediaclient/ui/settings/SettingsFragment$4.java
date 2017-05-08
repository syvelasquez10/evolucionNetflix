// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.Log;
import android.preference.ListPreference;
import com.netflix.mediaclient.service.player.bladerunnerclient.ManifestRequestParamBuilder;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$4 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$4(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        if (o instanceof String) {
            final String value = (String)o;
            ManifestRequestParamBuilder.presetVideoFormat(value);
            ((ListPreference)preference).setValue(value);
        }
        else {
            Log.e("SettingsFragment", "invalid offline video format " + o);
        }
        return true;
    }
}
