// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

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
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthSaving;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthDialog;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthLogging$InvokeLocation;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceClickListener;

class SettingsFragment$3 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$3(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        Log.d("SettingsFragment", "Debug: player type. preference:" + preference);
        final DataSaveConfigData bwSaveConfigData = this.this$0.serviceManager.getConfiguration().getBWSaveConfigData();
        if (bwSaveConfigData == null) {
            return true;
        }
        final ABTestConfigData abTestConfig_6733 = bwSaveConfigData.abTestConfig_6733;
        if (abTestConfig_6733 != null && !abTestConfig_6733.getCell().equals(ABTestConfigData$Cell.CELL_ONE)) {
            BandwidthDialog.createBwDialog(this.this$0.serviceManager.getActivity(), bwSaveConfigData, BandwidthLogging$InvokeLocation.FROM_SETTINGS).show(this.this$0.activity.getFragmentManager(), "frag_dialog");
            return true;
        }
        Log.e("SettingsFragment", "settings preference shown for wrong case " + bwSaveConfigData);
        return true;
    }
}
