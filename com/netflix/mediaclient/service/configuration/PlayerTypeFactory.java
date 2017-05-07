// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.media.JPlayer.AdaptiveMediaDecoderHelper;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.media.PlayerType;

public final class PlayerTypeFactory
{
    private static final String PREFERENCE_PLAYER_TYPE = "nflx_player_type";
    private static final String PREFERENCE_PLAYER_TYPE_QA_OVERRIDE = "nflx_player_type_qa";
    private static final String TAG = "nf-playertypefactory";
    private static int cryptoFactoryType;
    private static PlayerType currentType;
    
    public static void clearRecords(final Context context) {
        PreferenceUtils.removePref(context, "nflx_player_type");
    }
    
    public static PlayerType findDefaultPlayerType(final Context context) {
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (Log.isLoggable("nf-playertypefactory", 3)) {
            Log.d("nf-playertypefactory", "Crypto factory type (CDM is 2): " + PlayerTypeFactory.cryptoFactoryType);
        }
        if (androidVersion > 18 && AdaptiveMediaDecoderHelper.isAvcDecoderSupportsAdaptivePlayback()) {
            Log.d("nf-playertypefactory", "JB MR2+, Default to JPlayer2");
            return PlayerType.device12;
        }
        if (androidVersion >= 18 && isWidevineSupported()) {
            Log.d("nf-playertypefactory", "JB MR2 with WV, Default to JPlayer2");
            return PlayerType.device12;
        }
        if (androidVersion >= 17 && AndroidUtils.isPropertyStreamingVideoDrs()) {
            Log.d("nf-playertypefactory", "JB+ with ro.streaming.video.drs presented and true, Default to JPlayer2 ");
            return PlayerType.device12;
        }
        if (androidVersion >= 17) {
            Log.d("nf-playertypefactory", "JB MR1+, Default to JPlayer");
            return PlayerType.device10;
        }
        if (androidVersion >= 14) {
            Log.d("nf-playertypefactory", "Default to OpenMax AL (XAL)");
            return PlayerType.device7;
        }
        Log.d("nf-playertypefactory", "Default to visualon player");
        return PlayerType.device6;
    }
    
    public static PlayerType getCurrentType(final Context context) {
        synchronized (PlayerTypeFactory.class) {
            if (PlayerTypeFactory.currentType == null) {
                Log.d("nf-playertypefactory", "First call getCurrentType(). Initiate it. Check if it is saved to preferences before.");
                PlayerTypeFactory.currentType = getPlayerTypeFromPreferences(context);
            }
            if (PlayerTypeFactory.currentType == null) {
                Log.d("nf-playertypefactory", "Current type was not saved to preferences before. Find default player.");
                PlayerTypeFactory.currentType = findDefaultPlayerType(context);
            }
            if (!isValidPlayerType(context)) {
                Log.e("nf-playertypefactory", "Current type is not valid! Replace it with default!");
                PreferenceUtils.removePref(context, "nflx_player_type");
                PlayerTypeFactory.currentType = findDefaultPlayerType(context);
            }
            return PlayerTypeFactory.currentType;
        }
    }
    
    public static PlayerType getDrmPlayPlayer() {
        if (!AndroidUtils.isDrmPlaySupported()) {
            Log.w("nf-playertypefactory", "drm.play is not supported on this device");
            return null;
        }
        switch (AndroidUtils.getAndroidVersion()) {
            default: {
                return null;
            }
            case 8:
            case 9:
            case 10: {
                return PlayerType.device2;
            }
            case 11: {
                return PlayerType.device3;
            }
            case 12:
            case 13: {
                return PlayerType.device5;
            }
        }
    }
    
    public static PlayerType getJPlayer() {
        return PlayerType.device10;
    }
    
    public static PlayerType getJPlayer2() {
        return PlayerType.device12;
    }
    
    public static PlayerType getJPlayerBase() {
        return PlayerType.device11;
    }
    
    public static PlayerType getNfampPlayer() {
        return PlayerType.device9;
    }
    
    public static PlayerType getOmxPlayer() {
        switch (AndroidUtils.getAndroidVersion()) {
            default: {
                return null;
            }
            case 8: {
                return PlayerType.device1;
            }
            case 9:
            case 10: {
                return PlayerType.device4;
            }
        }
    }
    
    private static PlayerType getPlayerTypeFromPreferences(final Context context) {
        int intPref = -1;
        if (-1 < 1) {
            Log.d("nf-playertypefactory", "Check for configuration override override");
            intPref = PreferenceUtils.getIntPref(context, "nflx_player_type", -1);
        }
        PlayerType playerType;
        if (intPref < 1) {
            Log.d("nf-playertypefactory", "Player type was not saved in preferences before, no override.");
            playerType = null;
        }
        else if ((playerType = PlayerType.toPlayerType(intPref)) == null) {
            Log.e("nf-playertypefactory", "Player not found for type " + intPref);
            return null;
        }
        return playerType;
    }
    
    public static PlayerType getSoftwarePlayer() {
        return PlayerType.device6;
    }
    
    public static PlayerType getXalPlayer() {
        return PlayerType.device7;
    }
    
    public static PlayerType getXalmpPlayer() {
        return PlayerType.device8;
    }
    
    public static void initialize(final EsnProvider esnProvider) {
        PlayerTypeFactory.cryptoFactoryType = esnProvider.getCryptoFactoryType();
    }
    
    public static boolean isDefault(final Context context) {
        return PreferenceUtils.getIntPref(context, "nflx_player_type", -1) == -1;
    }
    
    public static boolean isDrmPlayPlayer(final PlayerType playerType) {
        return PlayerType.device2.equals(playerType) || PlayerType.device3.equals(playerType) || PlayerType.device5.equals(playerType);
    }
    
    public static boolean isJPlayer(final PlayerType playerType) {
        return PlayerType.device10.equals(playerType);
    }
    
    public static boolean isJPlayer2(final PlayerType playerType) {
        return PlayerType.device12.equals(playerType);
    }
    
    public static boolean isJPlayerBase(final PlayerType playerType) {
        return PlayerType.device11.equals(playerType);
    }
    
    public static boolean isNfampPlayer(final PlayerType playerType) {
        return PlayerType.device9.equals(playerType);
    }
    
    public static boolean isOmxPlayer(final PlayerType playerType) {
        return PlayerType.device1.equals(playerType) || PlayerType.device4.equals(playerType);
    }
    
    public static boolean isPlayerTypeSupported(final PlayerType playerType) {
        return isValidPlayerType(playerType);
    }
    
    public static boolean isSoftwarePlayer(final PlayerType playerType) {
        return PlayerType.device6.equals(playerType);
    }
    
    private static boolean isValidPlayerType(final Context context) {
        if (PlayerTypeFactory.currentType == null) {
            PlayerTypeFactory.currentType = findDefaultPlayerType(context);
        }
        return isValidPlayerType(PlayerTypeFactory.currentType);
    }
    
    private static boolean isValidPlayerType(final PlayerType playerType) {
        boolean b = true;
        if (playerType == null) {
            b = false;
        }
        else {
            final int androidVersion = AndroidUtils.getAndroidVersion();
            switch (playerType.getValue()) {
                default: {
                    if (playerType == PlayerType.device6) {
                        Log.d("nf-playertypefactory", "visualon catch all, supported platform " + androidVersion);
                        return true;
                    }
                    Log.d("nf-playertypefactory", "Not supported type " + playerType + " for this device!");
                    return false;
                }
                case 1: {
                    if (androidVersion != 8) {
                        Log.e("nf-playertypefactory", "Incorrect platform");
                        return false;
                    }
                    break;
                }
                case 2: {
                    if (!AndroidUtils.isDrmPlaySupported()) {
                        Log.e("nf-playertypefactory", "drm.play is not present on device!");
                        return false;
                    }
                    if (androidVersion > 10 && androidVersion < 8) {
                        Log.e("nf-playertypefactory", "Incorrect platform " + androidVersion);
                        return false;
                    }
                    if (Log.isLoggable("nf-playertypefactory", 3)) {
                        Log.d("nf-playertypefactory", "drm.play supported, supported platform " + androidVersion);
                        return true;
                    }
                    break;
                }
                case 3: {
                    if (!AndroidUtils.isDrmPlaySupported()) {
                        Log.e("nf-playertypefactory", "drm.play is not present on device!");
                        return false;
                    }
                    if (androidVersion != 11) {
                        Log.e("nf-playertypefactory", "Incorrect platform " + androidVersion);
                        return false;
                    }
                    if (Log.isLoggable("nf-playertypefactory", 3)) {
                        Log.d("nf-playertypefactory", "drm.play supported, supported platform " + androidVersion);
                        return true;
                    }
                    break;
                }
                case 4: {
                    if (androidVersion < 9 && androidVersion > 10) {
                        Log.e("nf-playertypefactory", "Incorrect platform " + androidVersion);
                        return false;
                    }
                    if (Log.isLoggable("nf-playertypefactory", 3)) {
                        Log.d("nf-playertypefactory", "OMX supported, supported platform " + androidVersion);
                        return true;
                    }
                    break;
                }
                case 5: {
                    if (!AndroidUtils.isDrmPlaySupported()) {
                        Log.e("nf-playertypefactory", "drm.play is not present on device!");
                        return false;
                    }
                    if (androidVersion < 12 || androidVersion > 13) {
                        Log.e("nf-playertypefactory", "Incorrect platform " + androidVersion);
                        return false;
                    }
                    if (Log.isLoggable("nf-playertypefactory", 3)) {
                        Log.d("nf-playertypefactory", "drm.play supported, supported platform " + androidVersion);
                        return true;
                    }
                    break;
                }
                case 7: {
                    Log.d("nf-playertypefactory", "XAL player " + androidVersion);
                    if (androidVersion <= 13) {
                        return false;
                    }
                    break;
                }
                case 8: {
                    Log.d("nf-playertypefactory", "XAL Main Profile player " + androidVersion);
                    if (androidVersion <= 13) {
                        return false;
                    }
                    break;
                }
                case 9: {
                    Log.d("nf-playertypefactory", "Netflix Android MediaPlayer " + androidVersion);
                    if (androidVersion <= 8) {
                        return false;
                    }
                    break;
                }
                case 10:
                case 11: {
                    Log.d("nf-playertypefactory", "JPlayer " + androidVersion);
                    if (androidVersion <= 15) {
                        return false;
                    }
                    break;
                }
                case 12: {
                    Log.d("nf-playertypefactory", "JPlayer2 " + androidVersion);
                    if (androidVersion < 16) {
                        return false;
                    }
                    break;
                }
            }
        }
        return b;
    }
    
    private static boolean isWidevineSupported() {
        return PlayerTypeFactory.cryptoFactoryType == 2;
    }
    
    public static boolean isXalPlayer(final PlayerType playerType) {
        return PlayerType.device7.equals(playerType);
    }
    
    public static boolean isXalmpPlayer(final PlayerType playerType) {
        return PlayerType.device8.equals(playerType);
    }
    
    public static PlayerType resetPlayerTypeByQA(final Context context) {
        synchronized (PlayerTypeFactory.class) {
            Log.d("nf-playertypefactory", "Removing player type QA override from preferences");
            PreferenceUtils.removePref(context, "nflx_player_type_qa");
            PlayerTypeFactory.currentType = findDefaultPlayerType(context);
            return getCurrentType(context);
        }
    }
    
    public static void setPlayerType(final Context context, final PlayerType currentType) {
        // monitorenter(PlayerTypeFactory.class)
        Label_0019: {
            if (currentType != null) {
                break Label_0019;
            }
            while (true) {
                try {
                    Log.w("nf-playertypefactory", "Type is null, do nothing!");
                    return;
                    // iftrue(Label_0053:, !Log.isLoggable("nf-playertypefactory", 3))
                    // iftrue(Label_0077:, isValidPlayerType(currentType))
                    while (true) {
                        Block_6: {
                            break Block_6;
                            Log.e("nf-playertypefactory", "Invalid player type for this device. We should never be here!");
                            return;
                        }
                        Log.d("nf-playertypefactory", "Updating player type " + currentType);
                        continue;
                    }
                }
                finally {
                }
                // monitorexit(PlayerTypeFactory.class)
                Label_0077: {
                    if (PlayerTypeFactory.currentType != null && PlayerTypeFactory.currentType.getValue() == currentType.getValue()) {
                        Log.d("nf-playertypefactory", "Already known player type, used for playback currently. Do nothing");
                        return;
                    }
                }
                if (Log.isLoggable("nf-playertypefactory", 3)) {
                    Log.d("nf-playertypefactory", "Saving to persistence new player type " + currentType);
                }
                PlayerTypeFactory.currentType = currentType;
                final Context context2;
                PreferenceUtils.putIntPref(context2, "nflx_player_type", currentType.getValue());
            }
        }
    }
    
    public static void setPlayerTypeForQAOverride(final Context context, final PlayerType currentType) {
        // monitorenter(PlayerTypeFactory.class)
        Label_0020: {
            if (currentType != null) {
                break Label_0020;
            }
            while (true) {
                try {
                    Log.w("nf-playertypefactory", "setPlayerTypeForQAOverride: Type is null, do nothing!");
                    return;
                    // iftrue(Label_0055:, !Log.isLoggable("nf-playertypefactory", 3))
                    // iftrue(Label_0080:, isValidPlayerType(currentType))
                    while (true) {
                        Block_6: {
                            break Block_6;
                            Log.e("nf-playertypefactory", "setPlayerTypeForQAOverride: Invalid player type for this device. We should never be here!");
                            return;
                        }
                        Log.d("nf-playertypefactory", "setPlayerTypeForQAOverride: Updating player type " + currentType);
                        continue;
                    }
                }
                finally {
                }
                // monitorexit(PlayerTypeFactory.class)
                Label_0080: {
                    if (PlayerTypeFactory.currentType != null && PlayerTypeFactory.currentType.getValue() == currentType.getValue()) {
                        Log.d("nf-playertypefactory", "setPlayerTypeForQAOverride: Already known player type, used for playback currently. Do nothing");
                        return;
                    }
                }
                if (Log.isLoggable("nf-playertypefactory", 3)) {
                    Log.d("nf-playertypefactory", "SsetPlayerTypeForQAOverride: aving to persistence new player type " + currentType);
                }
                PlayerTypeFactory.currentType = currentType;
                final Context context2;
                PreferenceUtils.putIntPref(context2, "nflx_player_type_qa", currentType.getValue());
            }
        }
    }
    
    public static void updateDevicePlayerType(final Context context, final String s) {
        // monitorenter(PlayerTypeFactory.class)
        Label_0032: {
            if (s == null) {
                break Label_0032;
            }
            try {
                if (PlayerType.toPlayerType(Integer.parseInt(s)) != null) {
                    setPlayerType(context, PlayerType.toPlayerType(Integer.parseInt(s)));
                }
                else {
                    clearRecords(context);
                }
            }
            finally {
            }
            // monitorexit(PlayerTypeFactory.class)
        }
    }
}
