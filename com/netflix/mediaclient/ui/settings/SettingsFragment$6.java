// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.preference.Preference;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$6 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    final /* synthetic */ ServiceManager val$manager;
    
    SettingsFragment$6(final SettingsFragment this$0, final ServiceManager val$manager) {
        this.this$0 = this$0;
        this.val$manager = val$manager;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        this.val$manager.getOfflineAgent().setRequiresUnmeteredNetwork((boolean)o);
        if (!(boolean)o && this.this$0.getActivity().getIntent() != null && this.this$0.getActivity().getIntent().hasExtra("playableId")) {
            this.this$0.getActivity().setResult(-1, this.this$0.getActivity().getIntent());
            this.this$0.getActivity().finish();
        }
        return true;
    }
}
