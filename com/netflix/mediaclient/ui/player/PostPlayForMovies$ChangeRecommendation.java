// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.model.Ratable;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import java.util.List;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class PostPlayForMovies$ChangeRecommendation implements View$OnTouchListener
{
    private final int selection;
    final /* synthetic */ PostPlayForMovies this$0;
    
    private PostPlayForMovies$ChangeRecommendation(final PostPlayForMovies this$0, final int selection) {
        this.this$0 = this$0;
        this.selection = selection;
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (Log.isLoggable("nf_postplay", 3)) {
            Log.d("nf_postplay", "BoxArt touched: " + this.selection);
        }
        if (this.this$0.mVideoFullScreen.getAndSet(false)) {
            Log.d("nf_postplay", "Video was full size, scale down");
            this.this$0.executeTransitionIn();
        }
        if (this.this$0.mPostPlayVideos.size() > this.selection) {
            this.this$0.mSelected = this.selection;
            this.this$0.updateUi(this.this$0.mPostPlayVideos.get(this.selection), this.selection);
            return true;
        }
        Log.e("nf_postplay", "PostPlay not found for index " + this.selection);
        return false;
    }
}
