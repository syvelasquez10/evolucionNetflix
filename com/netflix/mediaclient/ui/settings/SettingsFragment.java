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
import android.content.Context;
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

public class SettingsFragment extends PreferenceFragment implements SharedPreferences$OnSharedPreferenceChangeListener, ManagerStatusListener
{
    private static final String FAKE_KEY_BW_SAVE = "nf.bw_save";
    private static final String FAKE_KEY_DOWNLOADS_DELETE_ALL_CONFIG = "pref.downloads.remove_all";
    private static final String FAKE_KEY_DOWNLOADS_VIDEO_QUALITY_CONFIG = "pref.downloads.video_quality";
    private static final String FAKE_KEY_ENABLE_NOTIFICATIONS = "nf_notification_enable";
    private static final String FAKE_KEY_SUBTITLE_CONFIG = "ui.subtitleConfig";
    private static final String FAKE_KEY_WIFI_ONLY = "nf_play_no_wifi_warning";
    private static final String KEY_DOWNLOADS_WIFI_ONLY_CONFIG = "pref.downloads.wifi_only";
    private static final String KEY_PREF_NOTIFICATION = "pref.notification";
    private static final String OFFLINE_VIDEO_FORMAT_AVC = "Default";
    private static final String OFFLINE_VIDEO_FORMAT_HEVC = "HEVC";
    private static final String OFFLINE_VIDEO_FORMAT_VP9 = "VP9";
    private static final String PREFS_DOWNLOAD_CATEGORY_KEY = "pref.downloads";
    private static final String PREFS_NAME = "nfxpref";
    private static final String SUBTITLE_CONFIG_DEFAULT = "DEFAULT";
    private static final String SUBTITLE_CONFIG_ENHANCED_XML = "ENHANCED_XML";
    private static final String SUBTITLE_CONFIG_SIMPLE_XML = "SIMPLE_XML";
    private static final String TAG = "SettingsFragment";
    private Activity activity;
    private SettingsFragment$ActivityCallbackListener activityCallback;
    Dialog dialog;
    private final SimpleOfflineAgentListener mDeleteAllListener;
    private ServiceManager serviceManager;
    
    public SettingsFragment() {
        this.mDeleteAllListener = new SettingsFragment$1(this);
    }
    
    public static Fragment create() {
        return (Fragment)new SettingsFragment();
    }
    
    private void handleAllOfflineItemsDeleted() {
        final SettingsActivity settingsActivity = AndroidUtils.getContextAs((Context)this.getActivity(), SettingsActivity.class);
        if (settingsActivity == null) {
            return;
        }
        final Preference preference = this.findPreference((CharSequence)"pref.downloads.remove_all");
        final PreferenceGroup preferenceGroup = (PreferenceGroup)this.findPreference((CharSequence)"pref.downloads");
        if (preferenceGroup != null) {
            preferenceGroup.removePreference(preference);
        }
        settingsActivity.refreshStorageIndicator();
    }
    
    private void handleAndroidHttpStackSettings(final ServiceManager serviceManager) {
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
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$7(this));
    }
    
    private void handleCastAppIdSettings() {
        final Preference preference = this.findPreference((CharSequence)"ui.castAppId");
        if (preference != null) {
            preference.setSummary((CharSequence)((Object)this.getText(2131231009) + SettingsConfiguration.getCastApplicationId((Context)this.activity)));
            preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$12(this));
        }
    }
    
    private void handleDownloadsDeleteAllConfig(final ServiceManager serviceManager) {
        final Preference preference = this.findPreference((CharSequence)"pref.downloads.remove_all");
        if (serviceManager.getOfflineAgent() == null || preference == null) {
            return;
        }
        if (serviceManager.getOfflineAgent().getLatestOfflinePlayableList().getTitleCount() < 1) {
            ((PreferenceGroup)this.findPreference((CharSequence)"pref.downloads")).removePreference(preference);
            return;
        }
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$9(this, serviceManager));
    }
    
    private void handleDownloadsPreferenceGroup(final ServiceManager serviceManager) {
        if (serviceManager.isOfflineFeatureAvailable()) {
            this.handleDownloadsDeleteAllConfig(serviceManager);
            this.handleDownloadsVideoQualityConfig(serviceManager);
            this.handleDownloadsWifiOnlySetting(serviceManager);
            return;
        }
        this.getPreferenceScreen().removePreference(this.findPreference((CharSequence)"pref.downloads"));
    }
    
    private void handleDownloadsVideoQualityConfig(final ServiceManager serviceManager) {
        final ServiceAgent$ConfigurationAgentInterface configuration = serviceManager.getConfiguration();
        if (serviceManager.getOfflineAgent() == null || configuration == null) {
            return;
        }
        final Preference preference = this.findPreference((CharSequence)"pref.downloads");
        final Preference preference2 = this.findPreference((CharSequence)"pref.downloads.video_quality");
        if (preference == null || preference2 == null) {
            Log.d("SettingsFragment", "downloads downloadCategoryPref or downloadQualityPref is null");
            return;
        }
        if (!(preference instanceof PreferenceGroup)) {
            Log.d("SettingsFragment", "downloadCategoryPref not a group pref");
            return;
        }
        if (!(preference2 instanceof ListPreference)) {
            Log.d("SettingsFragment", "downloads downloadQualityPref not a list pref");
            return;
        }
        final ListPreference listPreference = (ListPreference)preference2;
        if (listPreference == null) {
            Log.w("SettingsFragment", "Debug: downloads video quality not found");
            return;
        }
        Log.d("SettingsFragment", "Debug: downloads video quality");
        listPreference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$10(this, listPreference, serviceManager));
        if (listPreference instanceof ListPreference) {
            this.populateDownloadsVideoQualityConfig(listPreference);
            return;
        }
        Log.e("SettingsFragment", "Preference downloads video quality type is NOT list preference!");
    }
    
    private void handleDownloadsWifiOnlySetting(final ServiceManager serviceManager) {
        if (serviceManager.getOfflineAgent() != null) {
            final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.findPreference((CharSequence)"pref.downloads.wifi_only");
            if (checkBoxPreference != null) {
                checkBoxPreference.setChecked(serviceManager.getOfflineAgent().getRequiresUnmeteredNetwork());
                checkBoxPreference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$6(this, serviceManager));
            }
        }
    }
    
    private void handleForceSoftwareDecoder() {
        final Preference preference = this.findPreference((CharSequence)"ui.forceswdecoder");
        if (preference instanceof CheckBoxPreference) {
            final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)preference;
            String s;
            if (ExoVideoCodecSelector.isHasVP9SoftwareDecoder()) {
                s = "VP9: SW decoder [" + "Yes";
            }
            else {
                s = "VP9: SW decoder [" + "No";
            }
            final String string = s + "], HW decoder [";
            String s2;
            if (ExoVideoCodecSelector.isHasVP9HardwareDecoder()) {
                s2 = string + "Yes";
            }
            else {
                s2 = string + "No";
            }
            final String string2 = s2 + "], secured [";
            String s3;
            if (ExoVideoCodecSelector.isHasSecureVP9Decoder()) {
                s3 = string2 + "Yes].";
            }
            else {
                s3 = string2 + "No].";
            }
            final String string3 = s3 + "\nHEVC: HW decoder [";
            String s4;
            if (ExoVideoCodecSelector.isHasHEVCHardwareDecoder()) {
                s4 = string3 + "Yes";
            }
            else {
                s4 = string3 + "No";
            }
            final String string4 = s4 + "], secured [";
            String summary;
            if (ExoVideoCodecSelector.isHasSecureHEVCDecoder()) {
                summary = string4 + "Yes].";
            }
            else {
                summary = string4 + "No].";
            }
            checkBoxPreference.setSummary((CharSequence)summary);
            checkBoxPreference.setChecked(ExoVideoCodecSelector.isUseSoftwareDecoder());
            checkBoxPreference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$5(this));
        }
    }
    
    private void handleOfflineVideoFormatSetting() {
        final Preference preference = this.findPreference((CharSequence)"ui.offlineVideoFormat");
        if (preference != null) {
            Log.d("SettingsFragment", "set offline video format");
            preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$4(this));
            if (!(preference instanceof ListPreference)) {
                Log.e("SettingsFragment", " offline video format is NOT list preference!");
                return;
            }
            final ArrayList<String> list = new ArrayList<String>();
            final ArrayList<String> list2 = new ArrayList<String>();
            list.add("Default");
            list.add("HEVC");
            list.add("VP9");
            list2.add("video/avc");
            list2.add("video/hevc");
            list2.add("video/x-vnd.on2.vp9");
            ((ListPreference)preference).setEntries((CharSequence[])list.toArray(new CharSequence[list.size()]));
            ((ListPreference)preference).setEntryValues((CharSequence[])list2.toArray(new CharSequence[list2.size()]));
            preference.setDefaultValue((Object)ManifestRequestParamBuilder.getPresetFormat());
            ((ListPreference)preference).setValue(ManifestRequestParamBuilder.getPresetFormat());
        }
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
            checkBoxPreference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new SettingsFragment$11(this));
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
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$8(this));
        if (preference instanceof ListPreference) {
            this.populateSubtitleConfig((ListPreference)preference);
            return;
        }
        Log.e("SettingsFragment", "Preference player type is NOT list preference!");
    }
    
    private void handleUserPinCheckSetting() {
        final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.findPreference((CharSequence)"ui.forcePinCheck");
        if (checkBoxPreference == null) {
            return;
        }
        checkBoxPreference.setChecked(PreferenceUtils.getBooleanPref((Context)this.getActivity(), "prefs_debug_settings_force_pin_check", false));
        checkBoxPreference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new SettingsFragment$2(this));
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
    
    private void populateDownloadsVideoQualityConfig(final ListPreference listPreference) {
        final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(StringUtils.generateTitleAndSubtitles(this.getString(2131231356), this.getString(2131231357)));
        list2.add(DownloadVideoQuality.DEFAULT.getValue());
        list.add(StringUtils.generateTitleAndSubtitles(this.getString(2131231354), this.getString(2131231355)));
        list2.add(DownloadVideoQuality.BEST.getValue());
        final DownloadVideoQuality currentDownloadVideoQuality = ((NetflixActivity)this.getActivity()).getServiceManager().getOfflineAgent().getCurrentDownloadVideoQuality();
        final Preference preference = this.findPreference((CharSequence)"pref.downloads.video_quality");
        switch (SettingsFragment$13.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$DownloadVideoQuality[currentDownloadVideoQuality.ordinal()]) {
            case 1: {
                listPreference.setValue(DownloadVideoQuality.BEST.getValue());
                preference.setSummary(this.getText(2131231354));
                break;
            }
            case 2:
            case 3: {
                listPreference.setValue(DownloadVideoQuality.DEFAULT.getValue());
                preference.setSummary(this.getText(2131231356));
                break;
            }
        }
        listPreference.setEntries((CharSequence[])list.toArray(new CharSequence[list.size()]));
        listPreference.setEntryValues((CharSequence[])list2.toArray(new CharSequence[list2.size()]));
    }
    
    private void populateSubtitleConfig(final ListPreference listPreference) {
        final SubtitleConfiguration loadQaLocalOverride = SubtitleConfiguration.loadQaLocalOverride((Context)this.activity);
        final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(this.getText(2131231522));
        list2.add("DEFAULT");
        list.add(this.getText(2131231523));
        list2.add("ENHANCED_XML");
        list.add(this.getText(2131231525));
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
    
    private void updateDownloadsVideoQualityConfig(final DownloadVideoQuality downloadVideoQuality, final ServiceManager serviceManager) {
        if (serviceManager.getOfflineAgent() != null) {
            serviceManager.getOfflineAgent().setDownloadVideoQuality(downloadVideoQuality);
        }
    }
    
    private void updateSubtitleConfig(final SubtitleConfiguration subtitleConfiguration) {
        Log.d("SettingsFragment", "Update subtitle config");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        intent.putExtra("lookupType", subtitleConfiguration.getLookupType());
        LocalBroadcastManager.getInstance((Context)this.activity).sendBroadcast(intent);
    }
    
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Activity)) {
            return;
        }
        try {
            this.activityCallback = (SettingsFragment$ActivityCallbackListener)activity;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement ActivityCallbackListener");
        }
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
        this.handleDownloadsPreferenceGroup(serviceManager);
        this.handleAndroidHttpStackSettings(this.serviceManager);
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
