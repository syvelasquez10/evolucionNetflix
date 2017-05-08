// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

public class KubrickLoMoViewGroup extends VideoViewGroup<KubrickVideo, KubrickVideoView>
{
    public KubrickLoMoViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected KubrickVideoView createChildView(final Context context) {
        return new KubrickVideoView(context);
    }
    
    @Override
    public void init(final int n) {
        super.init(n);
        if (BrowseExperience.showKidsExperience()) {
            ViewUtils.setPaddingBottom((View)this, this.getResources().getDimensionPixelSize(2131362200));
        }
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final KubrickVideoView kubrickVideoView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, n + n2);
        if (Log.isLoggable()) {
            Log.v("VideoViewGroup", "Setting view id to: " + computeViewId);
        }
        kubrickVideoView.setId(computeViewId);
    }
}
