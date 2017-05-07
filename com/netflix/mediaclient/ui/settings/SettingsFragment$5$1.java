// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.preference.PreferenceScreen;
import android.preference.PreferenceGroup;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.google.android.gcm.GCMRegistrar;
import android.preference.Preference$OnPreferenceClickListener;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import android.app.Activity;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;

class SettingsFragment$5$1 implements Runnable
{
    final /* synthetic */ SettingsFragment$5 this$1;
    final /* synthetic */ String val$newAppId;
    
    SettingsFragment$5$1(final SettingsFragment$5 this$1, final String val$newAppId) {
        this.this$1 = this$1;
        this.val$newAppId = val$newAppId;
    }
    
    @Override
    public void run() {
        SettingsConfiguration.setNewCastApplicationId((Context)this.this$1.this$0.activity, this.val$newAppId);
    }
}
