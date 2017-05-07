// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import com.netflix.mediaclient.android.widget.SocialVideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.android.widget.VideoView;
import com.netflix.mediaclient.servicemgr.model.Video;

public class LoMoViewGroup extends VideoViewGroup<Video, VideoView>
{
    public LoMoViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected VideoView createChildView(final Context context) {
        return new SocialVideoView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final VideoView videoView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, n + n2);
        if (Log.isLoggable("VideoViewGroup", 2)) {
            Log.v("VideoViewGroup", "Setting view id to: " + computeViewId);
        }
        videoView.setId(computeViewId);
    }
}
