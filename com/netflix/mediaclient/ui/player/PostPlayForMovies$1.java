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
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import java.util.List;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlayForMovies$1 implements View$OnClickListener
{
    final /* synthetic */ PostPlayForMovies this$0;
    final /* synthetic */ int val$i;
    
    PostPlayForMovies$1(final PostPlayForMovies this$0, final int val$i) {
        this.this$0 = this$0;
        this.val$i = val$i;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mSelected != this.val$i) {
            Log.e("nf_postplay", "This should NOT happen. This movie was NOT selected before");
        }
        this.this$0.handlePlayNow(false);
    }
}
