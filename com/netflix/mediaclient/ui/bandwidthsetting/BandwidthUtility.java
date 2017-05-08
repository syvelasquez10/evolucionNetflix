// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Context;

public final class BandwidthUtility
{
    private static final Boolean DEFAULT_AUTO_SWITCH_STATE_ON;
    public static final int DEFAULT_CELLULAR_AUTO_KBPS = 600;
    public static final int DEFAULT_MANUAL_CHOICE;
    private static final Boolean FORCE_DATA_SAVING_PREF;
    public static final String TAG = "nf_bw_saving";
    private static final int UNLIMITED_CELLULAR_BITRATE_KBPS = 20000;
    
    static {
        FORCE_DATA_SAVING_PREF = false;
        DEFAULT_AUTO_SWITCH_STATE_ON = true;
        DEFAULT_MANUAL_CHOICE = BandwidthPreferenceDialog$ManualBwChoice.LOW.getValue();
    }
    
    public static boolean canShowDataSavingPreference(final Context context) {
        if (!BandwidthUtility.FORCE_DATA_SAVING_PREF) {
            if (!ConnectivityUtils.hasCellular(context)) {
                Log.d("nf_bw_saving", "no cellular!!");
                return false;
            }
            if (isDataSaverDisabled(context)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean canSupportDDPlus51(final Context context) {
        return isDataSaverDisabled(context) || isAutomaticOn(context) || BandwidthPreferenceDialog$ManualBwChoice.LOW.getValue() != getManualChoice(context);
    }
    
    private static int getBitrateForManualChoice(final Context context, final int n, final DataSaveConfigData dataSaveConfigData) {
        if (BandwidthPreferenceDialog$ManualBwChoice.LOW.getValue() == n) {
            return dataSaveConfigData.videoBitrateLow;
        }
        if (BandwidthPreferenceDialog$ManualBwChoice.MEDIUM.getValue() == n) {
            return dataSaveConfigData.videoBitrateMedium;
        }
        if (BandwidthPreferenceDialog$ManualBwChoice.HIGH.getValue() == n) {
            return dataSaveConfigData.videoBitrateHigh;
        }
        if (BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED.getValue() == n) {
            return 20000;
        }
        if (BandwidthPreferenceDialog$ManualBwChoice.OFF.getValue() == n) {
            return 0;
        }
        return 600;
    }
    
    public static int getCellularVideoBitrateKbps(final Context context, final DataSaveConfigData dataSaveConfigData) {
        if (dataSaveConfigData == null) {
            return 600;
        }
        if (isAutomaticOn(context)) {
            return dataSaveConfigData.videoBitrateAuto;
        }
        return getBitrateForManualChoice(context, getManualChoice(context), dataSaveConfigData);
    }
    
    public static int getDataSaverDescription(final Context context) {
        if (Boolean.valueOf(isAutomaticOn(context))) {
            return 2131165430;
        }
        return getManualChoiceDescription(BandwidthPreferenceDialog$ManualBwChoice.create(getManualChoice(context)));
    }
    
    public static int getManualChoice(final Context context) {
        final int intPref = PreferenceUtils.getIntPref(context, "bw_user_manual_setting", -1);
        int default_MANUAL_CHOICE;
        if (intPref < 0 || (default_MANUAL_CHOICE = intPref) > BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED.getValue()) {
            default_MANUAL_CHOICE = BandwidthUtility.DEFAULT_MANUAL_CHOICE;
        }
        return default_MANUAL_CHOICE;
    }
    
    private static int getManualChoiceDescription(final BandwidthPreferenceDialog$ManualBwChoice bandwidthPreferenceDialog$ManualBwChoice) {
        switch (BandwidthUtility$1.$SwitchMap$com$netflix$mediaclient$ui$bandwidthsetting$BandwidthPreferenceDialog$ManualBwChoice[bandwidthPreferenceDialog$ManualBwChoice.ordinal()]) {
            default: {
                return 2131165430;
            }
            case 1: {
                return 2131165438;
            }
            case 2: {
                return 2131165434;
            }
            case 3: {
                return 2131165436;
            }
            case 4: {
                return 2131165432;
            }
            case 5: {
                return 2131165444;
            }
        }
    }
    
    public static boolean isAutomaticOn(final Context context) {
        final int intPref = PreferenceUtils.getIntPref(context, "bw_user_control_auto", -1);
        if (intPref < 0) {
            return BandwidthUtility.DEFAULT_AUTO_SWITCH_STATE_ON;
        }
        return intPref != 0;
    }
    
    public static boolean isBWSavingEnabledForPlay(final Context context) {
        boolean b = true;
        if (!isDataSaverDisabled(context)) {
            if (Log.isLoggable()) {
                Log.d("nf_bw_saving", String.format("isNetworkTypeCellular: %b", ConnectivityUtils.isNetworkTypeCellular(context)));
            }
            if (ConnectivityUtils.isNetworkTypeCellular(context)) {
                if (isAutomaticOn(context)) {
                    return true;
                }
                if (BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED.getValue() == getManualChoice(context)) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    public static boolean isDataSaverDisabled(final Context context) {
        return PreferenceUtils.getBooleanPref(context, "disable_data_saver", false);
    }
    
    public static boolean isPlaybackInWifiOnly(final Context context) {
        final boolean b = false;
        boolean booleanPref;
        if (isDataSaverDisabled(context)) {
            booleanPref = PreferenceUtils.getBooleanPref(context, "nf_play_no_wifi_warning", false);
        }
        else {
            booleanPref = b;
            if (!isAutomaticOn(context)) {
                final int manualChoice = getManualChoice(context);
                booleanPref = b;
                if (BandwidthPreferenceDialog$ManualBwChoice.OFF.getValue() == manualChoice) {
                    return true;
                }
            }
        }
        return booleanPref;
    }
    
    public static void migrateWifiOnlySetting(final Context context) {
        if (isDataSaverDisabled(context)) {
            Log.d("nf_bw_saving", "Data saver functionality is not yet enabled .. skip migrate");
        }
        else if (PreferenceUtils.getBooleanPref(context, "nf_play_no_wifi_warning", false)) {
            Log.d("nf_bw_saving", "migrating wifi only setting to latest");
            saveUserSelections(context, false, BandwidthPreferenceDialog$ManualBwChoice.OFF.getValue());
            Log.d("nf_bw_saving", "unsetting old wifi only setting");
            PreferenceUtils.putBooleanPref(context, "nf_play_no_wifi_warning", false);
        }
    }
    
    public static void saveUserSelections(final Context context, final Boolean b, final int n) {
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        PreferenceUtils.putIntPref(context, "bw_user_control_auto", n2);
        PreferenceUtils.putIntPref(context, "bw_user_manual_setting", n);
    }
    
    public static boolean shouldDelayBifForPlay(final Context context) {
        return isBWSavingEnabledForPlay(context) && BandwidthPreferenceDialog$ManualBwChoice.LOW.getValue() != getManualChoice(context);
    }
    
    public static boolean shouldLimitCellularVideoBitrate(final Context context) {
        return isAutomaticOn(context) || BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED.getValue() != getManualChoice(context);
    }
}
