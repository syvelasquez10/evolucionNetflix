// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.SharedPreferences;
import com.netflix.mediaclient.android.app.Status;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.preference.PreferenceScreen;
import android.preference.PreferenceGroup;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.google.android.gcm.GCMRegistrar;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$3 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$3(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        if (o instanceof String) {
            final String s = (String)o;
            if ("DEFAULT".equals(s)) {
                Log.d("SettingsFragment", "Sets ENHANCED XML subtitle configuration (default)");
                SubtitleConfiguration.clearQaLocalOverride((Context)this.this$0.activity);
                this.this$0.updateSubtitleConfig(SubtitleConfiguration.DEFAULT);
            }
            else if ("ENHANCED_XML".equals(s)) {
                Log.d("SettingsFragment", "Sets ENHANCED XML subtitle configuration (default)");
                SubtitleConfiguration.updateQaLocalOverride((Context)this.this$0.activity, SubtitleConfiguration.ENHANCED_XML.getLookupType());
                this.this$0.updateSubtitleConfig(SubtitleConfiguration.ENHANCED_XML);
            }
            else if ("SIMPLE_XML".equals(s)) {
                Log.d("SettingsFragment", "Sets SIMPLE XML subtitle configuration");
                SubtitleConfiguration.updateQaLocalOverride((Context)this.this$0.activity, SubtitleConfiguration.SIMPLE_XML.getLookupType());
                this.this$0.updateSubtitleConfig(SubtitleConfiguration.SIMPLE_XML);
            }
            else {
                Log.e("SettingsFragment", "Received unexpected value for player type " + s);
            }
        }
        else {
            Log.e("SettingsFragment", "Received unexpected NON string value for player type " + o);
        }
        return true;
    }
}
