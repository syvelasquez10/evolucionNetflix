// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

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
        if (this.this$0.mNetflixActivity.isFinishing()) {
            return;
        }
        if (status.isError() || postPlayVideosProvider == null) {
            Log.w("nf_postplay", "Error loading post play data");
            this.this$0.mPostPlayDataExist = false;
            return;
        }
        if (Log.isLoggable() && postPlayVideosProvider != null) {
            Log.d("nf_postplay", "Postplay data retrieved " + postPlayVideosProvider);
        }
        this.this$0.mPostPlayVideos = postPlayVideosProvider.getPostPlayVideos();
        final PostPlay this$0 = this.this$0;
        boolean mPostPlayDataExist = b;
        if (this.this$0.mPostPlayVideos != null) {
            mPostPlayDataExist = b;
            if (this.this$0.mPostPlayVideos.size() > 0) {
                mPostPlayDataExist = true;
            }
        }
        this$0.mPostPlayDataExist = mPostPlayDataExist;
        this.this$0.mPostPlayContexts = postPlayVideosProvider.getPostPlayContexts();
        this.this$0.updateOnPostPlayVideosFetched();
    }
}
