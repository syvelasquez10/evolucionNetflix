// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.media.Language;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;

class MiniPlayerControlsFrag$7 implements RemotePlayer$RemoteTargetUiListener
{
    final /* synthetic */ MiniPlayerControlsFrag this$0;
    
    MiniPlayerControlsFrag$7(final MiniPlayerControlsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void handleSeekbarUpdate(final RemotePlayer$RemoteTargetState remotePlayer$RemoteTargetState) {
        if (!this.this$0.draggingInProgress && MiniPlayerControlsFrag.state.controlsEnabled) {
            if (this.this$0.remotePlayer.getPositionInSeconds() >= 0) {
                this.this$0.log("Update video seekbar - pos: " + remotePlayer$RemoteTargetState.positionInSeconds);
                this.this$0.views.setProgress(remotePlayer$RemoteTargetState.positionInSeconds);
            }
            if (remotePlayer$RemoteTargetState.buffering || remotePlayer$RemoteTargetState.paused) {
                this.this$0.stopSimulatedVideoPositionUpdate();
                return;
            }
            if (!remotePlayer$RemoteTargetState.paused && this.this$0.isShowing()) {
                this.this$0.startSimulatedVideoPositionUpdate(remotePlayer$RemoteTargetState.positionInSeconds);
            }
        }
    }
    
    private boolean isErrorRequireDisableControl(final int n) {
        return n >= 100 && n < 300;
    }
    
    @Override
    public void cancelDialog() {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.activity)) {
            return;
        }
        if (Log.isLoggable()) {
            this.this$0.log("cancelDialog");
        }
        this.this$0.activity.removeVisibleDialog();
    }
    
    @Override
    public void endOfPlayback() {
        if (Log.isLoggable()) {
            this.this$0.log("endOfPlayback");
        }
        this.this$0.isEndOfPlayback = true;
        this.this$0.views.setControlsEnabled(false);
        if (BrowseExperience.shouldShowMemento((Context)this.this$0.getNetflixActivity())) {
            if (this.this$0.getServiceManager() != null && this.this$0.getServiceManager().getMdx().getSharedState() != null && StringUtils.isEmpty(this.this$0.getServiceManager().getMdx().getSharedState().getPostplayState()) && this.this$0.currentVideo != null && StringUtils.isNotEmpty(this.this$0.currentVideo.getId())) {
                final Intent intent = new Intent("com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_END");
                intent.putExtra("id", this.this$0.currentVideo.getId());
                this.this$0.hideRDP();
                LocalBroadcastManager.getInstance((Context)this.this$0.getActivity()).sendBroadcast(intent);
            }
        }
        else {
            this.this$0.activity.notifyMdxEndOfPlayback();
        }
        MiniPlayerControlsFrag.state.reset();
        this.this$0.currentVideo = null;
    }
    
    @Override
    public void error(final int n, final String s) {
        if (Log.isLoggable()) {
            this.this$0.log("error - code: " + n + ", descrip: " + s);
        }
        this.this$0.isEndOfPlayback = true;
        if (!this.this$0.isInBackground) {
            this.this$0.mdxErrorHandler.handleMdxError(n, s);
        }
        if (this.isErrorRequireDisableControl(n)) {
            this.this$0.views.setControlsEnabled(false);
            this.this$0.views.enableMdxMenu();
            this.this$0.activity.notifyMdxEndOfPlayback();
        }
        this.this$0.views.updateMdxMenu();
    }
    
    @Override
    public void mdxStateChanged(final boolean b) {
        if (Log.isLoggable()) {
            this.this$0.log("mdxStateChanged, ready: " + b);
        }
    }
    
    @Override
    public void showDialog(final RemoteDialog remoteDialog) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.activity)) {
            return;
        }
        if (Log.isLoggable()) {
            this.this$0.log("showDialog, " + remoteDialog.toString());
        }
        final ShowMessageDialogFrag instance = ShowMessageDialogFrag.newInstance(remoteDialog);
        instance.onManagerReady(this.this$0.getServiceManager(), CommonStatus.OK);
        instance.setCancelable(true);
        this.this$0.activity.showDialog(instance);
    }
    
    @Override
    public void targetListChanged() {
        this.this$0.log("targetListChanged");
    }
    
    @Override
    public void updateDuration(final int progressMax) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.activity)) {
            return;
        }
        this.this$0.log("updateDuration, " + progressMax);
        if (progressMax > 0) {
            this.this$0.views.setProgressMax(progressMax);
            return;
        }
        Log.w("MdxMiniPlayerFrag", "We received an invalid duration - ignoring");
    }
    
    @Override
    public void updateLanguage(final Language language) {
        if (Log.isLoggable()) {
            this.this$0.log("updateLanguage from remote player: " + language);
        }
        this.this$0.updateLanguage();
    }
    
    @Override
    public void updateTargetCapabilities(final MdxTargetCapabilities mdxTargetCapabilities) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.activity)) {
            return;
        }
        if (mdxTargetCapabilities == null) {
            Log.w("MdxMiniPlayerFrag", "Capabilities is null!");
            this.this$0.updateVolumeState(false);
            return;
        }
        if (Log.isLoggable()) {
            this.this$0.log("updateTargetCapabilities, " + mdxTargetCapabilities.toString());
        }
        this.this$0.updateVolumeState(mdxTargetCapabilities.isVolumeControl());
    }
    
    @Override
    public void updateUi(final RemotePlayer$RemoteTargetState remotePlayer$RemoteTargetState) {
        final boolean b = false;
        ThreadUtils.assertOnMain();
        if (Log.isLoggable()) {
            this.this$0.log("updateUi, " + remotePlayer$RemoteTargetState.toString());
        }
        this.this$0.isEndOfPlayback = false;
        MiniPlayerControlsFrag.state.mostRecentVolume = remotePlayer$RemoteTargetState.volume;
        this.this$0.updateVisibility(remotePlayer$RemoteTargetState.showMiniPlayer, remotePlayer$RemoteTargetState.paused, PersistentConfig.inMementoTest((Context)this.this$0.getActivity()));
        final MdxMiniPlayerViews access$600 = this.this$0.views;
        boolean controlsEnabled = b;
        if (!remotePlayer$RemoteTargetState.buffering) {
            controlsEnabled = b;
            if (remotePlayer$RemoteTargetState.showMiniPlayer) {
                controlsEnabled = true;
            }
        }
        access$600.setControlsEnabled(controlsEnabled);
        this.handleSeekbarUpdate(remotePlayer$RemoteTargetState);
    }
    
    @Override
    public void updateVideoMetadata() {
        this.this$0.log("updateVideoMetadata");
        if (this.this$0.getServiceManager() == null) {
            return;
        }
        final IMdx mdx = this.this$0.mdxMiniPlayerViewCallbacks.getMdx();
        final VideoDetails videoDetail = mdx.getVideoDetail();
        if (this.this$0.currentVideo != null && MdxUtils.isSameVideoPlaying(mdx, this.this$0.currentVideo.getPlayable().getPlayableId())) {
            this.this$0.log("Same video is already playing, doing nothing");
            return;
        }
        if (videoDetail == null) {
            Log.w("MdxMiniPlayerFrag", "null video details provided by mdx agent");
            return;
        }
        this.this$0.log("Different video, updating to: " + videoDetail.getTitle());
        this.this$0.updateVideoMetadata(videoDetail);
    }
}
