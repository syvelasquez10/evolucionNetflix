// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import android.widget.LinearLayout$LayoutParams;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.ArrayList;
import android.view.View$OnTouchListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import android.view.View;
import java.util.List;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

class KidsSeasonSelector$2 implements Runnable
{
    final /* synthetic */ KidsSeasonSelector this$0;
    final /* synthetic */ boolean val$useSmoothScroll;
    final /* synthetic */ int val$x;
    
    KidsSeasonSelector$2(final KidsSeasonSelector this$0, final boolean val$useSmoothScroll, final int val$x) {
        this.this$0 = this$0;
        this.val$useSmoothScroll = val$useSmoothScroll;
        this.val$x = val$x;
    }
    
    @Override
    public void run() {
        if (this.val$useSmoothScroll) {
            this.this$0.scroller.smoothScrollTo(this.val$x, 0);
            return;
        }
        this.this$0.scroller.scrollTo(this.val$x, 0);
    }
}
