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
import com.google.android.gcm.GCMRegistrar;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.preference.Preference;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthSaving;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.PlayerType;

class SettingsFragment$5 implements Runnable
{
    final /* synthetic */ SettingsFragment this$0;
    final /* synthetic */ PlayerType val$newType;
    
    SettingsFragment$5(final SettingsFragment this$0, final PlayerType val$newType) {
        this.this$0 = this$0;
        this.val$newType = val$newType;
    }
    
    @Override
    public void run() {
        Log.w("SettingsFragment", "Updating player type!");
        PlayerTypeFactory.setPlayerTypeForQAOverride((Context)this.this$0.activity, this.val$newType);
        Log.w("SettingsFragment", "Updating player type done.");
    }
}
