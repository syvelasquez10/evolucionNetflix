// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.SharedPreferences;
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
import android.preference.Preference$OnPreferenceClickListener;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
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
import android.content.SharedPreferences$OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences$OnSharedPreferenceChangeListener, ManagerStatusListener
{
    private static final String FAKE_KEY_BW_SAVE = "nf.bw_save";
    private static final String FAKE_KEY_ENABLE_NOTIFICATIONS = "nf_notification_enable";
    private static final String FAKE_KEY_PLAYER_TYPE = "ui.playertype";
    private static final String FAKE_KEY_SUBTITLE_CONFIG = "ui.subtitleConfig";
    private static final String FAKE_KEY_WIFI_ONLY = "nf_play_no_wifi_warning";
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
            new AlertDialog$Builder((Context)this.activity).setTitle((CharSequence)"").setMessage(2131231356).setPositiveButton(2131231128, (DialogInterface$OnClickListener)null).show();
            return;
        }
        new BackgroundTask().execute(new SettingsFragment$4(this, playerType));
    }
    
    public static Fragment create() {
        return (Fragment)new SettingsFragment();
    }
    
    private void handleBandwidthSaveSettings() {
        if (!BandwidthUtility.canShowDataSavingPreference(this.serviceManager.getContext())) {
            this.removeBwSettings();
            return;
        }
        final Preference preference = this.findPreference((CharSequence)"nf.bw_save");
        if (preference == null) {
            this.removeBwSettings();
            return;
        }
        this.setDataSaverDescription(this.serviceManager.getContext(), preference);
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$2(this));
    }
    
    private void handleCastAppIdSettings() {
        final Preference preference = this.findPreference((CharSequence)"ui.castAppId");
        if (preference != null) {
            preference.setSummary((CharSequence)((Object)this.getText(2131230994) + SettingsConfiguration.getCastApplicationId((Context)this.activity)));
            preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$6(this));
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
            checkBoxPreference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$5(this));
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
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$3(this));
        if (preference instanceof ListPreference) {
            this.populateSubtitleConfig((ListPreference)preference);
            return;
        }
        Log.e("SettingsFragment", "Preference player type is NOT list preference!");
    }
    
    private void handleWifiOnlySetting() {
        if (this.findPreference((CharSequence)"nf_play_no_wifi_warning") != null) {
            BandwidthUtility.migrateWifiOnlySetting(this.serviceManager.getContext());
            if (BandwidthUtility.canShowDataSavingPreference(this.serviceManager.getContext())) {
                this.removeWiFiOnlySettings();
            }
        }
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
        sb.append(this.getString(2131231355)).append(" ");
        sb.append(PlayerTypeFactory.findDefaultPlayerType().getDescription());
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(sb.toString());
        list2.add("DEFAULT");
        if (AndroidUtils.isOpenMaxALSupported()) {
            list.add((String)this.getText(2131231360));
            list2.add("XAL");
            if (PlayerTypeFactory.isXalPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XAL");
                }
                listPreference.setValue("XAL");
            }
            list.add((String)this.getText(2131231361));
            list2.add("XALAMP");
            if (PlayerTypeFactory.isXalmpPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XALAMP");
                }
                listPreference.setValue("XALAMP");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device10)) {
            list.add((String)this.getText(2131231357));
            list2.add("JPLAYER");
            if (PlayerTypeFactory.isJPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYER");
                }
                listPreference.setValue("JPLAYER");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device11)) {
            list.add((String)this.getText(2131231359));
            list2.add("JPLAYERBASE");
            if (PlayerTypeFactory.isJPlayerBase(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYERBASE");
                }
                listPreference.setValue("JPLAYERBASE");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device12)) {
            list.add((String)this.getText(2131231358));
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
        list.add(this.getText(2131231365));
        list2.add("DEFAULT");
        list.add(this.getText(2131231366));
        list2.add("ENHANCED_XML");
        list.add(this.getText(2131231368));
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
    
    private void removeWiFiOnlySettings() {
        Log.d("SettingsFragment", "removing WiFiOnly settings");
        ((PreferenceGroup)this.findPreference((CharSequence)"video.playback")).removePreference(this.findPreference((CharSequence)"nf_play_no_wifi_warning"));
    }
    
    private void setDataSaverDescription(final Context context, final Preference preference) {
        if (context == null || preference == null) {
            return;
        }
        preference.setSummary(BandwidthUtility.getDataSaverDescription(context));
    }
    
    private void updateSubtitleConfig(final SubtitleConfiguration subtitleConfiguration) {
        Log.d("SettingsFragment", "Update subtitle config");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        intent.putExtra("lookupType", subtitleConfiguration.getLookupType());
        LocalBroadcastManager.getInstance((Context)this.activity).sendBroadcast(intent);
    }
    
    public void onBandwidthSettingsDone(final Context context) {
        final Preference preference = this.findPreference((CharSequence)"nf.bw_save");
        if (preference == null) {
            return;
        }
        this.setDataSaverDescription(context, preference);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this.getActivity();
        this.getPreferenceManager().setSharedPreferencesMode(0);
        this.getPreferenceManager().setSharedPreferencesName("nfxpref");
        this.addPreferencesFromResource(2131099651);
        ((PreferenceGroup)this.findPreference((CharSequence)"pref.screen")).removePreference(this.findPreference((CharSequence)"pref.qa.debugonly"));
        this.handlePushNotificationsSettings();
    }
    
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d("SettingsFragment", "onManagerReady");
        this.serviceManager = serviceManager;
        this.handleWifiOnlySetting();
        this.handleBandwidthSaveSettings();
    }
    
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
    }
    
    public void onPause() {
        super.onPause();
        this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener((SharedPreferences$OnSharedPreferenceChangeListener)this);
    }
    
    public void onResume() {
        super.onResume();
        this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener((SharedPreferences$OnSharedPreferenceChangeListener)this);
    }
    
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String s) {
        if ("nf_play_no_wifi_warning".equals(s)) {
            PreferenceUtils.putBooleanPref((Context)this.getActivity(), "nf_play_user_no_wifi_warned_already", false);
        }
    }
}
