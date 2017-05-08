// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IPlayer;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.DeviceCategory;
import android.widget.TextView;
import com.netflix.model.leafs.PostPlayExperience;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.InteractivePostplay;
import android.widget.LinearLayout;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.util.TimeUtils$CountdownTimer;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.iko.model.InteractivePostplayModel;
import java.util.Iterator;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayFactory;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.PostPlayAction;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PostPlay$FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
{
    final /* synthetic */ PostPlay this$0;
    
    public PostPlay$FetchPostPlayForPlaybackCallback(final PostPlay this$0) {
        this.this$0 = this$0;
        super("nf_postplay");
        this$0.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.started;
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        final boolean b = false;
        super.onPostPlayVideosFetched(postPlayVideosProvider, status);
        if (Log.isLoggable()) {
            Log.v("nf_postplay", "postPlayVideosProvider: " + postPlayVideosProvider);
        }
        this.this$0.mPostPlayDataFetchStatus = PostPlay$PostPlayDataFetchStatus.postponed;
        if (!this.this$0.mNetflixActivity.isFinishing()) {
            synchronized (this.this$0) {
                if (this.this$0.mFetchPostPlayForPlaybackCallback != this) {
                    Log.w("nf_postplay", "Not current callback");
                    return;
                }
            }
            // monitorexit(postPlay)
            final PostPlayVideosProvider postPlayVideosProvider2;
            if (!status.isError() && postPlayVideosProvider2 != null) {
                if (Log.isLoggable() && postPlayVideosProvider2 != null) {
                    Log.d("nf_postplay", "Postplay data retrieved " + postPlayVideosProvider2);
                }
                this.this$0.mPostPlayExperience = postPlayVideosProvider2.getPostPlayExperienceData();
                this.this$0.mInteractivePostPlay = postPlayVideosProvider2.getInteractivePostplay();
                this.this$0.mPostPlayDataExist = (this.this$0.mPostPlayExperience != null);
                if (this.this$0.mPostPlayDataExist) {
                    if (this.this$0.mPostPlayExperience.getAutoplay() && this.this$0.mPostPlayExperience.getAutoplaySeconds() > 0) {
                        final PostPlayItem postPlayItem = this.this$0.mPostPlayExperience.getItems().get(this.this$0.mPostPlayExperience.getItemsInitialIndex());
                        if (postPlayItem != null) {
                            postPlayItem.setAutoPlay(true);
                            postPlayItem.setAutoPlaySeconds(this.this$0.mPostPlayExperience.getAutoplaySeconds());
                            postPlayItem.setNextEpisodeAutoPlay(this.this$0.mPostPlayExperience.getType().equals("nextEpisode"));
                        }
                        else {
                            Log.e("nf_postplay", "Could not find autoplay item");
                        }
                    }
                    int i = 0;
                    int n = 0;
                    while (i < this.this$0.mPostPlayExperience.getItems().size()) {
                        final PostPlayItem postPlayItem2 = this.this$0.mPostPlayExperience.getItems().get(i);
                        postPlayItem2.setExperienceType(this.this$0.mPostPlayExperience.getType());
                        for (final PostPlayAction postPlayAction : postPlayItem2.getActions()) {
                            postPlayAction.setItemIndex(i);
                            postPlayAction.setRequestId(this.this$0.mPostPlayExperience.getRequestId());
                            postPlayAction.setAncestorTitle(postPlayItem2.getAncestorTitle());
                            if (postPlayAction.getVideoType() == null) {
                                postPlayAction.setVideoType(postPlayItem2.getType());
                            }
                        }
                        if (this.this$0.hasValidPlayAction(postPlayItem2)) {
                            ++n;
                        }
                        ++i;
                    }
                    if (n == 0) {
                        Log.e("nf_postplay", "No playable items in post play response");
                        this.this$0.mPostPlayDataExist = false;
                    }
                }
                if (this.this$0.mInteractivePostPlay != null) {
                    final InteractivePostplayModel data = this.this$0.mInteractivePostPlay.getData();
                    if (data == null || StringUtils.isEmpty(data.getType())) {
                        if (Log.isLoggable()) {
                            Log.d("nf_postplay", "Interactive post play data is empty.");
                        }
                    }
                    else {
                        final String type = data.getType();
                        this.this$0.interactivePostPlayManager = InteractivePostPlayFactory.getManager(type, this.this$0, data);
                        if (this.this$0.interactivePostPlayManager == null) {
                            Log.e("nf_postplay", "Interactive post play manager is null. Unknown interactive post play type from endpoint - " + type);
                        }
                        else {
                            this.this$0.interactivePostPlayManager.startPreCachingResources();
                            this.this$0.isInteractivePostPlay = true;
                            if (this.this$0.mPlayerFragment != null) {
                                final Asset currentAsset = this.this$0.mPlayerFragment.getCurrentAsset();
                                boolean b2 = b;
                                if (currentAsset != null) {
                                    b2 = b;
                                    if (data.getInterrupterCount() >= currentAsset.getPostPlayVideoPlayed()) {
                                        b2 = true;
                                    }
                                }
                                if (b2 && this.this$0.mPlayerFragment.getHandler() != null) {
                                    this.this$0.mPlayerFragment.getHandler().removeCallbacks(this.this$0.onInterrupterStart);
                                    Log.d("nf_postplay", "Cancelling interrupter for interactive content until 8 post plays");
                                }
                            }
                        }
                    }
                }
                else if (Log.isLoggable()) {
                    Log.d("nf_postplay", "Interactive post play data is null.");
                }
                this.this$0.updateOnPostPlayVideosFetched();
                return;
            }
            Log.w("nf_postplay", "Error loading post play data");
            this.this$0.mPostPlayDataExist = false;
            if (NetflixApplication.isDebugToastEnabled()) {
                Toast.makeText((Context)this.this$0.mNetflixActivity, (CharSequence)"[DEBUG] loading post play data failed", 1).show();
            }
        }
    }
}
