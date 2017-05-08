// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnLongClickListener;
import com.netflix.mediaclient.Log;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import java.util.Iterator;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import java.util.ArrayList;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import java.util.List;
import android.widget.LinearLayout;

public class DownloadButton extends LinearLayout
{
    static final String BUTTON_TAG = "download_btn";
    private static final String TAG = "download_button";
    static List<String> preQueued;
    private DownloadButton$ButtonState buttonState;
    private boolean kidsExperience;
    private Playable playable;
    private String playableId;
    private TextView primaryMessageView;
    private ProgressBar progressBar;
    private boolean showPrimaryMessages;
    private boolean showViewMyDownloads;
    
    static {
        DownloadButton.preQueued = new ArrayList<String>();
    }
    
    public DownloadButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.buttonState = DownloadButton$ButtonState.NOT_AVAILABLE;
        this.initAttrs(context, set);
        final LayoutInflater from = LayoutInflater.from(this.getContext());
        int n;
        if (this.showPrimaryMessages) {
            n = 2130903123;
        }
        else {
            n = 2130903122;
        }
        this.findViews(from.inflate(n, (ViewGroup)this));
        this.initViews();
        this.setupLongPressMessages();
    }
    
    private void animateIconChange(final Drawable background) {
        if (this.progressBar == null) {
            return;
        }
        this.progressBar.clearAnimation();
        this.progressBar.animate().alpha(1.0f).setDuration(500L);
        this.progressBar.setBackground(background);
    }
    
    public static void clearPreQueued() {
        DownloadButton.preQueued.clear();
    }
    
    private void findViews(final View view) {
        this.progressBar = (ProgressBar)view.findViewById(2131755351);
        this.primaryMessageView = (TextView)view.findViewById(2131755352);
    }
    
    private Drawable getBrowseExperienceDrawable(final int n) {
        Drawable drawable2;
        final Drawable drawable = drawable2 = ContextCompat.getDrawable(this.getContext(), n);
        if (this.kidsExperience) {
            drawable2 = drawable;
            if (BrowseExperience.showKidsExperience()) {
                if (n != 2130837685) {
                    drawable2 = drawable;
                    if (n != 2130837681) {
                        return drawable2;
                    }
                }
                drawable2 = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTint(drawable2, ContextCompat.getColor(this.getContext(), 2131689600));
            }
        }
        return drawable2;
    }
    
    private void initAttrs(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.DownloadButton);
        this.showPrimaryMessages = obtainStyledAttributes.getBoolean(0, false);
        this.showViewMyDownloads = obtainStyledAttributes.getBoolean(1, true);
        this.kidsExperience = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
    }
    
    private void initViews() {
        this.progressBar.setBackground(this.getBrowseExperienceDrawable(2130837681));
        this.setContentDescription((CharSequence)"download_btn");
    }
    
    public static void removePlayablesFromPreQueued(final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            DownloadButton.preQueued.remove(iterator.next());
        }
    }
    
    public static void removePreQueued(final String s) {
        DownloadButton.preQueued.remove(s);
    }
    
    private void rotateIcon() {
        if (this.progressBar == null) {
            return;
        }
        final RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 90.0f, (float)(this.progressBar.getMeasuredWidth() / 2), (float)(this.progressBar.getMeasuredHeight() / 2));
        rotateAnimation.setDuration(400L);
        rotateAnimation.setInterpolator((Interpolator)new LinearInterpolator());
        rotateAnimation.setAnimationListener((Animation$AnimationListener)new DownloadButton$2(this));
        this.progressBar.startAnimation((Animation)rotateAnimation);
    }
    
    private void setButtonIcon() {
        if (Log.isLoggable()) {
            Log.i("download_button", "setButtonIcon playableId=" + this.getPlayableId() + " state=" + this.getState());
        }
        if (this.progressBar == null) {
            return;
        }
        switch (DownloadButton$3.$SwitchMap$com$netflix$mediaclient$ui$offline$DownloadButton$ButtonState[this.buttonState.ordinal()]) {
            default: {}
            case 1: {
                this.progressBar.setProgress(0);
                this.progressBar.setBackground(this.getBrowseExperienceDrawable(2130837685));
                this.setPrimaryMessage(2131296481);
                this.rotateIcon();
            }
            case 7: {
                this.progressBar.setProgress(0);
                this.animateIconChange(this.getBrowseExperienceDrawable(2130837688));
                this.setPrimaryMessage(2131296480);
            }
            case 3: {
                this.progressBar.clearAnimation();
                this.animateIconChange(this.getBrowseExperienceDrawable(2130837682));
                this.setPrimaryMessage(2131296481);
            }
            case 4: {
                this.animateIconChange(this.getBrowseExperienceDrawable(2130837687));
                this.setPrimaryMessage(2131296479);
            }
            case 2: {
                this.progressBar.setProgress(0);
                this.animateIconChange(this.getBrowseExperienceDrawable(2130837686));
                this.setPrimaryMessage(2131296481);
            }
            case 8: {
                this.progressBar.setProgress(0);
                this.animateIconChange(this.getBrowseExperienceDrawable(2130837683));
                this.setPrimaryMessage(2131296482);
            }
            case 6: {
                this.progressBar.setProgress(0);
                this.progressBar.setBackground((Drawable)null);
            }
            case 5: {
                this.progressBar.setProgress(0);
                this.progressBar.setBackground(this.getBrowseExperienceDrawable(2130837681));
                this.setPrimaryMessage(2131296478);
            }
        }
    }
    
    private void setPrimaryMessage(final int n) {
        if (this.primaryMessageView == null) {
            return;
        }
        this.primaryMessageView.setText((CharSequence)this.getResources().getString(n));
    }
    
    private void setupLongPressMessages() {
        if (this.progressBar == null) {
            return;
        }
        this.setOnLongClickListener((View$OnLongClickListener)new DownloadButton$1(this));
    }
    
    public String getPlayableId() {
        return this.playableId;
    }
    
    public DownloadButton$ButtonState getState() {
        return this.buttonState;
    }
    
    public void refreshDownloadButton(final String s, final NetflixActivity netflixActivity) {
        if (s != null && s.equals(this.playableId)) {
            this.setStateFromPlayable(this.playable, netflixActivity);
        }
    }
    
    public void setProgress(final int progress) {
        if (this.progressBar != null) {
            this.progressBar.setProgress(progress);
        }
    }
    
    void setState(final DownloadButton$ButtonState buttonState, final String playableId) {
        this.buttonState = buttonState;
        this.playableId = playableId;
        if (buttonState != DownloadButton$ButtonState.QUEUED) {
            removePreQueued(playableId);
        }
        this.setButtonIcon();
        this.setTag((Object)("download_btn" + playableId));
    }
    
    public void setStateFromPlayable(final Playable playable, final NetflixActivity netflixActivity) {
        boolean b = true;
        Log.i("download_button", "setStateFromPlayable");
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (this.progressBar == null || playable == null || serviceManager == null || !serviceManager.isReady()) {
            return;
        }
        this.playable = playable;
        this.setOnClickListener((View$OnClickListener)new DownloadButton$DownloadButtonClickListener(this, playable, netflixActivity));
        final OfflinePlayableUiList latestOfflinePlayableList = serviceManager.getOfflineAgent().getLatestOfflinePlayableList();
        OfflinePlayableViewData offlinePlayableViewData = null;
        if (latestOfflinePlayableList != null) {
            offlinePlayableViewData = latestOfflinePlayableList.getOfflinePlayableViewData(playable.getPlayableId());
        }
        if (offlinePlayableViewData == null) {
            b = false;
        }
        Log.i("download_button", "setStateFromPlayable hasOfflinePlayableData=%b", b);
        if (offlinePlayableViewData != null) {
            if (OfflineUiHelper.hasErrorOrWarning(offlinePlayableViewData)) {
                this.setState(DownloadButton$ButtonState.ERROR, playable.getPlayableId());
                return;
            }
            final DownloadState downloadState = offlinePlayableViewData.getDownloadState();
            final int percentageDownloaded = offlinePlayableViewData.getPercentageDownloaded();
            switch (DownloadButton$3.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$DownloadState[downloadState.ordinal()]) {
                default: {
                    if (!DownloadButton.preQueued.contains(playable.getPlayableId())) {
                        DownloadButton$ButtonState downloadButton$ButtonState;
                        if (playable.isAvailableOffline()) {
                            downloadButton$ButtonState = DownloadButton$ButtonState.AVAILABLE;
                        }
                        else {
                            downloadButton$ButtonState = DownloadButton$ButtonState.NOT_AVAILABLE;
                        }
                        this.setState(downloadButton$ButtonState, playable.getPlayableId());
                        return;
                    }
                    this.setState(DownloadButton$ButtonState.QUEUED, playable.getPlayableId());
                }
                case 1: {
                    if (offlinePlayableViewData.getWatchState().hasError()) {
                        this.setState(DownloadButton$ButtonState.ERROR, playable.getPlayableId());
                        return;
                    }
                    this.setState(DownloadButton$ButtonState.SAVED, playable.getPlayableId());
                }
                case 2: {
                    this.setState(DownloadButton$ButtonState.QUEUED, playable.getPlayableId());
                }
                case 3: {
                    this.setState(DownloadButton$ButtonState.DOWNLOADING, playable.getPlayableId());
                    this.setProgress(percentageDownloaded);
                }
                case 4: {
                    if (offlinePlayableViewData.getStopReason().showBangIconErrorInUi()) {
                        this.setState(DownloadButton$ButtonState.ERROR, playable.getPlayableId());
                        return;
                    }
                    if (percentageDownloaded > 0) {
                        this.setState(DownloadButton$ButtonState.PAUSED, playable.getPlayableId());
                        this.setProgress(percentageDownloaded);
                        return;
                    }
                    this.setState(DownloadButton$ButtonState.QUEUED, playable.getPlayableId());
                }
                case 5: {
                    this.setState(DownloadButton$ButtonState.ERROR, playable.getPlayableId());
                }
            }
        }
        else {
            if (!DownloadButton.preQueued.contains(playable.getPlayableId())) {
                DownloadButton$ButtonState downloadButton$ButtonState2;
                if (playable.isAvailableOffline()) {
                    downloadButton$ButtonState2 = DownloadButton$ButtonState.AVAILABLE;
                }
                else {
                    downloadButton$ButtonState2 = DownloadButton$ButtonState.NOT_AVAILABLE;
                }
                this.setState(downloadButton$ButtonState2, playable.getPlayableId());
                return;
            }
            this.setState(DownloadButton$ButtonState.QUEUED, playable.getPlayableId());
        }
    }
}
