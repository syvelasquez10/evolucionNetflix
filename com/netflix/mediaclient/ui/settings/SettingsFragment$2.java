// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$2 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$2(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(Preference preference, final Object o) {
        preference = this.this$0.findPreference((CharSequence)"nf.bw_save");
        if (preference != null) {
            preference.setEnabled(o.toString().equals("false"));
        }
        return true;
    }
}
