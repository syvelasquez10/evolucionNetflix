// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;

public class BillboardViewGroup extends VideoViewGroup<BillboardDetails, BillboardView>
{
    public BillboardViewGroup(final Context context) {
        super(context);
        this.setId(2131230760);
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
    protected boolean shouldOverlapPages() {
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
