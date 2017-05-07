// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

public class KubrickHighDensityCwViewGroup extends VideoViewGroup<CWVideo, KubrickHighDensityCwView>
{
    public KubrickHighDensityCwViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected KubrickHighDensityCwView createChildView(final Context context) {
        return new KubrickHighDensityCwView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final KubrickHighDensityCwView kubrickHighDensityCwView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, (n + n2) * 2);
        if (Log.isLoggable()) {
            Log.v("VideoViewGroup", "Setting base view id to: " + computeViewId);
        }
        kubrickHighDensityCwView.setId(computeViewId);
        kubrickHighDensityCwView.setInfoViewId(computeViewId + 1);
    }
}
