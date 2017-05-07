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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;

class SettingsFragment$7$1 implements Runnable
{
    final /* synthetic */ SettingsFragment$7 this$1;
    final /* synthetic */ String val$newAppId;
    
    SettingsFragment$7$1(final SettingsFragment$7 this$1, final String val$newAppId) {
        this.this$1 = this$1;
        this.val$newAppId = val$newAppId;
    }
    
    @Override
    public void run() {
        SettingsConfiguration.setNewCastApplicationId((Context)this.this$1.this$0.activity, this.val$newAppId);
    }
}
