// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

public class KubrickHeroViewGroup extends VideoViewGroup<KubrickVideo, KubrickHeroView>
{
    public KubrickHeroViewGroup(final Context context) {
        super(context, false);
    }
    
    @Override
    protected KubrickHeroView createChildView(final Context context) {
        return new KubrickHeroView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return false;
    }
    
    @Override
    protected void updateViewIds(final KubrickHeroView kubrickHeroView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, n + n2);
        if (Log.isLoggable("VideoViewGroup", 2)) {
            Log.v("VideoViewGroup", "Setting view id to: " + computeViewId);
        }
        kubrickHeroView.setId(computeViewId);
    }
}
