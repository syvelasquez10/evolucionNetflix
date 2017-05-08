// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.text.Spannable;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.graphics.Typeface;
import android.widget.Toast;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import java.util.Iterator;
import java.util.ArrayList;
import android.view.View;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.android.widgetry.buffet.BuffetBar$Callback;
import android.support.design.widget.CoordinatorLayout;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import android.text.style.ImageSpan;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Html;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import com.netflix.android.widgetry.buffet.BuffetBar;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;

public class ActivityPageOfflineAgentListener implements OfflineAgentListener
{
    public static final String BANG_ICON = "\ud83d\udca5";
    private static final String TAG = "ActivityPageOfflineAgentListener";
    private Drawable bangIconInSnackbar;
    private BuffetBar buffetBar;
    private final ViewGroup content;
    private final View$OnClickListener launchMyDownloads;
    private final boolean showMessages;
    
    public ActivityPageOfflineAgentListener(final ViewGroup content, final boolean showMessages) {
        this.launchMyDownloads = (View$OnClickListener)new ActivityPageOfflineAgentListener$1(this);
        this.showMessages = showMessages;
        this.content = content;
    }
    
    private void dismissBuffetBar() {
        if (this.buffetBar != null) {
            this.buffetBar.dismiss();
            this.buffetBar = null;
        }
    }
    
    private CharSequence getDecoratedText(final String s) {
        final SpannableString spannableString = new SpannableString((CharSequence)Html.fromHtml(s));
        if (spannableString.toString().contains("\ud83d\udca5")) {
            if (this.bangIconInSnackbar == null) {
                DrawableCompat.setTint(this.bangIconInSnackbar = DrawableCompat.wrap(ContextCompat.getDrawable(this.content.getContext(), 2130837744).mutate()), -1);
                final int dimensionPixelSize = this.content.getResources().getDimensionPixelSize(2131427837);
                this.bangIconInSnackbar.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
            final ImageSpan imageSpan = new ImageSpan(this.bangIconInSnackbar, 1);
            final int index = spannableString.toString().indexOf("\ud83d\udca5");
            ((Spannable)spannableString).setSpan((Object)imageSpan, index, "\ud83d\udca5".length() + index, 17);
        }
        return (CharSequence)spannableString;
    }
    
    private void handlePlayRightsRenewDone(final String s, final Status status) {
        if (this.content != null) {
            final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + s));
            if (downloadButton != null) {
                DownloadButton$ButtonState downloadButton$ButtonState;
                if (status.isSucces()) {
                    downloadButton$ButtonState = DownloadButton$ButtonState.SAVED;
                }
                else {
                    downloadButton$ButtonState = DownloadButton$ButtonState.ERROR;
                }
                downloadButton.setState(downloadButton$ButtonState, s);
                downloadButton.setEnabled(true);
            }
        }
    }
    
    private void resetSnackbarSession(final NetflixActivity netflixActivity) {
        OfflineUiHelper.setUserSwiped((Context)netflixActivity, true);
        OfflineUiHelper.resetSnackBarDownloadCompleteCount((Context)netflixActivity);
    }
    
    private BuffetBar showSnackbar(String string, int color) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.content.getContext(), NetflixActivity.class);
        if (netflixActivity != null && !AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity) && !(netflixActivity instanceof PlayerActivity)) {
            if (netflixActivity.isMdxPlayerShowing()) {
                Log.d("ActivityPageOfflineAgentListener", "No buffetBar while MDX mini player display");
                return null;
            }
            final View viewById = netflixActivity.findViewById(2131755332);
            if (viewById instanceof CoordinatorLayout) {
                color = ContextCompat.getColor(this.content.getContext(), color);
                final CharSequence decoratedText = this.getDecoratedText(string);
                if (this.buffetBar == null) {
                    this.buffetBar = BuffetBar.make(viewById, decoratedText, color, -2);
                }
                this.buffetBar.setText(decoratedText).setBackgroundColor(color);
                this.buffetBar.setCallback(new ActivityPageOfflineAgentListener$2(this, netflixActivity));
                this.buffetBar.getView().setOnClickListener(this.launchMyDownloads);
                return this.buffetBar;
            }
            string = "SPY-10658 : No suitable parent found to attach buffetBar, current activity is " + netflixActivity;
            Log.d("ActivityPageOfflineAgentListener", string);
            ErrorLoggingManager.logHandledException(string);
        }
        else {
            Log.d("ActivityPageOfflineAgentListener", "No buffetBar to display as activity is finishing (or in PlayerActivity). Current activity is %s", netflixActivity);
        }
        return null;
    }
    
    private void updateSnackbar() {
        this.updateSnackbar(true);
    }
    
    @Override
    public boolean isListenerDestroyed() {
        return this.content == null || AndroidUtils.isActivityFinishedOrDestroyed((Context)AndroidUtils.getContextAs(this.content.getContext(), NetflixActivity.class));
    }
    
    @Override
    public void onAllPlayablesDeleted(final Status status) {
        if (this.content == null) {
            return;
        }
        OfflineUiHelper.resetSnackBarDownloadCompleteCount(this.content.getContext());
        final ArrayList<View> list = new ArrayList<View>();
        this.content.findViewsWithText((ArrayList)list, (CharSequence)"download_btn", 2);
        DownloadButton.clearPreQueued();
        for (final View view : list) {
            if (view instanceof DownloadButton) {
                ((DownloadButton)view).setState(DownloadButton$ButtonState.AVAILABLE, ((DownloadButton)view).getPlayableId());
            }
        }
        this.updateSnackbar();
    }
    
    @Override
    public void onCreateRequestResponse(final String s, final Status status) {
        if (Log.isLoggable()) {
            Log.i("ActivityPageOfflineAgentListener", "onCreateRequestResponse playableId=" + s + " status=" + status);
        }
        if (this.content != null) {
            final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + s));
            if (downloadButton != null) {
                DownloadButton$ButtonState downloadButton$ButtonState;
                if (status.isSucces()) {
                    downloadButton$ButtonState = DownloadButton$ButtonState.QUEUED;
                }
                else {
                    downloadButton$ButtonState = DownloadButton$ButtonState.ERROR;
                }
                downloadButton.setState(downloadButton$ButtonState, s);
                downloadButton.setEnabled(true);
            }
            OfflineUiHelper.setUserSwiped(this.content.getContext(), false);
            if (!status.isSucces()) {
                this.updateSnackbar();
                return;
            }
            this.updateSnackbar();
            if (status.isWarning() && downloadButton != null) {
                downloadButton.setState(DownloadButton$ButtonState.ERROR, s);
            }
        }
    }
    
    @Override
    public void onDownloadCompleted(final OfflinePlayableViewData offlinePlayableViewData) {
        if (this.content != null) {
            OfflineUiHelper.incrementSnackBarDownloadCompleteCount(this.content.getContext());
            final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + offlinePlayableViewData.getPlayableId()));
            if (downloadButton != null) {
                downloadButton.setState(DownloadButton$ButtonState.SAVED, offlinePlayableViewData.getPlayableId());
            }
            OfflineUiHelper.setUserSwiped(this.content.getContext(), false);
            final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(offlinePlayableViewData.getPlayableId());
            if (offlineVideoDetails != null && !StringUtils.isEmpty(offlineVideoDetails.getTitle())) {
                this.updateSnackbar();
            }
        }
    }
    
    @Override
    public void onDownloadResumedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
        if (this.content == null) {
            return;
        }
        OfflineUiHelper.setUserSwiped(this.content.getContext(), false);
        this.updateSnackbar();
    }
    
    @Override
    public void onDownloadStopped(final OfflinePlayableViewData offlinePlayableViewData, final StopReason stopReason) {
        if (this.content == null) {
            return;
        }
        final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + offlinePlayableViewData.getPlayableId()));
        if (downloadButton != null) {
            if (stopReason.showBangIconErrorInUi()) {
                downloadButton.setState(DownloadButton$ButtonState.ERROR, offlinePlayableViewData.getPlayableId());
            }
            else if (stopReason != StopReason.WaitingToBeStarted) {
                downloadButton.setState(DownloadButton$ButtonState.PAUSED, offlinePlayableViewData.getPlayableId());
            }
        }
        OfflineUiHelper.setUserSwiped(this.content.getContext(), false);
        this.updateSnackbar();
    }
    
    @Override
    public void onError(final Status status) {
        if (this.content == null) {
            return;
        }
        switch (ActivityPageOfflineAgentListener$3.$SwitchMap$com$netflix$mediaclient$StatusCode[status.getStatusCode().ordinal()]) {
            default: {
                OfflineUiHelper.setUserSwiped(this.content.getContext(), false);
                this.updateSnackbar(true);
            }
            case 1:
            case 2: {
                DownloadButtonDialogHelper.createNoStorageDialog(this.content.getContext(), true).show();
            }
            case 3:
            case 4: {
                DownloadButtonDialogHelper.createDownloadFailedDialog(this.content.getContext(), "(" + status.getStatusCode().getValue() + ")").show();
            }
        }
    }
    
    @Override
    public void onLicenseRefreshDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
        this.handlePlayRightsRenewDone(offlinePlayableViewData.getPlayableId(), status);
    }
    
    @Override
    public void onOfflinePlayableDeleted(final String s, final Status status) {
        if (this.content == null) {
            return;
        }
        final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + s));
        if (downloadButton != null) {
            downloadButton.setState(DownloadButton$ButtonState.AVAILABLE, s);
        }
        DownloadButton.removePreQueued(s);
        this.updateSnackbar();
    }
    
    @Override
    public void onOfflinePlayableProgress(final OfflinePlayableViewData offlinePlayableViewData, final int progress) {
        if (this.content == null) {
            return;
        }
        final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + offlinePlayableViewData.getPlayableId()));
        if (downloadButton != null) {
            if (offlinePlayableViewData.getDownloadState() == DownloadState.Complete) {
                downloadButton.setState(DownloadButton$ButtonState.SAVED, offlinePlayableViewData.getPlayableId());
            }
            else {
                downloadButton.setState(DownloadButton$ButtonState.DOWNLOADING, offlinePlayableViewData.getPlayableId());
                downloadButton.setProgress(progress);
            }
        }
        this.updateSnackbar();
    }
    
    @Override
    public void onOfflinePlayablesDeleted(final List<String> list, final Status status) {
        if (this.content == null) {
            return;
        }
        for (final String s : list) {
            final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + s));
            if (downloadButton != null) {
                downloadButton.setState(DownloadButton$ButtonState.AVAILABLE, s);
                DownloadButton.removePreQueued(s);
            }
        }
        this.updateSnackbar();
    }
    
    @Override
    public void onOfflineStorageVolumeAddedOrRemoved(final boolean b) {
        if (this.content != null) {
            final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.content.getContext(), NetflixActivity.class);
            if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
                Toast.makeText((Context)netflixActivity, 2131296908, 1).show();
            }
        }
    }
    
    @Override
    public void onPlayWindowRenewDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
        this.handlePlayRightsRenewDone(offlinePlayableViewData.getPlayableId(), status);
    }
    
    public void refreshDownloadButton(final String s, final NetflixActivity netflixActivity) {
        if (this.content != null) {
            final DownloadButton downloadButton = (DownloadButton)this.content.findViewWithTag((Object)("download_btn" + s));
            if (downloadButton != null) {
                downloadButton.refreshDownloadButton(s, netflixActivity);
            }
        }
    }
    
    public void updateSnackbar(final boolean b) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.content.getContext(), NetflixActivity.class);
        if (netflixActivity != null && netflixActivity.canShowSnackBar() && this.showMessages) {
            final OfflineAgentInterface offlineAgent = netflixActivity.getServiceManager().getOfflineAgent();
            if (offlineAgent != null && (!OfflineUiHelper.isUserSwiped(this.content.getContext()) || this.buffetBar != null)) {
                final ActivityPageOfflineAgentListener$SnackbarMessage snackbarStatus = offlineAgent.getLatestOfflinePlayableList().getSnackbarStatus(this.content.getContext(), offlineAgent, this.content.getLayoutDirection() == 1);
                if (snackbarStatus == null) {
                    Log.i("ActivityPageOfflineAgentListener", "no message, dismiss snack-bar");
                    this.dismissBuffetBar();
                    return;
                }
                final String text = snackbarStatus.text;
                int n;
                if (snackbarStatus.errors > 0) {
                    n = 2131689575;
                }
                else {
                    n = 2131689574;
                }
                this.buffetBar = this.showSnackbar(text, n);
                if (this.buffetBar != null) {
                    this.buffetBar.setActionTypeface(Typeface.createFromAsset(this.content.getContext().getAssets(), "nf-icon.otf"));
                    final BuffetBar buffetBar = this.buffetBar;
                    int n2;
                    if (this.content.getLayoutDirection() == 1) {
                        n2 = 2131297051;
                    }
                    else {
                        n2 = 2131297052;
                    }
                    buffetBar.setAction(n2, this.launchMyDownloads);
                    this.buffetBar.show(b);
                }
            }
        }
    }
}
