// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.widget.SeekBar;
import com.netflix.mediaclient.servicemgr.IMdx;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AdapterView$OnItemClickListener;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog$Builder;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import android.app.AlertDialog;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class MdxUtils
{
    private static final int MDX_EOS_DELTA_INSECOND = 10;
    private static final String TAG = "MdxUtils";
    
    public static AlertDialog createMdxDisconnectDialog(final NetflixActivity netflixActivity, final MdxUtils$MdxTargetSelectionDialogInterface mdxUtils$MdxTargetSelectionDialogInterface) {
        if (netflixActivity == null || mdxUtils$MdxTargetSelectionDialogInterface == null || AndroidUtils.isActivityFinishedOrDestroyed(netflixActivity)) {
            Log.w("MdxUtils", "Activity is not valid or MdxFrag is null. Skipping MDX disconnect dialog");
            return null;
        }
        final View inflate = netflixActivity.getLayoutInflater().inflate(2130903168, (ViewGroup)null);
        ((TextView)inflate.findViewById(2131624363)).setText((CharSequence)ServiceManagerUtils.getCurrentDeviceFriendlyName(netflixActivity.getServiceManager()));
        final TextView textView = (TextView)inflate.findViewById(2131624364);
        final TextView textView2 = (TextView)inflate.findViewById(2131624365);
        final Playable playable = mdxUtils$MdxTargetSelectionDialogInterface.getPlayable();
        String string2;
        if (mdxUtils$MdxTargetSelectionDialogInterface.isPlayingRemotely() && playable != null) {
            final String string = netflixActivity.getResources().getString(2131165701, new Object[] { "" });
            textView2.setVisibility(0);
            String text;
            if (playable.isPlayableEpisode()) {
                text = netflixActivity.getResources().getString(2131165695, new Object[] { playable.getParentTitle(), playable.getSeasonAbbrSeqLabel(), playable.getEpisodeNumber(), playable.getPlayableTitle() });
            }
            else {
                text = playable.getPlayableTitle();
            }
            textView2.setText((CharSequence)text);
            string2 = string;
        }
        else {
            string2 = netflixActivity.getResources().getString(2131165694);
            textView2.setVisibility(8);
        }
        textView.setText((CharSequence)string2);
        final AlertDialog create = new AlertDialog$Builder((Context)netflixActivity).setPositiveButton(2131165693, (DialogInterface$OnClickListener)new MdxUtils$2(netflixActivity)).setView(inflate).setCancelable(true).create();
        create.setCanceledOnTouchOutside(true);
        return create;
    }
    
    public static AlertDialog createMdxMenuDialog(final NetflixActivity netflixActivity, final MdxUtils$MdxTargetSelectionDialogInterface mdxUtils$MdxTargetSelectionDialogInterface) {
        if (netflixActivity == null || AndroidUtils.isActivityFinishedOrDestroyed(netflixActivity)) {
            Log.w("MdxUtils", "Activity is not valid. Skipping MDX menu dialog");
            return null;
        }
        if (isTargetReadyToControl(netflixActivity.getServiceManager())) {
            return createMdxDisconnectDialog(netflixActivity, mdxUtils$MdxTargetSelectionDialogInterface);
        }
        return createMdxTargetSelectionDialog(netflixActivity, mdxUtils$MdxTargetSelectionDialogInterface);
    }
    
    public static AlertDialog createMdxTargetSelectionDialog(final NetflixActivity netflixActivity, final MdxUtils$MdxTargetSelectionDialogInterface mdxUtils$MdxTargetSelectionDialogInterface) {
        if (netflixActivity == null || mdxUtils$MdxTargetSelectionDialogInterface == null || AndroidUtils.isActivityFinishedOrDestroyed(netflixActivity)) {
            Log.w("MdxUtils", "Activity is not valid or MdxFrag is null. Skipping MDX target selection dialog");
            return null;
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        final MdxTargetSelection targetSelection = mdxUtils$MdxTargetSelectionDialogInterface.getTargetSelection();
        final int devicePositionByUUID = targetSelection.getDevicePositionByUUID(serviceManager.getMdx().getCurrentTarget());
        targetSelection.setTarget(devicePositionByUUID);
        final MdxTargetSelectionDialog$Builder mdxTargetSelectionDialog$Builder = new MdxTargetSelectionDialog$Builder(netflixActivity);
        mdxTargetSelectionDialog$Builder.setCancelable(true);
        mdxTargetSelectionDialog$Builder.setTitle(2131165556);
        mdxTargetSelectionDialog$Builder.setAdapterData(targetSelection.getTargets((Context)netflixActivity));
        String format = "";
        if (mdxUtils$MdxTargetSelectionDialogInterface.getPlayable() != null) {
            format = format;
            if (StringUtils.isNotEmpty(mdxUtils$MdxTargetSelectionDialogInterface.getPlayable().getPlayableTitle())) {
                format = String.format(netflixActivity.getString(2131165701), mdxUtils$MdxTargetSelectionDialogInterface.getPlayable().getPlayableTitle());
            }
        }
        mdxTargetSelectionDialog$Builder.setSelection(devicePositionByUUID, format);
        mdxTargetSelectionDialog$Builder.setOnItemClickListener((AdapterView$OnItemClickListener)new MdxUtils$1(netflixActivity, serviceManager, targetSelection, mdxUtils$MdxTargetSelectionDialogInterface));
        return mdxTargetSelectionDialog$Builder.create();
    }
    
    public static String getPlayableVideoId(final VideoDetails videoDetails) {
        if (videoDetails instanceof EpisodeDetails) {
            Log.d("MdxUtils", "Episode, use show ID as video ID");
            return ((EpisodeDetails)videoDetails).getShowId();
        }
        Log.d("MdxUtils", "Movie, use movie ID as video ID");
        return videoDetails.getId();
    }
    
    public static boolean isCurrentMdxTargetAvailable(final ServiceManager serviceManager) {
        if (serviceManager == null || !serviceManager.isReady() || serviceManager.getMdx() == null || !serviceManager.getMdx().isReady()) {
            Log.d("MdxUtils", "MDX service is NOT ready");
            return false;
        }
        return isMdxTargetAvailable(serviceManager, serviceManager.getMdx().getCurrentTarget());
    }
    
    public static boolean isMdxTargetAvailable(final ServiceManager serviceManager, final String s) {
        if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
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
            if (Log.isLoggable()) {
                Log.d("MdxUtils", "Right on target, no need to ajust seekbar position " + progress + " [sec]");
            }
            return progress2;
        }
        if (Log.isLoggable()) {
            Log.d("MdxUtils", "Progres : " + progress + " [sec] vs. bif position " + progress2 + " [sec]");
        }
        seekBar.setProgress(progress2);
        return progress2;
    }
}
