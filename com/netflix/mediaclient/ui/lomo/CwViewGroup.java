// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import com.netflix.mediaclient.util.CWTestUtil;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;

public class CwViewGroup extends VideoViewGroup<CWVideo, CwView>
{
    public CwViewGroup(final Context context) {
        super(context, true);
    }
    
    @Override
    protected CwView createChildView(final Context context) {
        if (CWTestUtil.isInTest(context)) {
            return new CwTestView(context);
        }
        return new CwView(context);
    }
    
    @Override
    protected boolean shouldApplyPaddingToChildren() {
        return true;
    }
    
    @Override
    protected void updateViewIds(final CwView cwView, int computeViewId, final int n, final int n2) {
        int n3;
        if (CWTestUtil.isInTest(this.getContext())) {
            n3 = 1;
        }
        else {
            n3 = 2;
        }
        computeViewId = LoLoMoFocusHandler.computeViewId(computeViewId, n3 * (n + n2));
        if (Log.isLoggable()) {
            Log.v("VideoViewGroup", "Setting base view id to: " + computeViewId);
        }
        cwView.setId(computeViewId);
        cwView.setInfoViewId(computeViewId + 1);
    }
}
