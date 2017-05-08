// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.model.leafs.PostPlayAction$CallToActionType;
import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayAction;

public class PostPlayCallToAction
{
    private String TAG;
    private PostPlayAction action;
    private PostPlayRequestContext context;
    private NetflixActivity netflixActivity;
    private PlayerFragment playerFragment;
    private View target;
    
    public PostPlayCallToAction(final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayAction postPlayAction, final PostPlayRequestContext postPlayRequestContext) {
        this(netflixActivity, playerFragment, postPlayAction, postPlayRequestContext, null);
    }
    
    public PostPlayCallToAction(final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayAction action, final PostPlayRequestContext context, final View target) {
        this.TAG = "PostPlayCallToAction";
        this.netflixActivity = netflixActivity;
        this.playerFragment = playerFragment;
        this.context = context;
        this.action = action;
        this.target = target;
        if (target != null) {
            if (target instanceof Button) {
                this.setButtonText((Button)target);
            }
            this.attachAction(target);
        }
    }
    
    private void attachAction(final View view) {
        switch (PostPlayCallToAction$4.$SwitchMap$com$netflix$model$leafs$PostPlayAction$CallToActionType[this.action.getType().ordinal()]) {
            case 1: {
                if (this.action.getPlayBackVideo() != null) {
                    this.attachPlayHandler(view);
                    return;
                }
                break;
            }
            case 2: {
                this.attachDisplayPageHandler(view);
            }
            case 3: {
                if (view instanceof Button) {
                    this.attachPlaylistHandler((Button)view);
                    return;
                }
                break;
            }
        }
    }
    
    private void attachDisplayPageHandler(final View view) {
        view.setOnClickListener((View$OnClickListener)new PostPlayCallToAction$3(this));
    }
    
    private void attachPlayHandler(final View view) {
        view.setOnClickListener(this.generatePlayHandler());
    }
    
    private void attachPlaylistHandler(final Button button) {
        final ServiceManager serviceManager = this.netflixActivity.getServiceManager();
        final String value = String.valueOf(this.action.getVideoId());
        if (button != null && serviceManager != null) {
            serviceManager.registerAddToMyListListener(value, serviceManager.createAddToMyListWrapper(this.netflixActivity, (TextView)button, value, this.action.getVideoType(), this.action.getTrackId(), false));
            serviceManager.updateMyListState(value, this.action.isInMyList());
        }
    }
    
    private void finishActivityIfNeeded() {
        if (!this.netflixActivity.isFinishing()) {
            this.netflixActivity.finish();
        }
    }
    
    private PlayContext getPlayContext() {
        return new PlayContextImp(this.action, this.action.getBookmarkPosition());
    }
    
    private String getString(final int n) {
        return this.netflixActivity.getResources().getString(n);
    }
    
    private void reportNextPlay() {
        Log.d(this.TAG, "User starts next play, report as such");
        UserActionLogUtils.reportEndPostPlay((Context)this.netflixActivity, IClientLogging$CompletionReason.success, IClientLogging$ModalView.playback, null, false, true, this.action.getVideoId(), this.action.getItemIndex(), this.action.getTrackId());
    }
    
    private void setButtonText(final Button button) {
        button.setText((CharSequence)this.getActionText());
    }
    
    private void stopAllNotifications() {
        final ServiceManager serviceManager = this.netflixActivity.getServiceManager();
        if (serviceManager != null) {
            ((MdxAgent)serviceManager.getMdx()).stopAllNotifications();
        }
    }
    
    public void displayPageAction() {
        if (this.context.equals(PostPlayRequestContext.MDX)) {
            this.mdxDisplayPageAction();
            return;
        }
        this.playerDisplayPageAction();
    }
    
    public View$OnClickListener generateDisplayPageHandler() {
        return (View$OnClickListener)new PostPlayCallToAction$2(this);
    }
    
    public View$OnClickListener generatePlayHandler() {
        return (View$OnClickListener)new PostPlayCallToAction$1(this);
    }
    
    public String getActionText() {
        if (this.action.getDisplayText() != null) {
            return this.action.getDisplayText();
        }
        switch (PostPlayCallToAction$4.$SwitchMap$com$netflix$model$leafs$PostPlayAction$CallToActionType[this.action.getType().ordinal()]) {
            default: {
                return "";
            }
            case 1: {
                if (this.action.getName().equals("playTrailer")) {
                    return this.getString(2131231181);
                }
                return this.getString(2131230914);
            }
            case 2: {
                if (this.action.getVideoType() != null && this.action.getVideoType().equals(VideoType.EPISODE)) {
                    return this.getString(2131230912);
                }
                return this.getString(2131231133);
            }
            case 3: {
                int n;
                if (this.action.isInMyList()) {
                    n = 2131230977;
                }
                else {
                    n = 2131231217;
                }
                return this.getString(n);
            }
        }
    }
    
    protected void mdxDisplayPageAction() {
        this.finishActivityIfNeeded();
        this.playerDisplayPageAction();
    }
    
    protected void mdxPlayAction(final boolean b) {
        if (this.action.getPlayBackVideo() != null) {
            final Asset create = Asset.create(this.action.getPlayBackVideo().getPlayable(), PlayContext.DFLT_MDX_CONTEXT, true);
            this.stopAllNotifications();
            MdxAgent$Utils.playVideo(this.netflixActivity, create, true);
        }
    }
    
    public void playAction(final boolean b) {
        if (this.context.equals(PostPlayRequestContext.MDX)) {
            this.mdxPlayAction(b);
            return;
        }
        this.playerPlayAction(b);
    }
    
    protected void playerDisplayPageAction() {
        final NetflixActivity netflixActivity = this.netflixActivity;
        VideoType videoType;
        if (this.action.getVideoType().equals(VideoType.MOVIE)) {
            videoType = VideoType.MOVIE;
        }
        else {
            videoType = VideoType.SHOW;
        }
        DetailsActivityLauncher.show(netflixActivity, videoType, String.valueOf(this.action.getVideoId()), this.action.getAncestorTitle(), this.getPlayContext(), null, null, "PostPlay");
    }
    
    protected void playerPlayAction(final boolean b) {
        final boolean b2 = true;
        if (this.action.getType().equals(PostPlayAction$CallToActionType.play) && this.playerFragment != null && this.action.getPlayBackVideo() != null && this.action.getPlayBackVideo().getPlayable() != null) {
            if (!this.playerFragment.isPostPlayed()) {
                this.playerFragment.setPostPlayed(true);
                this.reportNextPlay();
                if (this.action.getSeamlessStart() > 0) {
                    this.playerFragment.playNextVideo(this.action.getPlayBackVideo().getPlayable(), this.getPlayContext(), b, this.action.getSeamlessStart(), true);
                }
                else {
                    this.playerFragment.playNextVideo(this.action.getPlayBackVideo().getPlayable(), this.getPlayContext(), !this.action.isDoNotIncrementInterrupter() && b && b2);
                }
                this.finishActivityIfNeeded();
                return;
            }
            Log.d(this.TAG, "Play action already consumed, ignoring");
        }
    }
}
