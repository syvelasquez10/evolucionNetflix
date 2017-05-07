// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.widget.SeekBar;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.IMdx;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.ui.mdx.RemotePlayer;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import android.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxUtils
{
    private static final int MDX_EOS_DELTA_INSECOND = 10;
    private static final String TAG = "MdxUtils";
    
    public static AlertDialog createMdxTargetSelectionDialog(final NetflixActivity netflixActivity, final MdxTargetSelectionDialogInterface mdxTargetSelectionDialogInterface) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final MdxTargetSelection targetSelection = mdxTargetSelectionDialogInterface.getTargetSelection();
        final int devicePositionByUUID = targetSelection.getDevicePositionByUUID(serviceManager.getMdx().getCurrentTarget());
        targetSelection.setTarget(devicePositionByUUID);
        final MdxTargetSelectionDialog.Builder builder = new MdxTargetSelectionDialog.Builder(netflixActivity);
        builder.setCancelable(true);
        builder.setTitle(2131493156);
        builder.setAdapterData(targetSelection.getTargets((Context)netflixActivity));
        String format = "";
        if (mdxTargetSelectionDialogInterface.getVideoDetails() != null) {
            format = format;
            if (StringUtils.isNotEmpty(mdxTargetSelectionDialogInterface.getVideoDetails().getPlayableTitle())) {
                format = String.format(netflixActivity.getString(2131493252), mdxTargetSelectionDialogInterface.getVideoDetails().getPlayableTitle());
            }
        }
        builder.setSelection(devicePositionByUUID, format);
        builder.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, int positionInSeconds, final long n) {
                Log.d("MdxUtils", "Mdx target clicked: item with id " + n + ", on position " + positionInSeconds);
                netflixActivity.removeVisibleDialog();
                if (serviceManager == null || !serviceManager.isReady()) {
                    Log.w("MdxUtils", "Service not ready - bailing early");
                    return;
                }
                targetSelection.setTarget(positionInSeconds);
                final MdxTarget selectedTarget = targetSelection.getSelectedTarget();
                if (selectedTarget == null) {
                    Log.e("MdxUtils", "Target is NULL, this should NOT happen!");
                }
                else if (selectedTarget.getUUID() != null && selectedTarget.getUUID().equals(serviceManager.getMdx().getCurrentTarget())) {
                    if (Log.isLoggable("MdxUtils", 3)) {
                        Log.d("MdxUtils", "Same MDX target selected. Do nothing and dismiss dialog");
                    }
                }
                else if (selectedTarget.isLocal()) {
                    if (mdxTargetSelectionDialogInterface.isPlayingRemotely()) {
                        Log.d("MdxUtils", "We were playing remotely - switching to playback locally");
                        serviceManager.getMdx().switchPlaybackFromTarget(null, 0);
                        final Asset create = Asset.create(mdxTargetSelectionDialogInterface.getVideoDetails(), mdxTargetSelectionDialogInterface.getPlayContext(), PlayerActivity.PIN_VERIFIED);
                        create.setPlaybackBookmark((int)(mdxTargetSelectionDialogInterface.getCurrentPositionMs() / 1000L));
                        PlaybackLauncher.startPlaybackForceLocal(netflixActivity, create);
                        mdxTargetSelectionDialogInterface.notifyPlayingBackLocal();
                    }
                    else {
                        Log.d("MdxUtils", "Target is local. Remove current target from MDX agent.");
                        serviceManager.getMdx().setCurrentTarget(null);
                    }
                }
                else if (MdxUtils.isMdxTargetAvailable(serviceManager, selectedTarget.getUUID())) {
                    if (mdxTargetSelectionDialogInterface.isPlayingLocally() || mdxTargetSelectionDialogInterface.isPlayingRemotely()) {
                        if (Log.isLoggable("MdxUtils", 3)) {
                            Log.d("MdxUtils", "Remote target is available, switching playback to: " + selectedTarget.getUUID());
                        }
                        final RemotePlayer player = mdxTargetSelectionDialogInterface.getPlayer();
                        positionInSeconds = 0;
                        if (player != null) {
                            final int n2 = positionInSeconds = player.getPositionInSeconds();
                            if (Log.isLoggable("MdxUtils", 3)) {
                                Log.d("MdxUtils", "Start remote playback from position [sec] " + n2);
                                positionInSeconds = n2;
                            }
                        }
                        else {
                            Log.e("MdxUtils", "Remote player is null. This should not happen!");
                        }
                        serviceManager.getMdx().switchPlaybackFromTarget(selectedTarget.getUUID(), positionInSeconds);
                        mdxTargetSelectionDialogInterface.notifyPlayingBackRemote();
                    }
                    else {
                        if (Log.isLoggable("MdxUtils", 3)) {
                            Log.d("MdxUtils", "Target is remote. Setting new current target to: " + selectedTarget.getUUID());
                        }
                        serviceManager.getMdx().setCurrentTarget(selectedTarget.getUUID());
                    }
                }
                else {
                    Log.w("MdxUtils", "Remote target is NOT available, stay and dismiss dialog");
                }
                netflixActivity.invalidateOptionsMenu();
            }
        });
        return builder.create();
    }
    
    public static RatingDialogFrag.Rating getRating(final VideoDetails videoDetails, float value) {
        final float n = 0.0f;
        final float n2 = 0.0f;
        final RatingDialogFrag.Rating rating = new RatingDialogFrag.Rating();
        if (videoDetails.getUserRating() <= 0.0f && value <= 0.0f) {
            Log.d("MdxUtils", "User did not changed ratings before, use predicted rating");
            if (videoDetails.getPredictedRating() < 0.0f) {
                value = n2;
            }
            else {
                value = videoDetails.getPredictedRating();
            }
            rating.value = value;
            rating.user = false;
            return rating;
        }
        if (value > 0.0f && videoDetails.getUserRating() != value) {
            Log.d("MdxUtils", "User changed ratings, but video object is not updated on callback from web api, use user set rating");
            rating.value = value;
            rating.user = true;
            return rating;
        }
        Log.d("MdxUtils", "User changed rating before, use user rating");
        if (videoDetails.getUserRating() < 0.0f) {
            value = n;
        }
        else {
            value = videoDetails.getUserRating();
        }
        rating.value = value;
        rating.user = true;
        return rating;
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
    
    public interface MdxTargetSelectionDialogInterface
    {
        long getCurrentPositionMs();
        
        PlayContext getPlayContext();
        
        RemotePlayer getPlayer();
        
        MdxTargetSelection getTargetSelection();
        
        Playable getVideoDetails();
        
        boolean isPlayingLocally();
        
        boolean isPlayingRemotely();
        
        void notifyPlayingBackLocal();
        
        void notifyPlayingBackRemote();
    }
    
    public static class SetVideoRatingCallback extends LoggingManagerCallback
    {
        private final NetflixActivity activity;
        private final float rating;
        
        public SetVideoRatingCallback(final NetflixActivity activity, final float rating) {
            super("MdxUtils");
            this.activity = activity;
            this.rating = rating;
        }
        
        @Override
        public void onVideoRatingSet(final Status status) {
            super.onVideoRatingSet(status);
            if (this.activity.destroyed()) {
                return;
            }
            if (status.isError()) {
                Log.w("MdxUtils", "Invalid status code failed");
                Toast.makeText((Context)this.activity, 2131493206, 1).show();
                Log.d("MdxUtils", "Report rate action ended");
                UserActionLogUtils.reportRateActionEnded((Context)this.activity, IClientLogging.CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, this.activity.getString(2131493206), ActionOnUIError.displayedError), null, (int)this.rating);
                return;
            }
            Log.v("MdxUtils", "Rating has been updated ok");
            Toast.makeText((Context)this.activity, 2131493207, 1).show();
            UserActionLogUtils.reportRateActionEnded((Context)this.activity, IClientLogging.CompletionReason.success, null, null, (int)this.rating);
        }
    }
}
