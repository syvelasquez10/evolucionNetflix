// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.Fragment;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.media.Language;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class MdxMiniPlayerFrag$5 implements RemotePlayer$RemoteTargetUiListener
{
    final /* synthetic */ MdxMiniPlayerFrag this$0;
    
    MdxMiniPlayerFrag$5(final MdxMiniPlayerFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void handleSeekbarUpdate(final RemotePlayer$RemoteTargetState remotePlayer$RemoteTargetState) {
        if (!this.this$0.draggingInProgress && MdxMiniPlayerFrag.state.controlsEnabled) {
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
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
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
        this.this$0.activity.notifyMdxEndOfPlayback();
        MdxMiniPlayerFrag.state.reset();
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
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
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
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
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
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
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
        MdxMiniPlayerFrag.state.mostRecentVolume = remotePlayer$RemoteTargetState.volume;
        this.this$0.updateVisibility(remotePlayer$RemoteTargetState.showMiniPlayer, remotePlayer$RemoteTargetState.paused);
        final MdxMiniPlayerViews access$700 = this.this$0.views;
        boolean controlsEnabled = b;
        if (!remotePlayer$RemoteTargetState.buffering) {
            controlsEnabled = b;
            if (remotePlayer$RemoteTargetState.showMiniPlayer) {
                controlsEnabled = true;
            }
        }
        access$700.setControlsEnabled(controlsEnabled);
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
