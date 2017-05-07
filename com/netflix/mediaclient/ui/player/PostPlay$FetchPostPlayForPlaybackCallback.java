// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PostPlay$FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
{
    final /* synthetic */ PostPlay this$0;
    
    public PostPlay$FetchPostPlayForPlaybackCallback(final PostPlay this$0) {
        this.this$0 = this$0;
        super("nf_postplay");
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> mPostPlayVideos, final Status status) {
        final boolean b = false;
        super.onPostPlayVideosFetched(mPostPlayVideos, status);
        if (this.this$0.mContext.destroyed()) {
            return;
        }
        if (status.isError() || mPostPlayVideos == null) {
            Log.w("nf_postplay", "Error loading post play data");
            this.this$0.mPostPlayDataExist = false;
            return;
        }
        if (Log.isLoggable("nf_postplay", 3) && mPostPlayVideos != null) {
            Log.d("nf_postplay", "Postplay data retrieved " + mPostPlayVideos.size());
        }
        this.this$0.mPostPlayVideos = mPostPlayVideos;
        final PostPlay this$0 = this.this$0;
        boolean mPostPlayDataExist = b;
        if (mPostPlayVideos != null) {
            mPostPlayDataExist = b;
            if (mPostPlayVideos.size() > 0) {
                mPostPlayDataExist = true;
            }
        }
        this$0.mPostPlayDataExist = mPostPlayDataExist;
        this.this$0.updateOnPostPlayVideosFetched(mPostPlayVideos);
    }
}
