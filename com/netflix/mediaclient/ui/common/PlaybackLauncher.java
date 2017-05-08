// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.ui.verifyplay.PlayVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$PlayInvokedFrom;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.Asset;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class PlaybackLauncher
{
    private static final String TAG = "nf_play";
    public static final int UNDEFINED_START_TIME = -1;
    
    private static void displayErrorDialog(final NetflixActivity netflixActivity, final int n) {
        netflixActivity.displayDialog(AlertDialogFactory.createDialog((Context)netflixActivity, null, new AlertDialogFactory$AlertDialogDescriptor("", netflixActivity.getString(n), null, null)));
    }
    
    private static Intent getOldPlaybackIntent(final NetflixActivity netflixActivity, final Asset asset, final int n) {
        final Intent intent = new Intent((Context)netflixActivity, (Class)PlayerActivity.class);
        intent.addFlags(131072);
        intent.addFlags(268435456);
        if (n > -1 && n < asset.getDuration()) {
            intent.putExtra("BookmarkSecondsFromStart", n);
        }
        else {
            Log.w("nf_play", "Start time parameter was ignored since it exceeds the total duration.");
        }
        asset.toIntent(intent);
        return intent;
    }
    
    private static boolean isExisitingMdxTargetAvailable(final IMdx mdx, final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_play", "Check if MDX remote target exist in target list: " + s);
        }
        if (!mdx.isReady()) {
            Log.w("nf_play", "MDX service is NOT ready");
            return false;
        }
        final Pair<String, String>[] targetList = mdx.getTargetList();
        if (targetList == null || targetList.length < 1) {
            Log.w("nf_play", "No MDX remote targets found");
            return false;
        }
        for (int i = 0; i < targetList.length; ++i) {
            if (s.equals(targetList[i].first)) {
                Log.d("nf_play", "Target found");
                return true;
            }
        }
        Log.w("nf_play", "Target NOT found!");
        return false;
    }
    
    private static void logMdx(final IMdx mdx) {
        if (Log.isLoggable()) {
            Log.d("nf_play", "MDX is ready " + mdx.isReady());
            if (mdx.getTargetList() != null) {
                Log.d("nf_play", "MDX found targets: " + mdx.getTargetList().length);
            }
            else {
                Log.d("nf_play", "MDX found no targets ");
            }
            Log.d("nf_play", "MDX current target '" + mdx.getCurrentTarget() + "'");
        }
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Asset asset, final boolean b, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_play", "Asset to playback: " + asset);
        }
        if (asset == null) {
            return;
        }
        if (DeviceUtils.isTabletByContext((Context)netflixActivity) || !Coppola1Utils.isNewPlayerExperience((Context)netflixActivity)) {
            netflixActivity.startActivity(getOldPlaybackIntent(netflixActivity, asset, n));
            return;
        }
        Coppola1Utils.launchCoppolaDetails(netflixActivity, asset, b, n);
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext) {
        playVideo(netflixActivity, playable, playContext, -1);
    }
    
    public static void playVideo(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext, final int n) {
        playVideo(netflixActivity, Asset.create(playable, playContext, false), true, n);
    }
    
    public static boolean shouldPlayOnRemoteTarget(final ServiceManager serviceManager) {
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null) {
            Log.e("nf_play", "MDX or service manager are null! That should NOT happen. Default to local.");
            return false;
        }
        final IMdx mdx = serviceManager.getMdx();
        logMdx(mdx);
        final String currentTarget = mdx.getCurrentTarget();
        if (StringUtils.isEmpty(currentTarget)) {
            Log.d("nf_play", "Local target, play on device");
            return false;
        }
        return isExisitingMdxTargetAvailable(mdx, currentTarget);
    }
    
    public static void startPlaybackAfterPIN(final NetflixActivity netflixActivity, final Asset asset) {
        startPlaybackAfterPIN(netflixActivity, asset, -1);
    }
    
    public static void startPlaybackAfterPIN(final NetflixActivity netflixActivity, final Asset asset, final int n) {
        switch (PlaybackLauncher$2.$SwitchMap$com$netflix$mediaclient$ui$common$PlaybackLauncher$PlaybackTarget[whereToPlay(netflixActivity.getServiceManager()).ordinal()]) {
            default: {}
            case 1: {
                verifyAgeAndPinToPlay(netflixActivity, asset, false, n);
            }
            case 2: {
                verifyAgeAndPinToPlay(netflixActivity, asset, true, n);
            }
            case 3: {
                displayErrorDialog(netflixActivity, 2131231224);
            }
            case 4: {
                displayErrorDialog(netflixActivity, 2131231225);
            }
        }
    }
    
    public static void startPlaybackAfterPIN(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext) {
        startPlaybackAfterPIN(netflixActivity, Asset.create(playable, playContext, true), -1);
    }
    
    public static void startPlaybackAfterPIN(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext, final int n) {
        startPlaybackAfterPIN(netflixActivity, Asset.create(playable, playContext, true), n);
    }
    
    public static void startPlaybackForceLocal(final NetflixActivity netflixActivity, final Asset asset, final int n) {
        verifyAgeAndPinToPlay(netflixActivity, asset, false, n);
    }
    
    public static void startPlaybackForceRemote(final NetflixActivity netflixActivity, final Asset asset) {
        verifyAgeAndPinToPlay(netflixActivity, asset, true, -1);
    }
    
    public static void startPlaybackOnPINSuccess(final NetflixActivity netflixActivity, final Asset asset, final boolean b, final int n) {
        if (b) {
            Log.d("nf_play", "Starting MDX remote playback");
            if (!MdxAgent$Utils.playVideo(netflixActivity, asset, n, false)) {
                return;
            }
            new Handler(netflixActivity.getMainLooper()).postDelayed((Runnable)new PlaybackLauncher$1((Context)netflixActivity.getApplication()), 250L);
        }
        else {
            if (netflixActivity.getServiceManager().getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled()) {
                Log.d("nf_play", "Start local playback");
                playVideo(netflixActivity, asset, true, n);
                return;
            }
            Log.w("nf_play", "Local playback is disabled, we can not start playback!");
            displayErrorDialog(netflixActivity, 2131231224);
        }
    }
    
    private static void verifyAgeAndPinToPlay(final NetflixActivity netflixActivity, final Asset asset, final boolean b, final int n) {
        Log.d("nf_play", String.format("nf_pin verifyPinAndPlay - %s ageProtected: %b, pinProtected:%b", asset.getPlayableId(), asset.isAgeProtected(), asset.isPinProtected()));
        PlayVerifier.verify(netflixActivity, asset.isAgeProtected(), asset.isPinProtected(), new PlayVerifierVault(PlayVerifierVault$PlayInvokedFrom.PLAY_LAUNCHER.getValue(), asset, b, n));
    }
    
    public static PlaybackLauncher$PlaybackTarget whereToPlay(final ServiceManager serviceManager) {
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null) {
            Log.e("nf_play", "MDX or service manager are null! That should NOT happen. Default to local.");
            if (serviceManager == null || serviceManager.getConfiguration() == null) {
                Log.w("nf_play", "Service manager not available or ready! Guess that local playback is enabled! We should never end here!");
                return PlaybackLauncher$PlaybackTarget.local;
            }
            if (serviceManager.getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled()) {
                Log.w("nf_play", "MDX manager null, but configuration exist and local playback is enabled, go local.");
                return PlaybackLauncher$PlaybackTarget.local;
            }
            Log.w("nf_play", "MDX manager null, but configuration exist and local playback is disabled, display an error.");
            return PlaybackLauncher$PlaybackTarget.localButDisabled;
        }
        else {
            final boolean localPlaybackEnabled = serviceManager.getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled();
            final IMdx mdx = serviceManager.getMdx();
            logMdx(mdx);
            final String currentTarget = mdx.getCurrentTarget();
            if (StringUtils.isEmpty(currentTarget)) {
                if (localPlaybackEnabled) {
                    Log.d("nf_play", "Local target, play on device");
                    return PlaybackLauncher$PlaybackTarget.local;
                }
                Log.d("nf_play", "Local target, but local playback disabled. Try to find at least one remote target");
                final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
                if (targetList == null || targetList.length < 1) {
                    Log.d("nf_play", "Local target, local playback disabled and no remote targets. Display an error.");
                    return PlaybackLauncher$PlaybackTarget.localButDisabled;
                }
                if (Log.isLoggable()) {
                    Log.d("nf_play", "Try to set first remote target as current target and launch playback. To " + (String)targetList[0].second);
                }
                serviceManager.getMdx().setCurrentTarget((String)targetList[0].first);
                return PlaybackLauncher$PlaybackTarget.remote;
            }
            else {
                if (isExisitingMdxTargetAvailable(mdx, currentTarget)) {
                    return PlaybackLauncher$PlaybackTarget.remote;
                }
                if (localPlaybackEnabled) {
                    Log.d("nf_play", "Remote target not available and local playback enabled, play on device");
                    return PlaybackLauncher$PlaybackTarget.local;
                }
                Log.d("nf_play", "Remote target not available and local playback disabled, report an error!");
                return PlaybackLauncher$PlaybackTarget.remoteButNotAvailable;
            }
        }
    }
}
