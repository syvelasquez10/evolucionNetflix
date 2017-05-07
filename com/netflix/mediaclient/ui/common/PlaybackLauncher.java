// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.ui.pin.PinDialogVault;
import com.netflix.mediaclient.ui.pin.PinDialogVault$PinInvokedFrom;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.util.Pair;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IMdx;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class PlaybackLauncher
{
    private static final String TAG = "nf_play";
    
    private static void displayErrorDialog(final NetflixActivity netflixActivity, final int n) {
        netflixActivity.displayDialog(AlertDialogFactory.createDialog((Context)netflixActivity, null, new AlertDialogFactory$AlertDialogDescriptor("", netflixActivity.getString(n), null, null)));
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
        switch (PlaybackLauncher$2.$SwitchMap$com$netflix$mediaclient$ui$common$PlaybackLauncher$PlaybackTarget[whereToPlay(netflixActivity.getServiceManager()).ordinal()]) {
            default: {}
            case 1: {
                verifyPinAndPlay(netflixActivity, asset, false);
            }
            case 2: {
                verifyPinAndPlay(netflixActivity, asset, true);
            }
            case 3: {
                displayErrorDialog(netflixActivity, 2131493394);
            }
            case 4: {
                displayErrorDialog(netflixActivity, 2131493395);
            }
        }
    }
    
    public static void startPlaybackAfterPIN(final NetflixActivity netflixActivity, final Playable playable, final PlayContext playContext) {
        startPlaybackAfterPIN(netflixActivity, Asset.create(playable, playContext, PlayerActivity.PIN_VERIFIED));
    }
    
    public static void startPlaybackForceLocal(final NetflixActivity netflixActivity, final Asset asset) {
        verifyPinAndPlay(netflixActivity, asset, false);
    }
    
    public static void startPlaybackForceRemote(final NetflixActivity netflixActivity, final Asset asset) {
        verifyPinAndPlay(netflixActivity, asset, true);
    }
    
    public static void startPlaybackOnPINSuccess(final NetflixActivity netflixActivity, final Asset asset, final boolean b) {
        if (b) {
            Log.d("nf_play", "Starting MDX remote playback");
            if (!MdxAgent$Utils.playVideo(netflixActivity, asset, false)) {
                return;
            }
            new Handler(netflixActivity.getMainLooper()).postDelayed((Runnable)new PlaybackLauncher$1((Context)netflixActivity.getApplication()), 250L);
        }
        else {
            if (netflixActivity.getServiceManager().getConfiguration().getPlaybackConfiguration().isLocalPlaybackEnabled()) {
                Log.d("nf_play", "Start local playback");
                PlayerActivity.playVideo(netflixActivity, asset);
                return;
            }
            Log.w("nf_play", "Local playback is disabled, we can not start playback!");
            displayErrorDialog(netflixActivity, 2131493394);
        }
    }
    
    private static void verifyPinAndPlay(final NetflixActivity netflixActivity, final Asset asset, final boolean b) {
        Log.d("nf_play", String.format("nf_pin verifyPinAndPlay - %s protected:%b", asset.getPlayableId(), asset.isPinProtected()));
        PinVerifier.getInstance().verify(netflixActivity, asset.isPinProtected(), new PinDialogVault(PinDialogVault$PinInvokedFrom.PLAY_LAUNCHER.getValue(), asset, b));
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
