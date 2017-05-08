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
import com.netflix.mediaclient.Log;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.preference.Preference;
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
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class SettingsFragment$9$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ SettingsFragment$9 this$1;
    
    SettingsFragment$9$1(final SettingsFragment$9 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        UIViewLogUtils.reportUIViewCommand((Context)this.this$1.this$0.getActivity(), UIViewLogging$UIViewCommandName.RemoveAllCachedVideosCommand, IClientLogging$ModalView.settings, CommandEndedEvent$InputMethod.gesture, null);
        final OfflineAgentInterface offlineAgent = this.this$1.val$manager.getOfflineAgent();
        if (offlineAgent != null) {
            offlineAgent.addOfflineAgentListener(this.this$1.this$0.mDeleteAllListener);
            offlineAgent.deleteAllOfflineContent();
            DownloadButton.clearPreQueued();
        }
        dialogInterface.dismiss();
    }
}
