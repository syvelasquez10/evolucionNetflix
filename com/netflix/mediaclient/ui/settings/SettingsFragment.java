// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import java.io.Serializable;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build;
import android.preference.PreferenceScreen;
import android.preference.PreferenceGroup;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gcm.GCMRegistrar;
import android.support.v4.content.LocalBroadcastManager;
import android.preference.Preference$OnPreferenceClickListener;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceChangeListener;
import android.net.Uri;
import android.content.Intent;
import android.app.Fragment;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayerType;
import android.app.Activity;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment
{
    private static final String FAKE_KEY_ENABLE_NOTIFICATIONS = "nf_notification_enable";
    private static final String FAKE_KEY_PLAYER_TYPE = "ui.playertype";
    private static final String FAKE_KEY_SUBTITLE_CONFIG = "ui.subtitleConfig";
    private static final String KEY_PREF_NOTIFICATION = "pref.notification";
    private static final String PLAYER_TYPE_DEFAULT = "DEFAULT";
    private static final String PLAYER_TYPE_DRMPLAY = "DRM.PLAY";
    private static final String PLAYER_TYPE_JPLAYER = "JPLAYER";
    private static final String PLAYER_TYPE_JPLAYER2 = "JPLAYER2";
    private static final String PLAYER_TYPE_JPLAYERBASE = "JPLAYERBASE";
    private static final String PLAYER_TYPE_NFAMP = "NFAMP";
    private static final String PLAYER_TYPE_OMX = "OMX";
    private static final String PLAYER_TYPE_SOFTWARE = "SOFT";
    private static final String PLAYER_TYPE_XAL = "XAL";
    private static final String PLAYER_TYPE_XALMP = "XALAMP";
    private static final String PREFS_NAME = "nfxpref";
    private static final String SUBTITLE_CONFIG_DEFAULT = "DEFAULT";
    private static final String SUBTITLE_CONFIG_ENHANCED_XML = "ENHANCED_XML";
    private static final String SUBTITLE_CONFIG_SIMPLE_EVENTS = "SIMPLE_EVENTS";
    private static final String SUBTITLE_CONFIG_SIMPLE_XML = "SIMPLE_XML";
    private static final String TAG = "SettingsFragment";
    private Activity activity;
    
    private void changePlayer(final PlayerType playerType) {
        if (playerType == null) {
            Log.w("SettingsFragment", "Invalid player type choosen! This should not happen, report it.");
            new AlertDialog$Builder((Context)this.activity).setTitle((CharSequence)"").setMessage(2131493051).setPositiveButton(2131492910, (DialogInterface$OnClickListener)null).show();
            return;
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.w("SettingsFragment", "Updating player type!");
                PlayerTypeFactory.setPlayerTypeForQAOverride((Context)SettingsFragment.this.activity, playerType);
                Log.w("SettingsFragment", "Updating player type done.");
            }
        });
    }
    
    public static Fragment create() {
        return (Fragment)new SettingsFragment();
    }
    
    private Intent createViewLegalTermsOfUseIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.netflix.com/TermsOfUse"));
    }
    
    private Intent createViewPrivacyPolicyIntent() {
        return new Intent("android.intent.action.VIEW").setData(Uri.parse("https://signup.netflix.com/PrivacyPolicy"));
    }
    
    private void handlePlayerType() {
        final Preference preference = this.findPreference((CharSequence)"ui.playertype");
        if (preference == null) {
            Log.w("SettingsFragment", "Debug: player overlay not found");
            return;
        }
        Log.d("SettingsFragment", "Debug: player type");
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new Preference$OnPreferenceChangeListener() {
            public boolean onPreferenceChange(final Preference preference, final Object o) {
                if (o instanceof String) {
                    final String s = (String)o;
                    if ("DEFAULT".equals(s)) {
                        Log.d("SettingsFragment", "Reset player to default");
                        PlayerTypeFactory.resetPlayerTypeByQA((Context)SettingsFragment.this.activity);
                    }
                    else if ("OMX".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to OMX");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getOmxPlayer());
                    }
                    else if ("DRM.PLAY".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to drm.play");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getDrmPlayPlayer());
                    }
                    else if ("SOFT".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to software");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getSoftwarePlayer());
                    }
                    else if ("XAL".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to XAL");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getXalPlayer());
                    }
                    else if ("XALAMP".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to XALMP");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getXalmpPlayer());
                    }
                    else if ("NFAMP".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to NFAMP");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getNfampPlayer());
                    }
                    else if ("JPLAYER".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to JPLAYER");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getJPlayer());
                    }
                    else if ("JPLAYERBASE".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to JPLAYERBASE");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getJPlayerBase());
                    }
                    else if ("JPLAYER2".equals(s)) {
                        Log.d("SettingsFragment", "Set Player type to JPLAYER2");
                        SettingsFragment.this.changePlayer(PlayerTypeFactory.getJPlayer2());
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
        });
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
        if (Log.isLoggable("SettingsFragment", 3)) {
            Log.d("SettingsFragment", "Notifications were enabled " + registeredForPushNotifications);
        }
        final CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.findPreference((CharSequence)"nf_notification_enable");
        if (checkBoxPreference != null) {
            checkBoxPreference.setChecked(registeredForPushNotifications);
            checkBoxPreference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
                public boolean onPreferenceClick(final Preference preference) {
                    Log.d("SettingsFragment", "Notification enabled clicked");
                    if (preference instanceof CheckBoxPreference) {
                        if (((CheckBoxPreference)preference).isChecked()) {
                            Log.d("SettingsFragment", "Register for notifications");
                            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
                            intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
                            LocalBroadcastManager.getInstance((Context)SettingsFragment.this.activity).sendBroadcast(intent);
                        }
                        else {
                            Log.d("SettingsFragment", "Unregister from notifications");
                            final Intent intent2 = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
                            intent2.addCategory("com.netflix.mediaclient.intent.category.PUSH");
                            LocalBroadcastManager.getInstance((Context)SettingsFragment.this.activity).sendBroadcast(intent2);
                        }
                    }
                    else {
                        Log.e("SettingsFragment", "We did not received notification checkbox preference!");
                    }
                    return true;
                }
            });
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
        preference.setOnPreferenceChangeListener((Preference$OnPreferenceChangeListener)new Preference$OnPreferenceChangeListener() {
            public boolean onPreferenceChange(final Preference preference, final Object o) {
                if (o instanceof String) {
                    final String s = (String)o;
                    if ("DEFAULT".equals(s)) {
                        Log.d("SettingsFragment", "Sets ENHANCED XML subtitle configuration (default)");
                        SubtitleConfiguration.clearQaLocalOverride((Context)SettingsFragment.this.activity);
                        SettingsFragment.this.updateSubtitleConfig(SubtitleConfiguration.DEFAULT);
                    }
                    else if ("ENHANCED_XML".equals(s)) {
                        Log.d("SettingsFragment", "Sets ENHANCED XML subtitle configuration (default)");
                        SubtitleConfiguration.updateQaLocalOverride((Context)SettingsFragment.this.activity, SubtitleConfiguration.ENHANCED_XML.getLookupType());
                        SettingsFragment.this.updateSubtitleConfig(SubtitleConfiguration.ENHANCED_XML);
                    }
                    else if ("SIMPLE_XML".equals(s)) {
                        Log.d("SettingsFragment", "Sets SIMPLE XML subtitle configuration");
                        SubtitleConfiguration.updateQaLocalOverride((Context)SettingsFragment.this.activity, SubtitleConfiguration.SIMPLE_XML.getLookupType());
                        SettingsFragment.this.updateSubtitleConfig(SubtitleConfiguration.SIMPLE_XML);
                    }
                    else if ("SIMPLE_EVENTS".equals(s)) {
                        Log.d("SettingsFragment", "Sets SIMPLE EVENTS subtitle configuration (legacy)");
                        SubtitleConfiguration.updateQaLocalOverride((Context)SettingsFragment.this.activity, SubtitleConfiguration.SIMPLE_EVENTS.getLookupType());
                        SettingsFragment.this.updateSubtitleConfig(SubtitleConfiguration.SIMPLE_EVENTS);
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
        });
        if (preference instanceof ListPreference) {
            this.populateSubtitleConfig((ListPreference)preference);
            return;
        }
        Log.e("SettingsFragment", "Preference player type is NOT list preference!");
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
        final String registrationId = GCMRegistrar.getRegistrationId((Context)this.activity);
        Log.d("SettingsFragment", "RegId " + registrationId);
        return !StringUtils.isEmpty(registrationId);
    }
    
    private void populatePlayerTypes(final ListPreference listPreference) {
        final PlayerType currentType = PlayerTypeFactory.getCurrentType((Context)this.activity);
        final boolean default1 = PlayerTypeFactory.isDefault((Context)this.activity);
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getString(2131493047)).append(" ");
        PlayerType playerType;
        if ((playerType = PlayerTypeFactory.findDefaultPlayerType((Context)this.activity)) == null) {
            Log.e("SettingsFragment", "Default player not found! It should never happen!");
            playerType = PlayerType.device6;
        }
        sb.append(playerType.getDescription());
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        list.add(sb.toString());
        list2.add("DEFAULT");
        if (PlayerTypeFactory.getOmxPlayer() != null) {
            list.add((String)this.getText(2131493048));
            list2.add("OMX");
            if (PlayerTypeFactory.isOmxPlayer(currentType) && !default1) {
                listPreference.setDefaultValue((Object)"OMX");
            }
            listPreference.setValue("OMX");
        }
        if (AndroidUtils.isDrmPlaySupported()) {
            list.add((String)this.getText(2131493049));
            list2.add("DRM.PLAY");
            if (PlayerTypeFactory.isDrmPlayPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"DRM.PLAY");
                }
                listPreference.setValue("DRM.PLAY");
            }
        }
        list.add((String)this.getText(2131493050));
        list2.add("SOFT");
        if (PlayerTypeFactory.isSoftwarePlayer(currentType)) {
            if (!default1) {
                listPreference.setDefaultValue((Object)"SOFT");
            }
            listPreference.setValue("SOFT");
        }
        if (AndroidUtils.isOpenMaxALSupported()) {
            list.add((String)this.getText(2131493082));
            list2.add("XAL");
            if (PlayerTypeFactory.isXalPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XAL");
                }
                listPreference.setValue("XAL");
            }
            list.add((String)this.getText(2131493083));
            list2.add("XALAMP");
            if (PlayerTypeFactory.isXalmpPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"XALAMP");
                }
                listPreference.setValue("XALAMP");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device9)) {
            list.add((String)this.getText(2131493084));
            list2.add("NFAMP");
            if (PlayerTypeFactory.isNfampPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"NFAMP");
                }
                listPreference.setValue("NFAMP");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device10)) {
            list.add((String)this.getText(2131493089));
            list2.add("JPLAYER");
            if (PlayerTypeFactory.isJPlayer(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYER");
                }
                listPreference.setValue("JPLAYER");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device11)) {
            list.add((String)this.getText(2131493090));
            list2.add("JPLAYERBASE");
            if (PlayerTypeFactory.isJPlayerBase(currentType)) {
                if (!default1) {
                    listPreference.setDefaultValue((Object)"JPLAYERBASE");
                }
                listPreference.setValue("JPLAYERBASE");
            }
        }
        if (PlayerTypeFactory.isPlayerTypeSupported(PlayerType.device12)) {
            list.add((String)this.getText(2131493091));
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
        list.add(this.getText(2131493225));
        list2.add("DEFAULT");
        list.add(this.getText(2131493226));
        list2.add("ENHANCED_XML");
        list.add(this.getText(2131493227));
        list2.add("SIMPLE_XML");
        list.add(this.getText(2131493228));
        list2.add("SIMPLE_EVENTS");
        listPreference.setDefaultValue((Object)"DEFAULT");
        if (loadQaLocalOverride == SubtitleConfiguration.SIMPLE_EVENTS) {
            listPreference.setValue("SIMPLE_EVENTS");
        }
        else if (loadQaLocalOverride == SubtitleConfiguration.SIMPLE_XML) {
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
    
    private void updateAboutDevice() {
        final String string = this.getString(2131493111);
        int n = 0;
        Serializable s = string;
        while (true) {
            try {
                final PackageInfo packageInfo = this.activity.getPackageManager().getPackageInfo(this.activity.getPackageName(), 0);
                s = string;
                final String s2 = (String)(s = packageInfo.versionName);
                final int versionCode = packageInfo.versionCode;
                s = s2;
                n = versionCode;
                s = new StringBuilder().append((String)s);
                if (n > 0) {
                    ((StringBuilder)s).append(" (code ").append(n).append(")").append(", ");
                }
                ((StringBuilder)s).append("OS API: ").append(AndroidUtils.getAndroidVersion()).append("\n").append("model: ").append(Build.MODEL).append(", ").append("build: ").append(Build.DISPLAY);
                this.findPreference((CharSequence)"ui.about").setSummary((CharSequence)((StringBuilder)s).toString());
                this.findPreference((CharSequence)"ui.about").setSelectable(false);
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.handleException("SettingsFragment", (Exception)ex);
                continue;
            }
            break;
        }
    }
    
    private void updateSubtitleConfig(final SubtitleConfiguration subtitleConfiguration) {
        Log.d("SettingsFragment", "Update subtitle config");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PLAYER_SUBTITLE_CONFIG_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.PLAYER");
        intent.putExtra("lookupType", subtitleConfiguration.getLookupType());
        LocalBroadcastManager.getInstance((Context)this.activity).sendBroadcast(intent);
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this.getActivity();
        this.getPreferenceManager().setSharedPreferencesMode(0);
        this.getPreferenceManager().setSharedPreferencesName("nfxpref");
        this.addPreferencesFromResource(2130968577);
        this.findPreference((CharSequence)this.getString(2131492895)).setIntent(OpenSourceLicensesActivity.create((Context)this.activity));
        final Preference preference = this.findPreference((CharSequence)"pref.privacy");
        preference.setIntent(this.createViewPrivacyPolicyIntent());
        preference.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference) {
                LogUtils.reportUiModalViewChanged((Context)SettingsFragment.this.activity, IClientLogging.ModalView.privacyPolicy);
                return false;
            }
        });
        final Preference preference2 = this.findPreference((CharSequence)"pref.terms");
        preference2.setIntent(this.createViewLegalTermsOfUseIntent());
        preference2.setOnPreferenceClickListener((Preference$OnPreferenceClickListener)new Preference$OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference) {
                LogUtils.reportUiModalViewChanged((Context)SettingsFragment.this.activity, IClientLogging.ModalView.legalTerms);
                return false;
            }
        });
        this.updateAboutDevice();
        ((PreferenceGroup)this.findPreference((CharSequence)"pref.screen")).removePreference(this.findPreference((CharSequence)"pref.qa.debugonly"));
        this.handlePushNotificationsSettings();
    }
}
