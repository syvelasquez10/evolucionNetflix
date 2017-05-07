// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;

public class KubrickCwViewGroup extends VideoViewGroup<CWVideo, KubrickCwView>
{
    public KubrickCwViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected KubrickCwView createChildView(final Context context) {
        return new KubrickCwView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final KubrickCwView kubrickCwView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, (n + n2) * 2);
        if (Log.isLoggable("VideoViewGroup", 2)) {
            Log.v("VideoViewGroup", "Setting base view id to: " + computeViewId);
        }
        kubrickCwView.setId(computeViewId);
        kubrickCwView.setInfoViewId(computeViewId + 1);
    }
}
