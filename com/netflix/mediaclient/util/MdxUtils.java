// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.widget.SeekBar;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.IMdx;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.RatingDialogFrag$Rating;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import android.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxUtils
{
    private static final int MDX_EOS_DELTA_INSECOND = 10;
    private static final String TAG = "MdxUtils";
    
    public static AlertDialog createMdxTargetSelectionDialog(final NetflixActivity netflixActivity, final MdxUtils$MdxTargetSelectionDialogInterface mdxUtils$MdxTargetSelectionDialogInterface) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final MdxTargetSelection targetSelection = mdxUtils$MdxTargetSelectionDialogInterface.getTargetSelection();
        final int devicePositionByUUID = targetSelection.getDevicePositionByUUID(serviceManager.getMdx().getCurrentTarget());
        targetSelection.setTarget(devicePositionByUUID);
        final MdxTargetSelectionDialog$Builder mdxTargetSelectionDialog$Builder = new MdxTargetSelectionDialog$Builder(netflixActivity);
        mdxTargetSelectionDialog$Builder.setCancelable(true);
        mdxTargetSelectionDialog$Builder.setTitle(2131493129);
        mdxTargetSelectionDialog$Builder.setAdapterData(targetSelection.getTargets((Context)netflixActivity));
        String format = "";
        if (mdxUtils$MdxTargetSelectionDialogInterface.getVideoDetails() != null) {
            format = format;
            if (StringUtils.isNotEmpty(mdxUtils$MdxTargetSelectionDialogInterface.getVideoDetails().getPlayableTitle())) {
                format = String.format(netflixActivity.getString(2131493213), mdxUtils$MdxTargetSelectionDialogInterface.getVideoDetails().getPlayableTitle());
            }
        }
        mdxTargetSelectionDialog$Builder.setSelection(devicePositionByUUID, format);
        mdxTargetSelectionDialog$Builder.setOnItemClickListener((AdapterView$OnItemClickListener)new MdxUtils$1(netflixActivity, serviceManager, targetSelection, mdxUtils$MdxTargetSelectionDialogInterface));
        return mdxTargetSelectionDialog$Builder.create();
    }
    
    public static RatingDialogFrag$Rating getRating(final VideoDetails videoDetails, float value) {
        final float n = 0.0f;
        final float n2 = 0.0f;
        final RatingDialogFrag$Rating ratingDialogFrag$Rating = new RatingDialogFrag$Rating();
        if (videoDetails.getUserRating() <= 0.0f && value <= 0.0f) {
            Log.d("MdxUtils", "User did not changed ratings before, use predicted rating");
            if (videoDetails.getPredictedRating() < 0.0f) {
                value = n2;
            }
            else {
                value = videoDetails.getPredictedRating();
            }
            ratingDialogFrag$Rating.value = value;
            ratingDialogFrag$Rating.user = false;
            return ratingDialogFrag$Rating;
        }
        if (value > 0.0f && videoDetails.getUserRating() != value) {
            Log.d("MdxUtils", "User changed ratings, but video object is not updated on callback from web api, use user set rating");
            ratingDialogFrag$Rating.value = value;
            ratingDialogFrag$Rating.user = true;
            return ratingDialogFrag$Rating;
        }
        Log.d("MdxUtils", "User changed rating before, use user rating");
        if (videoDetails.getUserRating() < 0.0f) {
            value = n;
        }
        else {
            value = videoDetails.getUserRating();
        }
        ratingDialogFrag$Rating.value = value;
        ratingDialogFrag$Rating.user = true;
        return ratingDialogFrag$Rating;
    }
    
    public static String getVideoId(final VideoDetails videoDetails) {
        if (videoDetails instanceof EpisodeDetails) {
            Log.d("MdxUtils", "Episode, use show ID as video ID");
            return ((EpisodeDetails)videoDetails).getShowId();
        }
        Log.d("MdxUtils", "Movie, use movie ID as video ID");
        return videoDetails.getPlayable().getPlayableId();
    }
    
    public static boolean isCurrentMdxTargetAvailable(final ServiceManager serviceManager) {
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null || !serviceManager.getMdx().isReady()) {
            Log.d("MdxUtils", "MDX service is NOT ready");
            return false;
        }
        return isMdxTargetAvailable(serviceManager, serviceManager.getMdx().getCurrentTarget());
    }
    
    public static boolean isMdxTargetAvailable(final ServiceManager serviceManager, final String s) {
        if (Log.isLoggable("MdxUtils", 3)) {
            Log.d("MdxUtils", "Check if MDX remote target exist in target list: " + s);
        }
        if (StringUtils.isEmpty(s)) {
            Log.d("MdxUtils", "uuid is empty");
            return false;
        }
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null || !serviceManager.getMdx().isReady()) {
            Log.d("MdxUtils", "MDX service is NOT ready");
            return false;
        }
        final Pair<String, String>[] targetList = serviceManager.getMdx().getTargetList();
        if (targetList == null || targetList.length < 1) {
            Log.w("MdxUtils", "No MDX remote targets found");
            return false;
        }
        for (int i = 0; i < targetList.length; ++i) {
            if (s.equals(targetList[i].first)) {
                Log.d("MdxUtils", "Target found");
                return true;
            }
        }
        Log.w("MdxUtils", "Target NOT found!");
        return false;
    }
    
    public static boolean isMediaSessionAvailable() {
        return AndroidUtils.getAndroidVersion() >= 21;
    }
    
    public static boolean isSameVideoPlaying(final IMdx mdx, final String s) {
        if (mdx == null) {
            Log.w("MdxUtils", "MDX agent is null - isSameVideoPlaying returning false");
            return false;
        }
        if (mdx.getVideoDetail() == null) {
            Log.w("MdxUtils", "Video detail is null - isSameVideoPlaying returning false");
            return false;
        }
        final Playable playable = mdx.getVideoDetail().getPlayable();
        if (Log.isLoggable("MdxUtils", 3)) {
            if (StringUtils.isNotEmpty(s)) {
                Log.d("MdxUtils", "mCurrentPlayout.getPlayableId(): " + s);
            }
            else {
                Log.d("MdxUtils", "mCurrentPlayout is empty");
            }
            if (playable != null) {
                Log.d("MdxUtils", "currentVideo.getPlayableId(): " + playable.getPlayableId());
            }
            else {
                Log.d("MdxUtils", "currentVideo is null ");
            }
        }
        if (playable != null && playable.getPlayableId() != null && playable.getPlayableId().equals(s)) {
            Log.d("MdxUtils", "Same video is playing, just sync...");
            return true;
        }
        Log.d("MdxUtils", "Video is not currently playing or different video, start play if play is not already pending...");
        return false;
    }
    
    public static boolean isTargetReadyToControl(final ServiceManager serviceManager) {
        Log.d("MdxUtils", "isTargetReadyToControl");
        if (isCurrentMdxTargetAvailable(serviceManager)) {
            Log.d("MdxUtils", "isTargetReadyToControl check is launched");
            return serviceManager.getMdx().isTargetLaunchingOrLaunched();
        }
        return false;
    }
    
    public static void registerReceiver(final Activity activity, final BroadcastReceiver broadcastReceiver) {
        Log.d("MdxUtils", "Register receiver");
        IntentUtils.registerSafelyLocalBroadcastReceiver((Context)activity, broadcastReceiver, "LocalIntentNflxUi", "ui_rating", "nflx_button_selected", "nflx_button_canceled");
    }
    
    public static int setProgressByBif(final SeekBar seekBar) {
        final int progress = seekBar.getProgress();
        final int n = progress / 10 * 10;
        final int max = seekBar.getMax();
        int progress2 = n;
        if (n + 10 >= max) {
            progress2 = n;
            if (max > 0) {
                Log.d("MdxUtils", "seek to close to EOS, defaulting to 10 seconss before EOS.");
                progress2 = max - 10;
            }
        }
        if (progress2 == progress) {
            if (Log.isLoggable("MdxUtils", 3)) {
                Log.d("MdxUtils", "Right on target, no need to ajust seekbar position " + progress + " [sec]");
            }
            return progress2;
        }
        if (Log.isLoggable("MdxUtils", 3)) {
            Log.d("MdxUtils", "Progres : " + progress + " [sec] vs. bif position " + progress2 + " [sec]");
        }
        seekBar.setProgress(progress2);
        return progress2;
    }
    
    public static void unregisterReceiver(final Activity activity, final BroadcastReceiver broadcastReceiver) {
        IntentUtils.unregisterSafelyLocalBroadcastReceiver((Context)activity, broadcastReceiver);
    }
}
