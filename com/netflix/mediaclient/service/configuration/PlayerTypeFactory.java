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
    
    public static PlayerType findDefaultPlayerType() {
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (Log.isLoggable()) {
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
        Log.d("nf-playertypefactory", "Default to OpenMax AL (XAL)");
        return PlayerType.device7;
    }
    
    public static PlayerType getCurrentType(final Context context) {
        synchronized (PlayerTypeFactory.class) {
            if (PlayerTypeFactory.currentType == null) {
                Log.d("nf-playertypefactory", "First call getCurrentType(). Initiate it. Check if it is saved to preferences before.");
                PlayerTypeFactory.currentType = getPlayerTypeFromPreferences(context);
            }
            if (PlayerTypeFactory.currentType == null) {
                Log.d("nf-playertypefactory", "Current type was not saved to preferences before. Find default player.");
                PlayerTypeFactory.currentType = findDefaultPlayerType();
            }
            if (!isValidPlayerType(context)) {
                Log.e("nf-playertypefactory", "Current type is not valid! Replace it with default!");
                PreferenceUtils.removePref(context, "nflx_player_type");
                PlayerTypeFactory.currentType = findDefaultPlayerType();
            }
            return PlayerTypeFactory.currentType;
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
    
    private static PlayerType getPlayerTypeFromPreferences(final Context context) {
        Log.d("nf-playertypefactory", "Check for configuration override override");
        final int intPref = PreferenceUtils.getIntPref(context, "nflx_player_type", -1);
        if (intPref < 1) {
            Log.d("nf-playertypefactory", "Player type was not saved in preferences before, no override.");
            return null;
        }
        final PlayerType playerType = PlayerType.toPlayerType(intPref);
        if (playerType == null) {
            Log.e("nf-playertypefactory", "Player not found for type " + intPref);
            return null;
        }
        return playerType;
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
    
    public static boolean isJPlayer(final PlayerType playerType) {
        return PlayerType.device10.equals(playerType);
    }
    
    public static boolean isJPlayer2(final PlayerType playerType) {
        return PlayerType.device12.equals(playerType);
    }
    
    public static boolean isJPlayerBase(final PlayerType playerType) {
        return PlayerType.device11.equals(playerType);
    }
    
    public static boolean isPlayerTypeSupported(final PlayerType playerType) {
        return isValidPlayerType(playerType);
    }
    
    private static boolean isValidPlayerType(final Context context) {
        if (PlayerTypeFactory.currentType == null) {
            PlayerTypeFactory.currentType = findDefaultPlayerType();
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
                    Log.d("nf-playertypefactory", "Not supported type " + playerType + " for this device!");
                    return false;
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
            PlayerTypeFactory.currentType = findDefaultPlayerType();
            return getCurrentType(context);
        }
    }
    
    public static void setPlayerType(final Context context, final PlayerType currentType) {
        // monitorenter(PlayerTypeFactory.class)
        Label_0019: {
            if (currentType != null) {
                break Label_0019;
            }
        Block_6_Outer:
            while (true) {
                try {
                    Log.w("nf-playertypefactory", "Type is null, do nothing!");
                    return;
                    // iftrue(Label_0074:, isValidPlayerType(currentType))
                    while (true) {
                        while (true) {
                            Log.e("nf-playertypefactory", "Invalid player type for this device. We should never be here!");
                            return;
                            Log.d("nf-playertypefactory", "Updating player type " + currentType);
                            Label_0050: {
                                continue Block_6_Outer;
                            }
                        }
                        continue;
                    }
                }
                // iftrue(Label_0050:, !Log.isLoggable())
                finally {
                }
                // monitorexit(PlayerTypeFactory.class)
                Label_0074: {
                    if (PlayerTypeFactory.currentType != null && PlayerTypeFactory.currentType.getValue() == currentType.getValue()) {
                        Log.d("nf-playertypefactory", "Already known player type, used for playback currently. Do nothing");
                        return;
                    }
                }
                if (Log.isLoggable()) {
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
        Label_0019: {
            if (currentType != null) {
                break Label_0019;
            }
            while (true) {
                try {
                    Log.w("nf-playertypefactory", "setPlayerTypeForQAOverride: Type is null, do nothing!");
                    return;
                    while (true) {
                        Log.e("nf-playertypefactory", "setPlayerTypeForQAOverride: Invalid player type for this device. We should never be here!");
                        return;
                        Log.d("nf-playertypefactory", "setPlayerTypeForQAOverride: Updating player type " + currentType);
                        Label_0050: {
                            continue;
                        }
                    }
                }
                // iftrue(Label_0050:, !Log.isLoggable())
                // iftrue(Label_0074:, isValidPlayerType(currentType))
                finally {
                }
                // monitorexit(PlayerTypeFactory.class)
                Label_0074: {
                    if (PlayerTypeFactory.currentType != null && PlayerTypeFactory.currentType.getValue() == currentType.getValue()) {
                        Log.d("nf-playertypefactory", "setPlayerTypeForQAOverride: Already known player type, used for playback currently. Do nothing");
                        return;
                    }
                }
                if (Log.isLoggable()) {
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
