// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.model.leafs.PostPlayAction;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Iterator;
import android.animation.TimeInterpolator;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import android.widget.TextView;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlayForPlayer$SelectListener implements View$OnClickListener
{
    PostPlayBackground background;
    int index;
    final /* synthetic */ PostPlayForPlayer this$0;
    
    public PostPlayForPlayer$SelectListener(final PostPlayForPlayer this$0, final int index, final PostPlayBackground background) {
        this.this$0 = this$0;
        this.index = index;
        this.background = background;
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "BoxArt touched: " + this.index);
        }
        if (this.this$0.mVideoFullScreen.getAndSet(false)) {
            Log.d("nf_postplay", "Video was full size, scale down");
            this.this$0.executeTransitionIn();
        }
        this.this$0.scrollToItem(this.index);
    }
}
