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
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import java.util.List;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class KidsSeasonSelector$1 implements View$OnTouchListener
{
    final /* synthetic */ KidsSeasonSelector this$0;
    
    KidsSeasonSelector$1(final KidsSeasonSelector this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Log.v("KidsSeasonSelector", "onTouch, " + motionEvent);
            this.this$0.getCenteredChildView().performClick();
        }
        return false;
    }
}
