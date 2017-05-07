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
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
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
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.Log;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;

class SettingsFragment$1 implements Preference$OnPreferenceChangeListener
{
    final /* synthetic */ SettingsFragment this$0;
    
    SettingsFragment$1(final SettingsFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceChange(final Preference preference, final Object o) {
        if (o instanceof String) {
            final String s = (String)o;
            if ("DEFAULT".equals(s)) {
                Log.d("SettingsFragment", "Reset player to default");
                PlayerTypeFactory.resetPlayerTypeByQA((Context)this.this$0.activity);
            }
            else if ("XAL".equals(s)) {
                Log.d("SettingsFragment", "Set Player type to XAL");
                this.this$0.changePlayer(PlayerTypeFactory.getXalPlayer());
            }
            else if ("XALAMP".equals(s)) {
                Log.d("SettingsFragment", "Set Player type to XALMP");
                this.this$0.changePlayer(PlayerTypeFactory.getXalmpPlayer());
            }
            else if ("JPLAYER".equals(s)) {
                Log.d("SettingsFragment", "Set Player type to JPLAYER");
                this.this$0.changePlayer(PlayerTypeFactory.getJPlayer());
            }
            else if ("JPLAYERBASE".equals(s)) {
                Log.d("SettingsFragment", "Set Player type to JPLAYERBASE");
                this.this$0.changePlayer(PlayerTypeFactory.getJPlayerBase());
            }
            else if ("JPLAYER2".equals(s)) {
                Log.d("SettingsFragment", "Set Player type to JPLAYER2");
                this.this$0.changePlayer(PlayerTypeFactory.getJPlayer2());
            }
            else {
                Log.e("SettingsFragment", "Received unexpected value for player type " + s);
            }
        }
        else {
            Log.e("SettingsFragment", "Received unexpected NON string value for player type " + o);
        }
        return true;
    }
}
