// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.Billboard;

public class BillboardViewGroup extends VideoViewGroup<Billboard, BillboardView>
{
    public BillboardViewGroup(final Context context) {
        super(context, false);
        this.setId(2131165249);
    }
    
    @Override
    protected BillboardView createChildView(final Context context) {
        return new BillboardView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return false;
    }
    
    @Override
    protected void updateViewIds(final BillboardView billboardView, int computeViewId, final int n, final int n2) {
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, n + n2);
        if (Log.isLoggable("VideoViewGroup", 2)) {
            Log.v("VideoViewGroup", "Setting view id to: " + computeViewId);
        }
        billboardView.setId(computeViewId);
    }
}
