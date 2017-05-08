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
import android.preference.ListPreference;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.preference.PreferenceGroup;
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
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import android.content.DialogInterface$OnClickListener;
import android.support.v7.app.AlertDialog$Builder;
import android.text.Html;
import android.content.Context;
import android.text.format.Formatter;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceClickListener;

class SettingsFragment$13 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ SettingsFragment this$0;
    final /* synthetic */ Preference val$dlLocationPref;
    final /* synthetic */ OfflineAgentInterface val$offlineAgent;
    
    SettingsFragment$13(final SettingsFragment this$0, final OfflineAgentInterface val$offlineAgent, final Preference val$dlLocationPref) {
        this.this$0 = this$0;
        this.val$offlineAgent = val$offlineAgent;
        this.val$dlLocationPref = val$dlLocationPref;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        final OfflineStorageVolumeUiList offlineStorageVolumeList = this.val$offlineAgent.getOfflineStorageVolumeList();
        if (offlineStorageVolumeList.size() <= 0) {
            Log.e("SettingsFragment", "osvList size=%d", offlineStorageVolumeList.size());
            return true;
        }
        this.val$offlineAgent.recalculateSpaceUsageForOfflineStorageVolumes();
        final int currentSelectedVolumeIndex = offlineStorageVolumeList.getCurrentSelectedVolumeIndex();
        Log.i("SettingsFragment", "currentlySelected=%d", currentSelectedVolumeIndex);
        final CharSequence[] array = new CharSequence[offlineStorageVolumeList.size()];
        for (int i = 0; i < offlineStorageVolumeList.size(); ++i) {
            final OfflineStorageVolume offlineStorageVolume = offlineStorageVolumeList.get(i);
            final SettingsFragment this$0 = this.this$0;
            int n;
            if (offlineStorageVolume.isRemovable()) {
                n = 2131296916;
            }
            else {
                n = 2131296895;
            }
            array[i] = (CharSequence)Html.fromHtml(this.this$0.getString(2131296890, new Object[] { this$0.getString(n), Formatter.formatShortFileSize((Context)this.this$0.getActivity(), offlineStorageVolume.getFreeSpace()) }));
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.this$0.activity);
        alertDialog$Builder.setTitle(2131296889);
        alertDialog$Builder.setSingleChoiceItems(array, currentSelectedVolumeIndex, (DialogInterface$OnClickListener)new SettingsFragment$13$1(this));
        alertDialog$Builder.create().show();
        return true;
    }
}
