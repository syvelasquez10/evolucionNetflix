// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.content.SharedPreferences;
import com.netflix.mediaclient.android.app.Status;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.preference.PreferenceScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.player.bladerunnerclient.ManifestRequestParamBuilder;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.exoplayback.ExoVideoCodecSelector;
import android.preference.CheckBoxPreference;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.preference.PreferenceGroup;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Fragment;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;
import android.app.Dialog;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.preference.ListPreference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$10 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    final /* synthetic */ ListPreference val$customPrefs;
    final /* synthetic */ ServiceManager val$manager;
    
    SettingsFragment$10(final SettingsFragment this$0, final ListPreference val$customPrefs, final ServiceManager val$manager) {
        this.this$0 = this$0;
        this.val$customPrefs = val$customPrefs;
        this.val$manager = val$manager;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        if (!(o instanceof String)) {
            Log.e("SettingsFragment", "Received unexpected NON string value for downloads video quality " + o);
            return true;
        }
        final String s = (String)o;
        switch (s) {
            default: {
                Log.e("SettingsFragment", "Received unexpected value for downloads video quality " + s);
                return true;
            }
            case "BEST": {
                Log.d("SettingsFragment", "Set downloads video quality to best");
                this.val$customPrefs.setSummary(this.this$0.getText(2131231354));
                this.this$0.updateDownloadsVideoQualityConfig(DownloadVideoQuality.BEST, this.val$manager);
                return true;
            }
            case "DEFAULT": {
                Log.d("SettingsFragment", "Set downloads video quality to default");
                this.val$customPrefs.setSummary(this.this$0.getText(2131231356));
                this.this$0.updateDownloadsVideoQualityConfig(DownloadVideoQuality.DEFAULT, this.val$manager);
                return true;
            }
        }
    }
}
