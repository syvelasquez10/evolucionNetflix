// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.preference.PreferenceScreen;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.player.bladerunnerclient.ManifestRequestParamBuilder;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.exoplayback.ExoVideoCodecSelector;
import android.preference.CheckBoxPreference;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.preference.ListPreference;
import com.netflix.mediaclient.Log;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Dialog;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.android.app.Status;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;

class SettingsFragment$1 extends SimpleOfflineAgentListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$1(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean isListenerDestroyed() {
        return AndroidUtils.isActivityFinishedOrDestroyed((Context)AndroidUtils.getContextAs((Context)this.this$0.getActivity(), NetflixActivity.class));
    }
    
    @Override
    public void onAllPlayablesDeleted(final Status status) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs((Context)this.this$0.getActivity(), NetflixActivity.class);
        if (netflixActivity != null) {
            final OfflineAgentInterface offlineAgentOrNull = NetflixActivity.getOfflineAgentOrNull(netflixActivity);
            if (offlineAgentOrNull != null) {
                offlineAgentOrNull.removeOfflineAgentListener(this.this$0.mDeleteAllListener);
                this.this$0.handleAllOfflineItemsDeleted();
            }
        }
    }
}
