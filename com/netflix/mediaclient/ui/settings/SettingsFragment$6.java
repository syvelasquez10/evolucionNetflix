// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.app.Status;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.preference.PreferenceGroup;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.google.android.gcm.GCMRegistrar;
import android.preference.ListPreference;
import android.preference.Preference$OnPreferenceChangeListener;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthSaving;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData$Cell;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.preference.CheckBoxPreference;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceClickListener;

class SettingsFragment$6 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$6(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        Log.d("SettingsFragment", "Notification enabled clicked");
        if (preference instanceof CheckBoxPreference) {
            if (((CheckBoxPreference)preference).isChecked()) {
                Log.d("SettingsFragment", "Register for notifications");
                final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
                intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
                LocalBroadcastManager.getInstance((Context)this.this$0.activity).sendBroadcast(intent);
            }
            else {
                Log.d("SettingsFragment", "Unregister from notifications");
                final Intent intent2 = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
                intent2.addCategory("com.netflix.mediaclient.intent.category.PUSH");
                LocalBroadcastManager.getInstance((Context)this.this$0.activity).sendBroadcast(intent2);
            }
        }
        else {
            Log.e("SettingsFragment", "We did not received notification checkbox preference!");
        }
        return true;
    }
}
