// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.app.BackgroundTask;
import android.preference.EditTextPreference;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$5 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$5(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        Log.d("SettingsFragment", "onPreferenceChange " + o);
        if (preference instanceof EditTextPreference) {
            final String string = ((EditTextPreference)preference).getEditText().getText().toString();
            Log.d("SettingsFragment", "EditText.getText(): " + string);
            new BackgroundTask().execute(new SettingsFragment$5$1(this, string));
            return true;
        }
        return false;
    }
}
