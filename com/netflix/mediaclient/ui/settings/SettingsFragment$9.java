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
import com.netflix.mediaclient.service.player.exoplayback.ExoVideoCodecSelector;
import android.preference.CheckBoxPreference;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.preference.ListPreference;
import com.netflix.mediaclient.Log;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.preference.PreferenceGroup;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.servicemgr.interface_.offline.SimpleOfflineAgentListener;
import android.app.Dialog;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import com.netflix.mediaclient.ui.offline.DownloadButtonDialogHelper;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.preference.Preference;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.preference.Preference$OnPreferenceClickListener;

class SettingsFragment$9 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ SettingsFragment this$0;
    final /* synthetic */ ServiceManager val$manager;
    
    SettingsFragment$9(final SettingsFragment this$0, final ServiceManager val$manager) {
        this.this$0 = this$0;
        this.val$manager = val$manager;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        if (this.this$0.getActivity() instanceof NetflixActivity) {
            String downloadsSize = "";
            if (this.this$0.activityCallback != null) {
                downloadsSize = this.this$0.activityCallback.getDownloadsSize();
            }
            (this.this$0.dialog = DownloadButtonDialogHelper.createDownloadDeleteAllDialog((Context)this.this$0.getActivity(), (DialogInterface$OnClickListener)new SettingsFragment$9$1(this), downloadsSize, "")).show();
            return true;
        }
        return false;
    }
}
