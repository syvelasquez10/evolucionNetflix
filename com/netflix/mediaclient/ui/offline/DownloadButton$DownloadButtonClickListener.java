// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import android.view.View$OnLongClickListener;
import com.netflix.mediaclient.Log;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import android.widget.LinearLayout;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$RequestedBy;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier$PinAndAgeVerifyCallback;
import android.view.View$OnClickListener;

class DownloadButton$DownloadButtonClickListener implements View$OnClickListener
{
    PinAndAgeVerifier$PinAndAgeVerifyCallback mPinAndAgeVerifyCallback;
    private final NetflixActivity netflixActivity;
    private final Playable playable;
    private final String playableId;
    final /* synthetic */ DownloadButton this$0;
    private final VideoType videoType;
    
    DownloadButton$DownloadButtonClickListener(final DownloadButton this$0, final Playable playable, final NetflixActivity netflixActivity) {
        this.this$0 = this$0;
        this.mPinAndAgeVerifyCallback = new DownloadButton$DownloadButtonClickListener$1(this);
        this.netflixActivity = netflixActivity;
        this.playable = playable;
        this.playableId = playable.getPlayableId();
        VideoType videoType;
        if (playable.isPlayableEpisode()) {
            videoType = VideoType.EPISODE;
        }
        else {
            videoType = VideoType.MOVIE;
        }
        this.videoType = videoType;
    }
    
    private void tryStartDownload(final View view) {
        UIViewLogUtils.reportUIViewCommandStarted(view.getContext(), UIViewLogging$UIViewCommandName.AddCachedVideoCommand, IClientLogging$ModalView.addCachedVideoButton, null, null);
        final boolean requiresUnmeteredNetwork = this.netflixActivity.getServiceManager().getOfflineAgent().getRequiresUnmeteredNetwork();
        final boolean wiFiConnected = ConnectivityUtils.isWiFiConnected(this.this$0.getContext());
        if (requiresUnmeteredNetwork && !wiFiConnected && ConnectivityUtils.hasInternet(view.getContext())) {
            DownloadButtonDialogHelper.createNoWifiDialog(this.this$0.getContext(), this.this$0.getPlayableId(), this.videoType, false).show();
        }
        else if (ConnectivityUtils.hasInternet(view.getContext())) {
            if (DownloadButton.preQueued.contains(this.playableId)) {
                return;
            }
            ((DownloadButton)view).setState(DownloadButton$ButtonState.PRE_QUEUED, this.playableId);
            DownloadButton.preQueued.add(this.playableId);
            PlayContext playContext;
            if (this.netflixActivity instanceof PlayContextProvider) {
                playContext = ((PlayContextProvider)this.netflixActivity).getPlayContext();
            }
            else {
                playContext = PlayContext.EMPTY_CONTEXT;
            }
            PinAndAgeVerifier.verifyAgeAndPinForOfflineDownload((NetflixActivity)this.this$0.getContext(), this.playable.isAgeProtected(), new PlayVerifierVault(PlayVerifierVault$RequestedBy.OFFLINE_DOWNLOAD.getValue(), Asset.create(this.playable, playContext, false)), this.mPinAndAgeVerifyCallback);
        }
        else {
            DownloadButtonDialogHelper.createNoInternetDialog(this.this$0.getContext(), this.this$0.getPlayableId(), false).show();
        }
        UIViewLogUtils.reportUIViewCommandEnded(view.getContext());
    }
    
    public void onClick(final View view) {
        if (this.this$0.buttonState != DownloadButton$ButtonState.NOT_AVAILABLE) {
            final ServiceManager serviceManager = this.netflixActivity.getServiceManager();
            OfflineAgentInterface offlineAgent;
            if (serviceManager != null) {
                offlineAgent = serviceManager.getOfflineAgent();
            }
            else {
                offlineAgent = null;
            }
            if (offlineAgent != null) {
                final OfflinePlayableUiList latestOfflinePlayableList = offlineAgent.getLatestOfflinePlayableList();
                final boolean b = latestOfflinePlayableList.getInProgressCount() == 0;
                final OfflinePlayableViewData offlinePlayableViewData = latestOfflinePlayableList.getOfflinePlayableViewData(this.playableId);
                if (offlinePlayableViewData == null) {
                    this.tryStartDownload(view);
                    return;
                }
                switch (DownloadButton$3.$SwitchMap$com$netflix$mediaclient$ui$offline$DownloadButton$ButtonState[((DownloadButton)view).getState().ordinal()]) {
                    case 6: {
                        break;
                    }
                    default: {
                        this.tryStartDownload(view);
                    }
                    case 7: {
                        final Context context = view.getContext();
                        PlayContext playContext = PlayContext.EMPTY_CONTEXT;
                        if (context instanceof PlayContextProvider) {
                            playContext = ((PlayContextProvider)context).getPlayContext();
                        }
                        DownloadButtonDialogHelper.createWatchDeleteDialog(this.this$0.getContext(), this.this$0, this.playableId, this.videoType, this.this$0.showViewMyDownloads, playContext).show();
                    }
                    case 3: {
                        DownloadButtonDialogHelper.createDownloadingMenu(this.this$0.getContext(), this.this$0, this.playableId, this.this$0.showViewMyDownloads).show();
                    }
                    case 1:
                    case 2: {
                        DownloadButtonDialogHelper.createPausedMenu(this.this$0.getContext(), this.this$0, this.playableId, this.this$0.showViewMyDownloads, b).show();
                    }
                    case 4: {
                        DownloadButtonDialogHelper.createPausedMenu(this.this$0.getContext(), this.this$0, this.playableId, this.this$0.showViewMyDownloads, b).show();
                    }
                    case 8: {
                        this.netflixActivity.showDialog(OfflineErrorDialog.createOfflineErrorStateDialog(this.videoType, offlinePlayableViewData, offlineAgent));
                    }
                }
            }
        }
    }
}
