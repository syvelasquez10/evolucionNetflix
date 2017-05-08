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
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.player.bladerunnerclient.ManifestRequestParamBuilder;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.preference.ListPreference;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.preference.PreferenceGroup;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;
import android.app.Dialog;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.exoplayback.ExoVideoCodecSelector;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceClickListener;

class SettingsFragment$5 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$5(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        if (preference instanceof CheckBoxPreference) {
            final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)preference;
            if (ExoVideoCodecSelector.isHasVP9SoftwareDecoder()) {
                if (checkBoxPreference.isChecked()) {
                    Log.d("SettingsFragment", "force swdecoder selected");
                    ExoVideoCodecSelector.setUseSoftwareDecoder(true);
                }
                else {
                    Log.d("SettingsFragment", "force swdecoder unselected");
                    ExoVideoCodecSelector.setUseSoftwareDecoder(false);
                }
                if (!ExoVideoCodecSelector.isHasVP9HardwareDecoder() && this.this$0.activity instanceof NetflixActivity) {
                    ((NetflixActivity)this.this$0.activity).showDebugToast("No Need To Force Software Decoder");
                }
            }
            else {
                checkBoxPreference.setChecked(false);
                if (this.this$0.activity instanceof NetflixActivity) {
                    ((NetflixActivity)this.this$0.activity).showDebugToast("Software Decoder Not Available");
                    return true;
                }
            }
        }
        return true;
    }
}
