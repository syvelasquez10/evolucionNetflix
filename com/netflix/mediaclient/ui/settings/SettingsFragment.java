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
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment implements ManagerStatusListener
{
    private static final String FAKE_KEY_BW_SAVE = "nf.bw_save";
    private static final String FAKE_KEY_ENABLE_NOTIFICATIONS = "nf_notification_enable";
    private static final String FAKE_KEY_PLAYER_TYPE = "ui.playertype";
    private static final String FAKE_KEY_SUBTITLE_CONFIG = "ui.subtitleConfig";
    private static final String KEY_PREF_NOTIFICATION = "pref.notification";
    private static final String PLAYER_TYPE_DEFAULT = "DEFAULT";
    private static final String PLAYER_TYPE_JPLAYER = "JPLAYER";
    private static final String PLAYER_TYPE_JPLAYER2 = "JPLAYER2";
    private static final String PLAYER_TYPE_JPLAYERBASE = "JPLAYERBASE";
    private static final String PLAYER_TYPE_XAL = "XAL";
    private static final String PLAYER_TYPE_XALMP = "XALAMP";
    private static final String PREFS_NAME = "nfxpref";
    private static final String SUBTITLE_CONFIG_DEFAULT = "DEFAULT";
    private static final String SUBTITLE_CONFIG_ENHANCED_XML = "ENHANCED_XML";
    private static final String SUBTITLE_CONFIG_SIMPLE_XML = "SIMPLE_XML";
    private static final String TAG = "SettingsFragment";
    private Activity activity;
    private ServiceManager serviceManager;
    
    private void changePlayer(final PlayerType playerType) {
        if (playerType == null) {
            Log.w("SettingsFragment", "Invalid player type choosen! This should not happen, report it.");
            new AlertDialog$Builder((Context)this.activity).setTitle((CharSequence)"").setMessage(2131165764).setPositiveButton(2131165543, (DialogInterface$OnClickListener)null).show();
            return;
        }
        new BackgroundTask().execute(new SettingsFragment$5(this, playerType));
    }
    
    public static Fragment create() {
        return (Fragment)new SettingsFragment();
    }
    
    private void handleBandwidthSaveSettings() {
        final DataSaveConfigData bwSaveConfigData = this.serviceManager.getConfiguration().getBWSaveConfigData();
        if (bwSaveConfigData == null) {
            this.removeBwSettings();
            return;
        }
        final ABTestConfigData abTestConfig_6733 = bwSaveConfigData.abTestConfig_6733;
        if (abTestConfig_6733 == null || abTestConfig_6733.getCell().equals(ABTestConfigData$Cell.CELL_ONE)) {
            this.removeBwSettings();
            return;
        }
        final Preference preference = this.findPreference((CharSequence)"nf.bw_save");
        if (preference == null) {
            this.removeBwSettings();
            return;
        }
        if (!ConnectivityUtils.hasCellular((Context)this.serviceManager.getActivity()) && !this.serviceManager.getConfiguration().shouldForceBwSettingsInWifi()) {
            Log.d("SettingsFragment", "no cellular!!");
            this.removeBwSettings();
            return;
        }
        int summary;
        if (BandwidthSaving.isDataSavingEnabled((Context)this.serviceManager.getActivity(), bwSaveConfigData)) {
            summary = 2131165713;
        }
        else {
            summary = 2131165712;
        }
        preference.setSummary(summary);
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$3(this));
        preference.setEnabled(!PreferenceUtils.getBooleanPref((Context)this.getActivity(), "nf_play_no_wifi_warning", false));
    }
    
    private void handleCastAppIdSettings() {
        final Preference preference = this.findPreference((CharSequence)"ui.castAppId");
        if (preference != null) {
            preference.setSummary((CharSequence)((Object)this.getText(2131165414) + SettingsConfiguration.getCastApplicationId((Context)this.activity)));
            preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$7(this));
        }
    }
    
    private void handlePlayerType() {
        final Preference preference = this.findPreference((CharSequence)"ui.playertype");
        if (preference == null) {
            Log.w("SettingsFragment", "Debug: player overlay not found");
            return;
        }
        Log.d("SettingsFragment", "Debug: player type");
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$1(this));
        if (preference instanceof ListPreference) {
            this.populatePlayerTypes((ListPreference)preference);
            return;
        }
        Log.e("SettingsFragment", "Preference player type is NOT list preference!");
    }
    
    private void handlePushNotificationsSettings() {
        if (!this.isGcmSupported()) {
            Log.d("SettingsFragment", "Notifications are NOT supported!");
            this.removeNotificationGroup();
            return;
        }
        Log.d("SettingsFragment", "Enable notifications");
        final boolean registeredForPushNotifications = this.isRegisteredForPushNotifications();
        if (Log.isLoggable()) {
            Log.d("SettingsFragment", "Notifications were enabled " + registeredForPushNotifications);
        }
        final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.findPreference((CharSequence)"nf_notification_enable");
        if (checkBoxPreference != null) {
            checkBoxPreference.setChecked(registeredForPushNotifications);
            checkBoxPreference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$6(this));
            return;
        }
        this.removeNotificationGroup();
    }
    
    private void handleSubtitleConfig() {
        final Preference preference = this.findPreference((CharSequence)"ui.subtitleConfig");
        if (preference == null) {
            Log.w("SettingsFragment", "Debug: player overlay not found");
            return;
        }
        Log.d("SettingsFragment", "Debug: subtitle config");
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$4(this));
        if (preference instanceof ListPreference) {
            this.populateSubtitleConfig((ListPreference)preference);
            return;
        }
        Log.e("SettingsFragment", "Preference player type is NOT list preference!");
    }
    
    private void handleWifiOnlySetting() {
        final Preference preference = this.findPreference((CharSequence)"nf_play_no_wifi_warning");
        if (preference == null) {
            return;
        }
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$2(this));
    }
    
    private boolean isGcmSupported() {
        try {
            Log.d("SettingsFragment", "Verifies that the device supports GCM");
            GCMRegistrar.checkDevice((Context)this.activity);
            return true;
        }
        catch (Throwable t) {
            Log.e("SettingsFragment", "Device does NOT supports GCM");
            return false;
        }
    }
    
    private boolean isRegisteredForPushNotifications() {
        return SettingsConfiguration.getPushOptInStatus((Context)this.activity);
    }
    
    private void populatePlayerTypes(final ListPreference listPreference) {
        final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.activity);
        final boolean default1 = PlayerTypeFactory.isDefault((Context)this.activity);
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getString(2131165763)).append(" ");
        sb.append(PlayerTypeFactory.findDefaultPlayerType().getDescription());
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(sb.toString());
        list2.add("DEFAULT");
        if (AndroidUtils.isOpenMaxALSupported()) {
            list.add((String)this.getText(2131165768));
            list2.add("XAL");
            if (PlayerTypeFactory.isXalPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XAL");
                }
                listPreference.setValue("XAL");
            }
            list.add((String)this.getText(2131165769));
            list2.add("XALAMP");
            if (PlayerTypeFactory.isXalmpPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XALAMP");
                }
                listPreference.setValue("XALAMP");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device10)) {
            list.add((String)this.getText(2131165765));
            list2.add("JPLAYER");
            if (PlayerTypeFactory.isJPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYER");
                }
                listPreference.setValue("JPLAYER");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device11)) {
            list.add((String)this.getText(2131165767));
            list2.add("JPLAYERBASE");
            if (PlayerTypeFactory.isJPlayerBase(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYERBASE");
                }
                listPreference.setValue("JPLAYERBASE");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device12)) {
            list.add((String)this.getText(2131165766));
            list2.add("JPLAYER2");
            if (PlayerTypeFactory.isJPlayer2(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYER2");
                }
                listPreference.setValue("JPLAYER2");
            }
        }
        listPreference.setEntries((CharSequence[])list.toArray(new CharSequence[list.size()]));
        listPreference.setEntryValues((CharSequence[])list2.toArray(new CharSequence[list2.size()]));
    }
    
    private void populateSubtitleConfig(final ListPreference listPreference) {
        final SubtitleConfiguration loadQaLocalOverride = SubtitleConfiguration.loadQaLocalOverride((Context)this.activity);
        final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(this.getText(2131165773));
        list2.add("DEFAULT");
        list.add(this.getText(2131165774));
        list2.add("ENHANCED_XML");
        list.add(this.getText(2131165776));
        list2.add("SIMPLE_XML");
        listPreference.setDefaultValue((Object)"DEFAULT");
        if (loadQaLocalOverride == SubtitleConfiguration.SIMPLE_XML) {
            listPreference.setValue("SIMPLE_XML");
        }
        else if (loadQaLocalOverride == SubtitleConfiguration.ENHANCED_XML) {
            listPreference.setValue("ENHANCED_XML");
        }
        else {
            listPreference.setValue("DEFAULT");
        }
        listPreference.setEntries((CharSequence[])list.toArray(new CharSequence[list.size()]));
        listPreference.setEntryValues((CharSequence[])list2.toArray(new CharSequence[list2.size()]));
    }
    
    private void removeBwSettings() {
        Log.d("SettingsFragment", "removing bandwidth settings");
        ((PreferenceGroup)this.findPreference((CharSequence)"video.playback")).removePreference(this.findPreference((CharSequence)"nf.bw_save"));
    }
    
    private void removeNotificationGroup() {
        final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.findPreference((CharSequence)"nf_notification_enable");
        final PreferenceScreen preferenceScreen = this.getPreferenceScreen();
        final PreferenceGroup preferenceGroup = (PreferenceGroup)this.findPreference((CharSequence)"pref.notification");
        if (preferenceGroup != null) {
            if (checkBoxPreference != null) {
                preferenceGroup.removePreference((Preference)checkBoxPreference);
            }
            preferenceScreen.removePreference((Preference)preferenceGroup);
        }
    }
    
    private void updateSubtitleConfig(final SubtitleConfiguration subtitleConfiguration) {
        Log.d("SettingsFragment", "Update subtitle config");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        intent.putExtra("lookupType", subtitleConfiguration.getLookupType());
        LocalBroadcastManager.getInstance((Context)this.activity).sendBroadcast(intent);
    }
    
    public void onBandwidthSettingsDone(final ServiceManager serviceManager) {
        final DataSaveConfigData bwSaveConfigData = serviceManager.getConfiguration().getBWSaveConfigData();
        if (bwSaveConfigData != null) {
            final Preference preference = this.findPreference((CharSequence)"nf.bw_save");
            if (preference != null) {
                final boolean dataSavingEnabled = BandwidthSaving.isDataSavingEnabled((Context)serviceManager.getActivity(), bwSaveConfigData);
                Log.d("SettingsFragment", String.format("onBandwidthSettingsDone called - dataSavingEnabled: %b, ", dataSavingEnabled));
                int summary;
                if (dataSavingEnabled) {
                    summary = 2131165713;
                }
                else {
                    summary = 2131165712;
                }
                preference.setSummary(summary);
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this.getActivity();
        this.getPreferenceManager().setSharedPreferencesMode(0);
        this.getPreferenceManager().setSharedPreferencesName("nfxpref");
        this.addPreferencesFromResource(2131034115);
        ((PreferenceGroup)this.findPreference((CharSequence)"pref.screen")).removePreference(this.findPreference((CharSequence)"pref.qa.debugonly"));
        this.handleWifiOnlySetting();
        this.handlePushNotificationsSettings();
    }
    
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d("SettingsFragment", "onManagerReady");
        this.serviceManager = serviceManager;
        this.handleBandwidthSaveSettings();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
}
