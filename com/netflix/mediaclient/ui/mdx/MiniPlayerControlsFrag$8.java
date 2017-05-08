// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
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
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.Fragment;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.content.Context;
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
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import android.widget.SeekBar;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;

class MiniPlayerControlsFrag$8 implements IMdxMiniPlayerViewCallbacks
{
    private long startTrackingTouchTime;
    final /* synthetic */ MiniPlayerControlsFrag this$0;
    
    MiniPlayerControlsFrag$8(final MiniPlayerControlsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public VideoDetails getCurrentVideo() {
        return this.this$0.currentVideo;
    }
    
    @Override
    public ServiceManager getManager() {
        return this.this$0.getServiceManager();
    }
    
    @Override
    public IMdx getMdx() {
        return this.this$0.getServiceManager().getMdx();
    }
    
    @Override
    public boolean isEpisodeReady() {
        return MiniPlayerControlsFrag.state.isEpisodeReady;
    }
    
    @Override
    public boolean isLanguageReady() {
        Language language;
        if (this.this$0.remotePlayer == null) {
            language = null;
        }
        else {
            language = this.this$0.remotePlayer.getLanguage();
        }
        return language != null && language.isLanguageSwitchEnabled();
    }
    
    @Override
    public boolean isPanelExpanded() {
        return this.this$0.activity.isPanelExpanded();
    }
    
    @Override
    public boolean isPlayingRemotely() {
        return this.this$0.isPlayingRemotely();
    }
    
    @Override
    public boolean isRemotePlayerReady() {
        return this.this$0.remotePlayer != null;
    }
    
    @Override
    public void notifyControlsEnabled(final boolean controlsEnabled) {
        MiniPlayerControlsFrag.state.controlsEnabled = controlsEnabled;
    }
    
    @Override
    public void onPauseClicked() {
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.pause();
        }
    }
    
    @Override
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
    }
    
    @Override
    public void onResumeClicked() {
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.resume();
        }
    }
    
    @Override
    public void onShowLanguageSelectorDialog() {
        if (this.this$0.remotePlayer != null) {
            final Language language = this.this$0.remotePlayer.getLanguage();
            this.this$0.log("Displaying language dialog, language: " + language);
            this.this$0.languageSelector.display(language);
        }
    }
    
    @Override
    public void onSkipBackClicked() {
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.skipBackThirtySeconds();
            this.this$0.stopSimulatedVideoPositionUpdate();
        }
    }
    
    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {
        Log.v("MdxMiniPlayerFrag", "onStartTrackingTouch");
        this.this$0.draggingInProgress = true;
        this.startTrackingTouchTime = System.nanoTime();
        this.this$0.stopSimulatedVideoPositionUpdate();
    }
    
    @Override
    public void onStopClicked() {
        if (this.this$0.remotePlayer != null) {
            this.this$0.remotePlayer.stop(false);
        }
    }
    
    @Override
    public void onStopTrackingTouch(final SeekBar progressByBif, final boolean b) {
        Log.v("MdxMiniPlayerFrag", "onStopTrackingTouch, pos: " + progressByBif.getProgress());
        this.this$0.draggingInProgress = false;
        if (b) {
            final int n = (int)((System.nanoTime() - this.startTrackingTouchTime) / 1000000000L);
            final int progress = progressByBif.getProgress() + n;
            if (Log.isLoggable()) {
                Log.v("MdxMiniPlayerFrag", "Seconds elapsed during seek (back to snap position): " + n + ", new time: " + progress);
            }
            this.this$0.views.setProgress(progress);
            this.this$0.startSimulatedVideoPositionUpdate(progress);
            return;
        }
        Log.v("MdxMiniPlayerFrag", "Seeking...");
        this.this$0.views.setControlsEnabled(false);
        this.this$0.remotePlayer.seek(MdxUtils.setProgressByBif(progressByBif));
    }
}
