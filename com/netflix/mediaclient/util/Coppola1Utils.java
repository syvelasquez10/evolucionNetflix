// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import com.netflix.mediaclient.Log;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.app.Fragment;
import android.os.Parcelable;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;
import android.app.Activity;

public final class Coppola1Utils
{
    private static final String TAG = "Coppola1Utils";
    
    public static boolean didUserMutePlayback(final Activity activity) {
        return PreferenceUtils.getBooleanPref((Context)activity, "nf_play_user_muted_playback", false);
    }
    
    public static void forceToPortraitIfNeeded(final Activity activity) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed(activity) && PersistentConfig.getCoppola1ABTestCell((Context)activity).ordinal() >= ABTestConfig$Cell.CELL_TEN.ordinal() && (activity.getIntent() == null || !activity.getIntent().hasExtra("push_to_landscape"))) {
            activity.setRequestedOrientation(1);
        }
    }
    
    public static void injectPlayerFragmentIfNeeded(final Activity activity, final String s, final VideoType videoType, final PlayContext playContext, final ServiceManager serviceManager, final Status status) {
        if (shouldInjectPlayerFragment((Context)activity) && !AndroidUtils.isActivityFinishedOrDestroyed(activity)) {
            final PlayerFragment playerFragment = PlayerFragment.createPlayerFragment(s, videoType.getValue(), (Parcelable)playContext, 0);
            final FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
            int n;
            if (videoType == VideoType.MOVIE) {
                n = 2131689743;
            }
            else {
                n = 2131689744;
            }
            beginTransaction.add(n, (Fragment)playerFragment).commitAllowingStateLoss();
            new Handler().postDelayed((Runnable)new Coppola1Utils$1(activity, playerFragment, serviceManager, status), 2000L);
        }
    }
    
    public static boolean isAudioOn(final Context context) {
        return PersistentConfig.getCoppola1ABTestCell(context) != ABTestConfig$Cell.CELL_SEVEN && PersistentConfig.getCoppola1ABTestCell(context) != ABTestConfig$Cell.CELL_THIRTEEN;
    }
    
    public static boolean isAutoplay(final Context context) {
        return PersistentConfig.getCoppola1ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_THREE.ordinal() && PersistentConfig.getCoppola1ABTestCell(context).ordinal() != ABTestConfig$Cell.CELL_ELEVEN.ordinal();
    }
    
    public static boolean isBrowsePlay(final Activity activity) {
        if (Log.isLoggable()) {
            Log.v("Coppola1Utils", "isBrowsePlay() - CoppolaUtils.isCoppolaContext(activity): " + isCoppolaContext((Context)activity) + ", DeviceUtils.isPortrait(activity): " + DeviceUtils.isPortrait((Context)activity) + ", CoppolaUtils.isAutoplay(activity): " + isAutoplay((Context)activity));
        }
        return isCoppolaContext((Context)activity) && DeviceUtils.isPortrait((Context)activity) && isAutoplay((Context)activity);
    }
    
    public static boolean isCoppolaContext(final Context context) {
        return context instanceof CoppolaDetailsActivity;
    }
    
    public static boolean isCoppolaContextForPlayer(final Context context) {
        return isCoppolaContext(context) || shouldInjectPlayerFragment(context);
    }
    
    public static boolean isCoppolaExperience(final Context context) {
        return (PersistentConfig.getCoppola1ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_ONE.ordinal() && PersistentConfig.getCoppola1ABTestCell(context).ordinal() < ABTestConfig$Cell.CELL_NINE.ordinal()) || (PersistentConfig.getCoppola1ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_TEN.ordinal() && PersistentConfig.getCoppola1ABTestCell(context).ordinal() <= ABTestConfig$Cell.CELL_FIFTEEN.ordinal());
    }
    
    public static boolean isLowBitratePlaybackOnMobileNetwork(final Context context) {
        return PersistentConfig.getCoppola1ABTestCell(context) == ABTestConfig$Cell.CELL_SIX;
    }
    
    public static boolean isNewPlayerExperience(final Context context) {
        return (PersistentConfig.getCoppola1ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_TWO.ordinal() && PersistentConfig.getCoppola1ABTestCell(context).ordinal() < ABTestConfig$Cell.CELL_NINE.ordinal()) || (PersistentConfig.getCoppola1ABTestCell(context).ordinal() > ABTestConfig$Cell.CELL_TEN.ordinal() && PersistentConfig.getCoppola1ABTestCell(context).ordinal() < ABTestConfig$Cell.CELL_FIFTEEN.ordinal());
    }
    
    public static void launchCoppolaDetails(final NetflixActivity netflixActivity, final Asset asset, final boolean b, final int n) {
        final Bundle bundle = new Bundle();
        if (b) {
            bundle.putBoolean("push_to_landscape", true);
        }
        if (n > -1) {
            if (n < asset.getDuration()) {
                bundle.putInt("playback_start_seconds", n);
            }
            else {
                Log.w("Coppola1Utils", "Start time parameter was ignored since it exceeds the total duration.");
            }
        }
        else {
            bundle.putInt("playback_start_seconds", asset.getPlaybackBookmark());
        }
        if (asset.isEpisode()) {
            bundle.putString("extra_episode_id", asset.getPlayableId());
        }
        VideoType videoType;
        if (asset.isEpisode()) {
            videoType = VideoType.SHOW;
        }
        else {
            videoType = VideoType.MOVIE;
        }
        DetailsActivityLauncher.show(netflixActivity, videoType, asset.getParentId(), asset.getTitle(), NflxProtocolUtils.getPlayContext(String.valueOf(asset.getTrackId())), "lolomo", bundle);
    }
    
    public static void setUserMutePlayback(final Activity activity, final boolean b) {
        PreferenceUtils.putBooleanPref((Context)activity, "nf_play_user_muted_playback", b);
    }
    
    public static boolean shouldInjectPlayerFragment(final Context context) {
        return context instanceof DetailsActivity && PersistentConfig.getCoppola1ABTestCell(context).ordinal() == ABTestConfig$Cell.CELL_NINE.ordinal();
    }
    
    public static boolean shouldLockActivitiesToPortrait(final Activity activity) {
        final int cellId = PersistentConfig.getCoppola1ABTestCell((Context)activity).getCellId();
        if (Log.isLoggable()) {
            Log.v("Coppola1Utils", "shouldLockActivitiesToPortrait, cell: " + cellId);
        }
        return cellId >= ABTestConfig$Cell.CELL_ELEVEN.getCellId() && cellId <= ABTestConfig$Cell.CELL_FIFTEEN.getCellId();
    }
    
    public static boolean showCountdownTimer(final Context context) {
        return PersistentConfig.getCoppola1ABTestCell(context) == ABTestConfig$Cell.CELL_EIGHT || PersistentConfig.getCoppola1ABTestCell(context) == ABTestConfig$Cell.CELL_FORTEEN;
    }
    
    public static boolean showNewEpisodesFrag(final Context context) {
        return PersistentConfig.getCoppola1ABTestCell(context) == ABTestConfig$Cell.CELL_TWO || PersistentConfig.getCoppola1ABTestCell(context) == ABTestConfig$Cell.CELL_FIVE;
    }
    
    public static void unlockOrientationIfNeeded(final Activity activity) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed(activity) && PersistentConfig.getCoppola1ABTestCell((Context)activity).ordinal() > ABTestConfig$Cell.CELL_TEN.ordinal()) {
            activity.setRequestedOrientation(4);
        }
    }
}
