// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PostPlayFrag$5 extends BroadcastReceiver
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$5(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0.getNetflixActivity()) || intent == null || this.this$0.video == null) {
            return;
        }
        final String action = intent.getAction();
        switch (action) {
            default: {}
            case "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_END": {
                if (this.this$0.video.getType() == VideoType.MOVIE) {
                    this.this$0.getNetflixActivity().getServiceManager().getBrowse().fetchMovieDetails(this.this$0.video.getId(), null, new PostPlayFrag$FetchPostPlayForPlaybackCallback(this.this$0, "PostPlayFrag"));
                    return;
                }
                this.this$0.getNetflixActivity().getServiceManager().getBrowse().fetchPostPlayVideos(this.this$0.video.getPlayable().getParentId(), this.this$0.video.getType(), new PostPlayFrag$FetchPostPlayForPlaybackCallback(this.this$0, "PostPlayFrag"));
            }
            case "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_NEXT": {
                this.this$0.getNetflixActivity().getServiceManager().getBrowse().fetchEpisodeDetails(intent.getStringExtra("id"), null, new PostPlayFrag$FetchPostPlayForPlaybackCallback(this.this$0, "PostPlayFrag"));
            }
            case "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_HIDE": {
                this.this$0.hidePostPlayViews();
            }
        }
    }
}
