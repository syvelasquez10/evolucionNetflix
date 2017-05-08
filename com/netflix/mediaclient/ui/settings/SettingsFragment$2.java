// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$2 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$2(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        PreferenceUtils.putBooleanPref((Context)this.this$0.getActivity(), "prefs_debug_settings_force_pin_check", (boolean)o);
        return true;
    }
}
